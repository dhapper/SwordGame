package battle;

import java.awt.Graphics;

public class DrawAnimation implements Runnable{

	DrawBattle drawBattle;
	DrawSwing drawSwing;
	DrawJab drawJab;
	
	public DrawAnimation(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
		
		this.drawSwing = new DrawSwing(drawBattle);
    	this.drawJab = new DrawJab(drawBattle);
		
	}
	
	
	//int numFrames = 15;
	
	public void animateMove(int moveNum) {
		drawBattle.animating = true;
		drawBattle.frameNum = 1;
		
		if(moveNum == 1) {
			drawBattle.animatingFirstMove = true;
			drawBattle.animatingSecondMove = false;
			frameLoop(drawBattle.animationSequenceManager.getMoveString(moveNum));
		}else if(moveNum == 2) {
			drawBattle.animatingFirstMove = false;
			drawBattle.animatingSecondMove = true;
			frameLoop(drawBattle.animationSequenceManager.getMoveString(moveNum));
		}
		
		drawBattle.animating = false;
	}

	public void frameLoop(String move) {
		
		int numFrames = 0;
		if(move.equals("SWING"))
			numFrames = 15;
		else if(move.equals("JAB"))
			numFrames = 7;
		else if(move.equals("BLOCK") || move.equals("CHARGE") || move.equals("SWAP_SWORDS"))
			numFrames = 7;
		
		try {
			for(int i = 0; i < numFrames; i++) {
				drawBattle.repaint();
				Thread.sleep(100);
				drawBattle.frameNum++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		animateMove(1);
		animateMove(2);
		drawBattle.animatingFirstMove = false;
		drawBattle.animatingSecondMove = false;
	}
	
	
}
