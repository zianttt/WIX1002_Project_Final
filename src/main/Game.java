package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import objects.BasicEnemy;
import objects.Building;
import objects.Door;
import objects.Dragon;
import objects.EventsGenerator;
import objects.Fence;
import objects.Mountain;
import objects.Player;
import objects.Tower;
import objects.Tree;
import objects.Wall;
import objects.Water;
import staticpage.CitizenUpgrade;
import staticpage.GameOver;
import staticpage.Info;
import staticpage.Menu;
import staticpage.Shop;
import staticpage.StatusCheck;
import staticpage.TextBox;
import staticpage.TowerUpgrade;
import staticpage.Transition;
import staticpage.WallUpgrade;
import tiles.TileManager;
import utils.AudioPlayer;
import utils.Battle;
import utils.BufferedImageLoader;
import utils.Camera;
import utils.FontManager;
import utils.HUD;
import utils.Handler;
import utils.ID;
import utils.KeyInput;
import utils.STATES;
import utils.Spawner;
import utils.SpriteSheet;
import utils.Stats;
import utils.Window;

public class Game extends Canvas implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1538943766877881729L;
	
	// window settings
	public final int tileSize  = 32;
	public final int maxCol = 25;
	public final int maxRow = 16;
	public int width = maxCol * tileSize;
	public int height = maxRow * tileSize;
	
	// static screen 
	public final int staticCol = 32;
	public final int staticRow = 18;
	
	// world
	public final int maxWorldCol = 72;
	public final int maxWorldRow = 72;
	public final int worldWidth = maxWorldCol * tileSize;
	public final int worldHeight = maxWorldRow * tileSize;
	
	// helper objects
	private Handler handler;
	private Handler battleHandler;
	private Handler miniHandler;
	private HUD hud;
	private Spawner spawner;
	public Player player;
	private EventsGenerator eGen;
	private Stats stats;
	private Battle battle;
	
	
	// graphics
	TileManager tileManager;
	TileManager bgTileManager;
	public FontManager fontManager;
	
	
	// game loop
	private boolean running = false;
	private Thread thread;
	
	
	// pages
	private Camera camera;
	private Shop shop;
	private Menu menu;
	private Info info;
	private StatusCheck statusC;
	private GameOver gameOver;
	private TowerUpgrade towerUp;
	private WallUpgrade wallUp;
	private CitizenUpgrade citizenUp;
	private TextBox textbox;
	
	BufferedImageLoader loader2;
	BufferedImageLoader loader;
	
	private BufferedImage characters = null;
	private SpriteSheet css;
	private BufferedImage dragons = null;
	private SpriteSheet dragonss;
	private BufferedImage scenes = null;
	private SpriteSheet sss;
	private BufferedImage sceneDraw = null;
	private SpriteSheet drawss;
	private BufferedImage buildings = null;
	private SpriteSheet bss;

	
	// background sprites
	private BufferedImage bgrd_img = null;
	private BufferedImage mainmenu_img = null;
	private BufferedImage battlemap_img = null;
	private BufferedImage staticpage_img = null;

	
	
	//
	private BufferedImage map = null;
	// 
	
	public static int WIDTH = 1000, HEIGHT = 563;
	private double FPS = 60;
	
	Random r = new Random();
	public static boolean paused = false; 
	public static STATES gameState = STATES.Menu;
	public static STATES previousState = null;
	public static boolean justInOut = false;
	public static int win = 2;
	public static boolean fromGameOver = false;
	public static boolean menuToggle = false;
	private int dragonImcoming = 240;
	
	public Game() {
		
		new Window("Till The End", WIDTH, HEIGHT, this);
		
		
		handler = new Handler();
		battleHandler = new Handler();
		miniHandler = new Handler();
		
		fontManager = new FontManager();
		hud = new HUD();
		spawner = new Spawner(miniHandler, hud, this);
		camera = new Camera(0, 0);
		stats = new Stats(fontManager);
		textbox = new TextBox(fontManager);
		shop = new Shop(fontManager);
		menu = new Menu(fontManager);
		info = new Info(fontManager);
		statusC = new StatusCheck(fontManager);
		gameOver = new GameOver(handler, hud, fontManager);
		towerUp = new TowerUpgrade(fontManager);
		wallUp = new WallUpgrade(fontManager);
		citizenUp = new CitizenUpgrade(fontManager);

		this.addKeyListener(new KeyInput(handler));
		this.addKeyListener(new KeyInput(miniHandler));
		this.addMouseListener(shop);
		this.addMouseListener(towerUp);
		this.addMouseListener(wallUp);
		this.addMouseListener(citizenUp);
		this.addMouseListener(menu);
		this.addMouseListener(info);
		this.addMouseListener(statusC);
		
		AudioPlayer.load();
		AudioPlayer.mainMusic.loop();
		
		//loader2 = new BufferedImageLoader();
		loader = new BufferedImageLoader();
		tileManager = new TileManager(this, loader, 1);
		bgTileManager =  new TileManager(this, loader, 2);
		//map = loader.loadImage("/maps/mapImp14.png");
		map = loader.loadImage("/maps/map72x72.png");
		characters = loader.loadImage("/pics/character1.png");
		css = new SpriteSheet(characters);
		dragons = loader.loadImage("/pics/dragonss.png");
		dragonss = new SpriteSheet(dragons);
		scenes = loader.loadImage("/pics/scenes.png");
		sss = new SpriteSheet(scenes);	
		sceneDraw = loader.loadImage("/pics/bgTileImg.png");
		drawss = new SpriteSheet(sceneDraw);	
		buildings = loader.loadImage("/pics/buildings.png");
		bss = new SpriteSheet(buildings);
		

		bgrd_img = loader.loadImage("/pics/test1.jpg");
		mainmenu_img = loader.loadImage("/pics/mainmenu.png");
		battlemap_img = loader.loadImage("/pics/battleMap.png");
		staticpage_img = loader.loadImage("/pics/staticmap.png");
		loadMap(map);
		
		
		
		
		
		start();
		
	}
	
	
	private void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	
	private void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
		this.requestFocus(); // no need to press mouse to use keyboard
		
		// delta time game loop 
		long lastTime = System.nanoTime();
		double interval = 1000000000 / FPS;
		double delta = 0;
		long timer = System.currentTimeMillis();
		long currentTime;
		
		while(running) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / interval;
			lastTime = currentTime;
			
			while(delta >= 1) {
				tick();
				delta--;
			}
			
			render();
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
		
		stop();
			
	}
	
	
	public void tick() {
		
		// main game 
		if(gameState == STATES.Play) {	
			
			for(int i=0; i < handler.object.size(); i++) {
				if(handler.object.get(i).getId() == ID.Player) {
					camera.tick(handler.object.get(i));
				}
			}	
			handler.tick();	
			
			if(Stats.miniGameLimit == 0) {
				dragonImcoming--;
				if(dragonImcoming <= 0) {
					gameState = STATES.ToBattle;
				}
			}
			
			if(gameState == STATES.ToBattle) {

				Wall wall = new Wall(400, 0, ID.Wall, battleHandler, this, drawss);		
				battleHandler.addObject(wall);
				Tower tower = new Tower(500, 0, ID.Block, drawss, battleHandler);
				battleHandler.addObject(tower);
				battleHandler.addObject(new Wall(600, 0, ID.Wall, battleHandler, this, drawss));
				Dragon dragon = new Dragon((Game.WIDTH/2)-70, Game.HEIGHT + 100, ID.Dragon, dragonss, battleHandler, wall, loader);
				battleHandler.addObject(dragon);
				battle = new Battle(this, battleHandler, drawss, dragon, wall, tower);
				battle.reset();
			}
			
			if(gameState == STATES.ToMiniGame) {
				Player mini = new Player(WIDTH/2, HEIGHT/2, ID.MiniPlayer, miniHandler, css, eGen, loader, this);
				miniHandler.addObject(mini);
				miniHandler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, css, miniHandler));
			}
		}
		
		// battle 
		if(gameState == STATES.Battle) {
			battleHandler.tick();
			battle.tick();
			
			if(gameState == STATES.ToMain) {
				battleHandler.clearBattleField();
				justInOut = true;
				Player.direction = "up";
				player.setY(player.getY() - 10);
			}
			
			if(gameState == STATES.GameOver) {
				battleHandler.clearBattleField();
				handler.clearBattleField();
			}
		}
	
		// shop 
		if(gameState == STATES.Shop) {		
			shop.tick();
			if(gameState == STATES.ToMain) {
				justInOut = true;
				Player.direction = "down";
				player.setY(player.getY() + 15);		
			}
		}
		
		// status
		if(gameState == STATES.Status) {		
			statusC.tick();
			if(gameState == STATES.ToMain) {
				justInOut = true;
				Player.direction = "up";
				player.setY(player.getY() - 15);		
			}
		}
		
		// game over page
		if(gameState == STATES.GameOver) {
			gameOver.tick();
			
			if(gameState == STATES.Menu) {
				battleHandler.clearBattleField();
				handler.clearBattleField();
				fromGameOver = true;
			}
		}
		
		if(gameState == STATES.MinigameOver) {
			gameOver.tick();
		}
		
		
		
		if(gameState == STATES.Minigame) {
			hud.tick();
			spawner.tick();
			miniHandler.tick();
			
			if(HUD.HEALTH <= 0) {
				hud.setGoldEarned(hud.getScore() / 100);
				Stats.gold += hud.getGoldEarned();
				gameState = STATES.MinigameOver;
				Player.direction = "right";
				player.setX(player.getX() + 15);
				miniHandler.clearBattleField();
			}
		}
		
		if(gameState == STATES.Menu) {
			menu.tick();
		}
	}
	
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(gameState == STATES.Play || gameState == STATES.EventText) {
			
			g2d.translate(-camera.getX(), -camera.getY());

			tileManager.render(g2d);
			handler.render(g2d);
			
			g2d.translate(camera.getX(), camera.getY());
		}	
		
		if(gameState == STATES.ToShop) {
			Transition.toShop(g2d);
		}
		else if(gameState == STATES.Shop) {
			//bgTileManager.render(g2d);
			g2d.drawImage(bgrd_img, 0, 0, WIDTH, HEIGHT, null);
			shop.render(g2d);
		}
		else if(gameState == STATES.TowerUp) {
			//bgTileManager.render(g2d);
			g2d.drawImage(bgrd_img, 0, 0, WIDTH, HEIGHT, null);
			towerUp.render(g2d);
		}
		else if(gameState == STATES.WallUp) {
			//bgTileManager.render(g2d);
			g2d.drawImage(bgrd_img, 0, 0, WIDTH, HEIGHT, null);
			wallUp.render(g2d);
		}
		else if(gameState == STATES.CitizneUp) {
			//bgTileManager.render(g2d);
			g2d.drawImage(bgrd_img, 0, 0, WIDTH, HEIGHT, null);
			citizenUp.render(g2d);
		}
		else if(gameState == STATES.ToBattle) {
			Transition.toBattle(g2d);
		}
		else if(gameState == STATES.Battle) {
			bgTileManager.render(g2d);
			battleHandler.render(g2d);
			battle.render(g2d);	
		}
		else if(gameState == STATES.EventText) {
			textbox.render(g2d);
			if(handler.isAction()) {
				gameState = STATES.Play;
				player.setX(player.getX() + 10);
				Player.direction = "right";
			}
		}
		else if(gameState == STATES.GameOver) {
			bgTileManager.render(g2d);
			gameOver.render(g2d);
		}
		else if(gameState == STATES.Menu) {
			g2d.drawImage(mainmenu_img, 0, 0, WIDTH, HEIGHT, null);
			//bgTileManager.render(g2d);
			if(fromGameOver) {
				loadMap(map);
				fromGameOver = false;
			}
			menu.render(g2d);
		}
		else if(gameState == STATES.ToMain) {
			Transition.toMain(g2d);
		}
		else if(gameState == STATES.ToMiniGame) {
			Transition.toMiniGame(g2d);
		}
		else if(gameState == STATES.Minigame || gameState == STATES.MinigameOver) {
			//bgTileManager.render(g2d);
			g2d.drawImage(battlemap_img, 0, 0, WIDTH, HEIGHT, null);
			hud.render(g);
			if(gameState == STATES.Minigame) miniHandler.render(g2d);
			if(gameState == STATES.MinigameOver) {
				gameOver.render(g2d);
			}
		}
		else if(gameState == STATES.ToStatus) {
			Transition.toStatus(g2d);
		}
		else if(gameState == STATES.Status) {
			//bgTileManager.render(g2d);
			g2d.drawImage(bgrd_img, 0, 0, WIDTH, HEIGHT, null);
			statusC.render(g2d);
		}
		else if(gameState == STATES.Info || gameState == STATES.GeneralInfo || gameState == STATES.MiniGameInfo) {
			//bgTileManager.render(g2d);
			g2d.drawImage(staticpage_img, 0, 0, WIDTH, HEIGHT, null);
			info.render(g2d);
		}
		
		
		// player status
		if(gameState == STATES.Play || gameState == STATES.Shop || gameState == STATES.CitizneUp &&
				gameState == STATES.TowerUp || gameState == STATES.WallUp || gameState == STATES.Status) {
			stats.render(g2d);
		}
		
		g2d.dispose();
		g.dispose();
		bs.show();	
	}
	
	
	void loadMap(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx=0; xx < w; xx++) {
			for(int yy=0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 150 && green == 75 && blue == 0) {
					player = new Player(xx*32, yy*32, ID.Player, handler, css, eGen, loader, this);
					handler.addObject(player);	
				}
				
				
				else if(red == 255 && green == 0 && blue == 0) {
					handler.addObject(new Mountain(xx*32, yy*32, ID.Block, drawss));
				}
				
				else if(red == 0 && green == 255 && blue == 255) {
					handler.addObject(new Water(xx*32, yy*32, ID.Block, drawss));
				}	
				
				else if(red == 255 && green == 100) {
					handler.addObject(new Fence(xx*32, yy*32, ID.Block, drawss));
				}	
				
				else if(red == 0 && green == 0 && blue == 255) {
					handler.addObject(new Tower(xx*32, yy*32, ID.Block, drawss, handler));
				}
				
				
				else if(red == 255 && green == 255 && blue == 255) {
					handler.addObject(new Door(xx*32, yy*32, ID.ShopDoor, drawss));
				}
				
				else if(red == 245 && green == 245 && blue == 245) {
					handler.addObject(new Door(xx*32, yy*32, ID.WallDoor, drawss));
				}
				
				else if(red == 255 && green == 255 && blue == 0) {
					handler.addObject(new Wall(xx*32, yy*32, ID.Wall, handler, this, drawss));
				}	
				
				else if(red == 144 && green == 238) {
					handler.addObject(new Tree(xx*32, yy*32, ID.Block, drawss));
				}
				
				else if(red == 244 && green == 194) {
					if(blue == 194) {
						handler.addObject(new Building(xx*32, yy*32, ID.Block, bss, 0));
					}
					else if(blue == 195) {
						handler.addObject(new Building(xx*32, yy*32, ID.Block, bss, 1));
					}
					else if(blue == 196) {
						handler.addObject(new Building(xx*32, yy*32, ID.Block, bss, 2));
					}
				}
				
				else if(red == 128 && green == 128 && blue == 128) {
					eGen = new EventsGenerator(xx*32, yy*32, ID.EventGen, sss);
					handler.addObject(eGen);
				}
				
				else if(red == 200 && green == 200 && blue == 200) {
					handler.addObject(new Door(xx*32, yy*32, ID.MiniGameDoor, drawss));
				}
				
				else if(red == 150 && green == 150 && blue == 150) {
					handler.addObject(new Door(xx*32, yy*32, ID.StatusDoor, drawss));
				}
			}
		}
	}
	
	
	// create boundaries
	public static float clamp(float var, int min, int max) {
		if(var >= max) 
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;

	}
	
	
	public static void main(String[] args) {
		new Game();
	}
}
