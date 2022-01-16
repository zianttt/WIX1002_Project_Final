package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

// A class that handles image loading (including exceptions)
public class BufferedImageLoader {
	
	private BufferedImage image;
	
	public BufferedImage loadImage(String path) {
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}
