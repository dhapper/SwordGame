package battle.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import battle.PlayerBattleState;

public class DrawCharge {

	DrawBattle drawBattle;
	
	public DrawCharge(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
	}
	
	public void drawFrame(PlayerBattleState attacker, PlayerBattleState defender, Graphics g) {
		
		int adjustX = drawBattle.battleWidth/5 - drawBattle.battleWidth/150;
		int adjustY = drawBattle.battleHeight/5 + drawBattle.battleHeight/10 - drawBattle.battleHeight/100;
		int adjustAngle = 85;
		
		int movement = drawBattle.battleWidth/50;
		
		int chargeLen = 6;
		int chargeStart = 1;
		int chargeEnd = chargeStart + chargeLen;
		
		
		if(defender.isFaster() && defender.getCurrMove().equals("BLOCK"))
			drawBattle.drawBattleCharacter.drawStaticBlockingPose(defender, drawBattle.blockPoseDecreaseX, g);
		else if(defender.isFaster() && defender.getCurrMove().equals("CHARGE"))
			drawBattle.drawBattleCharacter.drawStaticChargingPose(defender, drawBattle.blockPoseDecreaseX, g);
		else if(defender.isFaster() && defender.getCurrMove().equals("SWAP_SWORDS"))
			drawBattle.drawBattleCharacter.drawStaticDefaultPoseWithSpecificSword(defender, defender.getInactiveSword().getName(), g);
		else
			drawBattle.drawBattleCharacter.drawStaticDefaultPose(defender, g);
		
		
		
		drawBattle.drawBattleCharacter.drawStaticChargingPose(attacker, drawBattle.blockPoseDecreaseX, g);

		drawBattle.drawBars.drawIncreasedStamBar(attacker, defender, drawBattle.frameNum, 0, chargeEnd + drawBattle.bufferFrames, g);
		
		if(drawBattle.frameNum == chargeEnd)
			attacker.setPrevStam(attacker.getCurrStamina());
		
	}
}
