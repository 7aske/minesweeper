package game;

public class Game {
	private int h = 10;
	private int w = 10;
	private Block[][] grid = new Block[h][w];
	public Game(){
		for(int i = 0; i < this.h;i++){
			for (int j = 0; j < this.w; j++) {
				this.grid[i][j] = new Block("B");
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
