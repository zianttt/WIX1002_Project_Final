package objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Rectangle;

import utils.ID;
import utils.SpriteSheet;

public class Tower extends SameBehaviour{
	
	// tower stats
	private static float towerAtk = 10;
	private static float towerCritical = 10;
	private static float towerAcc = 80;
	private static boolean tempDecrease = false;
	
	// for attack calculation use
	private float tempAtk = 0;
	private Random r = new Random();
	
	// display
	private BufferedImage tower_img;

	public Tower(int x, int y, ID id, SpriteSheet tss) {
		super(x, y, id, tss);
		
		tower_img = tss.grabImage(32, 0, 32, 64);
	}
	
	public String attack() {
		String msg;
		// attack or not
		if(r.nextInt(100) < towerAcc) {
			//critical or not
			if(r.nextInt(100) < towerCritical) {
				//System.out.println("Tower critical!");
				tempAtk = towerAtk * (float)1.5;
				msg = "Tower critical attack: " + (int)tempAtk;
			}
			else tempAtk = towerAtk;
			msg = "Tower attacks: " + (int)tempAtk;
		}
		else {
			//System.out.println("Tower fails to attack");
			msg = "Tower missed the target!";
			tempAtk = 0;
		}
		//System.out.println("Tower attacks: " + (int)tempAtk);
		// deduct dragon hp if successfully attacked
		Dragon.setHp(Dragon.getHp()-tempAtk);
		if(tempDecrease) {
			msg += " (accuracy affected by current event)";
		}
		return msg;
	}
	
	public void newSeason() {
		if(tempDecrease) {
			towerAcc += 20;
		}
	}
	
	public void reset() {
		towerAtk = 10;
		towerCritical = 10;
		towerAcc = 80;
		setTempDecrease(false);
	}
	

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics2D g2d) {	
		g2d.drawImage(tower_img, x, y, 48, 80, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 48, 80);
	}

	public static boolean isTempDecrease() {
		return tempDecrease;
	}

	public static void setTempDecrease(boolean tempDecrease) {
		Tower.tempDecrease = tempDecrease;
	}

	public static float getTowerAtk() {
		return towerAtk;
	}

	public static void setTowerAtk(float towerAtk) {
		Tower.towerAtk = towerAtk;
	}

	public static float getTowerCritical() {
		return towerCritical;
	}

	public static void setTowerCritical(float towerCritical) {
		Tower.towerCritical = towerCritical;
	}

	public static float getTowerAcc() {
		return towerAcc;
	}

	public static void setTowerAcc(float towerAcc) {
		Tower.towerAcc = towerAcc;
	}
	

}
