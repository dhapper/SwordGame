package battle.graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import battle.BattleManager;
import utilz.Constants;

public class DrawBattle extends JPanel{
    
	int battleWidth = Constants.FRAME_WIDTH * 3 / 4;
	int battleHeight = Constants.FRAME_HEIGHT;
	int logWidth = Constants.FRAME_WIDTH / 4;
	
	JFrame frame;
	JButton swingButton, jabButton, blockButton, chargeButton, swapSwordsButton, forfeitButton;
	
	BattleManager battleManager;
	BattleMouseHandler mouse;
	
	DrawButtons drawButtons;
	DrawBars drawBars;
	DrawBattleCharacter drawBattleCharacter;
	DrawLog drawLog;
	DrawText drawText;
	DrawPlayerInfo drawPlayerInfo;
	DrawSwordInfo drawSwordInfo;
	
	AnimationSequenceManager animationSequenceManager;
	
	String nextMove;
	boolean moveChosen = false;
	
	int healthBarW = battleWidth/3; 
	int healthBarH = battleHeight/20; 
	int healthBarX_A = battleWidth/2 - healthBarW;
	int healthBarX_B = healthBarX_A + healthBarW;
	int healthBarY = battleHeight/100;
	int healthBarRed = 0;
	int healthBarGreen = 150;
	int healthBarBlue = 0;
	
	int stamBarW = healthBarW*3/4;
	int stamBarH = healthBarH/2;
	int stamBarX_A = this.battleWidth/2 - stamBarW;
	int stamBarX_B = stamBarX_A + stamBarW;
	int stamBarY = healthBarY + healthBarH;
	int stamBarRed = 100;
	int stamBarGreen = 100;
	int stamBarBlue = 200;
	
	Color GreenHealthBar = new Color(0, 150, 0);
	Color BlueStaminaBar = new Color(100, 100, 200);
	Color OrangeBar = new Color(220, 140, 80);
	Color PinkBar = new Color(255, 80, 180);
	
	int shieldIconOffset = battleWidth/100;
	int shieldIconW = battleWidth/20;
	int shieldIconH = shieldIconW;
	int shieldIconX_A = shieldIconOffset;
	int shieldIconX_B = battleWidth - shieldIconW - shieldIconOffset;
	int shieldIconY = shieldIconOffset;
	int chargeIconY = 2*shieldIconY + shieldIconH;
	
	int iconBlockStringY = shieldIconY + shieldIconH*4/6;
	int iconChargeStringY = iconBlockStringY + shieldIconH*7/6;
	
	int buttonWidth = battleWidth/6;
	int buttonHeight = buttonWidth/2;
	int buttonX1 = battleWidth/2 - buttonWidth;
	int buttonX2 = battleWidth/2;
	int buttonY1 = battleHeight*17/24;
	int buttonY2 = buttonY1 + buttonHeight;
	int altButtonWidth = buttonWidth/2;
	
	int playerW = battleWidth/3;
	int playerH = playerW;
	int playerX_A = 0;
	int playerX_B = battleWidth - this.playerX_A;
	int playerY = battleHeight/5 + this.battleHeight/50;
	int blockPoseDecreaseX = battleWidth/40;
	
	int shieldY = playerY + playerH/3 + playerH/8;
	int shieldW = playerW/3;
	int shieldH = playerH/3;
	int shieldX_A = playerX_A + playerW/4 - playerW/12;
	int shieldX_B = battleWidth - shieldX_A;
	
	int sword_AX = playerX_A + playerW + playerW/6 - playerW/45;
	int sword_AY = playerY - playerH/3 + playerH/30;
	int sword_AW = playerW/3;
	int sword_AH = playerH;
	int sword_BX = battleWidth - sword_AX;
	int swordAngle = 40;
	
	int frameNum;
	int bufferFrames = 5;
	boolean animating = false;
	boolean animatingFirstMove = false;
	boolean animatingSecondMove = false;
	
	boolean hoveringSwapSwordsButton = false;
	
    public DrawBattle(JFrame frame, BattleManager battleManager) {

    	this.battleManager = battleManager;
    	
    	this.frame = frame;
		this.frame.getContentPane().removeAll();
		this.frame.revalidate();
		this.frame.add(this);
		this.frame.setVisible(true);
		this.frame.repaint();
		setLayout(null);
    	
    	this.mouse = new BattleMouseHandler(this);
    	addMouseListener(this.mouse);
    	addMouseMotionListener(this.mouse);
    	
    	this.swingButton = new JButton();
    	this.jabButton = new JButton();
    	this.blockButton = new JButton();
    	this.chargeButton = new JButton();
    	this.swapSwordsButton = new JButton();
    	this.forfeitButton = new JButton();
    	
    	this.drawButtons = new DrawButtons(this);
    	this.drawBars = new DrawBars(this);
    	this.drawBattleCharacter = new DrawBattleCharacter(this);
    	this.animationSequenceManager = new AnimationSequenceManager(this);
    	this.drawLog = new DrawLog(this);
    	this.drawText = new DrawText(this);
    	this.drawPlayerInfo = new DrawPlayerInfo();
    	this.drawSwordInfo = new DrawSwordInfo();
    }
    
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
    	
		Graphics2D g2d = (Graphics2D) g;
		
    	Font labelFont = new Font("Ariel", Font.BOLD, 20);
    	g.setFont(labelFont);
		
    	try {
			Image img = ImageIO.read(new File("res/background/background - mountain base.png"));
			g.drawImage(img, 0, 0, this.battleWidth, this.battleHeight, getFocusCycleRootAncestor());
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	DrawIcons drawIcons = new DrawIcons(this);
    	drawIcons.paint(g);
    	
    	if(!animating) {
	    
    		if(drawLog != null)
	    		drawLog.paint(drawLog.prevTurnMessages, g);
    		
    		if(drawBars != null)
    			drawBars.paint(g);
    		
    		if(drawText != null)
    			drawText.paint(g);
	    	
	    	if(this.drawBattleCharacter != null) {
	    		drawBattleCharacter.drawStaticDefaultPose(battleManager.getPbsA(), g);
	    		drawBattleCharacter.drawStaticDefaultPose(battleManager.getPbsB(), g);
	    	}
	    	
	    	if(battleManager.getInvalidMoveString() != null) {
		    	if(!battleManager.getInvalidMoveString().equals("NONE")) {
		    		FontMetrics metrics = g.getFontMetrics(g.getFont());
		    		int textBgIndent = battleWidth/60;
		    		int textWidth = metrics.stringWidth(battleManager.getInvalidMoveString());
		    		int startX = this.battleWidth/2 - (textWidth / 2);
		    		g.setColor(new Color(0, 0, 0, 150));
		    		g.fillRect(startX-textBgIndent, this.battleHeight/2, textWidth + 2*textBgIndent, this.battleHeight/15);
		    		g.setColor(Color.RED);
		    		g.drawString(battleManager.getInvalidMoveString(), startX, battleHeight/2 + battleHeight/25 + battleHeight/200);
		    		g.setColor(Color.BLACK);
		    	}
	    	}
	    	
	    	if(this.mouse != null) {
	    		if(!battleManager.isBattleOver()) {
	    			if(this.mouse.getPlayerHovered().equals("A")) {
	    				drawPlayerInfo.paint(this, battleManager.getPbsA(), battleWidth/10, g);
	            	}else if(this.mouse.getPlayerHovered().equals("B")) {
	            		drawPlayerInfo.paint(this, battleManager.getPbsB(), battleWidth*3/4-battleWidth/10, g);
	            	}else if(this.hoveringSwapSwordsButton) {
	            		if(battleManager.isTurnA())
	                		drawSwordInfo.paint(this, battleManager.getPbsA(), g);
	                	else
	                		drawSwordInfo.paint(this, battleManager.getPbsB(), g);
	            	}
	    		}
	    	}
	    	
	    	if(battleManager.isBattleOver()) {
	    		swingButton.setVisible(false);
	        	jabButton.setVisible(false);
	        	blockButton.setVisible(false);
	        	chargeButton.setVisible(false);
	        	swapSwordsButton.setVisible(false);
	        	forfeitButton.setVisible(false);
	        	
	        	g.setColor(new Color(0, 0, 0, 150));
	    		g.fillRect(0, 0, this.battleWidth, this.battleHeight);
	    		
	    		// game over (tie not implemented)
	    		try {
	    			
					if(battleManager.getDeadPlayer().equals("A")) {
						Image label = ImageIO.read(new File("res/labels/label - you lose.png"));
		    			g.drawImage(label, this.battleWidth/6, this.battleHeight/6, this.battleWidth*2/3, this.battleHeight*2/3, getFocusCycleRootAncestor());
		    		}else if(battleManager.getDeadPlayer().equals("B")) {
		    			Image label = ImageIO.read(new File("res/labels/label - you win.png"));
		    			g.drawImage(label, this.battleWidth/6, this.battleHeight/6, this.battleWidth*2/3, this.battleHeight*2/3, getFocusCycleRootAncestor());
		    		}
					
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
	
	    		// update stuff
	    
	    		// return to menu
	    		
	    	}
	    	
    	}else if(animating) {
    		if(drawLog != null)
	    		drawLog.paint(battleManager.getTurnMessages(), g);
    		
    		if(animatingFirstMove)
    			animationSequenceManager.getFirstMove(g);
    		else if(animatingSecondMove)
    			animationSequenceManager.getSecondMove(g);
    		
    	}
    }

	public void setNextMove(String nextMove) {
		this.nextMove = nextMove;
	}

	public String getNextMove() {
		return this.nextMove;
	}

	public boolean isMoveChosen() {
		return moveChosen;
	}

	public void setMoveChosen(boolean moveChosen) {
		this.moveChosen = moveChosen;
	}
	
}
