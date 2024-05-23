import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class testDraw extends JPanel{

	JFrame frame;
	boolean flag;
	
	public testDraw(JFrame frame){
		
		this.frame = frame;
		this.frame.getContentPane().removeAll();
		this.frame.revalidate();
		this.frame.add(this);
		this.frame.setVisible(true);
		this.frame.repaint();
		setLayout(null);
		
		JButton button = new JButton("testDraw");
		button.setBounds(300, 100, 100, 100);
		add(button);
		
		flag = true;
		
		button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	
            	
            	//frame.getContentPane().removeAll();
            	//frame.revalidate();
            	//frame.repaint();
                
            	
            	flag = false;
             
            }
        });
		
		startBackgroundProcess();
	}
	
	private void startBackgroundProcess() {
		Thread backgroundThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					System.out.println(flag);
					if (!flag) {
						System.out.println("clickkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
						
						flag = true;
					}
					try {
						// Sleep for a short period to avoid busy-waiting
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		backgroundThread.setDaemon(true);
		backgroundThread.start();
	}
	
	public void asd() {
		transitionBuffer t = new transitionBuffer(this.frame);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillRect(300, 0, 300, 300);
		
	}
}
