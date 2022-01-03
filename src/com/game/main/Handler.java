package com.game.main;

import java.util.LinkedList;
import java.awt.Graphics;

// A class to handle all game objects
public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private boolean up = false, down = false, left = false, right = false, action = false;
	
	public void tick() {
		
		for(int i=0; i < object.size(); i++) {
			
			GameObject temp = object.get(i);
			temp.tick();
			
		}
	}
	
	
	public void render(Graphics g) {
		
		for(int i=0; i < object.size(); i++) {
			
			GameObject temp = object.get(i);
			temp.render(g);
			
		}
	}
	
	
	public void addObject(GameObject gameObject) {
		object.add(gameObject);
	}
	
	
	public void removeObject(GameObject gameObject) {
		object.remove(gameObject);
	}
	
	
	public void clearBattleField() {
		object.clear();
	}
	
	
	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public boolean isAction() {
		return action;
	}
	
	public void setAction(boolean action) {
		this.action = action;
	}
	
}
