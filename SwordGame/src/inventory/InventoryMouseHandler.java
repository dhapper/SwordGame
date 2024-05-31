package inventory;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import battle.graphics.DrawBattle;
import entity.Sword;

public class InventoryMouseHandler implements MouseListener, MouseMotionListener{

	DrawInventory drawInventory;
	
	public InventoryMouseHandler(DrawInventory drawInventory) {
		this.drawInventory = drawInventory;
	}
	
	
	public void itemClicked(int x, int y) {
		
		Inventory inv = drawInventory.player.getInventory();
		
		// swords
		for (int i = 0; i < drawInventory.player.getInventory().getSwords().size(); i++) {
			int xMin = drawInventory.slotIndent * (i + 1) + drawInventory.slotWidth * i;
			int xMax = xMin + drawInventory.slotWidth;
			int yMin = drawInventory.slotSwordY;
			int yMax = yMin + drawInventory.slotHeight;
		
			if(x > xMin && x < xMax)
				if(y > yMin && y < yMax) {
					if(!inv.getSwords().get(i).equals(inv.activeSword) && !inv.getSwords().get(i).equals(inv.inactiveSword)) {
						inv.setInactiveSword(inv.getSword(i));
						drawInventory.repaint();
					}else if(inv.getSwords().get(i).equals(inv.inactiveSword) || inv.getSwords().get(i).equals(inv.activeSword)) {
						inv.swapActiveSword();
						drawInventory.repaint();
					}
				}
						
		}
		
		for (int i = 0; i < drawInventory.player.getInventory().getShields().size(); i++) {
			int xMin = drawInventory.slotIndent * (i + 1) + drawInventory.slotWidth * i;
			int xMax = xMin + drawInventory.slotWidth;
			int yMin = drawInventory.slotShieldY;
			int yMax = yMin + drawInventory.slotHeight;
		
			if(x > xMin && x < xMax)
				if(y > yMin && y < yMax)
					if(!inv.getShields().get(i).equals(inv.activeShield)) {
						inv.setActiveShield(inv.getShields().get(i));
						drawInventory.repaint();
					}
						
		}
		
		for (int i = 0; i < drawInventory.player.getInventory().getArmoury().size(); i++) {
			int xMin = drawInventory.slotIndent * (i + 1) + drawInventory.slotWidth * i;
			int xMax = xMin + drawInventory.slotWidth;
			int yMin = drawInventory.slotArmourY;
			int yMax = yMin + drawInventory.slotHeight;
		
			if(x > xMin && x < xMax)
				if(y > yMin && y < yMax)
					if(!inv.getArmoury().get(i).equals(inv.activeArmour)) {
						inv.setActiveArmour(inv.getArmoury().get(i));
						drawInventory.repaint();
					}
						
		}
	

	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		itemClicked(e.getX(), e.getY());
		
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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}