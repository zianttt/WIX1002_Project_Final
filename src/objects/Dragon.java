package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import utils.Battle;
import utils.BufferedImageLoader;
import utils.Handler;
import utils.ID;
import utils.SpriteSheet;

public class Dragon extends GameObject {
	
	// stats
	public static int level = 1;
	public static float maxHp = 100, hp = 100, atk = 7, critical = 20, acc = 80;
	
	// sprites
	private BufferedImage dragon1, dragon2, dragon3, dragonFire1, dragonFire2, dragonFire3;
	private int spriteCount = 0;
	private int spriteNum = 1;
	
	// attack use
	private float tempAtk = 0;
	private Wall wall;
	private Handler battleHandler;
	Random r = new Random();
	
	
	public Dragon(int x, int y, ID id, SpriteSheet ss, Handler battleHandler, Wall wall, BufferedImageLoader loader) {
		super(x, y, id, ss);
		this.battleHandler = battleHandler;
		this.wall = wall;
		this.ss = ss;
		
		dragon1 = ss.grabImage(0, 0, 180, 180);
		dragon2 = ss.grabImage(180, 0, 180, 180);
		dragon3 = ss.grabImage(360, 0, 180, 180);
		dragonFire1  = ss.grabImage(0, 180, 180, 180);
		dragonFire2 = ss.grabImage(180, 180, 180, 180);
		dragonFire3 = ss.grabImage(360, 180, 180, 180);
		
		/*
		dragon1 = loader.loadImage("/dragon11.png");
		dragon2 = loader.loadImage("/dragon22.png");
		dragon3 = loader.loadImage("/dragon33.png");
		dragonFire1  = loader.loadImage("/dragon1fire.png");
		dragonFire2 = loader.loadImage("/dragon2fire.png");
		dragonFire3 = loader.loadImage("/dragon3fire.png");
		*/
		
		velX = 0;
		velY = -3;
	}
	
	
	public void levelUp() {
		level++;
		maxHp+=15;
		hp = maxHp;
		atk++;
		critical+=2;
	}
	
	
	public String attack() {
		String msg;
		// attack or not
		if(r.nextInt(100) < acc) {
			//critical or not
			if(r.nextInt(100) < critical) {
				System.out.println("Dragon critical");
				tempAtk = atk * 2;
				msg = "Dragon critical attack: " + (int)tempAtk;
			}
			else {
				tempAtk = atk;
				msg = "Dragon attacks: " + (int)tempAtk;
			}
			
			if(wall.defence(tempAtk)) {
				msg = "Wall blocks the attack!";
			}
		}
		else {
			System.out.println("Dragon missed the target!");
			msg = "Dragon missed the target!";
			tempAtk = 0;
		}
		
		System.out.println("Dragon attacks: " + tempAtk);
		return msg;
	}
	
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
		
		/*
		if(Stats.timer1 <= 0) Stats.timer2--;
		if(Stats.timer2 <= 0) {
			if(Stats.timer3 >= 350) {
				int spawn = r.nextInt(15);
				if(spawn == 0) battleHandler.addObject(new Flame(x+40, y+40, ID.Dragon, battleHandler, ss));
			}
			else if(Stats.timer3 == 345) attack();
			else if(Stats.timer3 < -200) {
				attack();
				Stats.timer3 = 600;
				Stats.round++;
				if(Stats.round >= 3) {
					levelUp();
					Game.gameState = STATES.Play;
				}
			}
			Stats.timer3--;
			*/
			/*
			if(velX == 0) velX = 2;
			
			if(velX > 0)
			velX += 0.005f;
			else if(velX < 0)
			velX -= 0.005f;
		
			velX = Game.clamp(velX, -10, 10);
			*/		
		
		
		//if(y <= 0 || y >= Game.HEIGHT - 60) velY *= -1;
		//if(x <= 0 || x >= Game.WIDTH - 96) velX *= -1;
		
		//battleHandler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 80, 80, 0.008f, battleHandler));
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

}