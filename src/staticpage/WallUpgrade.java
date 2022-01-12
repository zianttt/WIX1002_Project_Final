package staticpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game;
import objects.Wall;
import utils.AudioPlayer;
import utils.Handler;
import utils.STATES;
import utils.Stats;


public class WallUpgrade extends MouseAdapter{
	
	private Handler handler;
	private boolean freeze = true;
	
	
	public WallUpgrade(Handler handler) {
		this.handler = handler;
	}
	
	
	public void mousePressed(MouseEvent e) {
		
		if(freeze) freeze = false;
		else {
			if(Game.gameState == STATES.WallUp) {
				
				int mx = e.getX();
				int my = e.getY();
				
				if(mx != 0 && my != 0) {
					AudioPlayer.clickSound.play();
				}
				
				
				if(Stats.gold >= 100) {
					// box 1
					if(mx >= Game.WIDTH/2-110 && mx <= Game.WIDTH/2 + 40) {
						if(my >= 100 && my <= 180) {
							Wall.wallHp += 75;
							Stats.gold -= 100;
						}
					}
					
					//box 2
					if(mx >= Game.WIDTH/2-110 && mx <= Game.WIDTH/2 + 40) {
						if(my >= 200 && my <= 280) {
							if(Wall.wallBlock+5 <= 50) {
								Wall.wallBlock += 5;
								Stats.gold -= 100;
							}				
						}
					}		
				}
			
				
				// back
				if(mx >= 425 && mx <= 525) {
					if(my >= 450 && my <= 500) {
						freeze = true;
						Game.gameState = STATES.Shop;
					}
				}
			}
		}			
	}
	
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.white);
		g2d.setFont(new Font("comicsan", 0, 48));
		g2d.drawString("Wall Upgrade", Game.WIDTH/2-180, 50);
		
		//box 1
		g2d.setFont(new Font("comicsan", 0, 12));
		g2d.drawString("Health Point +75%", 416, 130);
		g2d.drawString("Current: " +(int) Wall.wallHp, 429, 150);
		g2d.drawRect(Game.WIDTH/2-110, 100, 150, 80);
		
		//box 2
		g2d.drawString("Block Percentage +5%", 405, 230);
		g2d.drawString("Current: " + (int)Wall.wallBlock, 433, 250);
		g2d.drawRect(Game.WIDTH/2-110, 200, 150, 80);
		
		//back
		g2d.drawString("Back", 457, 480);
		g2d.drawRect(425, 450, 100, 50);
			
	}
}
