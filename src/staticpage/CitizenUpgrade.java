package staticpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game;
import objects.Citizen;
import utils.AudioPlayer;
import utils.Handler;
import utils.STATES;
import utils.Stats;


public class CitizenUpgrade extends MouseAdapter{
	
	private Handler handler;
	private boolean freeze = true;
	
	
	public CitizenUpgrade(Handler handler) {
		this.handler = handler;
	}
	
	
	public void mousePressed(MouseEvent e) {
		
		if(freeze) freeze = false;
		else {
			if(Game.gameState == STATES.CitizneUp) {
				
				int mx = e.getX();
				int my = e.getY();
				
				if(mx != 0 && my != 0) {
					AudioPlayer.clickSound.play();
				}
				
				if(Stats.gold >= 50) {
					// box 1 emotional
					if(mx >= 300 && mx <= 450) {
						if(my >= 100 && my <= 180) {
							if(Citizen.emo > 0) {
								if(Citizen.emo-50 <= 0) Citizen.emo = 0;
								else Citizen.emo -= 50;
								Stats.gold -= 50;
							}				
						}
					}
					
					//box 2 nervous
					if(mx >= 300 && mx <= 450) {
						if(my >= 200 && my <= 280) {
							if(Citizen.nervous > 0) {
								if(Citizen.nervous-50 <= 0) Citizen.nervous = 0;
								else Citizen.nervous -= 50;
								Stats.gold -= 50;	
							}
						}
					}
					
					// box 3 lazy
					if(mx >= 300 && mx <= 450) {
						if(my >= 300 && my <= 380) {
							if(Citizen.lazy > 0) {
								if(Citizen.lazy-50 <= 0) Citizen.lazy = 0;
								else Citizen.lazy -= 50;
								Stats.gold -= 50;
							}
						}
					}
					
					//box 4 berserk
					if(mx >= 500 && mx <= 650) {
						if(my >= 100 && my <= 180) {
							if(Citizen.berserk < 100) {
								if(Citizen.berserk+50 >= 100) Citizen.berserk = 100;
								else Citizen.berserk += 50;
								Citizen.statusCheck();
								Stats.gold -= 50;
							}
						}
					}
					
					//box 5 diligent
					if(mx >= 500 && mx <= 650) {
						if(my >= 200 && my <= 280) {
							if(Citizen.diligent < 100) {
								if(Citizen.diligent+50 >= 100) Citizen.diligent = 100;
								else Citizen.diligent += 50;
								Citizen.statusCheck();
								Stats.gold -= 50;		
							}
						}
					}
					
					//box 6 fearless
					if(mx >= 500 && mx <= 650) {
						if(my >= 300 && my <= 380) {
							if(Citizen.fearless < 100) {
								if(Citizen.fearless+50 >= 100) Citizen.fearless = 100;
								else Citizen.fearless += 50;
								Citizen.statusCheck();
								Stats.gold -= 50;
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
		g2d.drawString("Citizen Upgrade", Game.WIDTH/2-200, 50);
		
		
		//box 1
		g2d.setFont(new Font("comicsan", 0, 12));
		g2d.drawString("Emotional -50", 336, 130);
		g2d.drawString("Current: " + Citizen.emo, 345, 150);
		g2d.drawRect(300, 100, 150, 80);
		
		//box 2
		g2d.drawString("Nervous -50", 340, 230);
		g2d.drawString("Current: " + Citizen.nervous, 341, 250);
		g2d.drawRect(300, 200, 150, 80);
				
		//box 3
		g2d.drawString("Lazy -50", 351, 330);
		g2d.drawString("Current: " + Citizen.lazy, 345, 350);
		g2d.drawRect(300, 300, 150, 80);
		
		//box 4
		g2d.drawString("Berserk -50", 542, 130);
		g2d.drawString("Current: " + Citizen.berserk, 543, 150);
		g2d.drawRect(500, 100, 150, 80);
		
		//box 5
		g2d.drawString("Diligent +50", 541, 230);
		g2d.drawString("Current: " + Citizen.diligent, 543, 250);
		g2d.drawRect(500, 200, 150, 80);
		
		//box 6
		g2d.drawString("Fearless +50", 538, 330);
		g2d.drawString("Current: " + Citizen.fearless, 542, 350);
		g2d.drawRect(500, 300, 150, 80);
		
		//back
		g2d.drawString("Back", 457, 480);
		g2d.drawRect(425, 450, 100, 50);
			
	}
}
