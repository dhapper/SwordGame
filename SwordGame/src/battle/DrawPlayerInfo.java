package battle;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DrawPlayerInfo {
	
	public DrawPlayerInfo(DrawBattle drawBattle, PlayerBattleState player, int x, Graphics g) {
		
		int X_min = x;
		int X_max = x + drawBattle.getWidth()/4;
		int Y_min = drawBattle.getHeight()/4;
		int Y_max = drawBattle.getHeight()*3/4;
		
		int xStringOffset = X_min + drawBattle.getWidth()/30;
		int yStringOffset = drawBattle.getHeight()/20;
		int stringY = Y_min + drawBattle.getHeight()/24;
		
		g.setFont(null);
		
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(X_min, Y_min, X_max - X_min, (Y_max - Y_min)*3/4);

    	
    	g.setFont(new Font("Ariel", Font.BOLD, 14));
		
		g.setColor(new Color(200, 200, 0));
		g.drawString("Health: "+player.getCurrHealth()+"/"+player.getMaxHealth(), xStringOffset, stringY);
		g.drawString("Strength: "+player.getCurrStrength(), xStringOffset, stringY+yStringOffset);
		g.drawString("Speed: "+player.getCurrSpeed(), xStringOffset, stringY+yStringOffset*2);
		g.drawString("Stamina: "+player.getCurrStamina()+"/"+player.getMaxStamina(), xStringOffset, stringY+yStringOffset*3);
		g.drawString("Knowledge: "+player.getCurrKnowledge(), xStringOffset, stringY+yStringOffset*4);
		g.drawString("Sword Durability: "+player.getActiveSwordDurability()+"/"+player.getMaxActiveSwordDurability(), xStringOffset, stringY+yStringOffset*5);
		g.drawString("Shield Durability: "+player.getActiveShieldDurability()+"/"+player.getMaxActiveShieldDurability(), xStringOffset, stringY+yStringOffset*6);
		
    	g.setFont(new Font("Ariel", Font.BOLD, 20));
	}

}
