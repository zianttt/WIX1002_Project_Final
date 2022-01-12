package staticpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.Game;
import utils.AudioPlayer;
import utils.FontManager;
import utils.HUD;
import utils.Handler;
import utils.STATES;

public class GameOver {
	
	private Handler handler;
	private HUD hud;
	Font curFont, newFont;
	private FontManager fontManager;
	
	private static String[] gameOverTexts = {"You lost...", "You won!"};
	
	public GameOver(Handler handler, HUD hud, FontManager fontManager) {
		this.handler = handler;
		this.hud = hud;
		this.fontManager = fontManager;
	}
	

	public void render(Graphics2D g2d) {
		
		// set font
		g2d.setColor(Color.white);
		g2d.setFont(fontManager.getMaruMonica());
		curFont = g2d.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 22F);
		g2d.setFont(newFont);
		
		if(Game.gameState == STATES.GameOver) {
			
			g2d.drawString(gameOverTexts[Game.win], Game.WIDTH / 2 -100, Game.HEIGHT / 2 - 50);
			g2d.drawString("Press Space To Go Back To Main Menu", Game.WIDTH / 2 -150, Game.HEIGHT - 200);
		}
		
		if(Game.gameState == STATES.MinigameOver) {
			g2d.drawString("You died...", Game.WIDTH / 2 -150, Game.HEIGHT / 2 - 50);
			g2d.drawString("You earned " + hud.getGoldEarned() + " gold for your country!", Game.WIDTH / 2 -150, Game.HEIGHT / 2);
			g2d.drawString("Press Space To Go Back To Main Menu", Game.WIDTH / 2 -150, Game.HEIGHT / 2 + 50);
		}
		
		
	}
	
	public void tick() {
		
		if(handler.isAction()) {
			if(Game.gameState == STATES.GameOver) {
				Game.gameState = STATES.Menu;
				AudioPlayer.mainMusic.loop();
			}
			if(Game.gameState == STATES.MinigameOver) {
				hud.reset();
				Game.gameState = STATES.ToMain;
			}
		}
		
	}
	
}
