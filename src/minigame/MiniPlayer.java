package minigame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.main.Game;
import com.game.main.GameObject;
import com.game.main.Handler;
import com.game.main.ID;
import com.game.main.SpriteSheet;

public class MiniPlayer extends GameObject{
	
	Handler handler;

	public MiniPlayer(int x, int y, ID id, SpriteSheet ss, Handler handler) {
		super(x, y, id, ss);
		this.handler = handler;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = (int) Game.clamp(x, 0, Game.WIDTH - 37);
		y = (int) Game.clamp(y, 0, Game.HEIGHT - 60);
		
		collision();
		
	}
	
	private void collision() {
		for(int i=0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
				
			}
			
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 32, 32);
		
	}

	public Rectangle getBounds() {		
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
