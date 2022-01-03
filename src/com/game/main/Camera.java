package com.game.main;

public class Camera {
	
	private float x, y;
	
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void tick(GameObject object) {
		
		x += (((object.getX() - x) - 1000/2 + 32) * 0.065f) ; // * 0.05f || + 32
		y += (((object.getY() - y) - 563/2 + 32) * 0.065f) ; // * 0.05f || + 32
		
		
		if(x <= 0) x = 0;
		if(x >= 2086) x = 2086;
		if(y <= 0) y = 0;
		if(y >= 2546) y = 2546;
		
	}


	public float getX() {
		return x;
	}


	public void setX(float x) {
		this.x = x;
	}


	public float getY() {
		return y;
	}


	public void setY(float y) {
		this.y = y;
	}
}
