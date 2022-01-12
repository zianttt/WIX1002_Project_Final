package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import utils.ID;
import utils.SpriteSheet;

public class Building extends GameObject{
	
	private BufferedImage building_img = null;
	private int imgChoice;
	private int w, h;

	public Building(int x, int y, ID id, SpriteSheet bss, int choice) {
		super(x, y, id, bss);
		this.imgChoice = choice;
		
		if(imgChoice == 0) {
			w = 160;
			h = 288;
			building_img = bss.grabImage(0, 0, w, h);
		}
		else if(imgChoice == 1) {
			w = 320;
			h = 160;
			building_img = bss.grabImage(160, 0, w, h);
		}
		else if(imgChoice == 2) {
			w = 320;
			h = 160;
			building_img = bss.grabImage(160, 160, w, h);
		}
	}

	@Override
	public void tick() {
		

	}

	@Override
	public void render(Graphics2D g2d) {
		//g2d.setColor(new Color(244, 194, 194));
		//g2d.fillRect(x, y, 32, 32);
		
		g2d.drawImage(building_img, x, y, w, h, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}

}
