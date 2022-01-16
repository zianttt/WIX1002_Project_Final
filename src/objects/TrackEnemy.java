package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import utils.Handler;
import utils.ID;
import utils.SpriteSheet;

import java.awt.Color;

public class TrackEnemy extends SameBehaviour {
	
	private int color;
	private boolean revert = true;
	
	private Handler handler;
	private SameBehaviour player;
	
	public TrackEnemy(int x, int y, ID id, SpriteSheet ss, Handler handler) {
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
		
		if(!revert) {
			color--;
			if(color == 0) revert = true;
		}else {
			color++;
			if(color == 255) revert = false;
		}
		
		x += velX;
		y += velY;
		
		float difX = x - player.getX() - 16;
		float difY = y - player.getY() - 16;
		float distance = (float) Math.sqrt(Math.pow(x-player.getX(), 2) + Math.pow(y-player.getY(), 2));
		
		velX = ((-1/distance) * difX * (float)1.5) ;
		velY = ((-1/distance) * difY * (float)1.5);
		
		handler.addObject(new Trail(x, y, ID.Trail, new Color(color, color, 255), 16, 16, 0.02f, handler, ss));
		
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(new Color(color, color, 255));
		g2d.fillRect(x, y, 16, 16);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

}