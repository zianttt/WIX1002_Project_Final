package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.Game;

public class MiniDisplay {
	
	Font curFont, newFont;
	private FontManager fontManager;
	
	public int limits = 0;
	
	public static float HEALTH = 100;
	private float greenValue = 255f;
	
	private int score = 0;
	private int level = 1;
	private int goldEarned = 0;
	
	
	public MiniDisplay(FontManager fontManager) {
		this.fontManager = fontManager;
	}

	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100+(limits/2));
		greenValue = HEALTH*2;
		greenValue = Game.clamp(greenValue, 0, 255);
		
		score++;
	}
	
	public void render(Graphics2D g2d) {
		
		// set font
		g2d.setColor(new Color(255, 255, 255));
		g2d.setFont(fontManager.getMaruMonica());
		curFont = g2d.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 20F);
		g2d.setFont(newFont);
		
		
		g2d.setColor(Color.gray);
		g2d.fillRect(15, 15, 200 + limits, 32);
		g2d.setColor(new Color(75, (int)greenValue, 0)); // green color gradually turns red
		g2d.fillRect(15, 15, (int)HEALTH * 2, 32);
		g2d.setColor(Color.white);
		g2d.drawRect(15, 15, 200 + limits, 32);		
		
		g2d.drawString("Score: " + score, 15, 68);
	}
	
	public void reset() {
		HEALTH = 100;
		this.score = 0;
		this.level = 1;
		this.goldEarned = 0;
	}
	
	public void setscore(int score) {
		this.score = score;	
	}
	
	public int getScore() {
		return score;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getGoldEarned() {
		return goldEarned;
	}

	public void setGoldEarned(int goldEarned) {
		this.goldEarned = goldEarned;
	}
}






