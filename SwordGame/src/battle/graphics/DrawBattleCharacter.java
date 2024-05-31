package battle.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import battle.PlayerBattleState;
import utilz.SpriteSheet;

public class DrawBattleCharacter {

	DrawBattle drawBattle;
	
	public DrawBattleCharacter(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
	}
	
	public void drawStaticDefaultPose(PlayerBattleState player, Graphics g) {
		drawStaticDefaultPoseWithSpecificSword(player, player.getActiveSword().getName(), g);
	}
	
	public void drawStaticDefaultPoseWithSpecificSword(PlayerBattleState player, String swordName, Graphics g) {
		paintPlayer(player, 0, 0, g);
		paintArmour(player, 0, 0, g);
    	//paintShield(player, 0, 0, g);
    	//paintSpecificSword(player, swordName, 0, 0, drawBattle.swordAngle, g);
	}
	
	public void drawStaticBlockingPose(PlayerBattleState player, int xDecrease, Graphics g) {
		drawStaticBlockingPoseWithSpecificSword(player, player.getActiveSword().getName(), xDecrease, g);
	}
	
	public void drawStaticBlockingPoseWithSpecificSword(PlayerBattleState player, String swordName, int xDecrease, Graphics g) {
		paintSpecificSword(player, swordName, -xDecrease - drawBattle.battleWidth/5 - drawBattle.battleWidth/40, 0, 0, g);
		paintPlayer(player, 3, -xDecrease, g);
		paintArmour(player, 3, -xDecrease, g);
    	paintShield(player, -xDecrease + drawBattle.shieldW*2/3, -drawBattle.shieldW/3, g);
	}
	
	public void drawStaticChargingPose(PlayerBattleState player, int xDecrease, Graphics g) {
		paintPlayer(player, 1, 0, g);
		paintArmour(player, 1, 0, g);
		paintSword(player, -drawBattle.battleWidth/6, -drawBattle.battleHeight/8, 0, g);
    	paintShield(player, 0, 0, g);
	}
	
	public void paintPlayer(PlayerBattleState player, int spriteNum, int xIncrease, Graphics g) {
		
		int skinIndex = player.getPlayer().getModel()[0];
		int hairIndex = player.getPlayer().getModel()[1];
		int eyesIndex = player.getPlayer().getModel()[2];
		
		double hairIncX = drawBattle.playerW * 16.0/50.0 ;
		double hairIncY = drawBattle.playerW * 1.0/50.0 ;
		double hairRatio = 16.0/50.0;
		double eyeIncX = drawBattle.playerW * 22.0/50.0 ;
		double eyeIncY = drawBattle.playerW * 8.0/50.0 ;
		double eyeRatioW = 5.0/50;
		double eyeRatioH = 1.0/50.0;
		
		Graphics2D g2d = (Graphics2D) g;
		
		SpriteSheet skinSheet = new SpriteSheet("res/spritesheets/players/PLAYER - "+skinIndex+".png", 2, 2);
		SpriteSheet shadowSheet = new SpriteSheet("res/spritesheets/players/PLAYER - SHADOWS.png", 2, 2);
		SpriteSheet hairSheet = new SpriteSheet("res/spritesheets/players/HAIR.png", 3, 3);
		SpriteSheet eyeSheet = new SpriteSheet("res/spritesheets/players/EYES.png", 10, 1);
		
		if(player.getPlayerPos().equals("A")) {
			Image playerModelA = skinSheet.getSprite(spriteNum);
			AffineTransform transformPlayerModelA = new AffineTransform();
			transformPlayerModelA.translate(drawBattle.playerX_A + xIncrease, drawBattle.playerY);
			transformPlayerModelA.scale(drawBattle.playerW/playerModelA.getWidth(null), drawBattle.playerH/playerModelA.getHeight(null));
			g2d.drawImage(playerModelA, transformPlayerModelA, null);
			
			Image shadowA = shadowSheet.getSprite(spriteNum);
			AffineTransform transformShadowsA = new AffineTransform();
			transformShadowsA.translate(drawBattle.playerX_A + xIncrease, drawBattle.playerY);
			transformShadowsA.scale(drawBattle.playerW/playerModelA.getWidth(null), drawBattle.playerH/playerModelA.getHeight(null));
			g2d.drawImage(shadowA, transformShadowsA, null);
			
			Image hairImgA = hairSheet.getSprite(hairIndex);
			AffineTransform transformHairA = new AffineTransform();
			transformHairA.translate(drawBattle.playerX_A + xIncrease + hairIncX, drawBattle.playerY - hairIncY);
			transformHairA.scale(hairRatio * drawBattle.playerW/hairImgA.getWidth(null), hairRatio * drawBattle.playerH/hairImgA.getHeight(null));
			g2d.drawImage(hairImgA, transformHairA, null);
			
			Image eyesImgA = eyeSheet.getSprite(eyesIndex);
			AffineTransform transformEyesA = new AffineTransform();
			transformEyesA.translate(drawBattle.playerX_A + xIncrease + eyeIncX, drawBattle.playerY + eyeIncY);
			transformEyesA.scale(eyeRatioW * drawBattle.playerW/eyesImgA.getWidth(null), eyeRatioH * drawBattle.playerH/eyesImgA.getHeight(null));
			g2d.drawImage(eyesImgA, transformEyesA, null);
		}
		
		if(player.getPlayerPos().equals("B")) {
			Image playerModelB = skinSheet.getSprite(spriteNum);
			AffineTransform transformPlayerModelB = new AffineTransform();
			transformPlayerModelB.translate(drawBattle.playerX_B - xIncrease, drawBattle.playerY);
			transformPlayerModelB.scale(-drawBattle.playerW/playerModelB.getWidth(null), drawBattle.playerH/playerModelB.getHeight(null));
			g2d.drawImage(playerModelB, transformPlayerModelB, null);
			
			Image shadowB = shadowSheet.getSprite(spriteNum);
			AffineTransform transformShadowsB = new AffineTransform();
			transformShadowsB.translate(drawBattle.playerX_B - xIncrease, drawBattle.playerY);
			transformShadowsB.scale(-drawBattle.playerW/playerModelB.getWidth(null), drawBattle.playerH/playerModelB.getHeight(null));
			g2d.drawImage(shadowB, transformShadowsB, null);
			
			Image hairImgB = hairSheet.getSprite(hairIndex);
			AffineTransform transformHairB = new AffineTransform();
			transformHairB.translate(drawBattle.playerX_B - xIncrease - hairIncX, drawBattle.playerY - hairIncY);
			transformHairB.scale(-hairRatio * drawBattle.playerW/hairImgB.getWidth(null), hairRatio * drawBattle.playerH/hairImgB.getHeight(null));
			g2d.drawImage(hairImgB, transformHairB, null);
			
			Image eyesImgB = eyeSheet.getSprite(eyesIndex);
			AffineTransform transformEyesB = new AffineTransform();
			transformEyesB.translate(drawBattle.playerX_B - xIncrease - eyeIncX, drawBattle.playerY + eyeIncY);
			transformEyesB.scale(-eyeRatioW * drawBattle.playerW/eyesImgB.getWidth(null), eyeRatioH * drawBattle.playerH/eyesImgB.getHeight(null));
			g2d.drawImage(eyesImgB, transformEyesB, null);
		}
		
		
	}
	
	public void paintArmour(PlayerBattleState player, int spriteNum, int xIncrease, Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		SpriteSheet spriteSheet = new SpriteSheet("res/spritesheets/armour/ARMOUR - "+player.getActiveArmour().getName()+".png", 2, 2);
		
		if(player.getPlayerPos().equals("A")) {
			Image armourModelA = spriteSheet.getSprite(spriteNum);
			AffineTransform transformArmourModelA = new AffineTransform();
			transformArmourModelA.translate(drawBattle.playerX_A + xIncrease, drawBattle.playerY);
			transformArmourModelA.scale(drawBattle.playerW/armourModelA.getWidth(null), drawBattle.playerH/armourModelA.getHeight(null));
			g2d.drawImage(armourModelA, transformArmourModelA, null);	
		}
		
		if(player.getPlayerPos().equals("B")) {
			Image armourModelB = spriteSheet.getSprite(spriteNum);
    		AffineTransform transformArmourModelB = new AffineTransform();
    		transformArmourModelB.translate(drawBattle.playerX_B - xIncrease, drawBattle.playerY);
    		transformArmourModelB.scale(-drawBattle.playerW/armourModelB.getWidth(null), drawBattle.playerH/armourModelB.getHeight(null));
    		g2d.drawImage(armourModelB, transformArmourModelB, null);
		}
	}
	
	public void paintShield(PlayerBattleState player, int xIncrease, int yIncrease, Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		try {
			
			if(player.getPlayerPos().equals("A")) {
				Image shieldModelA = ImageIO.read(new File("res/shields/shield - "+ player.getActiveShield().getName() +".png"));
				AffineTransform transformShieldModelA = new AffineTransform();
				transformShieldModelA.translate(drawBattle.shieldX_A + xIncrease, drawBattle.shieldY + yIncrease);
				transformShieldModelA.scale(drawBattle.shieldW/shieldModelA.getWidth(null), drawBattle.shieldH/shieldModelA.getHeight(null));
				g2d.drawImage(shieldModelA, transformShieldModelA, null);	
			}
			
			if(player.getPlayerPos().equals("B")) {
				Image shieldModelB = ImageIO.read(new File("res/shields/shield - "+player.getActiveShield().getName()+".png"));
	    		AffineTransform transformShieldModelB = new AffineTransform();
	    		transformShieldModelB.translate(drawBattle.shieldX_B - xIncrease, drawBattle.shieldY + yIncrease);
	    		transformShieldModelB.scale(-drawBattle.shieldW/shieldModelB.getWidth(null), drawBattle.shieldH/shieldModelB.getHeight(null));
	    		g2d.drawImage(shieldModelB, transformShieldModelB, null);	
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintSword(PlayerBattleState player, int xIncrease, int yIncrease, int angle, Graphics g) {
		paintSpecificSword(player, player.getActiveSword().getName(), xIncrease, yIncrease, angle, g);
	}
	
	public void paintSpecificSword(PlayerBattleState player, String swordName, int xIncrease, int yIncrease, int angle, Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		try {
			
			if(player.getPlayerPos().equals("A")) {
				Image swordModelA = ImageIO.read(new File("res/swords/sword - "+swordName+".png"));
	    		AffineTransform transformSwordModelA = new AffineTransform();
	    		transformSwordModelA.translate(drawBattle.sword_AX + xIncrease, drawBattle.sword_AY + yIncrease);
	    		transformSwordModelA.scale(drawBattle.sword_AW/swordModelA.getWidth(null), drawBattle.sword_AH/swordModelA.getHeight(null));
	    		transformSwordModelA.rotate(Math.toRadians(angle));
	    		g2d.drawImage(swordModelA, transformSwordModelA, null);
			}
			
			if(player.getPlayerPos().equals("B")) {
				Image swordModelB = ImageIO.read(new File("res/swords/sword - "+swordName+".png"));
	    		AffineTransform transformSwordModelB = new AffineTransform();
	    		transformSwordModelB.translate(drawBattle.sword_BX - xIncrease, drawBattle.sword_AY + yIncrease);
	    		transformSwordModelB.scale(-drawBattle.sword_AW/swordModelB.getWidth(null), drawBattle.sword_AH/swordModelB.getHeight(null));
	    		transformSwordModelB.rotate(Math.toRadians(angle));
	    		g2d.drawImage(swordModelB, transformSwordModelB, null);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
