package minigame;

import java.awt.Color;
import java.awt.Graphics;

import com.game.main.Game;

public class HUD {
	
	public int bounds = 0;
	
	public static float HEALTH = 100;
	private float greenValue = 255f;
	
	private int score = 0;
	private int level = 1;
	private int goldEarned = 0;

	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100+(bounds/2));
		greenValue = HEALTH*2;
		greenValue = Game.clamp(greenValue, 0, 255);
		
		score++;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200 + bounds, 32);
		g.setColor(new Color(75, (int)greenValue, 0)); // green color gradually turns red
		g.fillRect(15, 15, (int)HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200 + bounds, 32);		
		
		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);
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






