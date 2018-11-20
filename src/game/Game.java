package game;

import org.omg.CORBA.MARSHAL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Random;

public class Game {
	private int h = 10;
	private int w = 10;
	private Block[][] grid = new Block[h][w];
	private int bombs;
	private int startBombs = 20;
	private JPanel panel;

	public Game(JPanel panel) {
		this.panel = panel;
		this.init();
	}

	public void init() {
		this.bombs = this.startBombs;
		for (int i = 0; i < this.h; i++) {
			for (int j = 0; j < this.w; j++) {
				this.grid[i][j] = new Block(0, i, j);
				this.grid[i][j].addMouseListener(new OnClick(this.grid[i][j], this));
				this.panel.add(grid[i][j]);
			}
		}
		while(this.bombs > 0) {
			int x = (int) (Math.random() * this.h);
			int y = (int) (Math.random() * this.w);
			Block b = this.getBlock(x, y);
			if(b.getType() != 1){
				b.setType(1);
				this.bombs--;
			}

		}

		this.setHints();
	}

	public void reset(String text) {
		for (int i = 0; i < this.h; i++) {
			for (int j = 0; j < this.w; j++) {
				Block b = this.grid[i][j];
				if(b.getFlag() && b.getType() == 1){
					b.setBackground(new Color(0.0f,1.0f,0.0f));

				} else if(b.getType() == 1){
					b.setBackground(new Color(1.0f,0.0f,0.0f));
					b.setText("B");
				}

			}
		}
		JOptionPane.showMessageDialog(null, text);
		this.panel.removeAll();
		this.init();
		this.panel.repaint();
		this.panel.revalidate();
	}

	public Block[] getAdjacent(Block b) {
		int i = b.getGridX();
		int j = b.getGridY();
		Block[] array = new Block[8];
		try {
			array[0] = this.grid[i - 1][j]; //top
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			array[1] = this.grid[i + 1][j]; //bot
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			array[2] = this.grid[i][j + 1]; //right
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			array[3] = this.grid[i][j - 1]; //left
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			array[4] = this.grid[i - 1][j + 1]; //topright
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			array[5] = this.grid[i - 1][j - 1]; //topleft
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			array[6] = this.grid[i + 1][j + 1]; //botright
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			array[7] = this.grid[i + 1][j - 1]; //botleft
		} catch (ArrayIndexOutOfBoundsException e) {

		}


		return array;
	}

	public void setHints() {
		for (int i = 0; i < this.h; i++) {
			for (int j = 0; j < this.w; j++) {
				int bombCount = 0;
				Block block = this.grid[i][j];
				Block[] array = this.getAdjacent(block);
				for (int k = 0; k < array.length; k++) {
					if (array[k] != null) {
						if (array[k].getType() == 1) {
							bombCount++;
						}
					}
				}
				if (block.getType() == 0) {
					block.setType(0, bombCount);
				}
			}
		}
	}

	public Block[] reveal(Block[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				if (!array[i].clicked() && array[i].getBombCount() == 0 && array[i].getType() == 0 && !array[i].getFlag()) {
					array[i].click();
					this.reveal(getAdjacent(array[i]));
				} else if (!array[i].clicked() && array[i].getType() == 0 && !array[i].getFlag()) {
					array[i].click();
				}
			}
		}
		return array;
	}

	public void checkWin() {
		int freeCount = 0;
		for (int i = 0; i < this.h; i++) {
			for (int j = 0; j < this.w; j++) {
				if (this.grid[i][j].clicked()) {
					freeCount++;
				}
			}
		}
		if (freeCount == this.h * this.w - 20) {
			this.reset("GREAT SUCCESS!");
		}
	}

	public void checkFlag(Block b) {
		Block[] array = this.getAdjacent(b);
		int flags = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				if (array[i].getFlag()) flags++;
			}
		}
		if (flags != 0 && flags == b.getBombCount()) {
			this.reveal(array);
		}
	}

	public Block[][] getGrid() {
		return grid;
	}

	public int getH() {
		return h;
	}

	public int getW() {
		return w;
	}

	public Block getBlock(int i, int j) {
		return this.grid[i][j];
	}
}

class OnClick implements MouseListener {
	private Block block;
	private Game game;

	public OnClick(Block block, Game game) {
		this.block = block;
		this.game = game;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			if(!this.block.getFlag()) {
				if (block.getType() == 1) {
					this.block.click();
					game.reset("Game Over!");
				} else if (this.block.getType() == 0) {
					if (this.block.clicked()) {
						this.game.checkFlag(this.block);
					} else {
						this.block.click();
						if (this.block.getBombCount() == 0) {
							game.reveal(game.getAdjacent(this.block));
						}
					}


				}
			}
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			this.block.flag();
		}
		game.checkWin();
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
