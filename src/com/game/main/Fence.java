package com.game.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Fence extends GameObject{
	
	private BufferedImage fence_img;

	public Fence(int x, int y, ID id, SpriteSheet sss) {
		super(x, y, id, sss);
		
		fence_img = sss.grabImage(265, 34, 32, 32);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		//g.setColor(new Color(255, 100, 0));
		//g.fillRect(x, y, 32, 32);
		
		g.drawImage(fence_img, x, y, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}