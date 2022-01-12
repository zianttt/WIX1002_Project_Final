package staticpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game;
import utils.AudioPlayer;
import utils.STATES;

public class Menu extends MouseAdapter {
	
	Font curFont, newFont;
	private Game game;
	
	public Menu(Game game) {
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e) {
		
		if(Game.gameState == STATES.Menu) {
			
			int mx = e.getX();
			int my = e.getY();
			
			if(mx != 0 && my != 0) {
				AudioPlayer.clickSound.play();
			}
			
			// Play button
			if(mx >= Game.WIDTH/2-240 && mx <= Game.WIDTH/2 - 140) {
				if(my >= 377 && my <= 457) {
					Game.gameState = STATES.Play;
				}
			}
			
			// info
			if(mx >= Game.WIDTH/2-80 && mx <= Game.WIDTH/2 + 20) {
				if(my >= 377 && my <= 457) {
					Game.gameState = STATES.Info;
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
		
	}
	
	public void render(Graphics2D g2d) {
		
		g2d.setColor(Color.black);
		g2d.setFont(game.fontManager.getMaruMonica());
		//g2d.setFont(new Font("comicsan", 0, 48));
		curFont = g2d.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 50F);
		g2d.setFont(newFont);
		//g2d.drawString("Till The End", Game.WIDTH/2-130, 90);
		
		//box 1
		newFont = curFont.deriveFont(Font.BOLD, 22F);
		g2d.setFont(newFont);
		g2d.drawString("Start", 290, 420);
		g2d.drawRoundRect(Game.WIDTH/2-240, 377, 100, 80, 20, 20);
		
		//box 2
		g2d.drawString("Info", 455, 420);
		g2d.drawRoundRect(Game.WIDTH/2-80, 377, 100, 80, 20, 20);
				
		//box 3
		g2d.drawString("Quit", 615, 420);
		g2d.drawRoundRect(Game.WIDTH/2+80, 377, 100, 80, 20, 20);
		
	}

}
