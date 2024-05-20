import javax.swing.JFrame; 
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class DrawBattle extends JPanel{
    
	int width = 600, height = 600;
	PlayerBattleState A, B;
	
    public DrawBattle(PlayerBattleState A, PlayerBattleState B) {
    	this.A = A;
    	this.B = B;
    	
    	JFrame frame = new JFrame();
    	frame.setTitle("frame title");
    	frame.setSize(width, height);
    	frame.setVisible(true);
    	frame.setResizable(false);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setLocationRelativeTo(null);
    	frame.add(this);
    	
    }
    
    
    
    public void paintComponent(Graphics g){
    	int width = 600, height = 600;
    	int labelOffset = 20;
    	super.paintComponent(g);
    	g.setColor(Color.red);
    	int playerVisualSize = 100;
    	Font labelFont = new Font("SansSerif", Font.BOLD, 14);
    	g.setFont(labelFont);
    	
    	g.drawRect(width/4-playerVisualSize/2, height/2-playerVisualSize/2, playerVisualSize, playerVisualSize);
    	g.drawRect(width*3/4-playerVisualSize/2, height/2-playerVisualSize/2, playerVisualSize, playerVisualSize);
    	g.drawRect(0,  0,  300, 300);
    	g.drawRect(300,  0,  300, 300);
    	g.drawString(A.getName(), width/4-playerVisualSize/2, height/2-playerVisualSize/2);
    	g.drawString(B.getName(), width*3/4-playerVisualSize/2, height/2-playerVisualSize/2);
    	
    	
    	g.drawString("health", labelOffset, 100+15);
    	g.drawRect(width/4-playerVisualSize/2, 100, playerVisualSize, 20);
    	g.fillRect(width/4-playerVisualSize/2, 100, playerVisualSize*A.getCurrHealth()/A.getMaxHealth(), 20);
    	
    	g.drawString("stamina", labelOffset, 130+15);
    	g.drawRect(width/4-playerVisualSize/2, 130, playerVisualSize, 20);
    	g.fillRect(width/4-playerVisualSize/2, 130, playerVisualSize*A.getCurrStamina()/A.getMaxStamina(), 20);
    	
    	g.drawString("block counter: "+A.getBlockCounter(), labelOffset, 160+15);
    	g.drawString("Charge factor: "+A.getCurrCharge(), labelOffset, 190+15);
    
    	g.drawString("health", labelOffset+width/2, 100+15);
    	g.drawRect(width*3/4-playerVisualSize/2, 100, playerVisualSize, 20);
    	g.fillRect(width*3/4-playerVisualSize/2, 100, playerVisualSize*B.getCurrHealth()/B.getMaxHealth(), 20);
    	
    	g.drawString("stamina", labelOffset+width/2, 130+15);
    	g.drawRect(width*3/4-playerVisualSize/2, 130, playerVisualSize, 20);
    	g.fillRect(width*3/4-playerVisualSize/2, 130, playerVisualSize*B.getCurrStamina()/B.getMaxStamina(), 20);
    	
    	g.drawString("block counter: "+B.getBlockCounter(), labelOffset+width/2, 160+15);
    	g.drawString("Charge factor: "+B.getCurrCharge(), labelOffset+width/2, 190+15);
    	
    	g.drawString("Pick "+A.getName()+"'s move", width/2-100, height*3/4-50);
    	
    	g.fillRect(width/9, height*7/10, width/9, height/10);
    	g.fillRect(width*3/9, height*7/10, width/9, height/10);
    	g.fillRect(width*5/9, height*7/10, width/9, height/10);
    	g.fillRect(width*7/9, height*7/10, width/9, height/10);
    	g.setColor(Color.white);
    	g.drawString("SWING", width/9, height*15/20);
    	g.drawString("JAB", width*3/9, height*15/20);
    	g.drawString("BLOCK", width*5/9, height*15/20);
    	g.drawString("CHARGE", width*7/9, height*15/20);
    }
   
}
