package utils;

import java.awt.image.BufferedImage;

// Provides funtionality to crop sprite sheets 
public class SpriteSheet {
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage grabImage(int xx, int yy, int width, int height) {
		return image.getSubimage(xx, yy, width, height); 
	}
	
}
