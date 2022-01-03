package com.game.main;

import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class StatusCheck extends MouseAdapter{
	
	private boolean backToMain = false;
	
	public StatusCheck() {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
		if(Game.gameState == STATES.Status) {
			int mx = e.getX();
			int my = e.getY();
			
			if(mx != 0 && my != 0) {
				AudioPlayer.clickSound.play();
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
		g.drawString("Status", Game.WIDTH/2-100, 50);
		
		
		//box 1
		g.drawRect(130, 100, 320, 250);
		g.setFont(new Font("comicsan", 0, 18));
		g.drawString("Tower Critical Chance: " + (int)Tower.towerCritical, 140, 130);	
		g.drawString("Tower Attack Point: " + (int)Tower.towerAtk, 140, 170);
		g.drawString("Tower Accuracy: " + (int)Tower.towerAcc, 140, 210);	
		g.drawString("Wall Health: " + (int)Wall.wallHp, 140, 250);	
		g.drawString("Wall Block Chance: " + (int)Wall.wallBlock, 140, 290);	
		g.drawString("Tax rate: " + Stats.tax, 140, 330);	
		
		
		//box 4
		g.drawRect(500, 100, 320, 250);
		g.drawString("Emotional: " + Citizen.berserk, 510, 130);
		g.drawString("Nervous: " + Citizen.nervous, 510, 170);
		g.drawString("Lazy: " + Citizen.lazy, 510, 210);
		g.drawString("Berserk: " + Citizen.berserk, 510, 250);
		g.drawString("Diligent: " + Citizen.diligent, 510, 290);
		g.drawString("Fearless: " + Citizen.fearless, 510, 330);
		
		
		//back
		g.drawString("Back", 452, 480);
		g.drawRect(425, 450, 100, 50);
		
	}
}
