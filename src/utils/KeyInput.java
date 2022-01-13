package utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.Game;
import objects.GameObject;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;

	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE) {
			handler.setAction(true);
		}
	
		for(int i=0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player || tempObject.getId() == ID.MiniPlayer) {
				if(key == KeyEvent.VK_W) handler.setUp(true);
				if(key == KeyEvent.VK_S) handler.setDown(true);
				if(key == KeyEvent.VK_A) handler.setLeft(true);
				if(key == KeyEvent.VK_D) handler.setRight(true);
				
				if(tempObject.getId() == ID.Player){
					if(key == KeyEvent.VK_ENTER) {
						Game.paused = !Game.paused;
					}
					
					if(key == KeyEvent.VK_ESCAPE) {
						Game.gameState = STATES.Menu;  
					}
				}
					
			}
		}
	}
	
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_D) handler.setRight(false);
		if(key == KeyEvent.VK_SPACE) handler.setAction(false);
		
		for(int i=0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player || tempObject.getId() == ID.MiniPlayer) {
				if(key == KeyEvent.VK_W) handler.setUp(false);
				if(key == KeyEvent.VK_S) handler.setDown(false);
				if(key == KeyEvent.VK_A) handler.setLeft(false);

			}
		}
	}
}
