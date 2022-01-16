package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;
import java.awt.Color;

import utils.Handler;
import utils.ID;
import utils.SpriteSheet;

public class Flame extends SameBehaviour {
	
	private Handler battleHandler;
	Random r = new Random();
	
	public Flame(int x, int y, ID id, Handler battleHandler, SpriteSheet ss) {
		super(x, y, id, ss);
		
		this.battleHandler = battleHandler;
		
		velX = r.nextInt(6) - 3;
		velY = -5;
	}


	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		//if(y <= 0 || y >= Game.HEIGHT - 60) velY *= -1;
		//if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		if(y <= 0) battleHandler.removeObject(this);
		
		battleHandler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.02f, battleHandler, ss));
		
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.red);
		g2d.fillRect(x, y, 16, 16);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

}
