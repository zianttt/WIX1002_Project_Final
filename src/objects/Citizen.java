package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import utils.ID;
import utils.SpriteSheet;

public class Citizen extends SameBehaviour{
	
	// citizens
	public static int emo = 10;
	public static int nervous = 10;
	public static int lazy = 10;
	public static int berserk = 10;
	public static int diligent = 10;
	public static int fearless = 10;

	public Citizen(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);	
	}
	
	public static void statusCheck() {
		if(emo >= 100) {
			Tower.setTowerAtk(Tower.getTowerAtk()-1);
			emo-=100;
		}
		if(emo <= 0) {
			emo=0;
		}
		if(nervous >= 100) {
			Tower.setTowerAcc(Tower.getTowerAcc()-5);
			nervous-=100;
		}
		if(nervous <= 0) {
			nervous=0;
		}
		if(lazy >= 100) {
			Wall.setWallHp(Wall.getWallHp()-100);
			lazy-=100;
		}
		if(lazy <= 0) {
			lazy=0;
		}
		if(berserk >= 100) {
			Tower.setTowerAtk(Tower.getTowerAtk()+1);
			berserk-=100;
		}
		if(diligent >= 100) {
			Wall.setWallHp(Wall.getWallHp()+75);
			diligent-=100;
		}
		if(fearless >= 100) {
			Tower.setTowerCritical(Tower.getTowerCritical()+5);
			fearless-=100;
		}
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics2D g2d) {
		
		
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}
}