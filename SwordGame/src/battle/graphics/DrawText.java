package battle.graphics;

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
    	int textBgIndent = drawBattle.battleWidth/60;
    	if(drawBattle.battleManager.isTurnA()) {
    		int textWidth = metrics.stringWidth("Pick "+drawBattle.battleManager.getPbsA().getName()+"'s move");
    		int startX = drawBattle.battleWidth/2 - (textWidth / 2);
    		g.fillRect(startX-textBgIndent, drawBattle.battleHeight*63/96, textWidth+2*textBgIndent, drawBattle.battleHeight/15);
        	g.setColor(Color.BLACK);
    		g.drawString("Pick "+drawBattle.battleManager.getPbsA().getName()+"'s move", startX, drawBattle.battleHeight/2+drawBattle.battleHeight/5-drawBattle.battleHeight/200);
    	
    	} else {
    		int textWidth = metrics.stringWidth("Pick "+drawBattle.battleManager.getPbsB().getName()+"'s move");
    		int startX = drawBattle.battleWidth/2 - (textWidth / 2);
    		g.fillRect(startX-textBgIndent, drawBattle.battleHeight*63/96, textWidth+2*textBgIndent, drawBattle.battleHeight/15);
        	g.setColor(Color.BLACK);
        	g.drawString("Pick "+drawBattle.battleManager.getPbsB().getName()+"'s move", startX, drawBattle.battleHeight/2+drawBattle.battleHeight/5-drawBattle.battleHeight/200);
    	}
	}
}
