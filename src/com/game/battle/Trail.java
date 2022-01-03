package com.game.battle;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.main.*;

import java.awt.AlphaComposite;
import java.awt.Color;

public class Trail extends GameObject {
	
	private float alpha = 1;
	private float life; // 0.001 < life < 0.1
	
	private Handler battleHandler;
	private Color color;
	
	private int width, height;
	

	public Trail(int x, int y, ID id, Color color, int width, int height, float life, Handler battleHandler, SpriteSheet ss) {
		super(x, y, id, ss);
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
		this.battleHandler = battleHandler;
	}

	@Override
	public void tick() {
		if(alpha > life) {
			alpha -= life - 0.0001f;
		}else battleHandler.removeObject(this);
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillRect(x, y, width, height);
		
		g2d.setComposite(makeTransparent(1));
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
