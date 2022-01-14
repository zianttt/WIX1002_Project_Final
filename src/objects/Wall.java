package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.Game;
import utils.Handler;
import utils.ID;
import utils.SpriteSheet;

public class Wall extends GameObject{
	
	private Handler battleHandler;
	private Game game;
	Random r = new Random();
	
	
	// stats
	public static float wallHp = 100;
	public static float wallBlock = 10;
	
	private BufferedImage wall_img;

	public Wall(int x, int y, ID id, Handler battleHandler, Game game, SpriteSheet sss) {
		super(x, y, id, sss);
		this.battleHandler = battleHandler;
		this.game = game;
		
		wall_img = sss.grabImage(16, 16, 16, 16);
		//wall_img = sss.grabImage(134, 66, 32, 32);
	}
	
	
	public boolean defence(float atk) {
		boolean block = false;
		if(r.nextInt(100) < wallBlock) {
			atk = 0;
			block = true;
			System.out.println("Wall blocks!");
		}

		wallHp -= atk;
		return block;
	}
	
	public void reset() {
		wallHp = 100;
		wallBlock = 10;
	}
	

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics2D g2d) {
		//g2d.setColor(new Color(255, 255, 0));
		//g2d.fillRect(x, y, 32, 32);
		g2d.drawImage(wall_img, x, y, 32, 32, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}