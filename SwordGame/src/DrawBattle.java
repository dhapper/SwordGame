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
	int Ax, Ay, Aw, Ah, Bx;
	int buttonX1, buttonY1;
	boolean buttonVisible;
	JButton swingButton, jabButton, blockButton, chargeButton;
	
    public DrawBattle(BattleLogic battleLogic) {
    	this.battleLogic = battleLogic;
    	this.width = 600;
    	this.height = 600;
    	this.buttonVisible = true;
    	
    	mouse = new MouseHandler(this);
    	addMouseListener(mouse);
    	addMouseMotionListener(mouse);
    	
    	setLayout(null);
    	
    	JFrame frame = new JFrame();
    	
    	int buttonWidth = this.width/5;
    	int buttonHeight = buttonWidth/2;
    	this.buttonX1 = this.width/2 - buttonWidth;
    	int buttonX2 = this.width/2;
    	this.buttonY1 = this.height*17/24;
    	int buttonY2 = buttonY1 + buttonHeight;
    	
    	
    	this.swingButton = new JButton();
    	this.jabButton = new JButton();
    	this.blockButton = new JButton();
    	this.chargeButton = new JButton();
    	try {
	    	swingButton.setBounds(buttonX1, buttonY1, buttonWidth, buttonHeight);
	    	add(swingButton);
	    	Image swingButtonImage = ImageIO.read(new File("res/swing button icon.png"));
			ImageIcon swingButtonIcon = new ImageIcon(swingButtonImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH));
	    	swingButton.setIcon(swingButtonIcon);
	    	
	    	jabButton.setBounds(buttonX1, buttonY2, buttonWidth, buttonHeight);
	    	add(jabButton);
	    	Image jabButtonImage = ImageIO.read(new File("res/jab button icon.png"));
			ImageIcon jabButtonIcon = new ImageIcon(jabButtonImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH));
			jabButton.setIcon(jabButtonIcon);
	    	
			blockButton.setBounds(buttonX2, buttonY1, buttonWidth, buttonHeight);
	    	add(blockButton);
	    	Image blockButtonImage = ImageIO.read(new File("res/block button icon.png"));
			ImageIcon blockButtonIcon = new ImageIcon(blockButtonImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH));
			blockButton.setIcon(blockButtonIcon);
	    	
	    	chargeButton.setBounds(buttonX2, buttonY2, buttonWidth, buttonHeight);
	    	add(chargeButton);
	    	Image chargeButtonImage = ImageIO.read(new File("res/charge button icon.png"));
			ImageIcon chargeButtonIcon = new ImageIcon(chargeButtonImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH));
			chargeButton.setIcon(chargeButtonIcon);
	    	
		} catch (IOException e1) {
			// TODO Auto-generated catch block
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
    	
    	
    	
    	frame.setTitle("frame title");
    	frame.setSize(width+16, height);	// idk why +16
    	frame.setVisible(true);
    	frame.setResizable(false);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setLocationRelativeTo(null);
    	frame.add(this);
    	
    	setOpaque(false);
    	
    }
    
    
    
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
    	
    	int labelOffset = this.width/100;
    	g.setColor(Color.red);
    	Font labelFont = new Font("SansSerif", Font.BOLD, 14);
    	g.setFont(labelFont);
    	
    	
    	int healthBarOffset = this.width/2;
    	int healthBarW = this.width/3; 
    	int healthBarH = this.width/20; 
    	int A_healthBarX = healthBarOffset - healthBarW;
    	int B_healthBarX = healthBarOffset;
    	int healthBarY = this.height/100;
    	
    	int stamBarOffset = healthBarOffset;
    	int stamBarW = healthBarW*3/4;
    	int stamBarH = healthBarH/2;
    	int A_stamBarX = stamBarOffset - stamBarW;
    	int B_stamBarX = stamBarOffset;
    	int stamBarY = healthBarY + this.height/20;
    	
    	int shieldIconOffset = this.width/100;
    	int shieldIconW = this.width/20;
    	int shieldIconH = shieldIconW;
    	int A_shieldIconX = shieldIconOffset;
    	int B_shieldIconX = this.width - shieldIconW - shieldIconOffset;
    	int shieldIconY = this.height/100;
    	int chargeIconY = 2*shieldIconY + shieldIconH;
    	
    	int blockStringOffset = this.height/27;
    	int A_blockStringX = A_shieldIconX + shieldIconW + shieldIconOffset;	// dont get this fully
    	int A_blockStringY = shieldIconY + blockStringOffset;
    	int A_chargeStringY = chargeIconY + blockStringOffset;
    	int B_blockStringX = B_shieldIconX - shieldIconW + shieldIconOffset;
    	
    	this.Aw = this.width/3;
    	this.Ah = this.Aw*6/5;
    	this.Ax = (width/4-this.Aw/2)/2;
    	this.Ay = this.height/2-this.Ah/2;
    	this.Bx = this.width - this.Ax - this.Aw;
    	
    	int shieldAX = this.Ax +  this.Aw/20;
    	int shieldAY = this.Ay + this.Ah*2/5;
    	int shieldAW = this.Aw/2;
    	int shieldAH = this.Aw/2;
    	int shieldBX = this.Bx - this.Aw/20;
    	int shieldAScale = 3; 
    	
    	int swordAX = this.Ax + this.Aw - this.width/25;
    	int swordAY = this.Ay - this.Ah/5;
    	int swordAngleA = 10;
    	int swordScaleA = 5;
    	int swordBX = this.Bx + this.width/25;
    	
    	
    	// set bg
    	try {
			Image img = ImageIO.read(new File("res/bg.png"));
			g.drawImage(img, 0, 0, this.width, this.height, getFocusCycleRootAncestor());
			g.setColor(new Color(255, 255, 255, 50))
;			g.fillRect(0, 0, this.width, this.height);
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	int healthBarGreen = 150;
    	int stamBarRed = 100;
    	int stamBarGreen = 100;
    	int stamBarBlue = 200;
    	
    	Graphics2D g2d = (Graphics2D) g;
    	
    	// draw icons
    	try {
			Image blockIconA = ImageIO.read(new File("res/block counter icon.png"));
			g.drawImage(blockIconA, A_shieldIconX, shieldIconY, shieldIconW, shieldIconH, getFocusCycleRootAncestor());
			Image chargeIconA = ImageIO.read(new File("res/charge multiplier icon.png"));
			g.drawImage(chargeIconA, A_shieldIconX, chargeIconY, shieldIconW, shieldIconH, getFocusCycleRootAncestor());
	    	
	    	Image blockIconB = ImageIO.read(new File("res/block counter icon.png"));
			g.drawImage(blockIconB, B_shieldIconX, shieldIconY, shieldIconW, shieldIconH, getFocusCycleRootAncestor());
			Image chargeIconB = ImageIO.read(new File("res/charge multiplier icon.png"));
			g.drawImage(chargeIconB, B_shieldIconX, chargeIconY, shieldIconW, shieldIconH, getFocusCycleRootAncestor());
			
			// whitener
			g.setColor(new Color(255, 255, 255, 75));
			g.fillRect(A_shieldIconX+shieldIconW, shieldIconY, shieldIconW, 2*shieldIconH+shieldIconOffset);
			g.fillRect(B_shieldIconX-shieldIconW, shieldIconY, shieldIconW, 2*shieldIconH+shieldIconOffset);
			
			
			g.setColor(Color.BLACK);
			FontMetrics metrics = g.getFontMetrics(g.getFont());
			
			int textWidth = metrics.stringWidth(""+battleLogic.getPbsA().getBlockCounter());
			int startX = A_shieldIconX + shieldIconW + shieldIconW/2 - (textWidth / 2);
			g.drawString(""+battleLogic.getPbsA().getBlockCounter(), startX, A_blockStringY);
			
			textWidth = metrics.stringWidth(""+battleLogic.getPbsA().getCurrCharge());
			startX = A_shieldIconX + shieldIconW + shieldIconW/2 - (textWidth / 2);
	    	g.drawString(""+battleLogic.getPbsA().getCurrCharge(), startX, A_chargeStringY);
	    	
	    	textWidth = metrics.stringWidth(""+battleLogic.getPbsB().getBlockCounter());
			startX = B_shieldIconX - shieldIconW/2 - (textWidth / 2);
	    	g.drawString(""+battleLogic.getPbsB().getBlockCounter(), startX, A_blockStringY);
	    	
	    	textWidth = metrics.stringWidth(""+battleLogic.getPbsB().getCurrCharge());
	    	startX = B_shieldIconX - shieldIconW/2 - (textWidth / 2);
	    	g.drawString(""+battleLogic.getPbsB().getCurrCharge(), startX, A_chargeStringY);
	    	
	    	// outline
			/*g.drawRect(A_shieldIconX, shieldIconY, shieldIconW, shieldIconH);
	    	g.drawRect(A_shieldIconX, chargeIconY, shieldIconW, shieldIconH);
			g.drawRect(B_shieldIconX, shieldIconY, shieldIconW, shieldIconH);
	    	g.drawRect(B_shieldIconX, chargeIconY, shieldIconW, shieldIconH);*/
	    	
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	// draw players
    	try {
    		
    		// player A
    		Image playerA = ImageIO.read(new File("res/player.png"));
    		AffineTransform transformPlayerA = new AffineTransform();
    		transformPlayerA.translate(this.Ax, this.Ay);
    		transformPlayerA.scale(this.Aw/playerA.getWidth(getFocusCycleRootAncestor()),
    				this.Ah/playerA.getHeight(getFocusCycleRootAncestor()));
    		g2d.drawImage(playerA, transformPlayerA, null);
    		
    		Image shieldA = ImageIO.read(new File("res/"+this.battleLogic.getPbsA().getPlayer().getInventory().getActiveShield().getName()+".png"));
    		AffineTransform transformShieldA = new AffineTransform();
    		transformShieldA.translate(shieldAX, shieldAY);
    		transformShieldA.scale(shieldAW/shieldA.getWidth(getFocusCycleRootAncestor()),
    				shieldAW/shieldA.getHeight(getFocusCycleRootAncestor()));
    		g2d.drawImage(shieldA, transformShieldA , null);
    		
    		Image swordA = ImageIO.read(new File("res/"+this.battleLogic.getPbsA().getPlayer().getInventory().getActiveSword().getName()+".png"));
			AffineTransform transform = new AffineTransform();
			transform.translate(swordAX, swordAY);
			transform.rotate(Math.toRadians(swordAngleA));
			transform.scale(swordScaleA, swordScaleA);
			g2d.drawImage(swordA, transform, null);
    		
			// player B
			Image playerB = ImageIO.read(new File("res/player2.png"));
			AffineTransform transformPlayerB = new AffineTransform();
    		transformPlayerB.translate(this.Bx+this.Aw, this.Ay);
    		transformPlayerB.scale(-this.Aw/playerB.getWidth(getFocusCycleRootAncestor()),
    				this.Ah/playerB.getHeight(getFocusCycleRootAncestor()));
    		g2d.drawImage(playerB, transformPlayerB, null);
			
    		Image shieldB = ImageIO.read(new File("res/"+this.battleLogic.getPbsB().getPlayer().getInventory().getActiveShield().getName()+".png"));
    		AffineTransform transformShieldB = new AffineTransform();
    		transformShieldB.translate(shieldBX+this.Aw, shieldAY);
    		transformShieldB.scale(-shieldAW/shieldB.getWidth(getFocusCycleRootAncestor()),
    				shieldAW/shieldB.getHeight(getFocusCycleRootAncestor()));
    		g2d.drawImage(shieldB, transformShieldB, null);
    		
    		Image swordB = ImageIO.read(new File("res/"+this.battleLogic.getPbsB().getPlayer().getInventory().getActiveSword().getName()+".png"));
			AffineTransform transformSwordB = new AffineTransform();
			transformSwordB.translate(swordBX, swordAY);
			transformSwordB.rotate(Math.toRadians(-swordAngleA));
			transformSwordB.scale(-swordScaleA, swordScaleA);
			g2d.drawImage(swordB, transformSwordB, null);
    		
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// A
    	// health bar
    	g.setColor(new Color(0, healthBarGreen, 0));
    	g.fillRect(A_healthBarX + (healthBarW-healthBarW*battleLogic.getPbsA().getCurrHealth()/battleLogic.getPbsA().getMaxHealth()),
    			healthBarY, healthBarW*battleLogic.getPbsA().getCurrHealth()/battleLogic.getPbsA().getMaxHealth(), healthBarH);
    	g.setColor(Color.BLACK);
    	g.drawRect(A_healthBarX, healthBarY, healthBarW, healthBarH);
    	// stam bar
    	g.setColor(new Color(stamBarRed, stamBarGreen, stamBarBlue));
    	g.fillRect(A_stamBarX + (stamBarW-stamBarW*battleLogic.getPbsA().getCurrStamina()/battleLogic.getPbsA().getMaxStamina()),
    			stamBarY, stamBarW*battleLogic.getPbsA().getCurrStamina()/battleLogic.getPbsA().getMaxStamina(), stamBarH);
    	g.setColor(Color.BLACK);
    	g.drawRect(A_stamBarX, stamBarY, stamBarW, stamBarH);
    	
    	// B
    	// health Bar
    	g.setColor(new Color(0, healthBarGreen, 0));
    	g.fillRect(B_healthBarX, healthBarY, healthBarW*battleLogic.getPbsB().getCurrHealth()/battleLogic.getPbsB().getMaxHealth(), healthBarH);
    	g.setColor(Color.BLACK);
    	g.drawRect(B_healthBarX, healthBarY, healthBarW, healthBarH);
    	// stam bar
    	g.setColor(new Color(stamBarRed, stamBarGreen, stamBarBlue));
    	g.fillRect(B_stamBarX, stamBarY, stamBarW*battleLogic.getPbsB().getCurrStamina()/battleLogic.getPbsB().getMaxStamina(), stamBarH);
    	g.setColor(Color.BLACK);
    	g.drawRect(B_stamBarX, stamBarY, stamBarW, stamBarH);
    	// temp player
    	//g.drawRect(Bx, Ay, Aw, Ah);
    	//g.drawString(battleLogic.getPbsB().getName(), Bx, Ay);
    	
    	//display name
    	g.setColor(new Color(255, 255, 255, 75));
    	//g.fillRect(this.width*1/3, this.Ay*2-this.height/40, this.width*1/3, 20);
    	
    	FontMetrics metrics = g.getFontMetrics(g.getFont());
    	int textBgIndent = this.width/60;
    	if(battleLogic.isTurnA()) {
    		int textWidth = metrics.stringWidth("Pick "+battleLogic.getPbsA().getName()+"'s move");
    		int startX = this.width/2 - (textWidth / 2);
    		g.fillRect(startX-textBgIndent, this.height*16/24, textWidth+2*textBgIndent, this.height/30);
        	g.setColor(Color.BLACK);
    		g.drawString("Pick "+battleLogic.getPbsA().getName()+"'s move", startX, this.buttonY1 - this.height/60);
    	} else {
    		int textWidth = metrics.stringWidth("Pick "+battleLogic.getPbsB().getName()+"'s move");
    		int startX = this.width/2 - (textWidth / 2);
    		g.fillRect(startX-textBgIndent, this.height*16/24, textWidth+2*textBgIndent, this.height/30);
        	g.setColor(Color.BLACK);
    		g.drawString("Pick "+battleLogic.getPbsB().getName()+"'s move", startX, this.buttonY1 - this.height/60);
    	}
    	
    	if(!battleLogic.isBattleOver() && mouse.getHovered().equals("A")) {
    		DrawPlayerInfo info = new DrawPlayerInfo(this, battleLogic.getPbsA(), this.Ax, this.Ay, g);
    	}else if(!battleLogic.isBattleOver() && mouse.getHovered() == "B") {
    		DrawPlayerInfo info = new DrawPlayerInfo(this, battleLogic.getPbsB(), this.Bx, this.Ay, g);
    	}
    	
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
					Image label = ImageIO.read(new File("res/you lose label.png"));
	    			g.drawImage(label, this.width/6, this.height/6, this.width*2/3, this.height*2/3, getFocusCycleRootAncestor());
	    			
	    			
	    		}else if(battleLogic.getDeadPlayer().equals("B")) {
	    			Image label = ImageIO.read(new File("res/you win label.png"));
	    			g.drawImage(label, this.width/6, this.height/6, this.width*2/3, this.height*2/3, getFocusCycleRootAncestor());
	    		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// wait 3 seconds
    		
    		// update stuff
    		
    		// return to menu
    		
    	}
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
		return Ax;
	}

	public void setAx(int ax) {
		Ax = ax;
	}

	public int getAy() {
		return Ay;
	}

	public void setAy(int ay) {
		Ay = ay;
	}

	public int getAw() {
		return Aw;
	}
	
	public void setAw(int aw) {
		Aw = aw;
	}
	
	public int getAh() {
		return Ah;
	}
	
	public void setAh(int ah) {
		Ah = ah;
	}

	public int getBx() {
		return Bx;
	}
	public void setBx(int bx) {
		Bx = bx;
	}
	
	

}
