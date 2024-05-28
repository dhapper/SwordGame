package battle;
import javax.imageio.ImageIO; 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;  
import javax.swing.JPanel;

import main.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class DrawBattle extends JPanel{
    
	int width, height;
	BattleManager battleManager;
	MouseHandler mouse;
	String nextMove;
	boolean moveChosen = false;
	int buttonWidth, buttonHeight, buttonX1, buttonX2, buttonY1, buttonY2, altButtonWidth;
	boolean buttonVisible;
	JButton swingButton, jabButton, blockButton, chargeButton, swapSwordsButton, forfeitButton;
	JFrame frame;
	boolean hoveringSwapSwordsButton = false;
	DrawButtons drawButtons;
	
	
	int healthBarW = this.width/3; 
	int healthBarH = this.height/20; 
	int healthBarX_A = this.width/2 - healthBarW;
	int healthBarX_B = healthBarX_A + healthBarW;
	int healthBarY = this.height/100;
	int healthBarRed = 0;
	int healthBarGreen = 150;
	int healthBarBlue = 0;
	
	int stamBarW = healthBarW*3/4;
	int stamBarH = healthBarH/2;
	int stamBarX_A = this.width/2 - stamBarW;
	int stamBarX_B = stamBarX_A + stamBarW;
	int stamBarY = healthBarY + healthBarH;
	int stamBarRed = 100;
	int stamBarGreen = 100;
	int stamBarBlue = 200;
	
	int shieldIconOffset = this.width/100;
	int shieldIconW = this.width/20;
	int shieldIconH = shieldIconW;
	int shieldIconX_A = shieldIconOffset;
	int shieldIconX_B = this.width - shieldIconW - shieldIconOffset;
	int shieldIconY = shieldIconOffset;
	int chargeIconY = 2*shieldIconY + shieldIconH;
	
	int iconBlockStringY = shieldIconY + shieldIconH*4/6;
	int iconChargeStringY = iconBlockStringY + shieldIconH*7/6;
	
	int playerW = this.width/3;
	int playerH = this.playerW;
	int playerX_A = 0;
	int playerX_B = this.width - this.playerX_A;
	int playerY = this.height/5 + this.height/50;
	
	int shieldY = playerY + playerH/3 + playerH/8;
	int shieldW = playerW/3;
	int shieldH = playerH/3;
	int shieldX_A = playerX_A + playerW/4 - playerW/12;
	int shieldX_B = width - shieldX_A;
	
	int sword_AX = playerX_A + playerW + playerW/6 - playerW/45;
	int sword_AY = playerY - playerH/3 + playerH/30;
	int sword_AW = playerW/3;
	int sword_AH = playerH;
	int sword_BX = width - sword_AX;
	int swordAngle = 40;
	
	int blockPoseDecreaseX = this.width/40;
	
	boolean frame1, frame2, frame3;
	int frameNum;
	
	DrawBars drawBars;
	
	DrawSwing drawSwing;
	DrawJab drawJab;
	
	boolean animating = false;
	DrawAnimation drawAnimation;
	
	AnimationSequenceManager animationSequenceManager;
	
	boolean animatingFirstMove = false;
	boolean animatingSecondMove = false;
	
	DrawCharacter drawCharacter;
	
    public DrawBattle(JFrame frame, BattleManager battleManager) {
    	
    	this.battleManager = battleManager;
    	this.width = frame.getWidth()-16;
    	this.height = frame.getHeight()-39;
    	
    	this.frame = frame;
		this.frame.getContentPane().removeAll();
		this.frame.revalidate();
		this.frame.add(this);
		this.frame.setVisible(true);
		this.frame.repaint();
		setLayout(null);
    	
    	this.mouse = new MouseHandler(this);
    	addMouseListener(this.mouse);
    	addMouseMotionListener(this.mouse);
    	
    	this.buttonWidth = this.width/6;
    	this.buttonHeight = buttonWidth/2;
    	this.buttonX1 = this.width/2 - buttonWidth;
    	this.buttonX2 = this.width/2;
    	this.buttonY1 = this.height*17/24;
    	this.buttonY2 = buttonY1 + buttonHeight;
    	this.altButtonWidth = buttonWidth/2;
    	
    	this.swingButton = new JButton();
    	this.jabButton = new JButton();
    	this.blockButton = new JButton();
    	this.chargeButton = new JButton();
    	this.swapSwordsButton = new JButton();
    	this.forfeitButton = new JButton();
    	
    	this.drawButtons = new DrawButtons(this);
    	this.drawAnimation = new DrawAnimation(this);
    	this.drawBars = new DrawBars(this);
    	//this.drawSwing = new DrawSwing(this);
    	//this.drawJab = new DrawJab(this);
    	this.drawCharacter = new DrawCharacter(this);
    	this.animationSequenceManager = new AnimationSequenceManager(this);
    	
    	this.healthBarW = this.width/3; 
    	this.healthBarH = this.height/20; 
    	this.healthBarX_A = this.width/2 - healthBarW;
    	this.healthBarX_B = healthBarX_A + healthBarW;
    	this.healthBarY = this.height/100;
    	this.healthBarRed = 0;
    	this.healthBarGreen = 150;
    	this.healthBarBlue = 0;
    	
    	this.stamBarW = healthBarW*3/4;
    	this.stamBarH = healthBarH/2;
    	this.stamBarX_A = this.width/2 - stamBarW;
    	this.stamBarX_B = stamBarX_A + stamBarW;
    	this.stamBarY = healthBarY + healthBarH;
    	this.stamBarRed = 100;
    	this.stamBarGreen = 100;
    	this.stamBarBlue = 200;
    	
    	this.shieldIconOffset = this.width/100;
    	this.shieldIconW = this.width/20;
    	this.shieldIconH = shieldIconW;
    	this.shieldIconX_A = shieldIconOffset;
    	this.shieldIconX_B = this.width - shieldIconW - shieldIconOffset;
    	this.shieldIconY = shieldIconOffset;
    	this.chargeIconY = 2*shieldIconY + shieldIconH;
    	
    	this.iconBlockStringY = shieldIconY + shieldIconH*4/6;
    	this.iconChargeStringY = iconBlockStringY + shieldIconH*7/6;
    	
    	this.playerW = this.width/3;
    	this.playerH = this.playerW;
    	this.playerX_A = 0;
    	this.playerX_B = this.width - this.playerX_A;
    	this.playerY = this.height/5 + this.height/50;
    	
    	this.shieldY = playerY + playerH/3 + playerH/8;
    	this.shieldW = playerW/3;
    	this.shieldH = playerH/3;
    	this.shieldX_A = playerX_A + playerW/4 - playerW/12;
    	this.shieldX_B = width - shieldX_A;
    	
    	this.sword_AX = playerX_A + playerW + playerW/6 - playerW/45;
    	this.sword_AY = playerY - playerH/3 + playerH/30;
    	this.sword_AW = playerW/3;
    	this.sword_AH = playerH;
    	this.sword_BX = width - sword_AX;
    	this.swordAngle = 40;
    	
    	this.blockPoseDecreaseX = this.width/40;

    }
    
    public void paintComponent(Graphics g){
    	
    	super.paintComponent(g);
    	
		Graphics2D g2d = (Graphics2D) g;
		
    	Font labelFont = new Font("Ariel", Font.BOLD, 20);
    	g.setFont(labelFont);
		
    	// set bg
    	try {
			Image img = ImageIO.read(new File("res/background - mountain base.png"));
			g.drawImage(img, 0, 0, this.width, this.height, getFocusCycleRootAncestor());
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	DrawIcons drawIcons = new DrawIcons(this);
    	drawIcons.paint(g);
    	
    	DrawBars drawBars = new DrawBars(this);
    	drawBars.paint(g);
    	
    	if(!animating) {
	    	
	    	DrawText drawText = new DrawText(this);
	    	drawText.paint(g);
	    	
	    	if(this.drawCharacter != null) {
	    		drawCharacter.drawStaticDefaultPose(battleManager.getPbsA(), g);
	    		drawCharacter.drawStaticDefaultPose(battleManager.getPbsB(), g);
	    		//drawCharacter.drawStaticBlockingPose(battleManager.getPbsA(), x, g);
	    		//drawCharacter.drawStaticBlockingPose(battleManager.getPbsB(), x, g);
	    		
	    		//drawCharacter.drawStaticChargingPose(battleManager.getPbsA(), 0, g);
	    	}
	    	
	    	/*try {
	    		
	    		// player A
	    		Image playerModelA = ImageIO.read(new File("res/player - default.png"));
	    		AffineTransform transformPlayerModelA = new AffineTransform();
	    		transformPlayerModelA.translate(this.playerX_A, this.playerY);
	    		transformPlayerModelA.scale(this.playerW/playerModelA.getWidth(getFocusCycleRootAncestor()), this.playerH/playerModelA.getHeight(getFocusCycleRootAncestor()));
	    		g2d.drawImage(playerModelA, transformPlayerModelA, null);
	    		
	    		Image armourModelA = ImageIO.read(new File("res/armour - "+battleManager.getPbsA().getPlayer().getInventory().getActiveArmour().getName()+".png"));
	    		AffineTransform transformArmourModelA = new AffineTransform();
	    		transformArmourModelA.translate(this.playerX_A, this.playerY);
	    		transformArmourModelA.scale(this.playerW/playerModelA.getWidth(getFocusCycleRootAncestor()), this.playerH/playerModelA.getHeight(getFocusCycleRootAncestor()));
	    		g2d.drawImage(armourModelA, transformArmourModelA, null);
	    		
	    		Image shieldModelA = ImageIO.read(new File("res/shield - "+battleManager.getPbsA().getPlayer().getInventory().getActiveShield().getName()+".png"));
	    		AffineTransform transformShieldModelA = new AffineTransform();
	    		transformShieldModelA.translate(shieldX_A, shieldY);
	    		transformShieldModelA.scale(shieldW/shieldModelA.getWidth(getFocusCycleRootAncestor()), shieldH/shieldModelA.getHeight(getFocusCycleRootAncestor()));
	    		g2d.drawImage(shieldModelA, transformShieldModelA, null);
	    		
	    		Image swordModelA = ImageIO.read(new File("res/sword - "+battleManager.getPbsA().getActiveSword().getName()+".png"));
	    		AffineTransform transformSwordModelA = new AffineTransform();
	    		transformSwordModelA.translate(sword_AX, sword_AY);
	    		transformSwordModelA.scale(sword_AW/swordModelA.getWidth(getFocusCycleRootAncestor()), sword_AH/swordModelA.getHeight(getFocusCycleRootAncestor()));
	    		transformSwordModelA.rotate(Math.toRadians(swordAngle));
	    		g2d.drawImage(swordModelA, transformSwordModelA, null);
	    		
	    		// player B
	    		Image playerModelB = ImageIO.read(new File("res/player - default.png"));
	    		AffineTransform transformPlayerModelB = new AffineTransform();
	    		transformPlayerModelB.translate(this.playerX_B, this.playerY);
	    		transformPlayerModelB.scale(-this.playerW/playerModelA.getWidth(getFocusCycleRootAncestor()), this.playerH/playerModelA.getHeight(getFocusCycleRootAncestor()));
	    		g2d.drawImage(playerModelB, transformPlayerModelB, null);
	    		
	    		Image armourModelB = ImageIO.read(new File("res/armour - "+battleManager.getPbsB().getPlayer().getInventory().getActiveArmour().getName()+".png"));
	    		AffineTransform transformArmourModelB = new AffineTransform();
	    		transformArmourModelB.translate(this.playerX_B, this.playerY);
	    		transformArmourModelB.scale(-this.playerW/armourModelB.getWidth(getFocusCycleRootAncestor()), this.playerH/armourModelB.getHeight(getFocusCycleRootAncestor()));
	    		g2d.drawImage(armourModelB, transformArmourModelB, null);
	    		
	    		Image shieldModelB = ImageIO.read(new File("res/shield - "+battleManager.getPbsB().getPlayer().getInventory().getActiveShield().getName()+".png"));
	    		AffineTransform transformShieldModelB = new AffineTransform();
	    		transformShieldModelB.translate(shieldX_B, shieldY);
	    		transformShieldModelB.scale(-shieldW/shieldModelB.getWidth(getFocusCycleRootAncestor()), shieldH/shieldModelB.getHeight(getFocusCycleRootAncestor()));
	    		g2d.drawImage(shieldModelB, transformShieldModelB, null);
	    		
	    		Image swordModelB = ImageIO.read(new File("res/sword - "+battleManager.getPbsB().getActiveSword().getName()+".png"));
	    		AffineTransform transformSwordModelB = new AffineTransform();
	    		transformSwordModelB.translate(sword_BX, sword_AY);
	    		transformSwordModelB.scale(-sword_AW/swordModelB.getWidth(getFocusCycleRootAncestor()), sword_AH/swordModelB.getHeight(getFocusCycleRootAncestor()));
	    		transformSwordModelB.rotate(Math.toRadians(swordAngle));
	    		g2d.drawImage(swordModelB, transformSwordModelB, null);
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	    	
	    	if(battleManager.getInvalidMoveString() != null) {
		    	if(!battleManager.getInvalidMoveString().equals("NONE")) {
		    		FontMetrics metrics = g.getFontMetrics(g.getFont());
		    		int textBgIndent = width/60;
		    		int textWidth = metrics.stringWidth(battleManager.getInvalidMoveString());
		    		int startX = this.width/2 - (textWidth / 2);
		    		g.setColor(new Color(0, 0, 0, 150));
		    		g.fillRect(startX-textBgIndent, this.height/2, textWidth + 2*textBgIndent, this.height/15);
		    		g.setColor(Color.RED);
		    		g.drawString(battleManager.getInvalidMoveString(), startX, height/2 + height/25 + height/200);
		    		g.setColor(Color.BLACK);
		    	}
	    	}
	    	
	    	// player info panel
	    	// inactive sword info panel
	    	if(this.mouse != null) {
	    		if(!battleManager.isBattleOver()) {
	    			
	    			if(this.mouse.getPlayerHovered().equals("A")) {
	    				DrawPlayerInfo playerInfoPanel = new DrawPlayerInfo(this, battleManager.getPbsA(), width/10, g);
	            	}else if(this.mouse.getPlayerHovered().equals("B")) {
	            		DrawPlayerInfo playerInfoPanel = new DrawPlayerInfo(this, battleManager.getPbsB(), width*3/4-width/10, g);
	            	}else if(this.hoveringSwapSwordsButton) {
	            		if(battleManager.isTurnA()) {
	                		DrawSwordInfo swordInfoPanel = new DrawSwordInfo(this, battleManager.getPbsA(), g);
	                	}else {
	                		DrawSwordInfo swordInfoPanel = new DrawSwordInfo(this, battleManager.getPbsB(), g);
	                	}
	            	}
	    		}
	    			
	    	}
	    	
	    	// inactive sword info panel
	    	
	    	// battle over graphic
	    	if(battleManager.isBattleOver()) {
	    		
	    		swingButton.setVisible(false);
	        	jabButton.setVisible(false);
	        	blockButton.setVisible(false);
	        	chargeButton.setVisible(false);
	        	
	        	g.setColor(new Color(0, 0, 0, 150));
	    		g.fillRect(0, 0, this.width, this.height);
	    		
	    		// write game over (tie not implemented)
	    		try {
					if(battleManager.getDeadPlayer().equals("A")) {
						Image label = ImageIO.read(new File("res/label - you lose.png"));
		    			g.drawImage(label, this.width/6, this.height/6, this.width*2/3, this.height*2/3, getFocusCycleRootAncestor());
		    			
		    			
		    		}else if(battleManager.getDeadPlayer().equals("B")) {
		    			Image label = ImageIO.read(new File("res/label - you win.png"));
		    			g.drawImage(label, this.width/6, this.height/6, this.width*2/3, this.height*2/3, getFocusCycleRootAncestor());
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
    		if(animatingFirstMove) {
    			//System.out.println("FIRST");
    			animationSequenceManager.getFirstMove(g);
    		}else if(animatingSecondMove) {
    			animationSequenceManager.getSecondMove(g);
    			//System.out.println("SECOND");
    		}
    	}
    }
    
    public void flipBoolean() {
    	
    }
    
	public BattleManager getBattleLogic() {
		return battleManager;
	}

	public MouseHandler getMouse() {
		return mouse;
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

	public int getAx() {
		return playerX_A;
	}

	public void setAx(int ax) {
		playerX_A = ax;
	}

	public int getAy() {
		return playerY;
	}

	public void setAy(int ay) {
		playerY = ay;
	}

	public int getAw() {
		return playerW;
	}
	
	public void setAw(int aw) {
		playerW = aw;
	}
	
	public int getAh() {
		return playerH;
	}
	
	public void setAh(int ah) {
		playerH = ah;
	}

	public int getBx() {
		return playerX_B;
	}
	
	public void setBx(int bx) {
		playerX_B = bx;
	}
	
	
}
