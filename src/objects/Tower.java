package objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Rectangle;

import utils.Handler;
import utils.ID;
import utils.SpriteSheet;

public class Tower extends GameObject{
	
	private Handler handler;
	Random r = new Random();
	
	// tower
	public static float towerAtk = 10;
	public static float towerCritical = 10;
	public static float towerAcc = 80;
	public static boolean tempDecrease = false;
	
	private float tempAtk = 0;
	
	private BufferedImage tower_img;

	public Tower(int x, int y, ID id, SpriteSheet tss, Handler handler) {
		super(x, y, id, tss);
		this.handler = handler;
		
		tower_img = tss.grabImage(32, 0, 32, 64);
	}
	
	public String attack() {
		String msg;
		// attack or not
		if(r.nextInt(100) < towerAcc) {
			//critical or not
			if(r.nextInt(100) < towerCritical) {
				System.out.println("Tower critical!");
				tempAtk = towerAtk * (float)1.5;
				msg = "Tower critical attack: " + (int)tempAtk;
			}
			else tempAtk = towerAtk;
			msg = "Tower attacks: " + (int)tempAtk;
		}
		else {
			System.out.println("Tower fails to attack");
			msg = "Tower missed the target!";
			tempAtk = 0;
		}
		System.out.println("Tower attacks: " + (int)tempAtk);
		Dragon.hp -= tempAtk;
		return msg;
	}
	
	public void reset() {
		towerAtk = 10;
		towerCritical = 10;
		towerAcc = 80;
		tempDecrease = false;
	}
	

	@Override
	public void tick() {
		
		/*
		if(Game.gameState == STATES.Battle) {
			Stats.timer1--;
			
			if(Stats.timer1 <= 0) Stats.timer2--;
			if(Stats.timer2 <= 0) {
				if(Stats.timer3 >= 0 && Stats.timer3 < 250) {
					int spawn = r.nextInt(15);
					if(spawn == 0) handler.addObject(new WallAtk(x+8, y-8, ID.Dragon, handler, ss));
				}
				else if(Stats.timer3 == -10) attack();
				else if(Stats.timer3 < -200) {
					Stats.timer3 = 600;
					Stats.round++;
					if(Stats.round >= 3) {
						Game.gameState = STATES.Play;
					}
				}
				Stats.timer3--;
			}
		}
		*/
	}

	@Override
	public void render(Graphics2D g2d) {	
		g2d.drawImage(tower_img, x, y, 48, 80, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 48, 80);
	}

}
