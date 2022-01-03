package com.game.battle;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import com.game.main.AudioPlayer;
import com.game.main.EventsGenerator;
import com.game.main.Game;
import com.game.main.Handler;
import com.game.main.ID;
import com.game.main.STATES;
import com.game.main.SpriteSheet;
import com.game.main.Stats;
import com.game.main.Tower;
import com.game.main.Wall;

public class Battle {
	
	private Game game;
	private Handler battleHandler;
	private Dragon dragon;
	private Wall wall;
	private SpriteSheet ss;
	private Tower tower;
	private EventsGenerator eGen;
	
	Font maruMonica, curFont, newFont;
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
	
	public Battle(Game game, Handler battleHandler, SpriteSheet ss, Dragon dragon, Wall wall, Tower tower, EventsGenerator eGen) {
		this.game = game;
		this.battleHandler = battleHandler;
		this.ss = ss;
		this.dragon = dragon;
		this.wall = wall;
		this.tower = tower;
		this.eGen = eGen;
		
		outputMsg = "";
		
		// create new font
		try {
			InputStream is = getClass().getResourceAsStream("/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
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
			
			tower.reset();
			wall.reset();
			dragon.reset();
			Stats.reset();
			
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
						battleHandler.addObject(new Flame(dragon.getX()+80, dragon.getY()+40, ID.Dragon, battleHandler, ss));
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
					spawn = r.nextInt(15);
					if(spawn == 0) {
						battleHandler.addObject(new WallAtk(tower.getX()+8, tower.getY()+32, ID.Dragon, battleHandler, ss));
						AudioPlayer.shurikenSound.play();
						AudioPlayer.spearSound.play();

					}
				}
				else if(timer3 == -10) {
					outputMsg = tower.attack();
					checkGameOver();
				}
				else if(timer3 < -200) {
					timer3 = 600;
					round++;
					if(round >= 3) {
						dragon.levelUp();
						Stats.newSeason();
						eGen.maxEvents = 2;
						Game.gameState = STATES.ToMain;
						AudioPlayer.battleMusic.stop();
						AudioPlayer.mainMusic.loop();
					}
				}
				timer3--;
			}
		}
		
		
	}
	
	
	public void render(Graphics g) {
		
		g.setColor(Color.white);
		g.setFont(maruMonica);
		curFont = g.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 25F);
		g.setFont(newFont);
		
		if(Game.gameState == STATES.Battle) {
			// dragon stats
			g.drawString("Drogon", 10, 25);
			g.drawString("Level: " + Dragon.level, 10, 60);
			g.drawString("Health Point: " + (int)Dragon.hp, 10, 90);
			g.drawString("Attack Point: " + (int)Dragon.atk, 10, 120);
			g.drawString("Critical Chance: " + (int)Dragon.critical + "%", 10, 150);
			g.drawString("Accuracy: " + (int)Dragon.acc + "%", 10, 180);
			
			// player stats
			g.drawString("Wall's HealthPoint: " + (int)Wall.wallHp, Game.WIDTH - 250, Game.HEIGHT - 100);
			
			if(infoTimer >= 0 && outputMsg.length() != 0) {
				g.drawString(outputMsg, Game.WIDTH - 300, Game.HEIGHT/2);
				infoTimer--;
			}
			else {
				infoTimer = 250;
				outputMsg = "";
			}
			
		}
	}
	
}




























