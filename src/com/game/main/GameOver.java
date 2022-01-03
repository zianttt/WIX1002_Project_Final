package com.game.main;

import java.awt.Graphics;

import minigame.HUD;

import java.awt.Color;
import java.awt.Font;

public class GameOver {
	
	private Handler handler;
	private HUD hud;
	
	private static String[] gameOverTexts = {"You lost...", "You won!"};
	
	public GameOver(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}

	public void render(Graphics g) {
		
		g.setColor(Color.white);
		g.setFont(new Font("comicsan", 35, 35));
		
		if(Game.gameState == STATES.GameOver) {
			
			g.drawString(gameOverTexts[Game.win], Game.WIDTH / 2 -100, Game.HEIGHT / 2 - 50);
			g.setFont(new Font("comicsan", 15, 15));
			g.drawString("Press Space To Go Back To Main Menu", Game.WIDTH / 2 -150, Game.HEIGHT - 200);
		}
		
		if(Game.gameState == STATES.MinigameOver) {
			g.drawString("You died...", Game.WIDTH / 2 -150, Game.HEIGHT / 2 - 50);
			g.setFont(new Font("comicsan", 25, 25));
			g.drawString("You earned " + hud.getGoldEarned() + " gold for your country!", Game.WIDTH / 2 -150, Game.HEIGHT / 2);
			g.setFont(new Font("comicsan", 15, 15));
			g.drawString("Press Space To Go Back To Main Menu", Game.WIDTH / 2 -150, Game.HEIGHT / 2 + 50);
		}
		
		
	}
	
	public void tick() {
		
		if(handler.isAction()) {
			if(Game.gameState == STATES.GameOver) {
				Game.gameState = STATES.Menu;
				AudioPlayer.mainMusic.loop();
			}
			if(Game.gameState == STATES.MinigameOver) {
				hud.reset();
				Game.gameState = STATES.ToMain;
			}
		}
		
	}
	
}
