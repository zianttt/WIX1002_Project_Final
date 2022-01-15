package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;

import main.Game;
import utils.Handler;
import utils.ID;
import utils.SpriteSheet;

public class FastEnemy extends GameObject {
	
	private int color;
	private boolean revert = true;
	
	private Handler handler;

	public FastEnemy(int x, int y, ID id, SpriteSheet ss, Handler handler) {
		super(x, y, id, ss);
		
		this.handler = handler;
		
		velX = 2;
		velY = 8;
		
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
		
		if(y <= 0 || y >= Game.HEIGHT - 60) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, new Color(155, 255, color), 16, 16, 0.02f, handler, ss));
		
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(new Color(155, 255, color));
		g2d.fillRect(x, y, 16, 16);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

}
