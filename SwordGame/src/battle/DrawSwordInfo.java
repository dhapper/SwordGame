package battle;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Sword;

public class DrawSwordInfo {

	int bgW;
	int bgH;
	int bgX;
	int bgY;
	
	int swordOffset;
	int swordX;
	int swordY;
	int swordW;
	int swordH;
	
	int stringNameX;
	int stringStatX;
	int stringStatDiffX;
	int stringY;
	int stringOffsetY;
	
	Sword active, inactive;
	
	public DrawSwordInfo(DrawBattle drawBattle, PlayerBattleState player, Graphics g){
		
		this.bgW = drawBattle.buttonWidth*5/2;
		this.bgH = bgW;
		this.bgX = drawBattle.width/2 - this.bgW/2;
		this.bgY = drawBattle.buttonY1 - drawBattle.buttonHeight/2 - bgH;
		
		this.swordOffset = bgH/20;
		this.swordX = bgX + swordOffset;
		this.swordY = bgY + swordOffset;
		this.swordW = (bgW - 2*swordOffset)/3;
		this.swordH = bgH - 2*swordOffset;
		
		this.stringNameX = this.bgX + this.swordW + this.swordOffset*2;
		this.stringY = bgY + bgH/4;
		this.stringOffsetY = drawBattle.height/15;
		this.stringStatX = bgX + bgW*3/4;
		this.stringStatDiffX = this.stringStatX + bgW/8;
		
		this.active = player.getActiveSword();
		this.inactive = player.getInactiveSword();
		
		//draw bg
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(bgX, bgY, bgW, bgH);
		
		//draw sword
		try {
			Image sword = ImageIO.read(new File("res/sword - "+player.getInactiveSword().getName()+".png"));
			g.setColor(Color.RED);
			//g.fillRect(swordX, swordY, swordW, swordH);
			g.drawImage(sword, swordX, swordY, swordW, swordH, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//drawString
		g.setFont(new Font("Ariel", Font.BOLD, 14));
		g.setColor(new Color(200, 200, 0));
		g.drawString(player.getInactiveSword().getName(), this.stringNameX, stringY);
		g.drawString("Damage: ", this.stringNameX, stringY + stringOffsetY);
		g.drawString("Weight: ", this.stringNameX, stringY + stringOffsetY*2);
		g.drawString("Piercing: ", this.stringNameX, stringY + stringOffsetY*3);
		g.drawString("Stamina Usage: ", this.stringNameX, stringY + stringOffsetY*4);
		g.drawString("Durability: ", this.stringNameX, stringY + stringOffsetY*5);
		
		g.setColor(Color.WHITE);
		
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		int textWidth = metrics.stringWidth(""+inactive.getDamage());
		int startX = this.stringStatX - textWidth/2;
		g.drawString(""+inactive.getDamage(), startX, stringY + stringOffsetY);
		textWidth = metrics.stringWidth(""+inactive.getWeight());
		startX = this.stringStatX - textWidth/2;
		g.drawString(""+inactive.getWeight(), startX, stringY + stringOffsetY*2);
		textWidth = metrics.stringWidth(""+inactive.getPiercing());
		startX = this.stringStatX - textWidth/2;
		g.drawString(""+inactive.getPiercing(), startX, stringY + stringOffsetY*3);
		textWidth = metrics.stringWidth(""+inactive.getStaminaUsage());
		startX = this.stringStatX - textWidth/2;
		g.drawString(""+inactive.getStaminaUsage(), startX, stringY + stringOffsetY*4);
		textWidth = metrics.stringWidth(""+inactive.getDurability());
		startX = this.stringStatX - textWidth/2;
		g.drawString(""+inactive.getDurability(), startX, stringY + stringOffsetY*5);
		
		drawStatDiff(inactive.getDamage(), active.getDamage(), 1, g);
		drawStatDiff(inactive.getWeight(), active.getWeight(), 2, g);
		drawStatDiff(inactive.getPiercing(), active.getPiercing(), 3, g);
		drawStatDiff(inactive.getStaminaUsage(), active.getStaminaUsage(), 4, g);
		drawStatDiff(inactive.getDurability(), active.getDurability(), 5, g);
		
		g.setColor(Color.BLACK);
		
	}
	
	public void drawStatDiff(int x, int y, int row, Graphics g) {
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		int textWidth = metrics.stringWidth(statDiff(x, y));
		int startX = this.stringStatDiffX - textWidth/2;
		g.setColor(statColor(x, y));
		g.drawString(statDiff(x, y), startX, this.stringY + this.stringOffsetY*row);
	}
	
	public Color statColor(int x, int y) {
		if(x > y)
			return Color.GREEN;
		else if(x < y)
			return Color.RED;
		return Color.WHITE;
	}
	
	public String statDiff(int x, int y) {
		if(x > y)
			return "(+"+(x-y)+")";
		else if(x < y)
			return "("+(x-y)+")";
		return " =";
	}
	
}
