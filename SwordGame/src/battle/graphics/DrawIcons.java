package battle.graphics;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DrawIcons {

	DrawBattle drawBattle;
	
	public DrawIcons(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
	}
	
	public void paint(Graphics g) {
    	// draw icons
    	try {
			Image blockIconA = ImageIO.read(new File("res/icons/icon - block counter.png"));
			g.drawImage(blockIconA, drawBattle.shieldIconX_A, drawBattle.shieldIconY, drawBattle.shieldIconW, drawBattle.shieldIconH, null);
			Image chargeIconA = ImageIO.read(new File("res/icons/icon - charge multiplier.png"));
			g.drawImage(chargeIconA, drawBattle.shieldIconX_A, drawBattle.chargeIconY, drawBattle.shieldIconW, drawBattle.shieldIconH, null);
	    	
	    	Image blockIconB = ImageIO.read(new File("res/icons/icon - block counter.png"));
			g.drawImage(blockIconB, drawBattle.shieldIconX_B, drawBattle.shieldIconY, drawBattle.shieldIconW, drawBattle.shieldIconH, null);
			Image chargeIconB = ImageIO.read(new File("res/icons/icon - charge multiplier.png"));
			g.drawImage(chargeIconB, drawBattle.shieldIconX_B, drawBattle.chargeIconY, drawBattle.shieldIconW, drawBattle.shieldIconH, null);
			
			// whitener
			g.setColor(new Color(255, 255, 255, 75));
			g.fillRect(drawBattle.shieldIconX_A+drawBattle.shieldIconW, drawBattle.shieldIconY, drawBattle.shieldIconW, 2*drawBattle.shieldIconH+drawBattle.shieldIconOffset);
			g.fillRect(drawBattle.shieldIconX_B-drawBattle.shieldIconW, drawBattle.shieldIconY, drawBattle.shieldIconW, 2*drawBattle.shieldIconH+drawBattle.shieldIconOffset);
			
			g.setColor(Color.BLACK);
			FontMetrics metrics = g.getFontMetrics(g.getFont());
			
			int textWidth = metrics.stringWidth(""+drawBattle.battleManager.getPbsA().getBlockCounter());
			int startX = drawBattle.shieldIconX_A + drawBattle.shieldIconW + drawBattle.shieldIconW/2 - (textWidth / 2);
			g.drawString(""+drawBattle.battleManager.getPbsA().getBlockCounter(), startX, drawBattle.iconBlockStringY);
			
			textWidth = metrics.stringWidth(""+drawBattle.battleManager.getPbsA().getCurrCharge());
			startX = drawBattle.shieldIconX_A + drawBattle.shieldIconW + drawBattle.shieldIconW/2 - (textWidth / 2);
	    	g.drawString(""+drawBattle.battleManager.getPbsA().getCurrCharge(), startX, drawBattle.iconChargeStringY);
	    	
	    	textWidth = metrics.stringWidth(""+drawBattle.battleManager.getPbsB().getBlockCounter());
			startX = drawBattle.shieldIconX_B - drawBattle.shieldIconW/2 - (textWidth / 2);
	    	g.drawString(""+drawBattle.battleManager.getPbsB().getBlockCounter(), startX, drawBattle.iconBlockStringY);
	    	
	    	textWidth = metrics.stringWidth(""+drawBattle.battleManager.getPbsB().getCurrCharge());
	    	startX = drawBattle.shieldIconX_B - drawBattle.shieldIconW/2 - (textWidth / 2);
	    	g.drawString(""+drawBattle.battleManager.getPbsB().getCurrCharge(), startX, drawBattle.iconChargeStringY);
	    	
	    	// test
	    	//SpriteSheet ss = new SpriteSheet("res/anime.png", 2, 2);
	    	//g.drawImage(ss.getSprite(3), 0, 0, 300, 300, null);
	    	
	    } catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
