import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener{

	DrawBattle drawBattle;
	int width, height;
	String nextMove;
	
	String hovered = "NONE";
	
	///DrawBattle drawBattle
	public MouseHandler(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
	}
	
	public void playerHovered(int x, int y) {
		this.hovered = "NONE";
		
		int AX_min = 0;
		int AX_max = drawBattle.getWidth()/4;
		int Y_min = drawBattle.getHeight()/4;
		int Y_max = drawBattle.getHeight()*3/4;
		int BX_min = drawBattle.getWidth()*3/4;
		int BX_max = drawBattle.getWidth();
		
		if(x >= AX_min && x <= AX_max) {
			if(y >= Y_min && y <= Y_max)
				this.hovered = "A";
		}else if(x >= BX_min && x <= BX_max) {
			if(y >= Y_min && y <= Y_max)
				this.hovered = "B";
		}
		
		this.drawBattle.repaint();
		
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
		hovered = "NONE";
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

	public String getHovered() {
		return this.hovered;
	}
	
	
}
