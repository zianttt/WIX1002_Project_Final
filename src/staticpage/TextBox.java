package staticpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.image.BufferedImage;

import main.Game;
import objects.EventsGenerator;
import utils.BufferedImageLoader;
import utils.FontManager;
import utils.STATES;

// Handles the pop up dialouge box 
public class TextBox {
	
	// Events generator
	private String[] eventTexts = {"Reinforcement : Tower ATK + 1", "Visitors : Gold + 100", "Festival : Berserk Diligent Fearless + 50",
	"Drought : Wall HP - 50", "Outing : Berserk Diligent Fearless + 50", "Heatstroke : Emo Nervous Lazy + 50",
	"Rainy : Tower Acc - 20", "Flood : Wall Hp - 50", "Harvest : Gold + 100",
	"Blizzard : Wall Hp - 50", "Alavanche : Emo Nervous Lazy + 50", "Hunger : Tower Acc - 20", "Tour Group Visit : Gold + 100"};

 	// Error messages
	private String[] errorTexts = {"Maximum amount of events exceeded..."};
	
	// Reminder texts
	private String[] reminderTexts = {"Make 2 wishes to the magic lamp before battle...",
										"It's time to fight the dragon!"};
	
	// Tips texts (from chest)
	private String[] infoTexts = {"Emotional - Decrease Tower AttackPoint by 1\nwhen reaches 100",
									"Nervous - Decrease Tower Accuracy by 5\nwhen reaches 100",
									"Lazy - Decrease Wall HP by 100\nwhen reaches 100",
									"Berserk - Increase Tower AttackPoint by 1\nwhen reaches 100",
									"Diligent - Increase Wall HP by 75\nwhen reaches 100",
									"Fearless - Increase Tower Critical Chance by 5\nwhen reaches 100",
									"Go to the magic lamp to wish for some events\nto happen each season",
									"Some of the dragon's flames are trapped\nin the minigame dojo...",
									"Tower's critical chance percentage is\ncapped at 50 %",
									"The dragon levels up after each battle and \nready to fight until you killed it",
									"Tax will be collected randomly at the start\nof each season"
									};
	// For retrieving the texts
	private int infoTextSize = 11;
	private int infoInd = 0;
	// 0 : meme chest, 1 : tips chest
	public int chestType = 0; 
	
	// Road signs
	private String[] names = {"Shop", "Status Hall", "Minigame", "Magic Lamp", "Town door"};
	public int namesInd = 0;
	
	// Memes loading
	private BufferedImageLoader loader;
	private static BufferedImage[] meme_img = new BufferedImage[10];
	private int memeNum = 0;
	private final int meme_size = 10;
	public int reminder = 0; 
	
	// others
	private String tempText;
	Font curFont, newFont;
	private FontManager fontManager;
	

	public TextBox(FontManager fontManager, BufferedImageLoader loader) {
		this.fontManager = fontManager;
		this.loader = loader;
		
		loadMemes();	
	}
	
	private void loadMemes() {
		meme_img[0] = loader.loadImage("/memes/m1.jpg");
		meme_img[1] = loader.loadImage("/memes/m2.jpg");
		meme_img[2] = loader.loadImage("/memes/m3.jpg");
		meme_img[3] = loader.loadImage("/memes/m4.jpg");
		meme_img[4] = loader.loadImage("/memes/m5.jpg");
		meme_img[5] = loader.loadImage("/memes/m6.jpg");
		meme_img[6] = loader.loadImage("/memes/m7.jpg");
		meme_img[7] = loader.loadImage("/memes/m8.jpg");
		meme_img[8] = loader.loadImage("/memes/m9.png");
		meme_img[9] = loader.loadImage("/memes/m10.png");
	}
	
	public void render(Graphics2D g2d) {
		
		g2d.setFont(fontManager.getMaruMonica());
		curFont = g2d.getFont();
		
		if(Game.gameState == STATES.EventText || Game.gameState == STATES.Reminder || Game.gameState == STATES.InfoBoard) {
			
			// set font
			g2d.setColor(new Color(0, 0, 0, 220));
			newFont = curFont.deriveFont(Font.BOLD, 22F);
			g2d.setFont(newFont);
			
			g2d.fillRoundRect(Game.WIDTH/2 - 250, 30, 500, 200, 35, 35);
			g2d.setColor(new Color(255, 255, 255));
			g2d.setStroke(new BasicStroke(5));
			g2d.drawRoundRect(Game.WIDTH/2 - 250, 30, 500, 200, 35, 35);
			
			g2d.setColor(Color.white);
			
			if(Game.gameState == STATES.EventText) {
				if(EventsGenerator.eventError) {
					tempText = errorTexts[0];
				}
				else {
					tempText = getEventText(EventsGenerator.eventTextSelect);
				}
				g2d.drawString(tempText, Game.WIDTH/2 - 200, 100);
			}
			else if(Game.gameState == STATES.Reminder){
				g2d.drawString(reminderTexts[reminder], Game.WIDTH/2 - 200, 115);
			}
			
			
			else if(Game.gameState == STATES.InfoBoard) {
				g2d.setColor(new Color(180, 50, 135));
				newFont = curFont.deriveFont(Font.BOLD, 35F);
				g2d.setFont(newFont);
				g2d.drawString(names[namesInd], Game.WIDTH/2 - 200, 115);
			}
			
			newFont = curFont.deriveFont(Font.BOLD, 22F);
			g2d.setFont(newFont);
			g2d.setColor(new Color(255, 255, 255));
			g2d.drawString("Press space to go back", Game.WIDTH/2 - 200, 155);
		}
		
		if(Game.gameState == STATES.ChestText) {
			
			
			g2d.setColor(new Color(0, 0, 0, 220));
			g2d.fillRoundRect(Game.WIDTH/2 - 250, 30, 500, 300, 35, 35);
			g2d.setColor(new Color(255, 255, 255));
			g2d.setStroke(new BasicStroke(5));
			g2d.drawRoundRect(Game.WIDTH/2 - 252, 32, 500, 300, 35, 35);
			
			if(chestType == 0) {
				g2d.drawImage(meme_img[memeNum], Game.WIDTH/2 - 150, 29, 300, 306, null);
			}
			else if(chestType == 1) {
				newFont = curFont.deriveFont(Font.BOLD, 33F);
				g2d.setFont(newFont);
				g2d.setColor(new Color(128, 53, 125));
				g2d.drawString("Tips", Game.WIDTH/2 - 200, 100);
				
				g2d.setColor(new Color(255, 255, 255));
				newFont = curFont.deriveFont(Font.BOLD, 22F);
				g2d.setFont(newFont);
				int h = 155;
				for(String temp: infoTexts[infoInd].split("\n")) {
					g2d.drawString(temp, Game.WIDTH/2 - 200, h);
					h+=35;
				}
				g2d.drawString("Press space to go back", Game.WIDTH/2 - 200, 255);
			}
			
		}	
		
		
	}

	private String getEventText(int index) {
		return eventTexts[index];
	}
	
	public int getMemeNum() {
		return memeNum;
	}

	public void setMemeNum(int memeNum) {
		this.memeNum = memeNum;
	}
	
	public int getMeme_size() {
		return meme_size;
	}

	public int getInfoInd() {
		return infoInd;
	}

	public void setInfoInd(int infoInd) {
		this.infoInd = infoInd;
	}

	public int getInfoTextSize() {
		return infoTextSize;
	}
	
	
}


