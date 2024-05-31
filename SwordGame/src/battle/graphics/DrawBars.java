package battle.graphics;

import java.awt.Color;
import java.awt.Graphics;

import battle.PlayerBattleState;

public class DrawBars {

	DrawBattle drawBattle;
	
	public DrawBars(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
		
	}
	
	public void paint(Graphics g) {
		
		drawBarBg(g);
		
		drawHealthBar(drawBattle.battleManager.getPbsA(), "PREV", drawBattle.GreenHealthBar, g);
		drawHealthBar(drawBattle.battleManager.getPbsB(), "PREV", drawBattle.GreenHealthBar, g);
		
		drawStamBar(drawBattle.battleManager.getPbsA(), "PREV", drawBattle.BlueStaminaBar, g);
		drawStamBar(drawBattle.battleManager.getPbsB(), "PREV", drawBattle.BlueStaminaBar, g);
		
	}
	
	public void drawBarBg(Graphics g) {
		Color bg = new Color(0, 0, 0, 50);
		g.setColor(bg);
		g.fillRect(drawBattle.healthBarX_A, drawBattle.healthBarY, drawBattle.healthBarW * 2, drawBattle.healthBarH);
		g.fillRect(drawBattle.stamBarX_A, drawBattle.stamBarY, drawBattle.stamBarW * 2, drawBattle.stamBarH);
	}
	
	public void drawHealthBar(PlayerBattleState player, String version, Color color, Graphics g) {
		
		int health = 0;
		if(version.equals("CURR"))
			health = player.getCurrHealth();
		else if(version.equals("PREV"))
			health = player.getPrevHealth();
		
		g.setColor(color);
		if(player.getPlayerPos().equals("A")) {
	    	g.fillRect(drawBattle.healthBarX_A + (drawBattle.healthBarW - drawBattle.healthBarW * health
	    			/drawBattle.battleManager.getPbsA().getMaxHealth()), drawBattle.healthBarY, drawBattle.healthBarW * health
	    			/drawBattle.battleManager.getPbsA().getMaxHealth(), drawBattle.healthBarH);
	    	
	    	g.setColor(Color.BLACK);
	    	g.drawRect(drawBattle.healthBarX_A, drawBattle.healthBarY, drawBattle.healthBarW, drawBattle.healthBarH);
	    	g.drawRect(drawBattle.healthBarX_A + (drawBattle.healthBarW - drawBattle.healthBarW * health
	    			/drawBattle.battleManager.getPbsA().getMaxHealth()), drawBattle.healthBarY, drawBattle.healthBarW * health
	    			/drawBattle.battleManager.getPbsA().getMaxHealth(), drawBattle.healthBarH);
		}else {
	    	g.fillRect(drawBattle.healthBarX_B, drawBattle.healthBarY, drawBattle.healthBarW * health
	    			/drawBattle.battleManager.getPbsB().getMaxHealth(), drawBattle.healthBarH);
	    	
	    	g.setColor(Color.BLACK);
	    	g.drawRect(drawBattle.healthBarX_B, drawBattle.healthBarY, drawBattle.healthBarW, drawBattle.healthBarH);
	    	g.drawRect(drawBattle.healthBarX_B, drawBattle.healthBarY, drawBattle.healthBarW * health
	    			/drawBattle.battleManager.getPbsB().getMaxHealth(), drawBattle.healthBarH);
		}
	}
	
	public void drawStamBar(PlayerBattleState player, String version, Color color, Graphics g) {
		
		int stamina = 0;
		if(version.equals("CURR"))
			stamina = player.getCurrStamina();
		else if(version.equals("PREV"))
			stamina = player.getPrevStam();
		
		g.setColor(color);
		if(player.getPlayerPos().equals("A")) {
	    	g.fillRect(drawBattle.stamBarX_A + (drawBattle.stamBarW - drawBattle.stamBarW * stamina
	    			/ drawBattle.battleManager.getPbsA().getMaxStamina()), drawBattle.stamBarY, drawBattle.stamBarW * stamina
	    			/ drawBattle.battleManager.getPbsA().getMaxStamina(), drawBattle.stamBarH);
	    	g.setColor(Color.BLACK);
	    	g.drawRect(drawBattle.stamBarX_A, drawBattle.stamBarY, drawBattle.stamBarW, drawBattle.stamBarH);
	    	g.drawRect(drawBattle.stamBarX_A + (drawBattle.stamBarW - drawBattle.stamBarW * stamina
	    			/ drawBattle.battleManager.getPbsA().getMaxStamina()), drawBattle.stamBarY, drawBattle.stamBarW * stamina
	    			/ drawBattle.battleManager.getPbsA().getMaxStamina(), drawBattle.stamBarH);
		}else {
	    	g.fillRect(drawBattle.stamBarX_B, drawBattle.stamBarY, drawBattle.stamBarW * stamina
	    			/ drawBattle.battleManager.getPbsB().getMaxStamina(), drawBattle.stamBarH);
	    	g.setColor(Color.BLACK);
	    	g.drawRect(drawBattle.stamBarX_B, drawBattle.stamBarY, drawBattle.stamBarW, drawBattle.stamBarH);
	    	g.drawRect(drawBattle.stamBarX_B, drawBattle.stamBarY, drawBattle.stamBarW * stamina
	    			/ drawBattle.battleManager.getPbsB().getMaxStamina(), drawBattle.stamBarH);
		}
	}
	
	public void drawUpdatedBars(PlayerBattleState attacker, PlayerBattleState defender, int frameNum, int start, int end, Graphics g) {
		
		drawBattle.drawBars.drawBarBg(g);
		
		if(drawBattle.frameNum <= start - 2) {
			drawBattle.drawBars.drawHealthBar(defender, "PREV", drawBattle.GreenHealthBar, g);
			drawBattle.drawBars.drawStamBar(attacker, "PREV", drawBattle.BlueStaminaBar, g);
		}else if(drawBattle.frameNum > start - 2) {
			drawBattle.drawBars.drawHealthBar(defender, "PREV", drawBattle.OrangeBar, g);
			drawBattle.drawBars.drawHealthBar(defender, "CURR", drawBattle.GreenHealthBar, g);
			
			drawBattle.drawBars.drawStamBar(attacker, "PREV", drawBattle.OrangeBar, g);
			drawBattle.drawBars.drawStamBar(attacker, "CURR", drawBattle.BlueStaminaBar, g);
		}
		
		if(!attacker.isFaster()) {
			drawBattle.drawBars.drawHealthBar(attacker, "CURR", drawBattle.GreenHealthBar, g);
			drawBattle.drawBars.drawStamBar(defender, "CURR", drawBattle.BlueStaminaBar, g);
		}else {
			drawBattle.drawBars.drawHealthBar(attacker, "PREV", drawBattle.GreenHealthBar, g);
			drawBattle.drawBars.drawStamBar(defender, "PREV", drawBattle.BlueStaminaBar, g);
		}
		
		
		int buffer = 0;
		if(attacker.getCurrMove().equals("JAB"))
			buffer = 3;
		
		if(drawBattle.frameNum == end + buffer) {
			defender.setPrevHealth(defender.getCurrHealth());
			attacker.setPrevStam(attacker.getCurrStamina());
		}
		
		if(drawBattle.frameNum > end) {
			drawBattle.drawBattleCharacter.drawStaticDefaultPose(attacker, g);
		}
	}
	
	public void drawIncreasedStamBar(PlayerBattleState attacker, PlayerBattleState defender, int frameNum, int start, int end, Graphics g) {
		
		drawBattle.drawBars.drawBarBg(g);
		
		drawBattle.drawBars.drawStamBar(attacker, "CURR", drawBattle.PinkBar, g);
		drawBattle.drawBars.drawStamBar(attacker, "PREV", drawBattle.BlueStaminaBar, g);
		
		drawBattle.drawBars.drawHealthBar(attacker, "PREV", drawBattle.GreenHealthBar, g);
		
		drawBattle.drawBars.drawHealthBar(defender, "PREV", drawBattle.GreenHealthBar, g);
		drawBattle.drawBars.drawStamBar(defender, "CURR", drawBattle.BlueStaminaBar, g);
	}
	
}
