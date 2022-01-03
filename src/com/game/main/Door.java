package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Door extends GameObject{

	public Door(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		/*
		if(this.id == ID.ShopDoor) {
			g.setColor(new Color(255, 255, 255));
			g.fillRect(x, y, 32, 32);
		}
		else if(this.id == ID.WallDoor){
			g.setColor(new Color(245, 245, 245));
			g.fillRect(x, y, 32, 32);
		}
		else if(this.id == ID.StatusDoor){
			g.setColor(new Color(150, 150, 150));
			g.fillRect(x, y, 32, 32);
		}
		else if(this.id == ID.MiniGameDoor){
			g.setColor(new Color(200, 200, 200));
			g.fillRect(x, y, 32, 32);
		}
		*/
		
	}

	@Override
	public Rectangle getBounds() {
		if(this.id == ID.ShopDoor) {
			return new Rectangle(x, y+5, 32, 32);
		}
		else if(this.id == ID.WallDoor){
			return new Rectangle(x, y, 32, 32);
		}
		else if(this.id == ID.StatusDoor){
			return new Rectangle(x, y-5, 32, 32);
		}
		else {
			return new Rectangle(x+5, y, 32, 32);
		}
	}

}
