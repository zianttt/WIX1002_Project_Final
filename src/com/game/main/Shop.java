package com.game.main;

import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;


public class Shop extends MouseAdapter{
	
	private Handler handler;
	private Stats stats;
	private boolean backToMain = false;
	
	
	public Shop(Handler handler, Stats stats) {
		this.handler = handler;
		this.stats = stats;

	}
	
	
	public void mousePressed(MouseEvent e) {
		
		if(Game.gameState == STATES.Shop) {
			
			int mx = e.getX();
			int my = e.getY();
			
			if(mx != 0 && my != 0) {
				AudioPlayer.clickSound.play();
			}
			
			// tower
			if(mx >= Game.WIDTH/2-240 && mx <= Game.WIDTH/2 - 140) {
				if(my >= 200 && my <= 280) {
					Game.gameState = STATES.TowerUp;
				}
			}
			
			// wall
			if(mx >= Game.WIDTH/2-80 && mx <= Game.WIDTH/2 + 20) {
				if(my >= 200 && my <= 280) {
					Game.gameState = STATES.WallUp;
				}
			}
			
			// citizen
			if(mx >= Game.WIDTH/2+80 && mx <= Game.WIDTH/2 + 180) {
				if(my >= 200 && my <= 280) {
					Game.gameState = STATES.CitizneUp;
				}
			}
			
			// back
			if(mx >= 425 && mx <= 525) {
				if(my >= 450 && my <= 500) {
					backToMain = true;
				}
			}
		}				
	}
	
	
	public void tick() {
		if(backToMain) {
			Game.gameState = STATES.ToMain;
			backToMain = false;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("comicsan", 0, 48));
		g.drawString("SHOP", Game.WIDTH/2-100, 50);
		
		//box 1
		g.setFont(new Font("comicsan", 0, 12));
		g.drawString("Tower", 290, 243);
		g.drawRect(Game.WIDTH/2-240, 200, 100, 80);
		
		//box 2
		g.drawString("Wall", 455, 243);
		g.drawRect(Game.WIDTH/2-80, 200, 100, 80);
				
		//box 3
		g.drawString("Citizens", 607, 243);
		g.drawRect(Game.WIDTH/2+80, 200, 100, 80);
		
		//back
		g.drawString("Back", 457, 480);
		g.drawRect(425, 450, 100, 50);
		
	}
	
	
}
























