package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import utils.ID;
import utils.SpriteSheet;

public abstract class SameBehaviour {
	
	protected int x, y;

	protected float velX = 0, velY = 0;
	protected ID id;
	protected SpriteSheet ss;
	protected int type;

	public SameBehaviour(int x, int y, ID id, SpriteSheet ss) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	
	public abstract void tick();
	public abstract void render(Graphics2D g2d);
	
	public abstract Rectangle getBounds(); // for collisions
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public float getVelY() {
		return velY;
	}

	public ID getId() {
		return id;
	}
	
	public int getType() {
		return type;
	}

}
