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

public class Info extends MouseAdapter{
	
	private String[] generalInfoText = {"1. Press W S A D to move the player UP DOWN LEFT RIGHT",
										"2. Go to the wall door to battle with the dragon",
										"3. Upgrade wall, tower and citizen stats in shop",
										"4. Check status in citizen hall",
										"5. Ask event generator for something to happen..."}; 
	
	private String[] minigameInfoText = {"1. Control the player to avoid contacting the enemies",
										 "2. Press space to shoot bullet to kill the enemies",
										 "3. Scores will turn into gold after minigame"};
	
	Font curFont, newFont;
	private FontManager fontManager;
	
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
				if(mx >= Game.WIDTH/2-200 && mx <= Game.WIDTH/2 - 100) {
					if(my >= 200 && my <= 280) {
						Game.gameState = STATES.GeneralInfo;
					}
				}
				
				// minigame info
				if(mx >= Game.WIDTH/2+80 && mx <= Game.WIDTH/2 + 180) {
					if(my >= 200 && my <= 280) {
						Game.gameState = STATES.MiniGameInfo;
					}
				}
			}
			
			// back
			if(mx >= 425 && mx <= 525) {
				if(my >= 450 && my <= 500) {
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
		
	}
	
	public void render(Graphics2D g2d) {
		
		// set font
		g2d.setColor(Color.black);
		g2d.setFont(fontManager.getMaruMonica());
		curFont = g2d.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 22F);
		g2d.setFont(newFont);
		
		
		if(Game.gameState == STATES.Info) {
			g2d.drawString("Info Page", Game.WIDTH/2-100, 50);
			
			//general info
			g2d.drawString("General Info", 315, 243);
			g2d.drawRect(Game.WIDTH/2-200, 200, 100, 80);
			
			// minigame info
			g2d.drawString("Minigame Info", 592, 243);
			g2d.drawRect(Game.WIDTH/2+80, 200, 100, 80);
		}
		
		if(Game.gameState == STATES.GeneralInfo) {
			g2d.drawString("General Info", Game.WIDTH/2-100, 50);
			
			g2d.drawString(generalInfoText[0], 290, 100);
			g2d.drawString(generalInfoText[1], 290, 150);
			g2d.drawString(generalInfoText[2], 290, 200);
			g2d.drawString(generalInfoText[3], 290, 250);
			g2d.drawString(generalInfoText[4], 290, 300);
			
		}
		
		if(Game.gameState == STATES.MiniGameInfo) {
			g2d.drawString("General Info", Game.WIDTH/2-100, 50);
			
			g2d.drawString(minigameInfoText[0], 290, 100);
			g2d.drawString(minigameInfoText[1], 290, 150);
			g2d.drawString(minigameInfoText[2], 290, 200);
		}

		//back
		g2d.drawString("Back", 457, 480);
		g2d.drawRect(425, 450, 100, 50);
	}

}
