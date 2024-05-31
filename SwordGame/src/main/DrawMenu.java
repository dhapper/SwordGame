package main;
import java.awt.Color;  
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import battle.BattleManager;
import entity.Player;
import inventory.DrawInventory;
import utilz.UseMongoDB;

public class DrawMenu extends JPanel{
	
	JFrame frame;
	
	public DrawMenu(JFrame frame) {
		
		this.frame = frame;
		this.frame.getContentPane().removeAll();
		this.frame.revalidate();
		this.frame.add(this);
		this.frame.setVisible(true);
		this.frame.repaint();
		setLayout(null);
		
		JLabel menuLabel = new JLabel("draw menu");
		menuLabel.setBounds(200, 100, 100, 100);
		menuLabel.setVisible(true);
		add(menuLabel);
		
		
		
		
		JButton battleButton = new JButton("battle");
		battleButton.setBounds(100, 100, 100, 100);
		add(battleButton);
		
		battleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	
            	
            	frame.getContentPane().removeAll();
            	frame.revalidate();
            	frame.repaint();
                
                UseMongoDB mongo = new UseMongoDB();
        		Player player = mongo.getPlayer("dhapper");
        		Player enemy = mongo.getPlayer("test");
        		BattleManager bl = new BattleManager(frame, player, enemy);
            }
        });
		
		
		JButton inventoryButton = new JButton("inv");
		inventoryButton.setBounds(300, 100, 100, 100);
		add(inventoryButton);
		
		inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	frame.getContentPane().removeAll();
            	frame.revalidate();
            	frame.repaint();
            	
            	UseMongoDB mongo = new UseMongoDB();
        		Player player = mongo.getPlayer("dhapper");
            	DrawInventory drawInventory = new DrawInventory(player, frame);
            }
        });
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.drawRect(0, 0, 300, 300);
		
	}
}
