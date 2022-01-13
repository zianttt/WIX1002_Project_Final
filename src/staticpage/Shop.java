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
	
	private int color;
	private boolean revert = true;

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
			if(mx >= 435 && mx <= 535) {
				if(my >= 450 && my <= 500) {
					backToMain = true;
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
		
		if(backToMain) {
			Game.gameState = STATES.ToMain;
			backToMain = false;
		}
	}
	
	
	public void render(Graphics2D g2d) {
		// set font
		g2d.setColor(new Color(155, 155, color));
		g2d.setFont(fontManager.getMaruMonica());
		curFont = g2d.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 55F);
		g2d.setFont(newFont);
		
		g2d.drawString("SHOP", Game.WIDTH/2-75, 75);
		
		
		newFont = curFont.deriveFont(Font.BOLD, 22F);
		g2d.setFont(newFont);
		g2d.setColor(new Color(155, 155, 245));
		
		// tower
		g2d.drawString("Tower", 285, 245);
		g2d.drawRoundRect(Game.WIDTH/2-240, 200, 100, 80, 30, 30);
		
		// wall
		g2d.drawString("Wall", 450, 245);
		g2d.drawRoundRect(Game.WIDTH/2-80, 200, 100, 80, 30, 30);
				
		// citizen
		g2d.drawString("Citizens", 598, 245);
		g2d.drawRoundRect(Game.WIDTH/2+80, 200, 100, 80, 30, 30);
		
		//back
		g2d.setColor(Color.white);
		newFont = curFont.deriveFont(Font.BOLD, 20F);
		g2d.setFont(newFont);
		g2d.drawString("Back", 464, 482);
		g2d.drawRoundRect(435, 450, 100, 50, 15, 15);
		
	}
	
	
}
























