package battle;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class DrawText {
	
	DrawBattle drawBattle;
	
	public DrawText(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
	}
	
	public void paint(Graphics g) {
		// display name, might be redundant
    	g.setColor(new Color(255, 255, 255, 75));
    	FontMetrics metrics = g.getFontMetrics(g.getFont());
    	int textBgIndent = drawBattle.width/60;
    	if(drawBattle.battleManager.isTurnA()) {
    		int textWidth = metrics.stringWidth("Pick "+drawBattle.battleManager.getPbsA().getName()+"'s move");
    		int startX = drawBattle.width/2 - (textWidth / 2);
    		g.fillRect(startX-textBgIndent, drawBattle.height*63/96, textWidth+2*textBgIndent, drawBattle.height/15);
        	g.setColor(Color.BLACK);
    		g.drawString("Pick "+drawBattle.battleManager.getPbsA().getName()+"'s move", startX, drawBattle.height/2+drawBattle.height/5-drawBattle.height/200);
    	
    	} else {
    		int textWidth = metrics.stringWidth("Pick "+drawBattle.battleManager.getPbsB().getName()+"'s move");
    		int startX = drawBattle.width/2 - (textWidth / 2);
    		g.fillRect(startX-textBgIndent, drawBattle.height*63/96, textWidth+2*textBgIndent, drawBattle.height/15);
        	g.setColor(Color.BLACK);
        	g.drawString("Pick "+drawBattle.battleManager.getPbsB().getName()+"'s move", startX, drawBattle.height/2+drawBattle.height/5-drawBattle.height/200);
    	}
	}
}
