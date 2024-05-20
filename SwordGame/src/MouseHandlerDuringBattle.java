import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandlerDuringBattle implements MouseListener, MouseMotionListener{

	DrawBattle drawBattle;
	
	public MouseHandlerDuringBattle(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
	}
	
	public void handleMouseClick(int x, int y) {
		if (x >= button1X && x <= button1X + buttonWidth && y >= buttonY && y <= buttonY + buttonHeight) {
            System.out.println("Button 1 clicked!");
            // Add your action for Button 1 here
        }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
        int y = e.getY();
		
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
		// TODO Auto-generated method stub
		
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

}
