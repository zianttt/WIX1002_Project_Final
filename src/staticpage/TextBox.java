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

public class TextBox {
	
	Font curFont, newFont;
	private FontManager fontManager;
	
	private String[] eventTexts = {"Reinforcement : Tower ATK + 1", "Visitors : Gold + 100", "Festival : Berserk Diligent Fearless + 50",
	"Drought : Wall HP - 50", "Outing : Berserk Diligent Fearless + 50", "Heatstroke : Emo Nervous Lazy + 50",
	"Rainy : Tower Acc - 20", "Flood : Wall Hp - 50", "Harvest : Gold + 100",
	"Blizzard : Wall Hp - 50", "Alavanche : Emo Nervous Lazy + 50", "Hunger : Tower Acc - 20", "Tour Group Visit : Gold + 100"};

 	
	private String[] errorTexts = {"Maximum amount of events exceeded..."};
	
	private String[] reminderTexts = {"Please make 2 wishes to the magic lamp before battle...",
										"It's time to fight the dragon!"};
	
	private String tempText;
	
	private BufferedImageLoader loader;
	private static BufferedImage[] meme_img = new BufferedImage[3];
	private int memeNum = 0;
	public final int meme_size = 3;
	public static int reminder = 0; 

	public TextBox(FontManager fontManager, BufferedImageLoader loader) {
		this.fontManager = fontManager;
		this.loader = loader;
		
		loadMemes();
		
	}
	
	private void loadMemes() {
		meme_img[0] = loader.loadImage("/memes/m1.jpg");
		meme_img[1] = loader.loadImage("/memes/m2.jpg");
		meme_img[2] = loader.loadImage("/memes/m3.jpg");
	}
	
	public void render(Graphics2D g2d) {
		
		
		if(Game.gameState == STATES.EventText || Game.gameState == STATES.Reminder) {
			
			// set font
			g2d.setColor(new Color(0, 0, 0, 220));
			g2d.setFont(fontManager.getMaruMonica());
			curFont = g2d.getFont();
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
				g2d.drawString(tempText, Game.WIDTH/2 - 175, 115);
			}
			else {
				g2d.drawString(reminderTexts[reminder], Game.WIDTH/2 - 175, 115);
			}

		
			
			g2d.drawString("Press space to go back", Game.WIDTH/2 - 175, 155);
		}
		
		if(Game.gameState == STATES.ChestText) {
			
			g2d.setColor(new Color(0, 0, 0, 220));
			g2d.fillRoundRect(Game.WIDTH/2 - 250, 30, 500, 300, 35, 35);
			g2d.setColor(new Color(255, 255, 255));
			g2d.setStroke(new BasicStroke(5));
			g2d.drawRoundRect(Game.WIDTH/2 - 252, 32, 500, 300, 35, 35);
			
			g2d.drawImage(meme_img[memeNum], Game.WIDTH/2 - 150, 29, 300, 306, null);
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
	
	
}


