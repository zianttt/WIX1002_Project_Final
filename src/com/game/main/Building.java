package com.game.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;

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
	public void render(Graphics g) {
		//g.setColor(new Color(244, 194, 194));
		//g.fillRect(x, y, 32, 32);
		
		g.drawImage(building_img, x, y, w, h, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}

}
