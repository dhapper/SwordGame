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
		if(x >= drawBattle.getAx() && x <= drawBattle.getAw() + drawBattle.getAx()) {
			if(y >= drawBattle.getAy() && y <= drawBattle.getAh() + drawBattle.getAy())
				this.hovered = "A";
		}else if(x >= drawBattle.getBx() && x <= drawBattle.getAw() + drawBattle.getBx()) {
			if(y >= drawBattle.getAy() && y <= drawBattle.getAh() + drawBattle.getAy())
				this.hovered = "B";
		}
		
		drawBattle.repaint();
		
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
