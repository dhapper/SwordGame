package inventory;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import battle.BattleManager;
import entity.Player;
import main.DrawMenu;
import utilz.SpriteSheet;
import utilz.UseMongoDB;

public class DrawInventory extends JPanel{
	
	JFrame frame;
	Player player;
	InventoryMouseHandler mouse;
	
	int woodPanelW = 300;
	int woodPanelH = woodPanelW * 2;
	int woodPanelX = 300;
	int woodPanelY = 0;
	
	int slotIndent = 25;
	int slotWidth = 100;
	int slotHeight = slotWidth;
	int slotX = 100;
	int slotSwordY = 200;
	int slotShieldY = slotSwordY + slotIndent + slotHeight;
	int slotArmourY = slotShieldY + slotIndent + slotHeight;
	int swordAngle = 45;
	int swordAdjustX = 65;
	int swordAdjustY = 5;
	int shieldAdjust = 10;
	int armourAdjustY = 15;
	
	int highlightIndent = 6;
	
	public DrawInventory(Player player, JFrame frame) {
		
		this.frame = frame;
		this.frame.getContentPane().removeAll();
		this.frame.revalidate();
		this.frame.add(this);
		this.frame.setVisible(true);
		this.frame.repaint();
		setLayout(null);
		
		this.player = player;
		
		this.mouse = new InventoryMouseHandler(this);
		addMouseListener(this.mouse);
    	addMouseMotionListener(this.mouse);
		
		JButton backButton = new JButton("back");
		backButton.setBounds(50, 50, 100, 50);
		add(backButton);
		
		
		
		backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	frame.getContentPane().removeAll();
            	frame.revalidate();
            	frame.repaint();
            	
            	DrawMenu drawMenu = new DrawMenu(frame);
            }
        });
		
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		try {
			Image background = ImageIO.read(new File("res/background/background - log.png"));
			g.drawImage(background, woodPanelX * 0, woodPanelY, woodPanelW, woodPanelH, null);
			g.drawImage(background, woodPanelX * 1, woodPanelY, woodPanelW, woodPanelH, null);
			g.drawImage(background, woodPanelX * 2, woodPanelY, woodPanelW, woodPanelH, null);
			g.drawImage(background, woodPanelX * 3, woodPanelY, woodPanelW, woodPanelH, null);
			
			
			Image invSlot = ImageIO.read(new File("res/background/background - INVENTORY SLOT.png"));
			Graphics2D g2d = (Graphics2D) g;
			for (int i = 0; i < player.getInventory().getSwords().size(); i++) {
				
				g.setColor(Color.CYAN);
				if(player.getInventory().getActiveSword().getName().equals(player.getInventory().getSwords().get(i).getName()))
					g.fillRect(slotIndent * (i + 1) + slotWidth * i - highlightIndent / 2, slotSwordY - highlightIndent / 2, slotWidth + highlightIndent, slotHeight + highlightIndent);
				else if(player.getInventory().getInactiveSword().getName().equals(player.getInventory().getSwords().get(i).getName())) {
					g.setColor(new Color(255, 100, 200));
					g.fillRect(slotIndent * (i + 1) + slotWidth * i - highlightIndent / 2, slotSwordY - highlightIndent / 2, slotWidth + highlightIndent, slotHeight + highlightIndent);;
				}
				
			    g.drawImage(invSlot, slotIndent * (i + 1) + slotWidth * i, slotSwordY, slotWidth, slotHeight, null);
			    
			    Image swordImg = ImageIO.read(new File("res/swords/sword - " + player.getInventory().getSwords().get(i).getName() + ".png"));
			    AffineTransform transformSword = new AffineTransform();
			    double scaleX = slotWidth / 2.0 / swordImg.getWidth(null);
			    double scaleY = slotWidth * 1.0 / swordImg.getHeight(null);
			    transformSword.translate(swordAdjustX + (slotWidth + slotIndent) * i, slotSwordY - swordAdjustY);
			    transformSword.rotate(Math.toRadians(swordAngle), swordImg.getWidth(null) / 2.0, swordImg.getHeight(null) / 2.0);
			    transformSword.scale(scaleX, scaleY);
			    g2d.drawImage(swordImg, transformSword, null);
			    
			}
			
			g.setColor(Color.CYAN);
			
			for (int i = 0; i < player.getInventory().getShields().size(); i++) {
				Image shieldImg = ImageIO.read(new File("res/shields/shield - " + player.getInventory().getShields().get(i).getName() + ".png"));
				if(player.getInventory().getActiveShield().getName().equals(player.getInventory().getShields().get(i).getName()))
					g.fillRect(slotIndent * (i + 1) + slotWidth * i - highlightIndent / 2, slotShieldY - highlightIndent / 2, slotWidth + highlightIndent, slotHeight + highlightIndent);
				
				g.drawImage(invSlot, slotIndent * (i + 1) + slotWidth * i, slotShieldY, slotWidth, slotHeight, null);
			    //g.drawImage(shieldImg, 25 + 10 + 125 * i, 325 + 10, 80, 80, null);
			    g.drawImage(shieldImg, slotIndent * (i + 1) + slotWidth * i + shieldAdjust, slotShieldY + shieldAdjust, slotWidth - shieldAdjust * 2, slotHeight - shieldAdjust * 2, null);
			}
			
			
			
			for (int i = 0; i < player.getInventory().getArmoury().size(); i++) {
				SpriteSheet spriteSheet = new SpriteSheet("res/spritesheets/armour/spritesheet - armour - " + player.getInventory().getArmoury().get(i).getName() + ".png", 2, 2);
				Image armourImg = spriteSheet.getSprite(0);
				if(player.getInventory().getActiveArmour().getName().equals(player.getInventory().getArmoury().get(i).getName()))
					g.fillRect(slotIndent * (i + 1) + slotWidth * i - highlightIndent / 2, slotArmourY - highlightIndent / 2, slotWidth + highlightIndent, slotHeight + highlightIndent);
				
				g.drawImage(invSlot, slotIndent * (i + 1) + slotWidth * i, slotArmourY, slotWidth, slotHeight, null);
			    //g.drawImage(armour, 25 + 10 + 125 * i - 10, 450 + 10 - 20, 80 + 20, 80 + 20, null);
				g.drawImage(armourImg, slotIndent * (i + 1) + slotWidth * i, slotArmourY - armourAdjustY, slotWidth, slotHeight, null);
			}
			
			
			
    	} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
