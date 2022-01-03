package com.game.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Water extends GameObject{
	
	private BufferedImage water_img;

	public Water(int x, int y, ID id, SpriteSheet sss) {
		super(x, y, id, sss);
		
		//water_img = sss.grabImage(32, 16, 16, 16);
		water_img = sss.grabImage(298, 232, 32, 32);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(water_img, x, y, 32, 32, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}