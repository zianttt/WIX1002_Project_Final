package staticpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game;
import objects.Wall;
import utils.AudioPlayer;
import utils.FontManager;
import utils.STATES;
import utils.Stats;


public class WallUpgrade extends MouseAdapter{
	
	private int color;
	private boolean revert = true;
	
	Font curFont, newFont;
	private FontManager fontManager;
	private boolean freeze = true;
	
	private Stats stats;
	
	
	public WallUpgrade(FontManager fontManager, Stats stats) {
		this.fontManager = fontManager;
		this.stats = stats;
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
				
				
				if(stats.gold >= 100) {
					// box 1
					if(mx >= Game.WIDTH/2-102 && mx <= Game.WIDTH/2 + 68) {
						if(my >= 100 && my <= 190) {
							Wall.wallHp += 75;
							stats.gold -= 100;
						}
					}
					
					//box 2
					if(mx >= Game.WIDTH/2-102 && mx <= Game.WIDTH/2 + 68) {
						if(my >= 200 && my <= 290) {
							if(Wall.wallBlock+5 <= 50) {
								Wall.wallBlock += 5;
								stats.gold -= 100;
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
		g2d.setColor(new Color(155, color, 155));
		g2d.setFont(fontManager.getMaruMonica());
		curFont = g2d.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 55F);
		g2d.setFont(newFont);
		
		
		
		g2d.drawString("Wall Upgrade", Game.WIDTH/2-155, 50);
		
		
		newFont = curFont.deriveFont(Font.BOLD, 17F);
		g2d.setFont(newFont);
		g2d.setColor(Color.white);
		
		g2d.drawString("Cost: " + 100 + "G", 447, 180);
		g2d.drawString("Cost: " + 100 + "G", 447, 280);
		
		newFont = curFont.deriveFont(Font.BOLD, 20F);
		g2d.setFont(newFont);
		g2d.setColor(new Color(155, 155, 240));
		
		//box 1
		g2d.drawString("Health Point +75%", 417, 130);
		g2d.drawString("Current: " +(int) Wall.wallHp, 434, 155);
		g2d.drawRoundRect(Game.WIDTH/2-102, 100, 170, 90, 25, 25);
		
		//box 2
		g2d.drawString("Block Chance +5%", 415, 230);
		g2d.drawString("Current: " + (int)Wall.wallBlock, 439, 255);
		g2d.drawRoundRect(Game.WIDTH/2-102, 200, 170, 90, 25, 25);
		
		//back
		g2d.setColor(Color.white);
		g2d.drawString("Back", 464, 482);
		g2d.drawRoundRect(435, 450, 100, 50, 15, 15);
			
	}
}
