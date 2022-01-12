package utils;

import java.util.Random;

import main.Game;
import objects.BasicEnemy;
import objects.FastEnemy;
import objects.SmartEnemy;

public class Spawner {
	
	private Handler handler;
	private HUD hud;
	private Game game;
	private Random r;
	
	private int scoreKeep = 0;
	
	public Spawner(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}
	
	public void tick() {
		scoreKeep++;
		
		if(scoreKeep >= 100) {
			r = new Random();
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
				if(hud.getLevel() == 2) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, null, handler));
				}else if(hud.getLevel() == 3) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, null, handler));
				}else if(hud.getLevel() == 4) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, null, handler));
				}else if(hud.getLevel() == 5) {
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, null, handler));
				}else if(hud.getLevel() == 6) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, null, handler));
				}else if(hud.getLevel() == 7) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, null, handler));
				}else if(hud.getLevel() == 8) {
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, null, handler));
				}
				else {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, null, handler));
				}	
				
		}
	}

}
