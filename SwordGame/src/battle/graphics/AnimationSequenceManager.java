package battle.graphics;

import java.awt.Graphics;

import battle.PlayerBattleState;

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
			if(drawBattle.battleManager.getPbsA().isFaster())
				return drawBattle.battleManager.getPbsA().getCurrMove();
			else
				return drawBattle.battleManager.getPbsB().getCurrMove();
		}else if(moveNum == 2) {
			if(!drawBattle.battleManager.getPbsA().isFaster())
				return drawBattle.battleManager.getPbsA().getCurrMove();
			else
				return drawBattle.battleManager.getPbsB().getCurrMove();
		}
		return null;
	}
	
	public void getFirstMove(Graphics g) {
		if(drawBattle.battleManager.getPbsA().isFaster()) {
			drawMoveAnimation(drawBattle.battleManager.getPbsA(), drawBattle.battleManager.getPbsB(), g);
		}else {
			drawMoveAnimation(drawBattle.battleManager.getPbsB(), drawBattle.battleManager.getPbsA(), g);
		}
	}
	
	public void getSecondMove(Graphics g) {
		if(!drawBattle.battleManager.getPbsA().isFaster()) {
			drawMoveAnimation(drawBattle.battleManager.getPbsA(), drawBattle.battleManager.getPbsB(), g);
		}else {
			drawMoveAnimation(drawBattle.battleManager.getPbsB(), drawBattle.battleManager.getPbsA(), g);
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
