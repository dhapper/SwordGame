package battle;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DrawSwapSwords {

	DrawBattle drawBattle;
	
	public DrawSwapSwords(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
	}
	
	public void drawFrame(PlayerBattleState attacker, PlayerBattleState defender, Graphics g) {
		
		int adjustX = drawBattle.width/5 - drawBattle.width/150;
		int adjustY = drawBattle.height/5 + drawBattle.height/10 - drawBattle.height/100;
		int adjustAngle = 85;
		
		int movement = drawBattle.width/50;
		
		int swapLen = 6;
		int swapStart = 1;
		int swapEnd = swapStart + swapLen;
		
		
		if(defender.isFaster() && defender.getCurrMove().equals("BLOCK"))
			drawBattle.drawCharacter.drawStaticBlockingPose(defender, drawBattle.blockPoseDecreaseX, g);
		else if(defender.isFaster() && defender.getCurrMove().equals("CHARGE"))
			drawBattle.drawCharacter.drawStaticChargingPose(defender, drawBattle.blockPoseDecreaseX, g);
		else if(defender.isFaster() && defender.getCurrMove().equals("SWAP_SWORDS"))
			drawBattle.drawCharacter.drawStaticDefaultPoseWithSpecificSword(defender, defender.getInactiveSword().getName(), g);
		else
			drawBattle.drawCharacter.drawStaticDefaultPose(defender, g);
		
		
		if(drawBattle.frameNum <= swapEnd)
			for(int i = swapStart; i <= swapEnd; i++) {
				if(drawBattle.frameNum == i) {
					drawBattle.drawCharacter.paintPlayer(attacker, 0, 0, g);
					drawBattle.drawCharacter.paintArmour(attacker, 0, 0, g);
					drawBattle.drawCharacter.paintShield(attacker, 0, 0, g);
					drawBattle.drawCharacter.paintSpecificSword(attacker, attacker.getInactiveSword().getName(), 0, (i - swapEnd) * 50, drawBattle.swordAngle, g);
					//attacker.getActiveSword().getName()
				}
			}
	}
}
