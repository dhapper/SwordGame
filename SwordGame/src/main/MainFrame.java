package main;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.StyleConstants.FontConstants;

import battle.BattleManager;
import entity.Player;
import utilz.Constants;
import utilz.UseMongoDB;

public class MainFrame extends JFrame{

	
	int height = 600, width = height*3/2;
	int logWidth = width/3;
	int screenWidth = width + logWidth;
	
	public MainFrame() {
	
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		add(panel);
		
		JLabel label = new JLabel("main frame");
		label.setBounds(300, 300, 100, 100);
		panel.add(label);
		
    	setVisible(true);
    	setTitle("MainFrame");
    	setSize(Constants.FRAME_WIDTH + Constants.FRAME_WIDTH_EXTRA, Constants.FRAME_HEIGHT + Constants.FRAME_HEIGHT_EXTRA);	// idk why +16 and +39
    	setVisible(true);
    	setResizable(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLocationRelativeTo(null);
    	
    	launchMenu();
    	//launchBattle();
    	
	}
	
	public void launchBattle() {
		
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.getContentPane().removeAll();
		this.revalidate();
		this.repaint();
		
		UseMongoDB mongo = new UseMongoDB();
		Player player = mongo.getPlayer("dhapper");
		Player enemy = mongo.getPlayer("test");
		BattleManager battleLogic = new BattleManager(this, player, enemy);
	}
	
	public void launchMenu() {
		DrawMenu drawMenu = new DrawMenu(this);
	}
	
	public int getLogWidth() {
		return logWidth;
	}
	
}
