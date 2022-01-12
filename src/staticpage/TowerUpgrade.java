package staticpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game;
import objects.Tower;
import utils.AudioPlayer;
import utils.Handler;
import utils.STATES;
import utils.Stats;


public class TowerUpgrade extends MouseAdapter{
	
	private Handler handler;
	private boolean freeze = true;
	
	
	public TowerUpgrade(Handler handler) {
		this.handler = handler;
	}
	
	
	public void mousePressed(MouseEvent e) {
		
		if(freeze) freeze = false;
		else {
			if(Game.gameState == STATES.TowerUp) {
				
				int mx = e.getX();
				int my = e.getY();
				
				if(mx != 0 && my != 0) {
					AudioPlayer.clickSound.play();
				}
				
				
				
				if(Stats.gold >= 100) {
					// attack point
					if(mx >= Game.WIDTH/2-110 && mx <= Game.WIDTH/2 + 40) {
						if(my >= 100 && my <= 180) {
							Tower.towerAtk++;
							Stats.gold -= 100;
						}
					}
					
					// critical chance (max 50%)
					if(mx >= Game.WIDTH/2-110 && mx <= Game.WIDTH/2 + 40) {
						if(my >= 200 && my <= 280) {
							if(Tower.towerCritical+5 <= 50) {
								Tower.towerCritical += 5;
								Stats.gold -= 100;
							}		
						}
					}
					
					// acccuracy (max 100%)
					if(mx >= Game.WIDTH/2-110 && mx <= Game.WIDTH/2 + 40) {
						if(my >= 300 && my <= 380) {
							if(Tower.towerAcc+4 <= 100) {
								Tower.towerAcc += 4;
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
	
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("comicsan", 0, 48));
		g.drawString("Tower Upgrade", Game.WIDTH/2-200, 50);
		
		//box 1
		g.setFont(new Font("comicsan", 0, 12));
		g.drawString("Attack Point +1", 423, 130);
		g.drawString("Current: " + (int)Tower.towerAtk, 435, 150);
		g.drawRect(Game.WIDTH/2-110, 100, 150, 80);
		
		//box 2
		g.drawString("Critical Percentage +5%", 400, 230);
		g.drawString("Current: " + (int)Tower.towerCritical, 435, 250);
		g.drawRect(Game.WIDTH/2-110, 200, 150, 80);
				
		//box 3
		g.drawString("Accuracy +4%", 428, 330);
		g.drawString("Current: " + (int)Tower.towerAcc, 435, 350);
		g.drawRect(Game.WIDTH/2-110, 300, 150, 80);
		
		//back
		g.drawString("Back", 457, 480);
		g.drawRect(425, 450, 100, 50);
			
	}
}