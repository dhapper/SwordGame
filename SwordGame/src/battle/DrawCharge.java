package battle;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DrawCharge {

	DrawBattle drawBattle;
	
	public DrawCharge(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
	}
	
	public void drawFrame(PlayerBattleState attacker, PlayerBattleState defender, Graphics g) {
		
		int adjustX = drawBattle.width/5 - drawBattle.width/150;
		int adjustY = drawBattle.height/5 + drawBattle.height/10 - drawBattle.height/100;
		int adjustAngle = 85;
		
		int movement = drawBattle.width/50;
		
		int chargeLen = 6;
		int chargeStart = 1;
		int chargeEnd = chargeStart + chargeLen;
		
		
		if(defender.isFaster() && defender.getCurrMove().equals("BLOCK"))
			drawBattle.drawCharacter.drawStaticBlockingPose(defender, drawBattle.blockPoseDecreaseX, g);
		else if(defender.isFaster() && defender.getCurrMove().equals("CHARGE"))
			drawBattle.drawCharacter.drawStaticChargingPose(defender, drawBattle.blockPoseDecreaseX, g);
		else if(defender.isFaster() && defender.getCurrMove().equals("SWAP_SWORDS"))
			drawBattle.drawCharacter.drawStaticDefaultPoseWithSpecificSword(defender, defender.getInactiveSword().getName(), g);
		else
			drawBattle.drawCharacter.drawStaticDefaultPose(defender, g);
		
		
		if(drawBattle.frameNum <= chargeEnd)
			for(int i = chargeStart; i <= chargeEnd; i++) {
				if(drawBattle.frameNum == i) {
					drawBattle.drawCharacter.drawStaticChargingPose(attacker, drawBattle.blockPoseDecreaseX, g);
				}
			}
	}
}
