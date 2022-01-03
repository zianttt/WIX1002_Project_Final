package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TextBox {
	
	private String[] eventTexts = {"Reinforcement : Tower ATK + 1", "Visitors : Gold + 100", "Festival : Berserk Diligent Fearless + 50",
	"Drought : Wall HP - 50", "Outing : Berserk Diligent Fearless + 50", "Heatstroke : Emo Nervous Lazy + 50",
	"Rainy : Tower Acc - 20", "Flood : Wall Hp - 50", "Harvest : Gold + 100",
	"Blizzard : Wall Hp - 50", "Alavanche : Emo Nervous Lazy + 50", "Hunger : Tower Acc - 20", "Tour Group Visit : Gold + 100"};
	
	private String eventErrorTexts = "Maximum amount of events exceeded...";
	
	private String tempText;
	
	public void render(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRoundRect(Game.WIDTH/2 - 250, 30, 500, 200, 35, 35);
		
		g.setColor(Color.white);
		g.setFont(new Font("comicsan", 20, 20));
		if(EventsGenerator.eventError) {
			tempText = eventErrorTexts;
		}
		else {
			tempText = getEventText(EventsGenerator.eventTextSelect);
		}
		g.drawString(tempText, Game.WIDTH/2 - 175, 125);
		g.drawString("Press space to go back", Game.WIDTH/2 - 175, 155);
	}
	
	private String getEventText(int index) {
		return eventTexts[index];
	}
}


