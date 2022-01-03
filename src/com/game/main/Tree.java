package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tree extends GameObject{
	
	private BufferedImage tree_img;

	public Tree(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		tree_img = ss.grabImage(1, 1, 32, 32);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		//g.setColor(new Color(144, 238, 144));
		//g.fillRect(x, y, 32, 32);
		g.drawImage(tree_img, x, y, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
