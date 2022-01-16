package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import main.Game;
import objects.EventsGenerator;

// Handles all the status
public class Stats {
	
	// main game
	public int gold = 10000000;
	public int[] taxs = {200, 250, 300, 350, 400};
	private int cur_tax = 0;
	public int year = 1;
	public int cur_season = 0;
	public String[] seasons = {"Spring", "Summer", "Autumn", "Winter"};
	public int miniGameLimit = 2;
	
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
		miniGameLimit = 2;
		EventsGenerator.maxEvents = 2;
	}
	
	// set default status when game is first started or restarted after game over
	public void reset() {
		gold = 10000000;
		year = 1;
		cur_season = 0;
		miniGameLimit = 2;
		int temp = r.nextInt(4);
		cur_tax = taxs[temp];
		gold += cur_tax;
		EventsGenerator.maxEvents = 2;
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

	

	
	
}






















