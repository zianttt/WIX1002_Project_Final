package staticpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game;
import objects.Citizen;
import utils.AudioPlayer;
import utils.FontManager;
import utils.STATES;
import utils.Stats;


public class CitizenUpgrade extends MouseAdapter{
	
	private int color;
	private boolean revert = true;
	
	private Stats stats;
	
	private FontManager fontManager;
	private Font curFont, newFont;
	private boolean freeze = true;
	
	
	public CitizenUpgrade(FontManager fontManager, Stats stats) {
		this.fontManager = fontManager;
		this.stats = stats;
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
				
				if(stats.gold >= 50) {
					// box 1 emotional
					if(mx >= 315 && mx <= 465) {
						if(my >= 100 && my <= 190) {
							if(Citizen.emo > 0) {
								Citizen.emo -= 50;
								stats.gold -= 50;
							}				
						}
					}
					
					//box 2 nervous
					if(mx >= 315 && mx <= 465) {
						if(my >= 200 && my <= 290) {
							if(Citizen.nervous > 0) {
								Citizen.nervous -= 50;
								stats.gold -= 50;	
							}
						}
					}
					
					// box 3 lazy
					if(mx >= 315 && mx <= 465) {
						if(my >= 300 && my <= 390) {
							if(Citizen.lazy > 0) {
								Citizen.lazy -= 50;
								stats.gold -= 50;
							}
						}
					}
					
					//box 4 berserk
					if(mx >= 515 && mx <= 665) {
						if(my >= 100 && my <= 190) {
							if(Citizen.berserk < 100) {
								Citizen.berserk += 50;
								Citizen.statusCheck();
								stats.gold -= 50;
							}
						}
					}
					
					//box 5 diligent
					if(mx >= 515 && mx <= 665) {
						if(my >= 200 && my <= 290) {
							if(Citizen.diligent < 100) {
								Citizen.diligent += 50;
								Citizen.statusCheck();
								stats.gold -= 50;		
							}
						}
					}
					
					//box 6 fearless
					if(mx >= 515 && mx <= 665) {
						if(my >= 300 && my <= 390) {
							if(Citizen.fearless < 100) {
								Citizen.fearless += 50;
								Citizen.statusCheck();
								stats.gold -= 50;
							}
						}
					}
				}
				
				// back
				if(mx >= 435 && mx <= 535) {
					if(my >= 450 && my <= 500) {
						freeze = true;
						Game.gameState = STATES.Shop;
					}
				}
			}	
		}	
	}
	
	
	public void render(Graphics2D g2d) {
		
		if(!revert) {
			color--;
			if(color == 0) revert = true;
		}else {
			color++;
			if(color == 255) revert = false;
		}
		
		
		// set font
		g2d.setColor(new Color(155, 155, color));
		g2d.setFont(fontManager.getMaruMonica());
		curFont = g2d.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 55F);
		g2d.setFont(newFont);
		
		// title
		g2d.drawString("Citizen Upgrade", Game.WIDTH/2-175, 50);
		
		
		newFont = curFont.deriveFont(Font.BOLD, 17F);
		g2d.setFont(newFont);
		g2d.setColor(Color.white);
		
		g2d.drawString("Cost: " + 50 + "G", 359, 180);
		g2d.drawString("Cost: " + 50 + "G", 359, 280);
		g2d.drawString("Cost: " + 50 + "G", 359, 380);
		g2d.drawString("Cost: " + 50 + "G", 558, 180);
		g2d.drawString("Cost: " + 50 + "G", 558, 280);
		g2d.drawString("Cost: " + 50 + "G", 558, 380);
		
		// change font size
		g2d.setColor(Color.black);
		newFont = curFont.deriveFont(Font.BOLD, 20F);
		g2d.setFont(newFont);
		
		//box 1
		g2d.drawString("Emotional -50", 336, 130);
		g2d.drawString("Current: " + Citizen.emo, 347, 155);
		g2d.drawRoundRect(315, 100, 150, 90, 25, 25);
		
		//box 2
		g2d.drawString("Nervous -50", 340, 230);
		g2d.drawString("Current: " + Citizen.nervous, 347, 255);
		g2d.drawRoundRect(315, 200, 150, 90, 25, 25);
				
		//box 3
		g2d.drawString("Lazy -50", 351, 330);
		g2d.drawString("Current: " + Citizen.lazy, 347, 355);
		g2d.drawRoundRect(315, 300, 150, 90, 25, 25);
		
		//box 4
		g2d.drawString("Berserk +50", 542, 130);
		g2d.drawString("Current: " + Citizen.berserk, 546, 155);
		g2d.drawRoundRect(515, 100, 150, 90, 25, 25);
		
		//box 5
		g2d.drawString("Diligent +50", 541, 230);
		g2d.drawString("Current: " + Citizen.diligent, 546, 255);
		g2d.drawRoundRect(515, 200, 150, 90, 25, 25);
		
		//box 6
		g2d.drawString("Fearless +50", 540, 330);
		g2d.drawString("Current: " + Citizen.fearless, 546, 355);
		g2d.drawRoundRect(515, 300, 150, 90, 25, 25);
		
		//back
		g2d.setColor(Color.white);
		g2d.drawString("Back", 464, 482);
		g2d.drawRoundRect(435, 450, 100, 50, 15, 15);
			
	}
}
