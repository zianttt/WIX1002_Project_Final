package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Game;
import staticpage.TextBox;
import utils.HUD;
import utils.AudioPlayer;
import utils.BufferedImageLoader;
import utils.Handler;
import utils.ID;
import utils.STATES;
import utils.SpriteSheet;
import utils.Stats;


public class Player extends GameObject{
	
	private Handler handler;
	
	private Game game;
	private EventsGenerator eGen;
	private Stats stats;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public static String direction;
	private BufferedImageLoader loader;
	private int spriteCount = 0;
	private int spriteNum = 1;
	
	public Player(int x, int y, ID id, Handler handler, SpriteSheet ss, EventsGenerator eGen, BufferedImageLoader loader, Game game, Stats stats) {
		super(x, y, id, ss);
		this.handler = handler;
		this.eGen = eGen;
		this.loader = loader;
		this.game = game;
		this.stats = stats;
		
		
		down1 = ss.grabImage(77, 9, 36, 52);
		down2 = ss.grabImage(206, 9, 36, 52);
		left1 = ss.grabImage(82, 76, 36, 52);
		left2 = ss.grabImage(206, 76, 36, 52);
		right1 = ss.grabImage(75, 140, 36, 52);
		right2 = ss.grabImage(206, 138, 36, 52);
		up1 = ss.grabImage(77, 204, 36, 52);
		up2 = ss.grabImage(207, 204, 36, 52);
		/*
		down1 = loader.loadImage("/down_1.png");
		down2 = loader.loadImage("/down_2.png");
		left1 = loader.loadImage("/left_1.png");
		left2 = loader.loadImage("/left_2.png");
		right1 = loader.loadImage("/right_1.png");
		right2 = loader.loadImage("/right_2.png");
		up1 = loader.loadImage("/up_1.png");
		up2 = loader.loadImage("/up_2.png");
		*/
		
		direction = "down";
		
	}

	@Override
	public void tick() {
		
		if(!Game.paused) {
			x += velX;	
			y += velY;
			
			collision();
			
			// smoother movements 
			// Keyinput > Hanlder > Player
			if(handler.isUp()) {
				direction = "up";
				velY = -5;
			}
			else if(!handler.isDown()) velY = 0;
			
			if(handler.isDown()) {
				direction = "down";
				velY = 5;
			}
			else if(!handler.isUp()) velY = 0;
			
			if(handler.isRight()) {
				direction = "right";
				velX = 5;
			}
			else if(!handler.isLeft()) velX = 0;
			
			if(handler.isLeft()) {
				direction = "left";
				velX = -5;
				/*
				if(Game.justInOut) {
					freeze--;
					if(freeze < 0) {
						Game.justInOut = false;
					}
				}
				*/
			}
			else if(!handler.isRight()) velX = 0;
			
			if(Game.gameState == STATES.Minigame) {
				x = (int) Game.clamp(x, 0, Game.WIDTH - 50);
				y = (int) Game.clamp(y, 0, Game.HEIGHT - 85);
			}
			
			
			// change sprite
			spriteCount++;
			if(spriteCount > 10) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCount = 0;
			}
			
		}	
	}
	
	private void collision() {
		for(int i=0; i < handler.object.size(); i++) {
			
			GameObject temp = handler.object.get(i);
			
			if(Game.gameState == STATES.Minigame) {
				if (temp.getId() == ID.BasicEnemy || temp.getId() == ID.FastEnemy || temp.getId() == ID.SmartEnemy) {
					if (getBounds().intersects(temp.getBounds())) {
						HUD.HEALTH -= 2;
					}
				}
			}
	
			if(Game.gameState == STATES.Play) {
				if((temp.getId() == ID.Block || temp.getId() == ID.Wall)) {
					
					if(getBounds().intersects(temp.getBounds())) {
						x += velX * -1;
						y += velY * -1;
					}
					
				}
				
				if(temp.getId() == ID.ShopDoor) {
					if(getBounds().intersects(temp.getBounds())) {
						x += velX * -1;
						y += velY * -1;
						
						Game.gameState = STATES.ToShop;
					}
				}
				
				if(temp.getId() == ID.MiniGameDoor) {
					if(getBounds().intersects(temp.getBounds())) {
						x += velX * -1;
						y += velY * -1;
						
						if(stats.miniGameLimit == 0) {
							TextBox.reminder = 1;
							Game.gameState = STATES.Reminder;
						}
						else {
							Game.gameState = STATES.ToMiniGame;
							stats.miniGameLimit--;
						}		
					}
				}
				
				if(temp.getId() == ID.StatusDoor) {
					if(getBounds().intersects(temp.getBounds())) {
						x += velX * -1;
						y += velY * -1;
						
						Game.gameState = STATES.ToStatus;
					}
				}
				
				if(temp.getId() == ID.WallDoor) {
					if(getBounds().intersects(temp.getBounds())) {
						x += velX * -1;
						y += velY * -1;
						
						if(EventsGenerator.maxEvents > 0) {
							TextBox.reminder = 0;
							Game.gameState = STATES.Reminder;
						}else {
							Game.gameState = STATES.ToBattle;
							AudioPlayer.mainMusic.stop();
							AudioPlayer.battleMusic.loop();
						}	
					}
				}

				if(temp.getId() == ID.EventGen) {
					if(getBounds().intersects(temp.getBounds())) {
						Game.gameState = STATES.EventText;
						x += velX * -1;
						y += velY * -1;
						if(EventsGenerator.maxEvents > 0) {
							eGen.generateEvent(stats.seasons[stats.cur_season]);
						}else {
							eGen.generateEventError();				
						}
					}
				}
				
				if(temp.getId() == ID.Chest) {
					if(getBounds().intersects(temp.getBounds())) {
						x += velX * -1;
						y += velY * -1;
						Game.gameState = STATES.ChestText;
					}
				}
			}	
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		//g2d.setColor(new Color(150, 75, 0));
		//g2d.fillRect(x, y, 32, 32);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}

		g2d.drawImage(image, x, y, 36, 52, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 50);
	}

}
