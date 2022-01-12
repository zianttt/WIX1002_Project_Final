package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import main.Game;
import utils.Handler;
import utils.ID;
import utils.SpriteSheet;

import java.awt.Color;

public class HardEnemy extends GameObject {
	
	private Handler handler;
	
	private Random r = new Random();
	
	public HardEnemy(int x, int y, ID id, SpriteSheet ss, Handler handler) {
		super(x, y, id, ss);
		
		this.handler = handler;
		
		velX = 5;
		velY = 5;
	}


	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 60) { if(velY < 0) velY = -(r.nextInt(7)+1)*-1; else velY = (r.nextInt(7)+1)*-1;}
		if(x <= 0 || x >= Game.WIDTH - 32) { if(velX < 0) velX = -(r.nextInt(7)+1)*-1; else velX = (r.nextInt(7)+1)*-1;}
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.yellow, 16, 16, 0.02f, handler, ss));
		
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.yellow);
		g2d.fillRect((int)x, (int)y, 16, 16);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}