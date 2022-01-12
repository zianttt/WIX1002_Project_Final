package staticpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.Game;
import utils.STATES;

public class Transition {
	
	private static int timer = 450;
	private static int color = 0;
	private static int fontColor = 0;
	
	public static void toBattle(Graphics2D g2d) {
		
		if(timer >= 0) {
			g2d.setColor(new Color(color, color, color));
			g2d.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			if(color+3 <= 255) {
				color+=1;
			}
			fontColor = 255-color;
			g2d.setColor(new Color(fontColor, fontColor, fontColor));
			g2d.setFont(new Font("comicsan", 35, 35));
			g2d.drawString("To Battle...", Game.WIDTH/2 - 100, Game.HEIGHT/2 - 30);
			timer--;
		}
		else {
			Game.gameState = STATES.Battle;
			timer = 300;
			color = 0;
		}	
	}
	
	public static void toShop(Graphics2D g2d) {
		if(timer >= 0) {
			g2d.setColor(new Color(color, color, color));
			g2d.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			if(color+3 <= 255) {
				color+=1;
			}
			fontColor = 255-color;
			g2d.setColor(new Color(fontColor, fontColor, fontColor));
			g2d.setFont(new Font("comicsan", 35, 35));
			g2d.drawString("To Shop...", Game.WIDTH/2 - 100, Game.HEIGHT/2 - 30);
			timer--;
		}
		else {
			Game.gameState = STATES.Shop;
			timer = 450;
			color = 0;
		}
	}
	
	
	public static void toMain(Graphics2D g2d) {
		
		if(timer >= 0) {
			g2d.setColor(new Color(color, color, color));
			g2d.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			if(color+3 <= 255) {
				color+=1;
			}
			fontColor = 255-color;
			g2d.setColor(new Color(fontColor, fontColor, fontColor));
			g2d.setFont(new Font("comicsan", 35, 35));
			g2d.drawString("To Main Map...", Game.WIDTH/2 - 100, Game.HEIGHT/2 - 30);
			timer--;
		}
		else {
			Game.gameState = STATES.Play;
			timer = 300;
			color = 0;
		}	
	}
	
	
	public static void toMiniGame(Graphics2D g2d) {
		if(timer >= 0) {
			g2d.setColor(new Color(color, color, color));
			g2d.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			if(color+3 <= 255) {
				color+=1;
			}
			fontColor = 255-color;
			g2d.setColor(new Color(fontColor, fontColor, fontColor));
			g2d.setFont(new Font("comicsan", 35, 35));
			g2d.drawString("To Minigame...", Game.WIDTH/2 - 100, Game.HEIGHT/2 - 30);
			timer--;
		}
		else {
			Game.gameState = STATES.Minigame;
			timer = 450;
			color = 0;
		}
	}
	
	public static void toStatus(Graphics2D g2d) {
		
		if(timer >= 0) {
			g2d.setColor(new Color(color, color, color));
			g2d.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			if(color+3 <= 255) {
				color+=1;
			}
			fontColor = 255-color;
			g2d.setColor(new Color(fontColor, fontColor, fontColor));
			g2d.setFont(new Font("comicsan", 35, 35));
			g2d.drawString("To Status Checker...", Game.WIDTH/2 - 250, Game.HEIGHT/2 - 30);
			timer--;
		}
		else {
			Game.gameState = STATES.Status;
			timer = 300;
			color = 0;
		}	
	}

}
