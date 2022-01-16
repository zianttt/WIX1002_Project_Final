package utils;

import java.util.Random;

import main.Game;
import objects.Enemy;
import objects.FastEnemy;
import objects.TrackEnemy;

// Handles the spawninig system in minigame
public class Spawner {
	
	private Handler handler;
	private MiniDisplay miniDis;
	private Random r = new Random();;
	
	private int scoreKeeper = 0;
	
	public Spawner(Handler handler, MiniDisplay miniDis) {
		this.handler = handler;
		this.miniDis = miniDis;
	}
	
	public void tick() {
		scoreKeeper++;
		
		if(scoreKeeper >= 100) {
			scoreKeeper = 0;
			miniDis.setLevel(miniDis.getLevel() + 1);
			
				if(miniDis.getLevel() == 2) {
					handler.addObject(new Enemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, null, handler));
				}else if(miniDis.getLevel() == 3) {
					handler.addObject(new Enemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, null, handler));
				}else if(miniDis.getLevel() == 4) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, null, handler));
				}else if(miniDis.getLevel() == 5) {
					handler.addObject(new TrackEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, null, handler));
				}else if(miniDis.getLevel() == 6) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, null, handler));
				}else if(miniDis.getLevel() == 7) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, null, handler));
				}else if(miniDis.getLevel() == 8) {
					handler.addObject(new TrackEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, null, handler));
				}
				else {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, null, handler));
				}			
		}
	}
}
