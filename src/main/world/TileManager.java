package main.world;

import java.awt.Graphics2D;
import java.io.*;

import javax.imageio.ImageIO;

import main.BulletHellLogic;
import main.swing.*;

public class TileManager {
	
	GamePanel gp =  BulletHellLogic.gPanel;
	Tile[] tile;
	int mapTileNum [] [];
	int mapNum = 1;
	
	public TileManager() {
		
		mapTileNum = new int[BulletHellLogic.maxScreenCol][BulletHellLogic.maxScreenRow];
		//number of tiles used
		tile = new Tile[17];
		
		getTileImage();
		loadMap();
	}
	
	public void getTileImage() {
		//Upload all tiles into array list
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tile1.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tile2.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tile3.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tile4.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tile5.png"));
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/TileSing1.png"));
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/TileSing2.png"));
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GrassBlank.png"));
			
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass1.png"));
			
			tile[9] = new Tile();
			tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass2.png"));
			
			tile[10] = new Tile();
			tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass3.png"));
			
			tile[11] = new Tile();
			tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass4.png"));
			
			tile[12] = new Tile();
			tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GrassFlower.png"));
			
			tile[13] = new Tile();
			tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Flower1.png"));
			
			tile[14] = new Tile();
			tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Flower2.png"));
			
			tile[15] = new Tile();
			tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Flower3.png"));
			
			tile[16] = new Tile();
			tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Flower4.png"));

			

		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void loadMap() {
		try {	
			InputStream is = getClass().getResourceAsStream("/maps/map" + mapNum + ".txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col = 0;
			int row = 0;
			
			while (col < BulletHellLogic.maxScreenCol && row < BulletHellLogic.maxScreenRow) {
				String line = br.readLine();
				while(col < BulletHellLogic.maxScreenCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}	
				if(col == BulletHellLogic.maxScreenCol) {
					col = 0;
					row ++;
				}
			}
			br.close();
			
		}catch(Exception e){
			
		}
	}
	//draw the images into the panel
	public void draw(Graphics2D g2) {
		 
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < BulletHellLogic.maxScreenCol && row  < BulletHellLogic.maxScreenRow) {
			
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, BulletHellLogic.tileSize, BulletHellLogic.tileSize, null);
			col++;
			x += BulletHellLogic.tileSize;
			if(col == BulletHellLogic.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += BulletHellLogic.tileSize;
			}
		}
	}

}
