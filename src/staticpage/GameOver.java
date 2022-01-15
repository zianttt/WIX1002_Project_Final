package staticpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.Game;
import utils.AudioPlayer;
import utils.FontManager;
import utils.Handler;
import utils.MiniDisplay;
import utils.STATES;

public class GameOver {
	
	private Handler handler;
	private MiniDisplay miniDis;
	Font curFont, newFont;
	private FontManager fontManager;
	
	private static String[] gameOverTexts = {"You lost...", "You won!"};
	
	public GameOver(Handler handler, MiniDisplay miniDis, FontManager fontManager) {
		this.handler = handler;
		this.miniDis = miniDis;
		this.fontManager = fontManager;
	}
	

	public void render(Graphics2D g2d) {
		
		// set font
		g2d.setColor(new Color(255,192,203));
		g2d.setFont(fontManager.getMaruMonica());
		curFont = g2d.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 44F);
		g2d.setFont(newFont);
		
		if(Game.gameState == STATES.GameOver) {
			
			g2d.drawString(gameOverTexts[Game.win], Game.WIDTH / 2 -100, Game.HEIGHT / 2 - 50);
			
			newFont = curFont.deriveFont(Font.BOLD, 25F);
			g2d.setFont(newFont);
			g2d.setColor(new Color(255, 255, 255));
			g2d.drawString("Press Space To Go Back To Main Menu", Game.WIDTH / 2 -200, Game.HEIGHT/2);
		}
		
		if(Game.gameState == STATES.MinigameOver) {
			g2d.drawString("You died...", Game.WIDTH / 2 -85, Game.HEIGHT / 2 - 50);
			
			newFont = curFont.deriveFont(Font.BOLD, 30F);
			g2d.setFont(newFont);
			g2d.setColor(new Color(255, 255, 255));
			g2d.drawString("You earned " + miniDis.getGoldEarned() + " gold for your country!", Game.WIDTH / 2 -200, Game.HEIGHT / 2);
			g2d.drawString("Press Space To Go Back To Main Map", Game.WIDTH / 2 -210, Game.HEIGHT / 2 + 50);
		}
		
		
	}
	
	public void tick() {
		
		if(handler.isAction()) {
			if(Game.gameState == STATES.GameOver) {
				Game.gameState = STATES.Menu;
				AudioPlayer.mainMusic.loop();
			}
			if(Game.gameState == STATES.MinigameOver) {
				miniDis.reset();
				Game.gameState = STATES.ToMain;
			}
		}
		
	}
	
}
