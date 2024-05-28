package battle;

import java.awt.Graphics;

public class AnimationSequenceManager {

	DrawBattle drawBattle;
	DrawSwing drawSwing;
	DrawJab drawJab;
	DrawBlock drawBlock;
	DrawCharge drawCharge;
	DrawSwapSwords drawSwapSwords;
	
	public AnimationSequenceManager(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
		
		this.drawSwing = new DrawSwing(drawBattle);
    	this.drawJab = new DrawJab(drawBattle);
    	this.drawBlock = new DrawBlock(drawBattle);
    	this.drawCharge = new DrawCharge(drawBattle);
    	this.drawSwapSwords = new DrawSwapSwords(drawBattle);
	}
	
	public String getMoveString(int moveNum) {
		if(moveNum == 1) {
			if(drawBattle.battleManager.pbsA.isFaster())
				return drawBattle.battleManager.pbsA.getCurrMove();
			else
				return drawBattle.battleManager.pbsB.getCurrMove();
		}else if(moveNum == 2) {
			if(!drawBattle.battleManager.pbsA.isFaster())
				return drawBattle.battleManager.pbsA.getCurrMove();
			else
				return drawBattle.battleManager.pbsB.getCurrMove();
		}
		return null;
	}
	
	public void getFirstMove(Graphics g) {
		if(drawBattle.battleManager.pbsA.isFaster()) {
			drawMoveAnimation(drawBattle.battleManager.pbsA, drawBattle.battleManager.pbsB, g);
		}else {
			drawMoveAnimation(drawBattle.battleManager.pbsB, drawBattle.battleManager.pbsA, g);
		}
	}
	
	public void getSecondMove(Graphics g) {
		if(!drawBattle.battleManager.pbsA.isFaster()) {
			drawMoveAnimation(drawBattle.battleManager.pbsA, drawBattle.battleManager.pbsB, g);
		}else {
			drawMoveAnimation(drawBattle.battleManager.pbsB, drawBattle.battleManager.pbsA, g);
		}
	}
	
	public void drawMoveAnimation(PlayerBattleState attacker, PlayerBattleState defender, Graphics g) {
		if(attacker.getCurrMove().equals("SWING")) {
			drawSwing.drawFrame(attacker, defender, g);
		}else if(attacker.getCurrMove().equals("JAB")) {
			drawJab.drawFrame(attacker, defender, g);
		}else if(attacker.getCurrMove().equals("BLOCK")) {
			drawBlock.drawFrame(attacker, defender, g);
		}else if(attacker.getCurrMove().equals("CHARGE")) {
			drawCharge.drawFrame(attacker, defender, g);
		}else if(attacker.getCurrMove().equals("SWAP_SWORDS")) {
			drawSwapSwords.drawFrame(attacker, defender, g);
		}
	}
}
