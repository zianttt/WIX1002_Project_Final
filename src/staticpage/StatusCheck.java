package staticpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game;
import objects.Citizen;
import objects.Tower;
import objects.Wall;
import utils.AudioPlayer;
import utils.STATES;
import utils.Stats;

public class StatusCheck extends MouseAdapter{
	
	private boolean backToMain = false;
	
	public StatusCheck() {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
		if(Game.gameState == STATES.Status) {
			int mx = e.getX();
			int my = e.getY();
			
			if(mx != 0 && my != 0) {
				AudioPlayer.clickSound.play();
			}
			
			// back
			if(mx >= 425 && mx <= 525) {
				if(my >= 450 && my <= 500) {
					backToMain = true;
				}
			}
		}		
	}
	
	public void tick() {
		if(backToMain) {
			Game.gameState = STATES.ToMain;
			backToMain = false;
		}
	}
	
	public void render(Graphics2D g2d) {
		
		g2d.setColor(Color.white);
		g2d.setFont(new Font("comicsan", 0, 48));
		g2d.drawString("Status", Game.WIDTH/2-100, 50);
		
		
		//box 1
		g2d.drawRect(130, 100, 320, 250);
		g2d.setFont(new Font("comicsan", 0, 18));
		g2d.drawString("Tower Critical Chance: " + (int)Tower.towerCritical, 140, 130);	
		g2d.drawString("Tower Attack Point: " + (int)Tower.towerAtk, 140, 170);
		g2d.drawString("Tower Accuracy: " + (int)Tower.towerAcc, 140, 210);	
		g2d.drawString("Wall Health: " + (int)Wall.wallHp, 140, 250);	
		g2d.drawString("Wall Block Chance: " + (int)Wall.wallBlock, 140, 290);	
		g2d.drawString("Tax rate: " + Stats.tax, 140, 330);	
		
		
		//box 4
		g2d.drawRect(500, 100, 320, 250);
		g2d.drawString("Emotional: " + Citizen.berserk, 510, 130);
		g2d.drawString("Nervous: " + Citizen.nervous, 510, 170);
		g2d.drawString("Lazy: " + Citizen.lazy, 510, 210);
		g2d.drawString("Berserk: " + Citizen.berserk, 510, 250);
		g2d.drawString("Diligent: " + Citizen.diligent, 510, 290);
		g2d.drawString("Fearless: " + Citizen.fearless, 510, 330);
		
		
		//back
		g2d.drawString("Back", 452, 480);
		g2d.drawRect(425, 450, 100, 50);
		
	}
}
