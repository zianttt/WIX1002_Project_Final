package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import utils.ID;
import utils.SpriteSheet;

public class Mountain extends GameObject{
	
	private BufferedImage mountain_img;

	public Mountain(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		mountain_img = ss.grabImage(32, 32, 16, 16);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics2D g2d) {
		//g2d.setColor(new Color(255, 0, 0));
		//g2d.fillRect(x, y, 32, 32);
		g2d.drawImage(mountain_img, x, y, 32, 32, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
