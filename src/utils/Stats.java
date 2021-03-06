package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

import main.Game;
import objects.EventsGenerator;

// Handles all the status
public class Stats {
	
	// main game
	private int gold = 200;
	private int[] taxs = {200, 250, 300, 350, 400};
	private int cur_tax = 0;
	private int year = 1;
	private int cur_season = 0;
	private String[] seasons = {"Spring", "Summer", "Autumn", "Winter"};
	private int miniGameLimit = 2;
	
	// others
	private FontManager fontManager;
	Font curFont, newFont;
	Random r = new Random();
	
	public Stats(FontManager fontManager) {
		this.fontManager = fontManager;	
	}
	
	// update status when entering new season
	public void newSeason() {
		if(cur_season < 3) cur_season++;
		else {
			cur_season = 0;
			year++;	
		}
		int temp = r.nextInt(4);
		cur_tax = taxs[temp];
		gold+=cur_tax;
		setMiniGameLimit(2);
		EventsGenerator.setMaxEvents(2);
		EventsGenerator.setEventError(false);
	}
	
	// set default status when game is first started or restarted after game over
	public void reset() {
		gold = 200;
		year = 1;
		cur_season = 0;
		setMiniGameLimit(2);
		int temp = r.nextInt(4);
		cur_tax = taxs[temp];
		gold += cur_tax;
		EventsGenerator.setMaxEvents(2);
		Game.menuTo = 2;
	}
	
	public void tick() {

	}
	
	public void render(Graphics2D g2d){
		
		g2d.setColor(Color.white);
		g2d.setFont(fontManager.getMaruMonica());
		curFont = g2d.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 22F);
		g2d.setFont(newFont);
		
		g2d.drawString("Year: " + year, 10, 25);
		g2d.drawString("Season: " + seasons[cur_season], 10, 55);
		g2d.drawString("Gold: " + gold, 10, 85);	
		g2d.drawString("Tax: " + cur_tax, 10, 115);
	
	}

	public int getMiniGameLimit() {
		return miniGameLimit;
	}

	public void setMiniGameLimit(int miniGameLimit) {
		this.miniGameLimit = miniGameLimit;
	}

	public int getCur_season() {
		return cur_season;
	}

	public String[] getSeasons() {
		return seasons;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	
	
	
	
}






















