package com.game.main;

import java.awt.image.BufferedImage;

//crop image
public class SpriteSheet {
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage grabImage(int xxx, int yyy, int width, int height) {
		return image.getSubimage(xxx, yyy, width, height); // row 2 will equal to 32 (the height of image) etc 
	}
	
}
