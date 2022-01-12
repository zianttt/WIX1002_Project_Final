package utils;

import objects.GameObject;

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
		if(x >= 1318) x = 1318;
		if(y <= 0) y = 0;
		if(y >= 1781) y = 1781;
		
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
