package staticpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game;
import objects.Citizen;
import objects.Tower;
import objects.Wall;
import utils.AudioPlayer;
import utils.FontManager;
import utils.STATES;

// Handles the display of status check hall
public class StatusCheck extends MouseAdapter{
	
	// Displays
	Font curFont, newFont;
	private FontManager fontManager;
	private int color;
	private boolean revert = true;
	
	// Game state logic
	private boolean backToMain = false;
	
	
	public StatusCheck(FontManager fontManager) {
		this.fontManager = fontManager;
	}
	
	public void mousePressed(MouseEvent e) {
		
		if(Game.gameState == STATES.Status) {
			int mx = e.getX();
			int my = e.getY();
			
			if(mx != 0 && my != 0) {
				AudioPlayer.clickSound.play();
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
		g2d.setColor(new Color(155, color, 155));
		g2d.setFont(fontManager.getMaruMonica());
		curFont = g2d.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 55F);
		g2d.setFont(newFont);
		
		
		g2d.drawString("Status", Game.WIDTH/2-85, 50);
		
		g2d.setColor(new Color(color, 155, 100));
		g2d.drawRoundRect(140, 80, 320, 250, 25, 25);
		g2d.drawRoundRect(510, 80, 320, 250, 25, 25);
		
		// change font size
		g2d.setColor(new Color(149, 53, 83));
		newFont = curFont.deriveFont(Font.BOLD, 20F);
		g2d.setFont(newFont);
		
		
		// display player related status
		g2d.drawString("Tower Critical Chance: " + (int)Tower.towerCritical, 150, 110);	
		g2d.drawString("Tower Attack Point: " + (int)Tower.towerAtk, 150, 150);
		g2d.drawString("Tower Accuracy: " + (int)Tower.towerAcc, 150, 190);	
		g2d.drawString("Wall Health: " + (int)Wall.wallHp, 150, 230);	
		g2d.drawString("Wall Block Chance: " + (int)Wall.wallBlock, 150, 270);	
		
		
		// citizens status
		g2d.drawString("Emotional: " + Citizen.berserk, 530, 110);
		g2d.drawString("Nervous: " + Citizen.nervous, 530, 150);
		g2d.drawString("Lazy: " + Citizen.lazy, 530, 190);
		g2d.drawString("Berserk: " + Citizen.berserk, 530, 230);
		g2d.drawString("Diligent: " + Citizen.diligent, 530, 270);
		g2d.drawString("Fearless: " + Citizen.fearless, 530, 310);
		
		
		//back
		g2d.setColor(Color.white);
		g2d.drawString("Back", 464, 482);
		g2d.drawRoundRect(435, 450, 100, 50, 15, 15);
		
	}
}
