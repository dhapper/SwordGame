package battle;

import java.awt.Color;
import java.awt.Graphics;

public class DrawBars {

	DrawBattle drawBattle;
	
	public DrawBars(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
		
	}
	
	public void paint(Graphics g) {
		
		
		// bg
		Color bg = new Color(0, 0, 0, 50);
		g.setColor(bg);
		g.fillRect(drawBattle.healthBarX_A, drawBattle.healthBarY, drawBattle.healthBarW * 2, drawBattle.healthBarH);
		g.fillRect(drawBattle.stamBarX_A, drawBattle.stamBarY, drawBattle.stamBarW * 2, drawBattle.stamBarH);
		
		// health bar
		// A
    	g.setColor(new Color(drawBattle.healthBarRed, drawBattle.healthBarGreen, drawBattle.healthBarBlue));
    	g.fillRect(drawBattle.healthBarX_A + (drawBattle.healthBarW-drawBattle.healthBarW*drawBattle.battleManager.getPbsA().getCurrHealth()
    			/drawBattle.battleManager.getPbsA().getMaxHealth()), drawBattle.healthBarY, drawBattle.healthBarW*drawBattle.battleManager.getPbsA().getCurrHealth()
    			/drawBattle.battleManager.getPbsA().getMaxHealth(), drawBattle.healthBarH);
    	g.setColor(Color.BLACK);
    	g.drawRect(drawBattle.healthBarX_A, drawBattle.healthBarY, drawBattle.healthBarW, drawBattle.healthBarH);
    	
    	g.drawRect(drawBattle.healthBarX_A + (drawBattle.healthBarW-drawBattle.healthBarW*drawBattle.battleManager.getPbsA().getCurrHealth()
    			/drawBattle.battleManager.getPbsA().getMaxHealth()), drawBattle.healthBarY, drawBattle.healthBarW*drawBattle.battleManager.getPbsA().getCurrHealth()
    			/drawBattle.battleManager.getPbsA().getMaxHealth(), drawBattle.healthBarH);
    	
    	// B
    	g.setColor(new Color(0, drawBattle.healthBarGreen, 0));
    	g.fillRect(drawBattle.healthBarX_B, drawBattle.healthBarY, drawBattle.healthBarW*drawBattle.battleManager.getPbsB().getCurrHealth()
    			/drawBattle.battleManager.getPbsB().getMaxHealth(), drawBattle.healthBarH);
    	g.setColor(Color.BLACK);
    	g.drawRect(drawBattle.healthBarX_B, drawBattle.healthBarY, drawBattle.healthBarW, drawBattle.healthBarH);
    	g.drawRect(drawBattle.healthBarX_B, drawBattle.healthBarY, drawBattle.healthBarW*drawBattle.battleManager.getPbsB().getCurrHealth()
    			/drawBattle.battleManager.getPbsB().getMaxHealth(), drawBattle.healthBarH);
    	
    	// stam bar
    	// A
    	g.setColor(new Color(drawBattle.stamBarRed, drawBattle.stamBarGreen, drawBattle.stamBarBlue));
    	g.fillRect(drawBattle.stamBarX_A + (drawBattle.stamBarW-drawBattle.stamBarW*drawBattle.battleManager.getPbsA().getCurrStamina()
    			/drawBattle.battleManager.getPbsA().getMaxStamina()), drawBattle.stamBarY, drawBattle.stamBarW*drawBattle.battleManager.getPbsA().getCurrStamina()
    			/drawBattle.battleManager.getPbsA().getMaxStamina(), drawBattle.stamBarH);
    	g.setColor(Color.BLACK);
    	g.drawRect(drawBattle.stamBarX_A, drawBattle.stamBarY, drawBattle.stamBarW, drawBattle.stamBarH);
    	g.drawRect(drawBattle.stamBarX_A + (drawBattle.stamBarW-drawBattle.stamBarW*drawBattle.battleManager.getPbsA().getCurrStamina()
    			/drawBattle.battleManager.getPbsA().getMaxStamina()), drawBattle.stamBarY, drawBattle.stamBarW*drawBattle.battleManager.getPbsA().getCurrStamina()
    			/drawBattle.battleManager.getPbsA().getMaxStamina(), drawBattle.stamBarH);
    	
    	// B
    	g.setColor(new Color(drawBattle.stamBarRed, drawBattle.stamBarGreen, drawBattle.stamBarBlue));
    	g.fillRect(drawBattle.stamBarX_B, drawBattle.stamBarY, drawBattle.stamBarW*drawBattle.battleManager.getPbsB().getCurrStamina()/
    			drawBattle.battleManager.getPbsB().getMaxStamina(), drawBattle.stamBarH);
    	g.setColor(Color.BLACK);
    	g.drawRect(drawBattle.stamBarX_B, drawBattle.stamBarY, drawBattle.stamBarW, drawBattle.stamBarH);
    	g.drawRect(drawBattle.stamBarX_B, drawBattle.stamBarY, drawBattle.stamBarW*drawBattle.battleManager.getPbsB().getCurrStamina()/
    			drawBattle.battleManager.getPbsB().getMaxStamina(), drawBattle.stamBarH);
	}
	
}
