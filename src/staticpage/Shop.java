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


public class Shop extends MouseAdapter{
	

	Font curFont, newFont;
	private FontManager fontManager;
	private boolean backToMain = false;
	
	
	public Shop(FontManager fontManager) {
		this.fontManager = fontManager;
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
	
	
	public void render(Graphics2D g2d) {
		// set font
		g2d.setColor(Color.white);
		g2d.setFont(fontManager.getMaruMonica());
		curFont = g2d.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 22F);
		g2d.setFont(newFont);
		
		g2d.drawString("SHOP", Game.WIDTH/2-100, 50);
		
		//box 1
		g2d.drawString("Tower", 290, 243);
		g2d.drawRect(Game.WIDTH/2-240, 200, 100, 80);
		
		//box 2
		g2d.drawString("Wall", 455, 243);
		g2d.drawRect(Game.WIDTH/2-80, 200, 100, 80);
				
		//box 3
		g2d.drawString("Citizens", 607, 243);
		g2d.drawRect(Game.WIDTH/2+80, 200, 100, 80);
		
		//back
		g2d.drawString("Back", 457, 480);
		g2d.drawRect(425, 450, 100, 50);
		
	}
	
	
}
























