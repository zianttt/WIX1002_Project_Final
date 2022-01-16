package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import utils.ID;
import utils.SpriteSheet;

public class Tree extends SameBehaviour{
	
	private BufferedImage tree_img;

	public Tree(int x, int y, ID id, SpriteSheet drawss) {
		super(x, y, id, drawss);
		
		tree_img = drawss.grabImage(0, 80, 32, 32);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(tree_img, x, y, null);	
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
