package main;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import battle.BattleManager;
import entity.Player;

public class MainFrame extends JFrame{

	
	public MainFrame() {
	
		
		int height = 600, width = height*3/2;
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		add(panel);
		
		JLabel label = new JLabel("main frame");
		label.setBounds(300, 300, 100, 100);
		panel.add(label);
		
    	setVisible(true);
    	setTitle("MainFrame");
    	setSize(width+16, height+39);	// idk why +16 and +39
    	setVisible(true);
    	setResizable(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLocationRelativeTo(null);
    	
    	launchMenu();
    	//launchBattle();
    	
	}
	
	public void launchBattle() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.getContentPane().removeAll();
		this.revalidate();
		this.repaint();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		UseMongoDB mongo = new UseMongoDB();
		Player player = mongo.getPlayer("dhapper");
		Player enemy = mongo.getPlayer("test");
		BattleManager battleLogic = new BattleManager(this, player, enemy);
	}
	
	public void launchMenu() {
		DrawMenu drawMenu = new DrawMenu(this);
	}
	
}
