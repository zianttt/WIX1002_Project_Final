package com.game.main;

import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
	
	Font curFont, newFont;
	private Game game;
	
	public Menu(Game game) {
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e) {
		
		if(Game.gameState == STATES.Menu) {
			
			int mx = e.getX();
			int my = e.getY();
			
			if(mx != 0 && my != 0) {
				AudioPlayer.clickSound.play();
			}
			
			// Play button
			if(mx >= Game.WIDTH/2-240 && mx <= Game.WIDTH/2 - 140) {
				if(my >= 200 && my <= 280) {
					Game.gameState = STATES.Play;
				}
			}
			
			// info
			if(mx >= Game.WIDTH/2-80 && mx <= Game.WIDTH/2 + 20) {
				if(my >= 200 && my <= 280) {
					Game.gameState = STATES.Info;
				}
			}
			
			// quit
			if(mx >= Game.WIDTH/2+80 && mx <= Game.WIDTH/2 + 180) {
				if(my >= 200 && my <= 280) {
					System.exit(1);
				}
			}
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.black);
		g.setFont(game.fontManager.getMaruMonica());
		//g.setFont(new Font("comicsan", 0, 48));
		curFont = g.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 50F);
		g.setFont(newFont);
		g.drawString("Till The End", Game.WIDTH/2-130, 90);
		
		//box 1
		newFont = curFont.deriveFont(Font.BOLD, 22F);
		g.setFont(newFont);
		g.drawString("Start", 290, 243);
		g.drawRoundRect(Game.WIDTH/2-240, 200, 100, 80, 20, 20);
		
		//box 2
		g.drawString("Info", 455, 243);
		g.drawRoundRect(Game.WIDTH/2-80, 200, 100, 80, 20, 20);
				
		//box 3
		g.drawString("Quit", 615, 243);
		g.drawRoundRect(Game.WIDTH/2+80, 200, 100, 80, 20, 20);
		
	}

}
