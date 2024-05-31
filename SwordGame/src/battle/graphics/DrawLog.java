package battle.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


public class DrawLog{

	DrawBattle drawBattle;
	ArrayList<String> prevTurnMessages;
	
	public DrawLog(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
		
	}
	
	public void paint(ArrayList<String> turnMessage, Graphics g) {
		
		try {
			Image img = ImageIO.read(new File("res/background/background - log.png"));
			g.drawImage(img, drawBattle.battleWidth, 0, drawBattle.logWidth, drawBattle.battleHeight, null);
			g.setColor(new Color(0, 0, 0, 100));
			g.fillRect(drawBattle.battleWidth, 0, drawBattle.logWidth, drawBattle.battleHeight);
    	} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		int xx = 15;
		
		Font labelFont = new Font("Ariel", Font.BOLD, xx);
    	g.setFont(labelFont);
		FontMetrics fontMetrics = g.getFontMetrics();
		
		g.setColor(new Color(235, 220, 120));
		int lineHeight = fontMetrics.getHeight();
		int lineIndent = 30;
		int x = drawBattle.battleWidth + lineIndent;
		int y = 80;
		int textWidth = drawBattle.logWidth - 2 * lineIndent;
		
		if(turnMessage != null) {
			for(int i = 0; i < turnMessage.size(); i++) {
				
				if(i == 0)
					g.setFont(new Font("Ariel", Font.BOLD, 30));
				else
					g.setFont(new Font("Ariel", Font.BOLD, xx));
				
				List<String> lines = getLines(turnMessage.get(i), fontMetrics, textWidth);
				for(String line : lines) {
					
					if(line.charAt(0) == '+')
						g.setColor(Color.GREEN);
					else if(line.charAt(0) == '-')
						g.setColor(Color.RED);
					
					g.drawString(line, x, y);
					y += lineHeight;
					g.setColor(new Color(235, 220, 120));
				}
				y += 10;
			}
		}
		
		this.prevTurnMessages = turnMessage;
		//System.out.println(prevTurnMessages);
		g.setFont(new Font("Ariel", Font.BOLD, 20));
	}
	
	
	private List<String> getLines(String text, FontMetrics fm, int maxWidth) {
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            String testLine = currentLine + word + " ";
            if (fm.stringWidth(testLine) > maxWidth) {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word + " ");
            } else {
                currentLine.append(word).append(" ");
            }
        }
        lines.add(currentLine.toString());
        return lines;
    }
	
}
