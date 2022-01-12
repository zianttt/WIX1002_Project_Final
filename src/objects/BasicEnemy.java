package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;

import utils.Handler;
import utils.ID;
import utils.SpriteSheet;
import main.Game;



public class BasicEnemy extends GameObject {
	
	private Handler handler;
	
	public BasicEnemy(int x, int y, ID id, SpriteSheet ss, Handler handler) {
		super(x, y, id, ss);
		
		this.handler = handler;
		
		velX = 5;
		velY = 5;
	}


	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 60) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 16, 16, 0.02f, handler, ss));
		
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.red);
		g2d.fillRect((int)x, (int)y, 16, 16);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}