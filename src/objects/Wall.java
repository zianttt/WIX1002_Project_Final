package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import utils.ID;
import utils.SpriteSheet;

public class Wall extends SameBehaviour{
	
	private Random r = new Random();
	
	// stats
	public static float wallHp = 100;
	public static float wallBlock = 10;
	
	// displays
	private BufferedImage wall_img;

	public Wall(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		wall_img = ss.grabImage(16, 16, 16, 16);
		//wall_img = sss.grabImage(134, 66, 32, 32);
	}
	
	// will be called if the dragon does not miss its attack
	public boolean defence(float atk) {
		boolean block = false;
		if(r.nextInt(100) < wallBlock) {
			atk = 0;
			block = true;
			//System.out.println("Wall blocks!");
		}
		// deducy wall HP if the dragon does not miss its attack + wall unable to block
		wallHp -= atk;
		return block;
	}
	
	// reset at new game
	public void reset() {
		wallHp = 100;
		wallBlock = 10;
	}
	

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(wall_img, x, y, 32, 32, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}