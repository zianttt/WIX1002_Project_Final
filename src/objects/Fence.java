package objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import utils.ID;
import utils.SpriteSheet;

public class Fence extends SameBehaviour{
	
	private BufferedImage fence_img;

	public Fence(int x, int y, ID id, SpriteSheet drawss) {
		super(x, y, id, drawss);
		
		fence_img = drawss.grabImage(32, 48, 32, 32);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics2D g2d) {
		//g2d.setColor(new Color(255, 100, 0));
		//g2d.fillRect(x, y, 32, 32);
		
		g2d.drawImage(fence_img, x, y, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}