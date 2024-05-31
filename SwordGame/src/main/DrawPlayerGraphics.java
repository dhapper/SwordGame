package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import entity.Player;
import utilz.SpriteSheet;

public class DrawPlayerGraphics {

	
	public void drawPlayer(Player player, String pos, int spriteNum, int x, int y, int scale, Graphics g) {

		//player = player;
		
		int skinIndex = player.getModel()[0];
		int hairIndex = player.getModel()[1];
		int eyesIndex = player.getModel()[2];
		
		double playerW = scale;
		double playerH = scale;
		double hairIncX = playerW * 16.0/50.0 ;
		double hairIncY = playerW * 1.0/50.0 ;
		double hairRatio = 16.0/50.0;
		double eyeIncX = playerW * 22.0/50.0 ;
		double eyeIncY = playerW * 8.0/50.0 ;
		double eyeRatioW = 5.0/50;
		double eyeRatioH = 1.0/50.0;
		
		Graphics2D g2d = (Graphics2D) g;
		
		SpriteSheet skinSheet = new SpriteSheet("res/spritesheets/players/PLAYER - "+skinIndex+".png", 2, 2);
		SpriteSheet shadowSheet = new SpriteSheet("res/spritesheets/players/PLAYER - SHADOWS.png", 2, 2);
		SpriteSheet hairSheet = new SpriteSheet("res/spritesheets/players/HAIR.png", 3, 3);
		SpriteSheet eyeSheet = new SpriteSheet("res/spritesheets/players/EYES.png", 10, 1);
		
		if(pos.equals("A")) {
			Image playerModelA = skinSheet.getSprite(spriteNum);
			AffineTransform transformPlayerModelA = new AffineTransform();
			transformPlayerModelA.translate(x, y);
			transformPlayerModelA.scale(playerW/playerModelA.getWidth(null), playerH/playerModelA.getHeight(null));
			g2d.drawImage(playerModelA, transformPlayerModelA, null);
			
			Image shadowA = shadowSheet.getSprite(spriteNum);
			AffineTransform transformShadowsA = new AffineTransform();
			transformShadowsA.translate(x, y);
			transformShadowsA.scale(playerW/playerModelA.getWidth(null), playerH/playerModelA.getHeight(null));
			g2d.drawImage(shadowA, transformShadowsA, null);
			
			Image hairImgA = hairSheet.getSprite(hairIndex);
			AffineTransform transformHairA = new AffineTransform();
			transformHairA.translate(x + hairIncX, y - hairIncY);
			transformHairA.scale(hairRatio * playerW/hairImgA.getWidth(null), hairRatio * playerH/hairImgA.getHeight(null));
			g2d.drawImage(hairImgA, transformHairA, null);
			
			Image eyesImgA = eyeSheet.getSprite(eyesIndex);
			AffineTransform transformEyesA = new AffineTransform();
			transformEyesA.translate(x + eyeIncX, y + eyeIncY);
			transformEyesA.scale(eyeRatioW * playerW/eyesImgA.getWidth(null), eyeRatioH * playerH/eyesImgA.getHeight(null));
			g2d.drawImage(eyesImgA, transformEyesA, null);
		}
		
	}
	
}
