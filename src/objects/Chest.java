package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import utils.ID;
import utils.SpriteSheet;

public class Chest extends GameObject{
	
	Random r = new Random();
	
	private BufferedImage chest_img = null;

	public Chest(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		chest_img = ss.grabImage(0, 0, 32, 32);
	}

	@Override
	public void tick() {

		
	}


	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(chest_img, x , y, 64, 54, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 54);
	}

}
