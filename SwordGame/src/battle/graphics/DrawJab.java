package battle.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import battle.PlayerBattleState;

public class DrawJab {

	DrawBattle drawBattle;
	boolean endAnimation;
	
	public DrawJab(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
	}
	
	public void drawFrame(PlayerBattleState attacker, PlayerBattleState defender, Graphics g) {
		
		int adjustX = drawBattle.battleWidth/5 - drawBattle.battleWidth/150;
		int adjustY = drawBattle.battleHeight/5 + drawBattle.battleHeight/10 - drawBattle.battleHeight/100;
		int adjustAngle = 85;
		
		int movement = 2 * drawBattle.battleWidth/24;
		
		int advLen = 2;
		int advStart = 1;
		int advEnd = advStart + advLen;
		
		int atkLen = 0;
		int atkStart = advEnd + 1;
		int atkEnd = atkStart + atkLen;
		
		int retreatLen = advLen;
		int retreatStart = atkEnd + 1;
		int retreatEnd = retreatStart + retreatLen;
		
		if(defender.isFaster() && defender.getCurrMove().equals("BLOCK"))
			drawBattle.drawBattleCharacter.drawStaticBlockingPose(defender, drawBattle.blockPoseDecreaseX, g);
		else if(defender.isFaster() && defender.getCurrMove().equals("CHARGE"))
			drawBattle.drawBattleCharacter.drawStaticChargingPose(defender, drawBattle.blockPoseDecreaseX, g);
		else
			drawBattle.drawBattleCharacter.drawStaticDefaultPose(defender, g);
		
		// adv
		if(drawBattle.frameNum <= advEnd)
			for(int i = advStart; i <= advEnd; i++) {
				if(drawBattle.frameNum == i) {
					drawBattle.drawBattleCharacter.paintPlayer(attacker, 0, movement*i, g);
					drawBattle.drawBattleCharacter.paintArmour(attacker, 0, movement*i, g);
					drawBattle.drawBattleCharacter.paintShield(attacker, movement*i, 0, g);
					drawBattle.drawBattleCharacter.paintSword(attacker, movement*i, 0, drawBattle.swordAngle, g);
				}
			}
		// atk
			if(drawBattle.frameNum <= atkEnd)
			for(int i = atkStart; i <= atkEnd; i++) {
				if(drawBattle.frameNum == i) {
					drawBattle.drawBattleCharacter.paintPlayer(attacker, 2, movement*advEnd, g);
					drawBattle.drawBattleCharacter.paintArmour(attacker, 2, movement*advEnd, g);
					drawBattle.drawBattleCharacter.paintShield(attacker, movement*advEnd, 0, g);
					drawBattle.drawBattleCharacter.paintSword(attacker, movement*advEnd + adjustX, adjustY, adjustAngle, g);
				}
			}
		// retreat
		if(drawBattle.frameNum <= retreatEnd)
			for(int i = retreatStart; i <= retreatEnd; i++) {
				if(drawBattle.frameNum == i) {
					drawBattle.drawBattleCharacter.paintPlayer(attacker, 0, movement*(retreatEnd-i), g);
					drawBattle.drawBattleCharacter.paintArmour(attacker, 0, movement*(retreatEnd-i), g);
					drawBattle.drawBattleCharacter.paintShield(attacker, movement*(retreatEnd-i), 0, g);
					drawBattle.drawBattleCharacter.paintSword(attacker, movement*(retreatEnd-i), 0, drawBattle.swordAngle, g);
				}
			}
		
		drawBattle.drawBars.drawUpdatedBars(attacker, defender, drawBattle.frameNum, atkEnd + 1, retreatEnd, g);
		
		if(drawBattle.frameNum == retreatEnd)
			attacker.setPrevStam(attacker.getCurrStamina());
		
	}
}
