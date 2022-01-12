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


public class Stats {
	
	private FontManager fontManager;
	Font curFont, newFont;
	Random r = new Random();
	
	// game
	public static int gold = 10000000;
	public static int tax = 200;
	public static int year = 1;
	public static int cur_season = 0;
	public static String[] seasons = {"Spring", "Summer", "Autumn", "Winter"};
	public static int miniGameLimit = 2;
	
	public Stats(FontManager fontManager) {
		this.fontManager = fontManager;
		
	}
	
	public static void newSeason() {
		if(cur_season < 3) cur_season++;
		else {
			cur_season = 0;
			year++;	
		}
		gold+=tax;
		tax += 50;
		miniGameLimit = 2;
		EventsGenerator.maxEvents = 2;
	}
	
	public static void reset() {
		gold = 10000000;
		tax = 200;
		year = 1;
		cur_season = 0;
		miniGameLimit = 2;
		EventsGenerator.maxEvents = 2;
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
	
	}

	

	
	
}






















