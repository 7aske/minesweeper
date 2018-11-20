package game;

import settings.Settings;

import java.util.Random;

public class Game {
	private int h = Settings.GRID_H;
	private int w = Settings.GRID_W;
	private Block[][] grid = new Block[h][w];
	private int[][] matrix = new int[h][w];

	public Game(){
        int occupiedSpots = 0;
        while(occupiedSpots < Settings.MINE_COUNT){
            Random random = new Random();
            int x = random.nextInt(h);
            int y = random.nextInt(w);
            if(matrix[x][y] == 0){
                matrix[x][y] = -1;
                occupiedSpots++;
            }
        }

		for(int i = 0; i < this.h;i++){
			for (int j = 0; j < this.w; j++) {
				this.grid[i][j] = new Block(matrix[i][j]);
			}
		}
	}

	public Block[][] getGrid() {
		return grid;
	}

	public void setGrid(Block[][] grid) {
		this.grid = grid;
	}

	public int getH() {
		return h;
	}

	public int getW() {
		return w;
	}
}
