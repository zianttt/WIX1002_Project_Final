package tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.Game;
import utils.BufferedImageLoader;
import utils.SpriteSheet;


public class TileManager {
	
	private Game game;
	private Tile[] tiles;
	private int mapTileNum[][];
	
	private int choiceBG;
	
	////
	private BufferedImageLoader loader;
	private BufferedImage scenes = null;
	private SpriteSheet sss;
	private BufferedImage sceneDraw = null;
	private SpriteSheet drawss;
	private BufferedImage background = null;
	private SpriteSheet fss;
	////
	
	public TileManager(Game game, BufferedImageLoader loader, int choice) {
		this.game = game;
		this.loader = loader;
		choiceBG = choice;
		
		tiles = new Tile[11];
		mapTileNum = new int[game.maxWorldCol][game.maxWorldRow];
		
		
		
		////
		//sceneDraw = loader.loadImage("/pics/tileImg.png");
		sceneDraw = loader.loadImage("/pics/bgTileImg.png");
		drawss = new SpriteSheet(sceneDraw);
		background = loader.loadImage("/pics/background.png");
		fss = new SpriteSheet(background);	
		////
		
		getTileImage();
		if(choice == 1) {	
			loadMap("/maps/map2.txt");
		}
		else if(choice == 2) {
			loadMap("/maps/staticBG.txt");
		}
		
	}

	private void getTileImage() {
		
		
		try {
			// 0 grass
			tiles[0] = new Tile();
			tiles[0].image = fss.grabImage(298, 232, 32, 32);
			//tiles[0].image = drawss.grabImage(32, 80, 32, 32);
			
			
			// 1 water
			tiles[1] = new Tile();
			//tiles[1].image = sss.grabImage(298, 232, 32, 32);
			tiles[1].image = drawss.grabImage(0, 48, 32, 32);
			
			// 2 road
			tiles[2] = new Tile();
			tiles[2].image = drawss.grabImage(32, 0, 16, 16);
			
			// 3 mountain
			tiles[3] = new Tile();
			tiles[3].image = drawss.grabImage(0, 32, 16, 16);
			
			// 4 lava0
			tiles[4] = new Tile();
			tiles[4].image = drawss.grabImage(48, 0, 16, 16);
			
			// 5 lava1
			tiles[5] = new Tile();
			tiles[5].image = drawss.grabImage(48, 16, 16, 16);
			
			// 6 lava2
			tiles[6] = new Tile();
			tiles[6].image = drawss.grabImage(0, 32, 16, 16);
			
			// 7 lava3
			tiles[7] = new Tile();
			tiles[7].image = drawss.grabImage(16, 32, 16, 16);
			
			// 8 lava4
			tiles[8] = new Tile();
			tiles[8].image = drawss.grabImage(32, 32, 16, 16);
			
			// 9 lava5
			tiles[9] = new Tile();
			tiles[9].image = drawss.grabImage(48, 32, 16, 16);
			
			// 10 mountainStyle
			tiles[10] = new Tile();
			tiles[10].image = drawss.grabImage(48, 0, 16, 16);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	private void loadMap(String filename) {
		
		try {
			
			InputStream input = getClass().getResourceAsStream(filename);
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			int col = 0;
			int row = 0;
			
			if(choiceBG == 1) {
				while(col < game.maxWorldCol && row < game.maxWorldRow) {
					
					String line = reader.readLine();
					
					while(col < game.maxWorldCol) {
						
						String[] nums = line.split(" ");
						int num = Integer.parseInt(nums[col]);
						
						mapTileNum[col][row] = num;
						col++;
						
					}
					if(col == game.maxWorldCol) {
						col = 0;
						row++;
					}
				}
			}
			if(choiceBG == 2) {
				while(col < game.staticCol && row < game.staticRow) {
					
					String line = reader.readLine();
					
					while(col < game.staticCol) {
						
						String[] nums = line.split(" ");
						int num = Integer.parseInt(nums[col]);
						
						mapTileNum[col][row] = num;
						col++;
						
					}
					if(col == game.staticCol) {
						col = 0;
						row++;
					}
				}
			}
			

			reader.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void render(Graphics2D g2d) {
		
		int col = 0;
		int row = 0;
		int xx = 0;
		int yy = 0;
		
		if(choiceBG == 1) {
			while(col < game.maxWorldCol && row < game.maxWorldRow) {
				
				int tileNum = mapTileNum[row][col];
				
				
				g2d.drawImage(tiles[tileNum].image, xx, yy, game.tileSize, game.tileSize, null);
				col++;
				xx += game.tileSize;
				
				if(col == game.maxWorldCol) {
					col = 0;
					xx = 0;
					row++;
					yy += game.tileSize;
				}
			}
		}
		
		else if(choiceBG == 2) {
			while(col < game.staticCol && row < game.staticRow) {
				
				int tileNum = mapTileNum[col][row];			
				
				g2d.drawImage(tiles[tileNum].image, xx, yy, game.tileSize, game.tileSize, null);
				col++;
				xx += game.tileSize;
				
				if(col == game.staticCol) {
					col = 0;
					xx = 0;
					row++;
					yy += game.tileSize;
				}
			}
		}
	}
}
