package battle;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DrawCharacter {

	DrawBattle drawBattle;
	
	public DrawCharacter(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
	}
	
	public void drawStaticDefaultPose(PlayerBattleState player, Graphics g) {
		drawStaticDefaultPoseWithSpecificSword(player, player.getActiveSword().getName(), g);
	}
	
	public void drawStaticDefaultPoseWithSpecificSword(PlayerBattleState player, String swordName, Graphics g) {
		paintPlayer(player, 0, 0, g);
		paintArmour(player, 0, 0, g);
    	paintShield(player, 0, 0, g);
    	paintSpecificSword(player, swordName, 0, 0, drawBattle.swordAngle, g);
	}
	
	public void drawStaticBlockingPose(PlayerBattleState player, int xDecrease, Graphics g) {
		drawStaticBlockingPoseWithSpecificSword(player, player.getActiveSword().getName(), xDecrease, g);
	}
	
	public void drawStaticBlockingPoseWithSpecificSword(PlayerBattleState player, String swordName, int xDecrease, Graphics g) {
		paintSpecificSword(player, swordName, -xDecrease - drawBattle.width/5 - drawBattle.width/40, 0, 0, g);
		paintPlayer(player, 3, -xDecrease, g);
		paintArmour(player, 3, -xDecrease, g);
    	paintShield(player, -xDecrease + drawBattle.shieldW*2/3, -drawBattle.shieldW/3, g);
	}
	
	public void drawStaticChargingPose(PlayerBattleState player, int xDecrease, Graphics g) {
		paintPlayer(player, 1, 0, g);
		paintArmour(player, 1, 0, g);
		paintSword(player, -drawBattle.width/6, -drawBattle.height/8, 0, g);
    	paintShield(player, 0, 0, g);
	}
	
	public void paintPlayer(PlayerBattleState player, int spriteNum, int xIncrease, Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		SpriteSheet spriteSheet = new SpriteSheet("res/spritesheet - player - DEFAULT.png", 2, 2);
		
		if(player.getPlayerPos().equals("A")) {
			Image playerModelA = spriteSheet.getSprite(spriteNum);
			AffineTransform transformPlayerModelA = new AffineTransform();
			transformPlayerModelA.translate(drawBattle.playerX_A + xIncrease, drawBattle.playerY);
			transformPlayerModelA.scale(drawBattle.playerW/playerModelA.getWidth(null), drawBattle.playerH/playerModelA.getHeight(null));
			g2d.drawImage(playerModelA, transformPlayerModelA, null);	
		}
		
		if(player.getPlayerPos().equals("B")) {
			Image playerModelB = spriteSheet.getSprite(spriteNum);
			AffineTransform transformPlayerModelB = new AffineTransform();
			transformPlayerModelB.translate(drawBattle.playerX_B - xIncrease, drawBattle.playerY);
			transformPlayerModelB.scale(-drawBattle.playerW/playerModelB.getWidth(null), drawBattle.playerH/playerModelB.getHeight(null));
			g2d.drawImage(playerModelB, transformPlayerModelB, null);
		}
	}
	
	public void paintArmour(PlayerBattleState player, int spriteNum, int xIncrease, Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		SpriteSheet spriteSheet = new SpriteSheet("res/spritesheet - armour - "+player.getActiveArmour().getName()+".png", 2, 2);
		
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
				Image shieldModelA = ImageIO.read(new File("res/shield - "+ player.getActiveShield().getName() +".png"));
				AffineTransform transformShieldModelA = new AffineTransform();
				transformShieldModelA.translate(drawBattle.shieldX_A + xIncrease, drawBattle.shieldY + yIncrease);
				transformShieldModelA.scale(drawBattle.shieldW/shieldModelA.getWidth(null), drawBattle.shieldH/shieldModelA.getHeight(null));
				g2d.drawImage(shieldModelA, transformShieldModelA, null);	
			}
			
			if(player.getPlayerPos().equals("B")) {
				Image shieldModelB = ImageIO.read(new File("res/shield - "+player.getActiveShield().getName()+".png"));
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
				Image swordModelA = ImageIO.read(new File("res/sword - "+swordName+".png"));
	    		AffineTransform transformSwordModelA = new AffineTransform();
	    		transformSwordModelA.translate(drawBattle.sword_AX + xIncrease, drawBattle.sword_AY + yIncrease);
	    		transformSwordModelA.scale(drawBattle.sword_AW/swordModelA.getWidth(null), drawBattle.sword_AH/swordModelA.getHeight(null));
	    		transformSwordModelA.rotate(Math.toRadians(angle));
	    		g2d.drawImage(swordModelA, transformSwordModelA, null);
			}
			
			if(player.getPlayerPos().equals("B")) {
				Image swordModelB = ImageIO.read(new File("res/sword - "+swordName+".png"));
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
