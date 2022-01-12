package staticpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.Game;
import objects.EventsGenerator;
import utils.FontManager;

public class TextBox {
	
	Font curFont, newFont;
	private FontManager fontManager;
	
	private String[] eventTexts = {"Reinforcement : Tower ATK + 1", "Visitors : Gold + 100", "Festival : Berserk Diligent Fearless + 50",
	"Drought : Wall HP - 50", "Outing : Berserk Diligent Fearless + 50", "Heatstroke : Emo Nervous Lazy + 50",
	"Rainy : Tower Acc - 20", "Flood : Wall Hp - 50", "Harvest : Gold + 100",
	"Blizzard : Wall Hp - 50", "Alavanche : Emo Nervous Lazy + 50", "Hunger : Tower Acc - 20", "Tour Group Visit : Gold + 100"};
	
	private String eventErrorTexts = "Maximum amount of events exceeded...";
	
	private String tempText;
	
	
	public TextBox(FontManager fontManager) {
		this.fontManager = fontManager;
	}
	
	public void render(Graphics2D g2d) {
		
		// set font
		g2d.setColor(Color.black);
		g2d.setFont(fontManager.getMaruMonica());
		curFont = g2d.getFont();
		newFont = curFont.deriveFont(Font.BOLD, 22F);
		g2d.setFont(newFont);
		
		g2d.fillRoundRect(Game.WIDTH/2 - 250, 30, 500, 200, 35, 35);
		
		g2d.setColor(Color.white);
		
		if(EventsGenerator.eventError) {
			tempText = eventErrorTexts;
		}
		else {
			tempText = getEventText(EventsGenerator.eventTextSelect);
		}
		g2d.drawString(tempText, Game.WIDTH/2 - 175, 125);
		g2d.drawString("Press space to go back", Game.WIDTH/2 - 175, 155);
	}
	
	private String getEventText(int index) {
		return eventTexts[index];
	}
}


