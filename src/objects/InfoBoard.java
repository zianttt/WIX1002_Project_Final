package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import utils.ID;
import utils.SpriteSheet;

public class InfoBoard extends GameObject{

	public InfoBoard(int x, int y, ID id, SpriteSheet ss, int type) {
		super(x, y, id, ss);
		this.type = type;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(new Color(150, 50, 80));
		g2d.fillRect(x, y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
