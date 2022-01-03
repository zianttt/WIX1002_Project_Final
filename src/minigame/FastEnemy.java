package minigame;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.battle.Trail;
import com.game.main.Game;
import com.game.main.GameObject;
import com.game.main.Handler;
import com.game.main.ID;
import com.game.main.SpriteSheet;

import java.awt.Color;

public class FastEnemy extends GameObject {
	
	private Handler handler;

	public FastEnemy(int x, int y, ID id, SpriteSheet ss, Handler handler) {
		super(x, y, id, ss);
		
		this.handler = handler;
		
		velX = 2;
		velY = 8;
		
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 60) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.CYAN, 16, 16, 0.02f, handler, ss));
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
