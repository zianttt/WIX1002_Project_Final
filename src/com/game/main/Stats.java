package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import com.game.battle.Dragon;


public class Stats {
	
	Game game;
	Font maruMonica, curFont, newFont;
	Random r = new Random();
	
	// game
	public static int gold = 10000000;
	public static int tax = 200;
	public static int year = 1;
	public static int cur_season = 0;
	public static String[] seasons = {"Spring", "Summer", "Autumn", "Winter"};
	
	public Stats(Game game) {
		this.game = game;
		
		// create new font
		try {
			InputStream is = getClass().getResourceAsStream("/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void newSeason() {
		if(cur_season < 3) cur_season++;
		else {
			cur_season = 0;
			year++;
			gold+=tax;
			tax += 50;
		}
		
	}
	
	public static void reset() {
		gold = 10000000;
		tax = 200;
		year = 1;
		cur_season = 0;
	}
	
	public void tick() {

	}
	
	public void render(Graphics g){
		
		g.setColor(Color.white);
		g.setFont(maruMonica);
		curFont = g.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 25F);
		g.setFont(newFont);
		
		g.drawString("Year: " + year, 10, 25);
		g.drawString("Season: " + seasons[cur_season], 10, 55);
		g.drawString("Gold: " + gold, 10, 85);	
	
	}

	

	
	
}






















