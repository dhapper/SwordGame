import java.awt.Color;
import java.awt.Graphics;

public class DrawPlayerInfo {
	
	public DrawPlayerInfo(DrawBattle drawBattle, PlayerBattleState player, int x, int y, Graphics g) {
		
		int bgW = drawBattle.getWidth()/4;
		int bgH = drawBattle.getHeight()/4;
		int xBgOffset = x + drawBattle.getWidth()/24;
		int xStringOffset = x + drawBattle.getWidth()/24 + drawBattle.getWidth()/25;
		int yStringOffset = drawBattle.getHeight()/25;
		int stringY = y + drawBattle.getHeight()/20;
		
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(xBgOffset, y, bgW, bgH);
		
		g.setColor(new Color(200, 200, 0));
		g.drawString("Health: "+player.getCurrHealth()+"/"+player.getMaxHealth(), xStringOffset, stringY);
		g.drawString("Strength: "+player.getCurrStrength(), xStringOffset, stringY+yStringOffset);
		g.drawString("Speed: "+player.getCurrSpeed(), xStringOffset, stringY+yStringOffset*2);
		g.drawString("Stamina: "+player.getCurrStamina()+"/"+player.getMaxStamina(), xStringOffset, stringY+yStringOffset*3);
		g.drawString("Knowledge: "+player.getCurrKnowledge(), xStringOffset, stringY+yStringOffset*4);
	}

}
