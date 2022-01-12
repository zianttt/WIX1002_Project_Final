package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import utils.ID;
import utils.SpriteSheet;
import utils.Stats;

public class EventsGenerator extends GameObject{
	
	Random r = new Random();
	
	public static int maxEvents = 2;
	private int eventNum;
	public static int eventTextSelect;
	public static boolean eventError = false;
	
	public EventsGenerator(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
	}
	
	public void generateEventError() {
		eventError = true;
	}
	
	public void generateEvent(String season) {
		
		// winter got 4 event choices
		if(Stats.cur_season == 3) eventNum = r.nextInt(4);
		else eventNum = r.nextInt(3);
		
		if(season.equals("Spring")) {
			if(eventNum == 0) reinforcement();
			else if(eventNum == 1) visitors();
			else if(eventNum == 2) festival();
			eventTextSelect = eventNum;
		}
		
		if(season.equals("Summer")) {
			if(eventNum == 0) drought();
			else if(eventNum == 1) outing();
			else if(eventNum == 2) heatstroke();
			eventTextSelect = eventNum + 3;
		}
		
		if(season.equals("Autumn")) {
			if(eventNum == 0) rainy();
			else if(eventNum == 1) flood();
			else if(eventNum == 2) harvest();
			eventTextSelect = eventNum + 6;
		}
		
		if(season.equals("Winter")) {
			if(eventNum == 0) blizzard();
			else if(eventNum == 1) avalanche();
			else if(eventNum == 2) hunger();
			else if(eventNum == 3) tourGroup();
			eventTextSelect = eventNum + 9;
		}
		
		Citizen.statusCheck();
		maxEvents--;
	}
	
	// Spring
	private void reinforcement() {
		Tower.towerAtk += 1;
		System.out.println("Tower ATK + 1");
	}
	
	private void visitors() {
		Stats.gold += 100;
		System.out.println("Gold + 100");
	}

	private void festival() {
		Citizen.berserk += 50;
		Citizen.diligent += 50;
		Citizen.fearless += 50;
		System.out.println("Berserk Diligent Fearless + 50");
	}
	
	
	// Summer
	private void drought() {
		Wall.wallHp -= 50;
		System.out.println("Wall HP - 50");
	}
	
	private void outing() {
		Citizen.berserk += 50;
		Citizen.diligent += 50;
		Citizen.fearless += 50;
		System.out.println("Berserk Diligent Fearless + 50");
	}
	
	private void heatstroke() {
		Citizen.emo += 50;
		Citizen.nervous += 50;
		Citizen.lazy += 50;
		System.out.println("Emo Nervous Lazy + 50");
	}
	
	
	// Autumn
	private void rainy() {
		Tower.towerAcc -= 20;
		Tower.tempDecrease = true;	
		System.out.println("Tower Acc - 20");
	}
	
	private void flood() {
		Wall.wallHp -= 50;
		System.out.println("Wall Hp - 50");
	}
	
	private void harvest() {
		Stats.gold += 100;
		System.out.println("Gold + 100");
	}
	
	
	// Winter
	private void blizzard() {
		Wall.wallHp -= 50;
		System.out.println("Wall Hp - 50");
	}
	
	private void avalanche() {
		Citizen.emo += 50;
		Citizen.nervous += 50;
		Citizen.lazy += 50;
		System.out.println("Emo Nervous Lazy + 50");
	}
	
	private void hunger() {
		Tower.towerAcc -= 20;
		Tower.tempDecrease = true;
		System.out.println("Tower Acc - 20");
	}
	
	private void tourGroup() {
		Stats.gold += 100;
		System.out.println("Gold + 100");
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics2D g2d) {	
		g2d.setColor(new Color(128, 128, 128));
		g2d.fillRect(x, y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {	
		return new Rectangle(x, y, 32, 32);
	}

}
