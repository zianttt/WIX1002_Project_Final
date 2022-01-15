package staticpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.Game;
import utils.FontManager;
import utils.STATES;

public class Transition {
	
	private Font curFont, newFont;
	private FontManager fontManager;
	
	private int color;
	private boolean revert = true;
	
	private static int timer = 450;
	private static int fontColor = 0;
	
	public Transition(FontManager fontManager) {
		this.fontManager = fontManager;
	}
	
	
	public void toBattle(Graphics2D g2d) {
		
		colorInvert();
		setFont(g2d);
		
		if(timer >= 0) {
			g2d.setColor(new Color(255, 50, color));
			g2d.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			fontColor = 255-color;
			g2d.setColor(new Color(fontColor, fontColor, fontColor));
			g2d.drawString("To Battle...", Game.WIDTH/2 - 125, Game.HEIGHT/2 - 30);
			timer--;
		}
		else {
			Game.gameState = STATES.Battle;
			timer = 300;
		}	
	}
	
	public void toShop(Graphics2D g2d) {
		
		colorInvert();
		setFont(g2d);

		if(timer >= 0) {
			g2d.setColor(new Color(50, 200, color));
			g2d.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			fontColor = 255-color;
			g2d.setColor(new Color(fontColor, fontColor, fontColor));
			g2d.drawString("To Shop...", Game.WIDTH/2 - 100, Game.HEIGHT/2 - 30);
			timer--;
		}
		else {
			Game.gameState = STATES.Shop;
			timer = 450;
		}
	}
	
	
	public void toMain(Graphics2D g2d) {
		
		colorInvert();
		setFont(g2d);
		
		if(timer >= 0) {
			g2d.setColor(new Color(color, 255, 100));
			g2d.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			fontColor = 255-color;
			g2d.setColor(new Color(fontColor, fontColor, fontColor));
			g2d.drawString("To Main Map...", Game.WIDTH/2 - 155, Game.HEIGHT/2 - 30);
			timer--;
		}
		else {
			Game.gameState = STATES.Play;
			timer = 300;
		}	
	}
	
	
	public void toMiniGame(Graphics2D g2d) {
		
		colorInvert();
		setFont(g2d);
		
		if(timer >= 0) {
			g2d.setColor(new Color(255, color, color));
			g2d.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			fontColor = 255-color;
			g2d.setColor(new Color(fontColor, fontColor, fontColor));
			g2d.drawString("To Minigame...", Game.WIDTH/2 - 150, Game.HEIGHT/2 - 30);
			timer--;
		}
		else {
			Game.gameState = STATES.Minigame;
			timer = 450;
		}
	}
	
	public void toStatus(Graphics2D g2d) {
		
		colorInvert();
		setFont(g2d);
		
		if(timer >= 0) {
			g2d.setColor(new Color(0, color, color));
			g2d.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			fontColor = 255-color;
			g2d.setColor(new Color(fontColor, fontColor, fontColor));
			g2d.drawString("To Status Checker...", Game.WIDTH/2 - 200, Game.HEIGHT/2 - 30);
			timer--;
		}
		else {
			Game.gameState = STATES.Status;
			timer = 300;
		}	
	}
	
	private void colorInvert() {
		if(!revert) {
			color--;
			if(color == 0) revert = true;
		}else {
			color++;
			if(color == 255) revert = false;
		}
	}
	
	private void setFont(Graphics2D g2d) {
		// set font
		g2d.setColor(new Color(155, 155, color));
		g2d.setFont(fontManager.getMaruMonica());
		curFont = g2d.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 55F);
		g2d.setFont(newFont);
		
	}

}
