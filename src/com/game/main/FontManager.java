package com.game.main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class FontManager {
	
	Font maruMonica, curFont, newFont;
	
	public FontManager() {
		// create new font
		try {
			InputStream is = getClass().getResourceAsStream("/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public Font getMaruMonica() {
		return maruMonica;
	}
	

}
