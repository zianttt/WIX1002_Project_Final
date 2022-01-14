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

public class Menu extends MouseAdapter {
	
	private int color;
	private boolean revert = true;
	
	Font curFont, newFont;
	private FontManager fontManager;
	
	public Menu(FontManager fontManager) {
		this.fontManager = fontManager;
	}
	
	public void mousePressed(MouseEvent e) {
		
		if(Game.gameState == STATES.Menu) {
			
			int mx = e.getX();
			int my = e.getY();
			
			if(mx != 0 && my != 0) {
				AudioPlayer.clickSound.play();
			}
			
			// Info button
			if(mx >= Game.WIDTH/2-240 && mx <= Game.WIDTH/2 - 140) {
				if(my >= 377 && my <= 457) {
					Game.gameState = STATES.Info;
				}
			}
			
			// Play
			if(mx >= Game.WIDTH/2-80 && mx <= Game.WIDTH/2 + 20) {
				if(my >= 377 && my <= 457) {
					if(Game.menuTo == 0) {
						Game.gameState = STATES.Play;
					}
					else if(Game.menuTo == 1) {
						Game.gameState = STATES.Minigame;
					}
				}
			}
			
			// quit
			if(mx >= Game.WIDTH/2+80 && mx <= Game.WIDTH/2 + 180) {
				if(my >= 377 && my <= 457) {
					System.exit(1);
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
		
		g2d.setFont(fontManager.getMaruMonica());
		curFont = g2d.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 22F);
		g2d.setFont(newFont);
		
		// start
		g2d.setColor(new Color(color, color, 0));
		g2d.drawRoundRect(Game.WIDTH/2-80, 377, 100, 80, 20, 20);
		g2d.drawString("Start", 448, 422);
		
		// info and quit
		g2d.setColor(Color.black);
		g2d.drawRoundRect(Game.WIDTH/2-240, 377, 100, 80, 20, 20);
		g2d.drawRoundRect(Game.WIDTH/2+80, 377, 100, 80, 20, 20);
		g2d.drawString("Info", 292, 422);
		g2d.drawString("Quit", 613, 422);
	}
}
