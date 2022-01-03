package com.game.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	
	public static Map<String, Music> mainMap = new HashMap<String, Music>();
	public static Map<String, Music> battleMap = new HashMap<String, Music>();
	public static Map<String, Sound> clickMap = new HashMap<String, Sound>();
	
	public static Music mainMusic;
	public static Music battleMusic;
	public static Music dragonMusic;
	public static Sound clickSound;
	public static Sound shurikenSound;
	public static Sound spearSound;

	public static void load() {
		
		try {
			mainMusic = new Music("media/funnyback1.wav");
			battleMusic = new Music("media/battle1.wav");
			dragonMusic = new Music("media/dragonAtk.wav");
			clickSound = new Sound("media/click.wav");
			shurikenSound = new Sound("media/shuriken.wav");
			spearSound = new Sound("media/spears.wav");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
}













