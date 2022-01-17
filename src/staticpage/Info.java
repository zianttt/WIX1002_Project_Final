	package staticpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game;
import utils.AudioPlayer;
import utils.FontManager;
import utils.STATES;

// Main menu info page
public class Info extends MouseAdapter{
	
	// Displays
	Font curFont, newFont;
	private FontManager fontManager;
	private int color;
	private boolean revert = true;
	
	// General info
	private String[] generalInfoText = {"1. W S A D to move UP DOWN LEFT RIGHT",
										"2. Fight the dragon!",
										"3. Upgrade equipments in shop",
										"4. Check status in citizen hall",
										"5. Make a wish to the magic lamp...",
										"6. Walk around and discover treasures!",
										"7. Press ESC to pause game."
										}; 
	
	// Minigame info
	private String[] minigameInfoText = {"1. Avoid the dragon's flames",
										 "2. Survive longer to earn more golds",
										 };
	
	
	public Info(FontManager fontManager) {
		this.fontManager = fontManager;
	}
	
	
	public void mousePressed(MouseEvent e) {
		
		if(Game.gameState == STATES.Info || Game.gameState == STATES.GeneralInfo || Game.gameState == STATES.MiniGameInfo) {
			
			int mx = e.getX();
			int my = e.getY();
			
			if(mx != 0 && my != 0) {
				AudioPlayer.clickSound.play();
			}
			
			if(Game.gameState == STATES.Info) {
				
				// general info
				if(mx >= Game.WIDTH/2-150 && mx <= Game.WIDTH/2 - 30) {
					if(my >= 200 && my <= 280) {
						Game.gameState = STATES.GeneralInfo;
					}
				}
				
				// minigame info
				if(mx >= Game.WIDTH/2+30 && mx <= Game.WIDTH/2 + 150) {
					if(my >= 200 && my <= 280) {
						Game.gameState = STATES.MiniGameInfo;
					}
				}
			}
			
			// back
			if(mx >= 455 && mx <= 555) {
				if(my >= 367 && my <= 417) {
					if(Game.gameState != STATES.Info) {
						Game.gameState = STATES.Info;
					}
					else {
						Game.gameState = STATES.Menu;
					}		
				}
			}
		}
	}
	
	public void tick() {
		
		if(!revert) {
			color--;
			if(color == 0) revert = true;
		}else {
			color++;
			if(color == 255) revert = false;
		}
		
	}
	
	public void render(Graphics2D g2d) {
	
		// set font
		g2d.setColor(new Color(color, 155, 155));
		g2d.setFont(fontManager.getMaruMonica());
		curFont = g2d.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 35F);
		g2d.setFont(newFont);
		
		
		if(Game.gameState == STATES.Info) {
			
			g2d.setColor(Color.black);
			g2d.drawString("Info Page", Game.WIDTH/2-57, 150);
			
			newFont = curFont.deriveFont(Font.BOLD, 20F);
			g2d.setFont(newFont);
			
			
			
			g2d.drawString("General", 377, 245);
			g2d.drawString("Minigame", 550, 245);
			
			//general info
			// minigame info
			g2d.setColor(new Color(color, 0, 155));
			g2d.drawRoundRect(Game.WIDTH/2-150, 200, 120, 80, 25, 25);
			g2d.drawRoundRect(Game.WIDTH/2+30, 200, 120, 80, 25, 25);
			
			
			
			
		}
		
		if(Game.gameState == STATES.GeneralInfo) {
			
			g2d.setColor(Color.black);
			g2d.drawString("General Info", Game.WIDTH/2-75, 150);
			
			newFont = curFont.deriveFont(Font.BOLD, 20F);
			g2d.setFont(newFont);
			
			g2d.drawString(generalInfoText[0], 345, 210);
			g2d.drawString(generalInfoText[1], 345, 235);
			g2d.drawString(generalInfoText[2], 345, 260);
			g2d.drawString(generalInfoText[3], 345, 285);
			g2d.drawString(generalInfoText[4], 345, 310);
			g2d.drawString(generalInfoText[5], 345, 335);
			g2d.drawString(generalInfoText[6], 345, 360);
			
		}
		
		if(Game.gameState == STATES.MiniGameInfo) {
			g2d.setColor(Color.black);
			g2d.drawString("Minigame", Game.WIDTH/2-65, 150);
			
			newFont = curFont.deriveFont(Font.BOLD, 20F);
			g2d.setFont(newFont);
			
			g2d.drawString(minigameInfoText[0], 355, 205);
			g2d.drawString(minigameInfoText[1], 355, 250);
		}

		//back
		g2d.setColor(Color.black);
		g2d.drawRoundRect(455, 367, 100, 50, 15, 15);
		g2d.drawString("Back", 484, 400);
	}
}
