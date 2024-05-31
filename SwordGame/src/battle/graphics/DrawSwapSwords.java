package battle.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import battle.PlayerBattleState;

public class DrawSwapSwords {

	DrawBattle drawBattle;
	
	public DrawSwapSwords(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
	}
	
	public void drawFrame(PlayerBattleState attacker, PlayerBattleState defender, Graphics g) {
		
		int adjustX = drawBattle.battleWidth/5 - drawBattle.battleWidth/150;
		int adjustY = drawBattle.battleHeight/5 + drawBattle.battleHeight/10 - drawBattle.battleHeight/100;
		int adjustAngle = 85;
		
		int movement = drawBattle.battleWidth/50;
		
		int swapLen = 6;
		int swapStart = 1;
		int swapEnd = swapStart + swapLen;
		
		
		if(defender.isFaster() && defender.getCurrMove().equals("BLOCK"))
			drawBattle.drawBattleCharacter.drawStaticBlockingPose(defender, drawBattle.blockPoseDecreaseX, g);
		else if(defender.isFaster() && defender.getCurrMove().equals("CHARGE"))
			drawBattle.drawBattleCharacter.drawStaticChargingPose(defender, drawBattle.blockPoseDecreaseX, g);
		else
			drawBattle.drawBattleCharacter.drawStaticDefaultPose(defender, g);
		
		
		
		drawBattle.drawBattleCharacter.paintPlayer(attacker, 0, 0, g);
		drawBattle.drawBattleCharacter.paintArmour(attacker, 0, 0, g);
		drawBattle.drawBattleCharacter.paintShield(attacker, 0, 0, g);
		
		if(drawBattle.frameNum <= swapEnd)
			for(int i = swapStart; i <= swapEnd; i++) {
				if(drawBattle.frameNum == i) {
					drawBattle.drawBattleCharacter.paintSpecificSword(attacker, attacker.getActiveSword().getName(), 0, (i - swapEnd) * 50, drawBattle.swordAngle, g);
				}
			}
		
		if(drawBattle.frameNum > swapEnd)
			drawBattle.drawBattleCharacter.drawStaticDefaultPose(attacker, g);
		
		
		drawBattle.drawBars.drawIncreasedStamBar(attacker, defender, drawBattle.frameNum, 0, swapEnd + drawBattle.bufferFrames, g);
		
		if(drawBattle.frameNum == swapEnd)
			attacker.setPrevStam(attacker.getCurrStamina());
	}
}
