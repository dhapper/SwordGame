package battle;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DrawStaticBlock {

	DrawBattle drawBattle;
	
	public DrawStaticBlock(DrawBattle drawBattle) {
		this.drawBattle = drawBattle;
	}
	
	public void frameSelector(PlayerBattleState player, Graphics g) {
		
		int adjustX = drawBattle.width/5 - drawBattle.width/150;
		int adjustY = drawBattle.height/5 + drawBattle.height/10 - drawBattle.height/100;
		int adjustAngle = 85;
		
		int movement = drawBattle.width/(9 * 3);
		
		
		int advLen = 5;
		int advStart = 1;
		//if(movingFirst)
		//	advStart = 16;
		int advEnd = advStart + advLen;
		
		int atkLen = 2;
		int atkStart = advEnd + 1;
		int atkEnd = atkStart + atkLen;
		
		int retreatLen = advLen;
		int retreatStart = atkEnd + 1;
		int retreatEnd = retreatStart + retreatLen;
		
	}
	
	public void paintStaticDefendingBlocker(String playerPos, Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		SpriteSheet spriteSheet = new SpriteSheet("res/spritesheet - player.png", 2, 2);
		
		int xDecrease = drawBattle.getWidth()/100;
		
		if(playerPos == "A") {
			Image playerModelA = spriteSheet.getSprite(3);
			AffineTransform transformPlayerModelA = new AffineTransform();
			transformPlayerModelA.translate(drawBattle.playerX_A + xDecrease, drawBattle.playerY);
			transformPlayerModelA.scale(drawBattle.playerW/playerModelA.getWidth(null), drawBattle.playerH/playerModelA.getHeight(null));
			g2d.drawImage(playerModelA, transformPlayerModelA, null);
		}else if(playerPos == "B") {
			Image playerModelB = spriteSheet.getSprite(3);
			AffineTransform transformPlayerModelB = new AffineTransform();
			transformPlayerModelB.translate(drawBattle.playerX_B + xDecrease, drawBattle.playerY);
			transformPlayerModelB.scale(-drawBattle.playerW/playerModelB.getWidth(null), drawBattle.playerH/playerModelB.getHeight(null));
			g2d.drawImage(playerModelB, transformPlayerModelB, null);
		}
	}
	
	public void paintPlayer(int spriteNum, int xIncrease, Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		SpriteSheet spriteSheet = new SpriteSheet("res/spritesheet - player.png", 2, 2);
		
		// player A
		Image playerModelA = spriteSheet.getSprite(spriteNum);
		AffineTransform transformPlayerModelA = new AffineTransform();
		transformPlayerModelA.translate(drawBattle.playerX_A + xIncrease, drawBattle.playerY);
		transformPlayerModelA.scale(drawBattle.playerW/playerModelA.getWidth(null), drawBattle.playerH/playerModelA.getHeight(null));
		g2d.drawImage(playerModelA, transformPlayerModelA, null);
		
		// player B
		Image playerModelB = spriteSheet.getSprite(spriteNum+1);
		AffineTransform transformPlayerModelB = new AffineTransform();
		transformPlayerModelB.translate(drawBattle.playerX_B, drawBattle.playerY);
		transformPlayerModelB.scale(-drawBattle.playerW/playerModelA.getWidth(null), drawBattle.playerH/playerModelA.getHeight(null));
		g2d.drawImage(playerModelB, transformPlayerModelB, null);
	}
	
	public void paintShield(String shieldName, int xIncrease, Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		try {
			Image shieldModelA = ImageIO.read(new File("res/shield - "+ shieldName +".png"));
			AffineTransform transformShieldModelA = new AffineTransform();
			transformShieldModelA.translate(drawBattle.shieldX_A + xIncrease, drawBattle.shieldY);
			transformShieldModelA.scale(drawBattle.shieldW/shieldModelA.getWidth(null), drawBattle.shieldH/shieldModelA.getHeight(null));
			g2d.drawImage(shieldModelA, transformShieldModelA, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintSword(String swordName, int xIncrease, int yIncrease, int rotation, Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		try {
			Image swordModelA = ImageIO.read(new File("res/sword - "+swordName+".png"));
    		AffineTransform transformSwordModelA = new AffineTransform();
    		transformSwordModelA.translate(drawBattle.sword_AX + xIncrease, drawBattle.sword_AY + yIncrease);
    		transformSwordModelA.scale(drawBattle.sword_AW/swordModelA.getWidth(null), drawBattle.sword_AH/swordModelA.getHeight(null));
    		transformSwordModelA.rotate(Math.toRadians(rotation));
    		g2d.drawImage(swordModelA, transformSwordModelA, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
