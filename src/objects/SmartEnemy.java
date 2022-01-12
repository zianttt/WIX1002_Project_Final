package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import utils.Handler;
import utils.ID;
import utils.SpriteSheet;

import java.awt.Color;

public class SmartEnemy extends GameObject {
	
	private Handler handler;
	private GameObject player;
	
	public SmartEnemy(int x, int y, ID id, SpriteSheet ss, Handler handler) {
		super(x, y, id, ss);		
		this.handler = handler;
		
		for(int i=0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.MiniPlayer) {
				player = handler.object.get(i);
			}
		}
	}


	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		
		
		float difX = x - player.getX() - 16;
		float difY = y - player.getY() - 16;
		float distance = (float) Math.sqrt(Math.pow(x-player.getX(), 2) + Math.pow(y-player.getY(), 2));
		
		velX = ((-1/distance) * difX * (float)1.5) ;
		velY = ((-1/distance) * difY * (float)1.5);
 		
		//if(y <= 0 || y >= Game.HEIGHT - 60) velY *= -1;
		//if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.02f, handler, ss));
		
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.green);
		g2d.fillRect((int)x, (int)y, 16, 16);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}