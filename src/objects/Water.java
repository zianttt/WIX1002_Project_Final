package objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import utils.ID;
import utils.SpriteSheet;



public class Water extends GameObject{
	
	private BufferedImage water_img;

	public Water(int x, int y, ID id, SpriteSheet drawss) {
		super(x, y, id, drawss);
		
		//water_img = sss.grabImage(32, 16, 16, 16);
		water_img = drawss.grabImage(0, 48, 32, 32);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(water_img, x, y, 32, 32, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}