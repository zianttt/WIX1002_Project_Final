package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import utils.Battle;
import utils.BufferedImageLoader;
import utils.ID;
import utils.SpriteSheet;

public class Dragon extends SameBehaviour {
	
	// stats
	private static int level = 1;
	private static float maxHp = 100, hp = 100, atk = 7, critical = 20, acc = 80;
	
	// sprites
	private BufferedImage dragon1, dragon2, dragon3;
	//private BufferedImage dragonFire1, dragonFire2, dragonFire3;
	private int spriteCount = 0;
	private int spriteNum = 1;
	
	// attack use
	private float tempAtk = 0;
	private Wall wall;
	private Random r = new Random();
	
	
	public Dragon(int x, int y, ID id, SpriteSheet ss, Wall wall, BufferedImageLoader loader) {
		super(x, y, id, ss);
		this.wall = wall;
		this.ss = ss;
		
		dragon1 = ss.grabImage(0, 0, 180, 180);
		dragon2 = ss.grabImage(180, 0, 180, 180);
		dragon3 = ss.grabImage(360, 0, 180, 180);
		//dragonFire1  = ss.grabImage(0, 180, 180, 180);
		//dragonFire2 = ss.grabImage(180, 180, 180, 180);
		//dragonFire3 = ss.grabImage(360, 180, 180, 180);
		
		velX = 0;
		velY = -3;
	}
	
	// Upgrade the dragon after battle
	public void levelUp() {
		level++;
		maxHp+=15;
		hp = maxHp;
		atk++;
		if(critical+2 <= 50) {
			critical+=2;
		}
		else {
			critical=50;
		}
	}
	
	// return attack message to be diplayed by the battle class
	public String attack() {
		String msg;
		// attack or not
		if(r.nextInt(100) < acc) {
			//critical or not
			if(r.nextInt(100) < critical) {
				//System.out.println("Dragon critical");
				tempAtk = atk * 2;
				msg = "Dragon critical attack: " + (int)tempAtk;
			}
			else {
				tempAtk = atk;
				msg = "Dragon attacks: " + (int)tempAtk;
			}
			
			// check if wall successfully blocked the attack
			if(wall.defence(tempAtk)) {
				msg = "Wall blocks the attack!";
			}
		}
		else {
			//System.out.println("Dragon missed the target!");
			msg = "Dragon missed the target!";
			tempAtk = 0;
		}
		
		return msg;
	}
	
	// Reset after game over
	public void reset() {
		level = 1;
		hp = 100; 
		atk = 7; 
		critical = 20; 
		acc = 80;
	}
	
	

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		
		if(Battle.timer1 <= 0) velY = 0;
		else Battle.timer1--;
		
		// change sprite
		spriteCount++;
		if(spriteCount > 20) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			else if(spriteNum == 3) {
				spriteNum = 0;
			}
			spriteCount = 0;
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		
		BufferedImage image = null;
		
		switch(spriteNum) {
		case 1:
			image = dragon1;
			break;
		case 2:
			image = dragon2;
			break;
		case 3:
			image = dragon3;
		}
			
		g2d.drawImage(image, x, y, 180, 180, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 180, 180);
	}

	public static int getLevel() {
		return level;
	}

	public static float getHp() {
		return hp;
	}

	public static void setHp(float hp) {
		Dragon.hp = hp;
	}

	public static float getAtk() {
		return atk;
	}

	public static float getCritical() {
		return critical;
	}

	public static float getAcc() {
		return acc;
	}
}