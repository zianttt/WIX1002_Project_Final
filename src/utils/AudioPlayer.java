package utils;

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
			mainMusic = new Music("res/audio/funnyback1.wav");
			battleMusic = new Music("res/audio/battle1.wav");
			dragonMusic = new Music("res/audio/dragonAtk.wav");
			clickSound = new Sound("res/audio/click.wav");
			shurikenSound = new Sound("res/audio/shuriken.wav");
			spearSound = new Sound("res/audio/spears.wav");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
}













