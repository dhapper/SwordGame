package battle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener{

	DrawBattle drawBattle;
	String playerHovered = "NONE";
	boolean swapSwordsHovered = false;
	
	///DrawBattle drawBattle
	public MouseHandler(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
	}
	
	
	public void playerHovered(int x, int y) {
		this.playerHovered = "NONE";
		
		int AX_min = 0;
		int AX_max = drawBattle.width/4;
		int BX_min = drawBattle.width*3/4;
		int BX_max = drawBattle.width;
		int Y_min = drawBattle.height/4;
		int Y_max = drawBattle.height*3/4;

		if(x >= BX_min && x <= BX_max) {
			if(y >= Y_min && y <= Y_max) {
				this.playerHovered = "B";
				this.drawBattle.repaint();
			}
		}else if(x >= AX_min && x <= AX_max) {
			if(y >= Y_min && y <= Y_max) {
				this.playerHovered = "A";
				this.drawBattle.repaint();
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		playerHovered(e.getX(), e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public String getPlayerHovered() {
		return this.playerHovered;
	}
	
	public boolean getSwapSwordsHovered() {
		return this.swapSwordsHovered;
	}
	
	
}
