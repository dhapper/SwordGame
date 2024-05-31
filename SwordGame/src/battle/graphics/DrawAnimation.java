package battle.graphics;

import java.awt.Graphics;

public class DrawAnimation implements Runnable{

	DrawBattle drawBattle;
	DrawSwing drawSwing;
	DrawJab drawJab;
	
	
	int moveNum;
	int updateBarsFrameTime = 100;
	
	public DrawAnimation(DrawBattle drawBattle, int moveNum) {
		this.drawBattle = drawBattle;
		this.moveNum = moveNum;
		
		this.drawSwing = new DrawSwing(drawBattle);
    	this.drawJab = new DrawJab(drawBattle);
		
	}
	
	
	//int numFrames = 15;
	
	public void animateMove() {//int moveNum) {
		drawBattle.animating = true;
		drawBattle.frameNum = 1;
		
		int animationFrameTime = 100;
		
		if(moveNum == 1) {
			drawBattle.animatingFirstMove = true;
			drawBattle.animatingSecondMove = false;
			frameLoop(drawBattle.animationSequenceManager.getMoveString(moveNum), animationFrameTime);
			//frameLoop("UPDATE_BARS", updateBarsFrameTime);
		}else if(moveNum == 2) {
			drawBattle.animatingFirstMove = false;
			drawBattle.animatingSecondMove = true;
			frameLoop(drawBattle.animationSequenceManager.getMoveString(moveNum), animationFrameTime);
			//frameLoop("UPDATE_BARS", updateBarsFrameTime);
		}
		
		drawBattle.animating = false;
	}

	public void frameLoop(String move, int frameTime) {
		
		int bufferFrames = 5;
		
		int numFrames = 0;
		if(move.equals("SWING"))
			numFrames = 15;
		else if(move.equals("JAB"))
			numFrames = 10;
		else if(move.equals("BLOCK") || move.equals("CHARGE") || move.equals("SWAP_SWORDS")) {
			numFrames = 12;
			bufferFrames = 0;
		}
		
		
		try {
			for(int i = 0; i < numFrames + bufferFrames; i++) {
				drawBattle.repaint();
				Thread.sleep(frameTime);
				drawBattle.frameNum++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		//animationSequenceManager.getAnimatedHealthBar(battleManager.pbsA, battleManager.pbsA, 1, g);
		
		/*if(move.equals("UPDATE_BARS")) {
			if(moveNum == 1) {
				if(drawBattle.battleManager.pbsA.isFaster())
					drawBattle.battleManager.pbsA.setPrevHealth(drawBattle.battleManager.pbsA.getPrevHealth());
				else
					drawBattle.battleManager.pbsB.setPrevHealth(drawBattle.battleManager.pbsB.getPrevHealth());
			}else {
				if(!drawBattle.battleManager.pbsA.isFaster())
					drawBattle.battleManager.pbsA.setPrevHealth(drawBattle.battleManager.pbsA.getPrevHealth());
				else 
					drawBattle.battleManager.pbsB.setPrevHealth(drawBattle.battleManager.pbsB.getPrevHealth());
			}
			//System.out.println(drawBattle.battleManager.pbsA.getPrevHealth());
		}*/
	}
	
	@Override
	public void run() {
		
		animateMove();
		
		//drawBattle.animatingFirstMove = false;
		//drawBattle.animatingSecondMove = false;
	}
	
	
}
