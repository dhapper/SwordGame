import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;  
import javax.swing.JPanel;
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
	BattleLogic battleLogic;
	MouseHandler mouse;
	String nextMove;
	boolean moveChosen = false;
	int playerX_A, playerY, playerW, playerH, playerX_B;
	int buttonWidth, buttonHeight, buttonX1, buttonX2, buttonY1, buttonY2, altButtonWidth;
	boolean buttonVisible;
	JButton swingButton, jabButton, blockButton, chargeButton, swapSwordsButton, forfeitButton;
	JFrame frame;
	boolean hoveringSwapSwordsButton = false;
	
    public DrawBattle(JFrame frame, BattleLogic battleLogic) {
    	
    	this.battleLogic = battleLogic;
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
    	
    	try {
	    	swingButton.setBounds(buttonX1, buttonY1, buttonWidth, buttonHeight);
	    	add(swingButton);
	    	Image swingButtonImage = ImageIO.read(new File("res/button - swing.png"));
			ImageIcon swingButtonIcon = new ImageIcon(swingButtonImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH));
	    	swingButton.setIcon(swingButtonIcon);
	    	
	    	jabButton.setBounds(buttonX1, buttonY2, buttonWidth, buttonHeight);
	    	add(jabButton);
	    	Image jabButtonImage = ImageIO.read(new File("res/button - jab.png"));
			ImageIcon jabButtonIcon = new ImageIcon(jabButtonImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH));
			jabButton.setIcon(jabButtonIcon);
	    	
			blockButton.setBounds(buttonX2, buttonY1, buttonWidth, buttonHeight);
	    	add(blockButton);
	    	Image blockButtonImage = ImageIO.read(new File("res/button - block.png"));
			ImageIcon blockButtonIcon = new ImageIcon(blockButtonImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH));
			blockButton.setIcon(blockButtonIcon);
	    	
	    	chargeButton.setBounds(buttonX2, buttonY2, buttonWidth, buttonHeight);
	    	add(chargeButton);
	    	Image chargeButtonImage = ImageIO.read(new File("res/button - charge.png"));
			ImageIcon chargeButtonIcon = new ImageIcon(chargeButtonImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH));
			chargeButton.setIcon(chargeButtonIcon);
			
			swapSwordsButton.setBounds(buttonX1-altButtonWidth, buttonY1+buttonHeight/2, altButtonWidth, buttonHeight);
	    	add(swapSwordsButton);
	    	Image swapSwordsButtonImage = ImageIO.read(new File("res/button - swap swords.png"));
			ImageIcon swapSwordsButtonIcon = new ImageIcon(swapSwordsButtonImage.getScaledInstance(altButtonWidth, buttonHeight, Image.SCALE_SMOOTH));
			swapSwordsButton.setIcon(swapSwordsButtonIcon);
	    	
			forfeitButton.setBounds(buttonX2+buttonWidth, buttonY1+buttonHeight/2, altButtonWidth, buttonHeight);
			add(forfeitButton);
	    	Image forfeitButtonImage = ImageIO.read(new File("res/button - forfeit.png"));
			ImageIcon forfeitButtonIcon = new ImageIcon(forfeitButtonImage.getScaledInstance(altButtonWidth, buttonHeight, Image.SCALE_SMOOTH));
			forfeitButton.setIcon(forfeitButtonIcon);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	
    	swingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	moveChosen = true;
            	setNextMove("SWING");
            }
        });
    	
    	jabButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	moveChosen = true;
            	setNextMove("JAB");
            }
        });
    	
    	blockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	moveChosen = true;
            	setNextMove("BLOCK");
            }
        });
    	
    	chargeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	moveChosen = true;
            	setNextMove("CHARGE");
            }
        });
    	
    	swapSwordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	moveChosen = true;
            	setNextMove("CHARGE");
            }
        });
    	
    	swapSwordsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	hoveringSwapSwordsButton = true;
            	repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	hoveringSwapSwordsButton = false;
            	repaint();
            }
        });
    	
    	forfeitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	// count as loss
            	
            	DrawMenu menu = new DrawMenu(frame);
            }
        });
    }
    
    
    
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
    	
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
    	
    	this.playerW = this.width/3;
    	this.playerH = this.playerW;
    	this.playerX_A = 0;
    	this.playerX_B = this.width - this.playerX_A;
    	this.playerY = this.height/5 + this.height/50;
    	
		int shieldY = playerY + playerH/3 + playerH/8;
		int shieldW = playerW/3;
		int shieldH = playerH/3;
		int shieldX_A = playerX_A + playerW/4 - playerW/12;
		int shieldX_B = width - shieldX_A;
		
		int sword_AX = playerX_A + playerW + playerW/6;
		int sword_AY = playerY - playerH/3 + playerH/40;
		int sword_AW = playerW/3;
		int sword_AH = playerH;
		int sword_BX = width - sword_AX;
		int swordAngle = 40;
    	
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
    	
    	// draw icons
    	try {
			Image blockIconA = ImageIO.read(new File("res/icon - block counter.png"));
			g.drawImage(blockIconA, shieldIconX_A, shieldIconY, shieldIconW, shieldIconH, getFocusCycleRootAncestor());
			Image chargeIconA = ImageIO.read(new File("res/icon - charge multiplier.png"));
			g.drawImage(chargeIconA, shieldIconX_A, chargeIconY, shieldIconW, shieldIconH, getFocusCycleRootAncestor());
	    	
	    	Image blockIconB = ImageIO.read(new File("res/icon - block counter.png"));
			g.drawImage(blockIconB, shieldIconX_B, shieldIconY, shieldIconW, shieldIconH, getFocusCycleRootAncestor());
			Image chargeIconB = ImageIO.read(new File("res/icon - charge multiplier.png"));
			g.drawImage(chargeIconB, shieldIconX_B, chargeIconY, shieldIconW, shieldIconH, getFocusCycleRootAncestor());
			
			// whitener
			g.setColor(new Color(255, 255, 255, 75));
			g.fillRect(shieldIconX_A+shieldIconW, shieldIconY, shieldIconW, 2*shieldIconH+shieldIconOffset);
			g.fillRect(shieldIconX_B-shieldIconW, shieldIconY, shieldIconW, 2*shieldIconH+shieldIconOffset);
			
			g.setColor(Color.BLACK);
			FontMetrics metrics = g.getFontMetrics(g.getFont());
			
			int textWidth = metrics.stringWidth(""+battleLogic.getPbsA().getBlockCounter());
			int startX = shieldIconX_A + shieldIconW + shieldIconW/2 - (textWidth / 2);
			g.drawString(""+battleLogic.getPbsA().getBlockCounter(), startX, iconBlockStringY);
			
			textWidth = metrics.stringWidth(""+battleLogic.getPbsA().getCurrCharge());
			startX = shieldIconX_A + shieldIconW + shieldIconW/2 - (textWidth / 2);
	    	g.drawString(""+battleLogic.getPbsA().getCurrCharge(), startX, iconChargeStringY);
	    	
	    	textWidth = metrics.stringWidth(""+battleLogic.getPbsB().getBlockCounter());
			startX = shieldIconX_B - shieldIconW/2 - (textWidth / 2);
	    	g.drawString(""+battleLogic.getPbsB().getBlockCounter(), startX, iconBlockStringY);
	    	
	    	textWidth = metrics.stringWidth(""+battleLogic.getPbsB().getCurrCharge());
	    	startX = shieldIconX_B - shieldIconW/2 - (textWidth / 2);
	    	g.drawString(""+battleLogic.getPbsB().getCurrCharge(), startX, iconChargeStringY);
	    } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	// health bar
    	g.setColor(new Color(healthBarRed, healthBarGreen, healthBarBlue));
    	g.fillRect(healthBarX_A + (healthBarW-healthBarW*battleLogic.getPbsA().getCurrHealth()/battleLogic.getPbsA().getMaxHealth()),
    			healthBarY, healthBarW*battleLogic.getPbsA().getCurrHealth()/battleLogic.getPbsA().getMaxHealth(), healthBarH);
    	g.setColor(Color.BLACK);
    	g.drawRect(healthBarX_A, healthBarY, healthBarW, healthBarH);
    	
    	g.setColor(new Color(0, healthBarGreen, 0));
    	g.fillRect(healthBarX_B, healthBarY, healthBarW*battleLogic.getPbsB().getCurrHealth()/battleLogic.getPbsB().getMaxHealth(), healthBarH);
    	g.setColor(Color.BLACK);
    	g.drawRect(healthBarX_B, healthBarY, healthBarW, healthBarH);
    	
    	// stam bar
    	g.setColor(new Color(stamBarRed, stamBarGreen, stamBarBlue));
    	g.fillRect(stamBarX_A + (stamBarW-stamBarW*battleLogic.getPbsA().getCurrStamina()/battleLogic.getPbsA().getMaxStamina()),
    			stamBarY, stamBarW*battleLogic.getPbsA().getCurrStamina()/battleLogic.getPbsA().getMaxStamina(), stamBarH);
    	g.setColor(Color.BLACK);
    	g.drawRect(stamBarX_A, stamBarY, stamBarW, stamBarH);
    	

    	g.setColor(new Color(stamBarRed, stamBarGreen, stamBarBlue));
    	g.fillRect(stamBarX_B, stamBarY, stamBarW*battleLogic.getPbsB().getCurrStamina()/battleLogic.getPbsB().getMaxStamina(), stamBarH);
    	g.setColor(Color.BLACK);
    	g.drawRect(stamBarX_B, stamBarY, stamBarW, stamBarH);
    	
    	// display name, might be redundant
    	g.setColor(new Color(255, 255, 255, 75));
    	FontMetrics metrics = g.getFontMetrics(g.getFont());
    	int textBgIndent = this.width/60;
    	if(battleLogic.isTurnA()) {
    		int textWidth = metrics.stringWidth("Pick "+battleLogic.getPbsA().getName()+"'s move");
    		int startX = this.width/2 - (textWidth / 2);
    		g.fillRect(startX-textBgIndent, this.height*63/96, textWidth+2*textBgIndent, this.height/15);
        	g.setColor(Color.BLACK);
        	
    		g.drawString("Pick "+battleLogic.getPbsA().getName()+"'s move", startX, height/2+height/5-height/200);
    	
    	} else {
    		int textWidth = metrics.stringWidth("Pick "+battleLogic.getPbsB().getName()+"'s move");
    		int startX = this.width/2 - (textWidth / 2);
    		g.fillRect(startX-textBgIndent, this.height*63/96, textWidth+2*textBgIndent, this.height/15);
        	g.setColor(Color.BLACK);
        	g.drawString("Pick "+battleLogic.getPbsB().getName()+"'s move", startX, height/2+height/5-height/200);
    	}
    
    	try {
    		
    		// player A
    		Image playerModelA = ImageIO.read(new File("res/player - default.png"));
    		AffineTransform transformPlayerModelA = new AffineTransform();
    		transformPlayerModelA.translate(this.playerX_A, this.playerY);
    		transformPlayerModelA.scale(this.playerW/playerModelA.getWidth(getFocusCycleRootAncestor()), this.playerH/playerModelA.getHeight(getFocusCycleRootAncestor()));
    		g2d.drawImage(playerModelA, transformPlayerModelA, null);
    		
    		Image armourModelA = ImageIO.read(new File("res/armour - "+battleLogic.getPbsA().getPlayer().getInventory().getActiveArmour().getName()+".png"));
    		AffineTransform transformArmourModelA = new AffineTransform();
    		transformArmourModelA.translate(this.playerX_A, this.playerY);
    		transformArmourModelA.scale(this.playerW/playerModelA.getWidth(getFocusCycleRootAncestor()), this.playerH/playerModelA.getHeight(getFocusCycleRootAncestor()));
    		g2d.drawImage(armourModelA, transformArmourModelA, null);
    		
    		Image shieldModelA = ImageIO.read(new File("res/shield - "+battleLogic.getPbsA().getPlayer().getInventory().getActiveShield().getName()+".png"));
    		AffineTransform transformShieldModelA = new AffineTransform();
    		transformShieldModelA.translate(shieldX_A, shieldY);
    		transformShieldModelA.scale(shieldW/shieldModelA.getWidth(getFocusCycleRootAncestor()), shieldH/shieldModelA.getHeight(getFocusCycleRootAncestor()));
    		g2d.drawImage(shieldModelA, transformShieldModelA, null);
    		
    		Image swordModelA = ImageIO.read(new File("res/sword - "+battleLogic.getPbsA().getActiveSword().getName()+".png"));
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
    		
    		Image armourModelB = ImageIO.read(new File("res/armour - "+battleLogic.getPbsB().getPlayer().getInventory().getActiveArmour().getName()+".png"));
    		AffineTransform transformArmourModelB = new AffineTransform();
    		transformArmourModelB.translate(this.playerX_B, this.playerY);
    		transformArmourModelB.scale(-this.playerW/armourModelB.getWidth(getFocusCycleRootAncestor()), this.playerH/armourModelB.getHeight(getFocusCycleRootAncestor()));
    		g2d.drawImage(armourModelB, transformArmourModelB, null);
    		
    		Image shieldModelB = ImageIO.read(new File("res/shield - "+battleLogic.getPbsB().getPlayer().getInventory().getActiveShield().getName()+".png"));
    		AffineTransform transformShieldModelB = new AffineTransform();
    		transformShieldModelB.translate(shieldX_B, shieldY);
    		transformShieldModelB.scale(-shieldW/shieldModelB.getWidth(getFocusCycleRootAncestor()), shieldH/shieldModelB.getHeight(getFocusCycleRootAncestor()));
    		g2d.drawImage(shieldModelB, transformShieldModelB, null);
    		
    		Image swordModelB = ImageIO.read(new File("res/sword - "+battleLogic.getPbsB().getActiveSword().getName()+".png"));
    		AffineTransform transformSwordModelB = new AffineTransform();
    		transformSwordModelB.translate(sword_BX, sword_AY);
    		transformSwordModelB.scale(-sword_AW/swordModelB.getWidth(getFocusCycleRootAncestor()), sword_AH/swordModelB.getHeight(getFocusCycleRootAncestor()));
    		transformSwordModelB.rotate(Math.toRadians(swordAngle));
    		g2d.drawImage(swordModelB, transformSwordModelB, null);
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if(!battleLogic.getInvalidMoveString().equals("NONE")) {
    		
    		int textWidth = metrics.stringWidth(battleLogic.getInvalidMoveString());
    		int startX = this.width/2 - (textWidth / 2);
    		g.setColor(new Color(0, 0, 0, 150));
    		g.fillRect(startX-textBgIndent, this.height/2, textWidth + 2*textBgIndent, this.height/15);
    		g.setColor(Color.RED);
    		g.drawString(battleLogic.getInvalidMoveString(), startX, height/2 + height/25 + height/200);
    		g.setColor(Color.BLACK);
    	}
    	
    	// player info panel
    	// inactive sword info panel
    	if(this.mouse != null) {
    		if(!battleLogic.isBattleOver()) {
    			
    			if(this.mouse.getPlayerHovered().equals("A")) {
    				DrawPlayerInfo playerInfoPanel = new DrawPlayerInfo(this, battleLogic.getPbsA(), width/10, g);
            	}else if(this.mouse.getPlayerHovered().equals("B")) {
            		DrawPlayerInfo playerInfoPanel = new DrawPlayerInfo(this, battleLogic.getPbsB(), width*3/4-width/10, g);
            	}else if(this.hoveringSwapSwordsButton) {
            		if(battleLogic.isTurnA()) {
                		DrawSwordInfo swordInfoPanel = new DrawSwordInfo(this, battleLogic.getPbsA(), g);
                	}else {
                		DrawSwordInfo swordInfoPanel = new DrawSwordInfo(this, battleLogic.getPbsB(), g);
                	}
            	}
    		}
    			
    	}
    	
    	// inactive sword info panel
    	
    	// battle over graphic
    	if(battleLogic.isBattleOver()) {
    		
    		swingButton.setVisible(false);
        	jabButton.setVisible(false);
        	blockButton.setVisible(false);
        	chargeButton.setVisible(false);
        	
        	g.setColor(new Color(0, 0, 0, 150));
    		g.fillRect(0, 0, this.width, this.height);
    		
    		// write game over (tie not implemented)
    		try {
				if(battleLogic.getDeadPlayer().equals("A")) {
					Image label = ImageIO.read(new File("res/label - you lose.png"));
	    			g.drawImage(label, this.width/6, this.height/6, this.width*2/3, this.height*2/3, getFocusCycleRootAncestor());
	    			
	    			
	    		}else if(battleLogic.getDeadPlayer().equals("B")) {
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
    	//g.setColor(Color.RED);
    	//g.drawRect(0, 0, 300, 300);
    	//g.drawRect(300, 0, 300, 300);
    	//g.drawRect(0, 300, 300, 300);
    	//g.drawRect(600, 0, 300, 300);
    }
    
	public BattleLogic getBattleLogic() {
		return battleLogic;
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
