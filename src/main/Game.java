package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import objects.*;
import staticpage.*;
import tiles.TileManager;
import utils.*;

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
	private MiniDisplay miniDis;
	private Spawner spawner;
	public Player player;
	private EventsGenerator eGen;
	private Stats stats;
	private Battle battle;
	
	
	// graphics
	TileManager tileManager;
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
	private Transition transition;
	
	BufferedImageLoader loader2;
	BufferedImageLoader loader;
	
	private BufferedImage characters = null;
	private SpriteSheet css;
	private BufferedImage dragons = null;
	private SpriteSheet dragonss;
	//private BufferedImage scenes = null;
	//private SpriteSheet sss;
	private BufferedImage sceneDraw = null;
	private SpriteSheet drawss;
	private BufferedImage buildings = null;
	private SpriteSheet bss;
	private BufferedImage items = null;
	private SpriteSheet item_ss; 

	
	// background sprites
	private BufferedImage bgrd_img = null;
	private BufferedImage mainmenu_img = null;
	private BufferedImage battlemap_img = null;
	private BufferedImage staticpage_img = null;
	private BufferedImage dragonBattle_img = null;

	
	
	//
	private BufferedImage map = null;
	// 
	
	public static int WIDTH = 1000, HEIGHT = 563;
	private double FPS = 60;
	
	Random r = new Random();
	public static boolean paused = false; 
	public static STATES gameState = STATES.Menu;
	public static STATES previousState = null;
	//public static boolean justInOut = false;
	public static int win = 2;
	public static boolean fromGameOver = false;
	public static boolean menuToggle = false;
	public static int menuTo = 2;
	
	public Game() {
		
		new Window("Till The End", WIDTH, HEIGHT, this);
		
		loader = new BufferedImageLoader();
		
		handler = new Handler();
		battleHandler = new Handler();
		miniHandler = new Handler();
		
		fontManager = new FontManager();
		transition = new Transition(fontManager);
		miniDis = new MiniDisplay(fontManager);
		spawner = new Spawner(miniHandler, miniDis);
		camera = new Camera(0, 0);
		stats = new Stats(fontManager);
		textbox = new TextBox(fontManager, loader);
		shop = new Shop(fontManager);
		menu = new Menu(fontManager);
		info = new Info(fontManager);
		statusC = new StatusCheck(fontManager);
		gameOver = new GameOver(handler, miniDis, fontManager);
		towerUp = new TowerUpgrade(fontManager, stats);
		wallUp = new WallUpgrade(fontManager, stats);
		citizenUp = new CitizenUpgrade(fontManager, stats);

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
		
		tileManager = new TileManager(this, loader);
		map = loader.loadImage("/maps/map72Ver9.png");
		characters = loader.loadImage("/pics/character1.png");
		css = new SpriteSheet(characters);
		dragons = loader.loadImage("/pics/dragonss.png");
		dragonss = new SpriteSheet(dragons);
		//scenes = loader.loadImage("/pics/scenes.png");
		//sss = new SpriteSheet(scenes);	
		sceneDraw = loader.loadImage("/pics/bgTileImg.png");
		drawss = new SpriteSheet(sceneDraw);	
		buildings = loader.loadImage("/pics/buildings.png");
		bss = new SpriteSheet(buildings);
		items = loader.loadImage("/pics/items.png");
		item_ss = new SpriteSheet(items);
		

		bgrd_img = loader.loadImage("/pics/test1.jpg");
		mainmenu_img = loader.loadImage("/pics/mainmenu.png");
		battlemap_img = loader.loadImage("/pics/battleMap.png");
		staticpage_img = loader.loadImage("/pics/staticmap.png");
		dragonBattle_img = loader.loadImage("/pics/dragonBattle.png");
		loadMap(map);
		
		stats.reset();
		
		
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
			
			if(menuTo == 2) {
				gameState = STATES.ToBattle;
			}
			
			for(int i=0; i < handler.object.size(); i++) {
				if(handler.object.get(i).getId() == ID.Player) {
					camera.tick(handler.object.get(i));
				}
			}	
			handler.tick();	
			
			if(gameState == STATES.ToBattle) {

				Wall wall = new Wall(400, 0, ID.Wall, drawss);		
				battleHandler.addObject(wall);
				for(int yy=0; yy <= 128; yy+=32) {
					for(int xx=0; xx < WIDTH; xx+=32) {
						battleHandler.addObject(new Wall(xx, yy, ID.Wall, drawss));
					}
				}
				
				Tower[] towers = new Tower[5];
				for(int xx=94, ind=0; xx < WIDTH; xx+=192, ind++) {
					Tower t = new Tower(xx, 32, ID.Block, item_ss, battleHandler);
					towers[ind] = t;
					battleHandler.addObject(t);
				}
				
				Dragon dragon = new Dragon((Game.WIDTH/2)-85, Game.HEIGHT + 100, ID.Dragon, dragonss, battleHandler, wall, loader);
				battleHandler.addObject(dragon);
				battle = new Battle(this, battleHandler, drawss, dragon, wall, towers, fontManager, stats);
				battle.reset();
			}
		
			if(gameState == STATES.ToMiniGame) {
				menuTo = 1;
				Player mini = new Player(WIDTH/2, HEIGHT/2, ID.MiniPlayer, miniHandler, css, eGen, this, stats, textbox);
				miniHandler.addObject(mini);
				miniHandler.addObject(new Enemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, css, miniHandler));
			}
		}
		
		// battle 
		if(gameState == STATES.Battle) {
			battleHandler.tick();
			battle.tick();
			
			if(gameState == STATES.ToMain) {
				battleHandler.clearBattleField();
				player.setY(player.getY() - 10);
				Player.direction = "up";
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
				player.setY(player.getY() + 10);
				Player.direction = "down";
			}
		}
		
		// status
		if(gameState == STATES.Status) {		
			statusC.tick();
			if(gameState == STATES.ToMain) {
				//justInOut = true;	
				player.setY(player.getY() - 10);
				Player.direction = "up";
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
			miniDis.tick();
			spawner.tick();
			miniHandler.tick();
			
			if(MiniDisplay.HEALTH <= 0) {
				menuTo = 0;
				miniDis.setGoldEarned(miniDis.getScore() / 100);
				stats.gold += miniDis.getGoldEarned();
				gameState = STATES.MinigameOver;
				miniHandler.clearBattleField();
				player.setX(player.getX() + 10);
				Player.direction = "right";
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
		
		if(gameState == STATES.Play || gameState == STATES.EventText || 
				gameState == STATES.ChestText || gameState == STATES.Reminder ||
				gameState == STATES.InfoBoard) {
			
			g2d.translate(-camera.getX(), -camera.getY());

			tileManager.render(g2d);
			handler.render(g2d);
			
			g2d.translate(camera.getX(), camera.getY());
		}	
		
		if(gameState == STATES.ToShop) {
			transition.toShop(g2d);
		}
		else if(gameState == STATES.Shop) {
			g2d.drawImage(bgrd_img, 0, 0, WIDTH, HEIGHT, null);
			shop.render(g2d);
		}
		else if(gameState == STATES.TowerUp) {
			g2d.drawImage(bgrd_img, 0, 0, WIDTH, HEIGHT, null);
			towerUp.render(g2d);
		}
		else if(gameState == STATES.WallUp) {
			g2d.drawImage(bgrd_img, 0, 0, WIDTH, HEIGHT, null);
			wallUp.render(g2d);
		}
		else if(gameState == STATES.CitizneUp) {
			g2d.drawImage(bgrd_img, 0, 0, WIDTH, HEIGHT, null);
			citizenUp.render(g2d);
		}
		else if(gameState == STATES.ToBattle) {
			transition.toBattle(g2d);
		}
		else if(gameState == STATES.Battle) {
			g2d.drawImage(dragonBattle_img, 0, 0, WIDTH, HEIGHT, null);
			battleHandler.render(g2d);
			battle.render(g2d);	
		}
		else if(gameState == STATES.EventText || gameState == STATES.ChestText ||
				gameState == STATES.Reminder || gameState == STATES.InfoBoard) {
			textbox.render(g2d);
			if(handler.isAction()) {
				if(gameState == STATES.EventText) {
					player.setX(player.getX() + 10);
					Player.direction = "right";
				}
				else if(gameState == STATES.ChestText) {
					if(textbox.chestType == 0) {
						textbox.setMemeNum((textbox.getMemeNum()+1) % textbox.getMeme_size());
					}
					else if(textbox.chestType == 1) {
						textbox.setInfoInd((textbox.getInfoInd()+1) % textbox.getInfoTextSize());
					}
					player.setY(player.getY() + 5);
					Player.direction = "down";
				}
				else if(gameState == STATES.Reminder) {
					if(textbox.reminder == 0) {
						player.setY(player.getY() - 10);
						Player.direction = "up";
					}else {
						player.setX(player.getX() + 10);
						Player.direction = "right";
					}
					
				}
				else if(gameState == STATES.InfoBoard) {
					switch(textbox.namesInd) {
					case 0:
						player.setY(player.getY() + 10);
						Player.direction = "down";
						break;
						
					case 1:
						player.setY(player.getY() - 10);
						Player.direction = "up";
						break;
					
					case 2:
						player.setX(player.getX() + 10);
						Player.direction = "right";
						break;
					
					case 3:
						player.setX(player.getX() + 10);
						Player.direction = "right";
						break;
					
					case 4:
						player.setY(player.getY() - 10);
						Player.direction = "up";
						break;
						
					}
				}
				
				gameState = STATES.Play;
			}
		}
		else if(gameState == STATES.GameOver) {
			g2d.drawImage(dragonBattle_img, 0, 0, WIDTH, HEIGHT, null);
			gameOver.render(g2d);
		}
		else if(gameState == STATES.Menu) {
			g2d.drawImage(mainmenu_img, 0, 0, WIDTH, HEIGHT, null);
			if(fromGameOver) {
				loadMap(map);
				fromGameOver = false;
			}
			menu.render(g2d);
		}
		else if(gameState == STATES.ToMain) {
			transition.toMain(g2d);
		}
		else if(gameState == STATES.ToMiniGame) {
			transition.toMiniGame(g2d);
		}
		else if(gameState == STATES.Minigame || gameState == STATES.MinigameOver) {
			g2d.drawImage(battlemap_img, 0, 0, WIDTH, HEIGHT, null);
			miniDis.render(g2d);
			if(gameState == STATES.Minigame) miniHandler.render(g2d);
			if(gameState == STATES.MinigameOver) {
				gameOver.render(g2d);
			}
		}
		else if(gameState == STATES.ToStatus) {
			transition.toStatus(g2d);
		}
		else if(gameState == STATES.Status) {
			g2d.drawImage(bgrd_img, 0, 0, WIDTH, HEIGHT, null);
			statusC.render(g2d);
		}
		else if(gameState == STATES.Info || gameState == STATES.GeneralInfo || gameState == STATES.MiniGameInfo) {
			g2d.drawImage(staticpage_img, 0, 0, WIDTH, HEIGHT, null);
			info.render(g2d);
		}
		
		
		// player status
		if(gameState == STATES.Play || gameState == STATES.Shop || gameState == STATES.CitizneUp ||
				gameState == STATES.TowerUp || gameState == STATES.WallUp || gameState == STATES.Status ||
				gameState == STATES.ChestText) {
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
					player = new Player(xx*32, yy*32, ID.Player, handler, css, eGen, this, stats, textbox);
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
					handler.addObject(new Tower(xx*32, yy*32, ID.Block, item_ss, handler));
				}
				
				
				else if(red == 255 && green == 255 && blue == 255) {
					handler.addObject(new Door(xx*32, yy*32, ID.ShopDoor, drawss));
				}
				
				else if(red == 245 && green == 245 && blue == 245) {
					handler.addObject(new Door(xx*32, yy*32, ID.WallDoor, drawss));
				}
				
				else if(red == 255 && green == 255 && blue == 0) {
					handler.addObject(new Wall(xx*32, yy*32, ID.Wall, drawss));
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
					eGen = new EventsGenerator(xx*32, yy*32, ID.EventGen, item_ss, stats);
					handler.addObject(eGen);
				}
				
				else if(red == 200 && green == 200 && blue == 200) {
					handler.addObject(new Door(xx*32, yy*32, ID.MiniGameDoor, drawss));
				}
				
				else if(red == 150 && green == 150 && blue == 150) {
					handler.addObject(new Door(xx*32, yy*32, ID.StatusDoor, drawss));
				}
				
				else if(red == 128 && green == 0) {
					if(blue == 128) {
						handler.addObject(new Chest(xx*32, yy*32, ID.Chest, item_ss, 0));
					}
					else if(blue == 100) {
						handler.addObject(new Chest(xx*32, yy*32, ID.Chest, item_ss, 1));
					}
					
				}
				
				else if(red == 150 && green == 50) {
					if(blue == 80) handler.addObject(new InfoBoard(xx*32, yy*32, ID.InfoBoard, item_ss, 0));
					else if(blue == 79) handler.addObject(new InfoBoard(xx*32, yy*32, ID.InfoBoard, item_ss, 1));
					else if(blue == 78) handler.addObject(new InfoBoard(xx*32, yy*32, ID.InfoBoard, item_ss, 2));
					else if(blue == 77) handler.addObject(new InfoBoard(xx*32, yy*32, ID.InfoBoard, item_ss, 3));
					else if(blue == 76) handler.addObject(new InfoBoard(xx*32, yy*32, ID.InfoBoard, item_ss, 4));
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
