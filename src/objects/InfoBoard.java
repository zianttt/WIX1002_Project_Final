package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import utils.ID;
import utils.SpriteSheet;

public class InfoBoard extends SameBehaviour{
	
	// Displays
	private BufferedImage sign_img = null;

	public InfoBoard(int x, int y, ID id, SpriteSheet ss, int type) {
		super(x, y, id, ss);
		this.type = type;
		
		if(type == 0) {
			sign_img = ss.grabImage(32, 64, 32, 32);
		}else {
			sign_img = ss.grabImage(0, 64, 32, 32);
		}
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(sign_img, x, y, 32, 32, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
