package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

import main.Game;
import objects.Dragon;
import objects.Flame;
import objects.Tower;
import objects.Wall;
import objects.WallAtk;

public class Battle {
	
	Font curFont, newFont;
	
	private Game game;
	private Handler battleHandler;
	private Dragon dragon;
	private Wall wall;
	private SpriteSheet ss;
	private Tower[] tower;
	private FontManager fontManager;
	
	private Stats stats;
	
	Random r = new Random();
	
	// battlefield
	public static int timer1 = 80;
	public static int timer2 = 50;
	public static int timer3 = 600;
	public static int round = 0;
	
	private int spawn;
	private boolean dragonPlaying = false;
	private int infoTimer = 250;
	private String outputMsg;
	
	public Battle(Game game, Handler battleHandler, SpriteSheet ss, Dragon dragon, Wall wall, Tower[] tower, FontManager fontManager, Stats stats) {
		this.game = game;
		this.battleHandler = battleHandler;
		this.ss = ss;
		this.dragon = dragon;
		this.wall = wall;
		this.tower = tower;	
		this.fontManager = fontManager;
		this.stats = stats;
		this.outputMsg = "";
		
	}
	
	public void reset() {
		timer1 = 115;
		timer2 = 50;
		timer3 = 600;
		round = 0;
	}
	
	private void checkGameOver() {
		if(Wall.wallHp <= 0 || Dragon.hp <= 0) {
			
			if(Wall.wallHp <= 0) Game.win = 0;
			if(Dragon.hp <= 0) Game.win = 1;
			
			tower[0].reset();
			wall.reset();
			dragon.reset();
			stats.reset();
			
			Game.gameState = STATES.GameOver;
		}
	}
	
	public void tick() {
		if(Game.gameState == STATES.Battle) {
			if(timer1 <= 0) timer2--;
			if(timer2 <= 0) {
				if(timer3 >= 350) {
					spawn = r.nextInt(15);
					if(spawn == 0) {
						battleHandler.addObject(new Flame(dragon.getX()+80, dragon.getY()+35, ID.Dragon, battleHandler, ss));
						if(!dragonPlaying) {
							AudioPlayer.dragonMusic.play();
							dragonPlaying = true;
						}
					}
				}
				else if(timer3 == 345) {
					outputMsg = dragon.attack();
					checkGameOver();
				}
				else if(timer3 >= 0 && timer3 < 250) {
					AudioPlayer.dragonMusic.stop();
					dragonPlaying = false;
					spawn = r.nextInt(45);
					if(spawn == 0) {
						for(Tower t: tower) {
							battleHandler.addObject(new WallAtk(t.getX()+15, t.getY()+32, ID.Dragon, battleHandler, ss));
						}
						
						AudioPlayer.shurikenSound.play();
						AudioPlayer.spearSound.play();

					}
				}
				else if(timer3 == -10) {
					outputMsg = tower[0].attack();
					checkGameOver();
				}
				else if(timer3 < -200) {
					timer3 = 600;
					round++;
					if(round >= 1) {
						dragon.levelUp();
						stats.newSeason();
						Game.gameState = STATES.ToMain;
						AudioPlayer.battleMusic.stop();
						AudioPlayer.mainMusic.loop();
					}
				}
				timer3--;
			}
		}
		
		
	}
	
	
	public void render(Graphics2D g2d) {
		
		// set font
		g2d.setColor(new Color(255, 50, 50));
		g2d.setFont(fontManager.getMaruMonica());
		curFont = g2d.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 25F);
		g2d.setFont(newFont);
		
		if(Game.gameState == STATES.Battle) {
			// dragon stats
			g2d.drawString("Drogon", Game.WIDTH - 250, Game.HEIGHT-250);
			g2d.drawString("Level: " + Dragon.level, Game.WIDTH - 250, Game.HEIGHT-220);
			g2d.drawString("Health Point: " + (int)Dragon.hp, Game.WIDTH - 250, Game.HEIGHT-190);
			g2d.drawString("Attack Point: " + (int)Dragon.atk, Game.WIDTH - 250, Game.HEIGHT-160);
			g2d.drawString("Critical Chance: " + (int)Dragon.critical + "%", Game.WIDTH - 250, Game.HEIGHT-130);
			g2d.drawString("Accuracy: " + (int)Dragon.acc + "%", Game.WIDTH - 250, Game.HEIGHT-100);
			
			// player stats  
			g2d.setColor(new Color(149, 53, 83));
			g2d.drawString("Wall's HealthPoint: " + (int)Wall.wallHp, 10, 200);
			
			if(infoTimer >= 0 && outputMsg.length() != 0) {
				g2d.setColor(new Color(255, 180, 54));
				g2d.drawString(outputMsg, 100, Game.HEIGHT/2 + 40);
				infoTimer--;
			}
			else {
				infoTimer = 250;
				outputMsg = "";
			}
			
		}
	}
	
}




























