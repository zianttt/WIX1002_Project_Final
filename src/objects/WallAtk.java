package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import main.Game;
import utils.Handler;
import utils.ID;
import utils.STATES;
import utils.SpriteSheet;

import java.awt.Color;


public class WallAtk extends GameObject {
	
	private Handler battleHandler;
	Random r = new Random();
	
	public WallAtk(int x, int y, ID id, Handler battleHandler, SpriteSheet ss) {
		super(x, y, id, ss);
		
		this.battleHandler = battleHandler;
		
		velX = r.nextInt(6) - 3;
		velY = 5;
	}


	@Override
	public void tick() {
		
		if(Game.gameState == STATES.Battle) {
			x += velX;
			y += velY;
			
			//if(y <= 0 || y >= Game.HEIGHT - 60) velY *= -1;
			//if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
			
			if(y >= Game.HEIGHT) {
				battleHandler.removeObject(this);
			}
			
			battleHandler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.02f, battleHandler, ss));	
		}	
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.green);
		g2d.fillRect(x, y, 16, 16);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

}