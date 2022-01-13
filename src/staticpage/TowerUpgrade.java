package staticpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game;
import objects.Tower;
import utils.AudioPlayer;
import utils.FontManager;
import utils.STATES;
import utils.Stats;


public class TowerUpgrade extends MouseAdapter{
	
	Font curFont, newFont;
	private FontManager fontManager;
	
	private int color;
	private boolean revert = true;
	
	private boolean freeze = true;
	
	
	public TowerUpgrade(FontManager fontManager) {
		this.fontManager = fontManager;
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
					if(mx >= Game.WIDTH/2-102 && mx <= Game.WIDTH/2 + 68) {
						if(my >= 100 && my <= 190) {
							Tower.towerAtk++;
							Stats.gold -= 100;
						}
					}
					
					// critical chance (max 50%)
					if(mx >= Game.WIDTH/2-102 && mx <= Game.WIDTH/2 + 68) {
						if(my >= 200 && my <= 290) {
							if(Tower.towerCritical+5 <= 50) {
								Tower.towerCritical += 5;
								Stats.gold -= 100;
							}		
						}
					}
					
					// acccuracy (max 100%)
					if(mx >= Game.WIDTH/2-102 && mx <= Game.WIDTH/2 + 68) {
						if(my >= 300 && my <= 390) {
							if(Tower.towerAcc+4 <= 100) {
								Tower.towerAcc += 4;
								Stats.gold -= 100;
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
		g2d.drawString("Tower Upgrade", Game.WIDTH/2-175, 75);
		
		
		newFont = curFont.deriveFont(Font.BOLD, 17F);
		g2d.setFont(newFont);
		g2d.setColor(new Color(255, 255, 255));
		
		g2d.drawString("Cost: " + 100 + "G", 445, 180);
		g2d.drawString("Cost: " + 100 + "G", 445, 280);
		g2d.drawString("Cost: " + 100 + "G", 445, 380);
		
		
		
		newFont = curFont.deriveFont(Font.BOLD, 20F);
		g2d.setFont(newFont);
		g2d.setColor(new Color(0, 0, 0));
		
		//box 1
		g2d.drawString("Attack Point +1", 424, 130);
		g2d.drawString("Current: " + (int)Tower.towerAtk, 438, 155);
		g2d.drawRoundRect(Game.WIDTH/2-102, 100, 170, 90, 30, 30);
		
		//box 2
		g2d.drawString("Critical Chance +5%", 410, 230);
		g2d.drawString("Current: " + (int)Tower.towerCritical, 438, 255);
		g2d.drawRoundRect(Game.WIDTH/2-102, 200, 170, 90, 30, 30);
				
		//box 3
		g2d.drawString("Accuracy +4%", 428, 330);
		g2d.drawString("Current: " + (int)Tower.towerAcc, 438, 355);
		g2d.drawRoundRect(Game.WIDTH/2-102, 300, 170, 90, 30, 30);
		
		//back
		g2d.setColor(Color.white);
		g2d.drawString("Back", 464, 482);
		g2d.drawRoundRect(435, 450, 100, 50, 15, 15);
			
	}
}