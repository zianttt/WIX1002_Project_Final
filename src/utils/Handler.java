package utils;

import java.awt.Graphics2D;
import java.util.LinkedList;

import objects.SameBehaviour;

// A class to handle all game objects
public class Handler {
	
	// Create linkedlist to store all the game objects
	public LinkedList<SameBehaviour> object = new LinkedList<SameBehaviour>();
	
	private boolean up = false, down = false, left = false, right = false, action = false, test = false;;
	
	public void tick() {
		
		for(int i=0; i < object.size(); i++) {
			
			SameBehaviour temp = object.get(i);
			temp.tick();
			
		}
	}
	
	
	public void render(Graphics2D g2d) {
		
		for(int i=0; i < object.size(); i++) {
			
			SameBehaviour temp = object.get(i);
			temp.render(g2d);
			
		}
	}
	
	
	public void addObject(SameBehaviour gameObject) {
		object.add(gameObject);
	}
	
	
	public void removeObject(SameBehaviour gameObject) {
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


	public boolean isTest() {
		return test;
	}
	
	public void setTest(boolean test) {
		this.test = test;
	}
	
}
