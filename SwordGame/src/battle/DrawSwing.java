package battle;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DrawSwing {

	DrawBattle drawBattle;
	
	public DrawSwing(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
	}
	
	public void drawFrame(PlayerBattleState attacker, PlayerBattleState defender, Graphics g) {
		
		int adjustX = drawBattle.width/5 - drawBattle.width/150;
		int adjustY = drawBattle.height/5 + drawBattle.height/10 - drawBattle.height/100;
		int adjustAngle = 85;
		
		int movement = drawBattle.width/24;
		
		int advLen = 5;
		int advStart = 1;
		int advEnd = advStart + advLen;
		
		int atkLen = 2;
		int atkStart = advEnd + 1;
		int atkEnd = atkStart + atkLen;
		
		int retreatLen = advLen;
		int retreatStart = atkEnd + 1;
		int retreatEnd = retreatStart + retreatLen;
		
		if(defender.isFaster() && defender.getCurrMove().equals("BLOCK"))
			drawBattle.drawCharacter.drawStaticBlockingPose(defender, drawBattle.blockPoseDecreaseX, g);
		else if(defender.isFaster() && defender.getCurrMove().equals("CHARGE"))
			drawBattle.drawCharacter.drawStaticChargingPose(defender, drawBattle.blockPoseDecreaseX, g);
		else
			drawBattle.drawCharacter.drawStaticDefaultPose(defender, g);
		
		// adv
		if(drawBattle.frameNum <= advEnd)
			for(int i = advStart; i <= advEnd; i++) {
				if(drawBattle.frameNum == i) {
					drawBattle.drawCharacter.paintPlayer(attacker, 0, movement*i, g);
					drawBattle.drawCharacter.paintArmour(attacker, 0, movement*i, g);
					drawBattle.drawCharacter.paintShield(attacker, movement*i, 0, g);
					drawBattle.drawCharacter.paintSword(attacker, movement*i, 0, drawBattle.swordAngle, g);
				}
			}
		// atk
		if(drawBattle.frameNum <= atkEnd)
			for(int i = atkStart; i <= atkEnd; i++) {
				if(drawBattle.frameNum == i) {
					drawBattle.drawCharacter.paintPlayer(attacker, 2, movement*advEnd, g);
					drawBattle.drawCharacter.paintArmour(attacker, 2, movement*advEnd, g);
					drawBattle.drawCharacter.paintShield(attacker, movement*advEnd, 0, g);
					drawBattle.drawCharacter.paintSword(attacker, movement*advEnd + adjustX, adjustY, adjustAngle, g);
				}
			}
		// retreat
		if(drawBattle.frameNum <= retreatEnd)
			for(int i = retreatStart; i <= retreatEnd; i++) {
				if(drawBattle.frameNum == i) {
					drawBattle.drawCharacter.paintPlayer(attacker, 0, movement*(15-i), g);
					drawBattle.drawCharacter.paintArmour(attacker, 0, movement*(15-i), g);
					drawBattle.drawCharacter.paintShield(attacker, movement*(15-i), 0, g);
					drawBattle.drawCharacter.paintSword(attacker, movement*(15-i), 0, drawBattle.swordAngle, g);
				}
			}
	}
}
