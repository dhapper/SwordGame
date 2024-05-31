package playercustomization;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.Player;
import main.DrawPlayerGraphics;

public class DrawPlayerCustomizer extends JPanel{

	JFrame frame;
	Player player;
	
	public DrawPlayerCustomizer(Player player, JFrame frame) {
		
		this.frame = frame;
		this.frame.getContentPane().removeAll();
		this.frame.revalidate();
		this.frame.add(this);
		this.frame.setVisible(true);
		this.frame.repaint();
		setLayout(null);
		
		this.player = player;
		
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//g.fillRect(100, 100, 400, 10);
		
		DrawPlayerGraphics dp = new DrawPlayerGraphics();
		int scale = 2200;
		dp.drawPlayer(player, "A", 0, -scale/3, -15, scale, g);
		
		g.drawRect(700, 100, 300, 100);
		g.drawRect(700, 250, 300, 100);
		g.drawRect(700, 400, 300, 100);
	}
}
