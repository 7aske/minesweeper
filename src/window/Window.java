package window;

import game.Game;
import settings.Settings;

import javax.swing.*;

public class Window extends JFrame {
	private Settings settings = new Settings();
	private JPanel panel = new JPanel();
	private Game game = new Game();
	private game.Block[][] grid;
	public Window(){
		this.setSize(settings.WINDOW_SIZE);
		this.setLocationRelativeTo(null);
		this.setTitle("Minesweeper");
		this.setResizable(false);
		this.grid = game.getGrid();
		for (int i = 0; i < game.getH(); i++){
			for (int j = 0; j < game.getW(); j++) {
				this.panel.add(grid[i][j]);
			}
		}
		this.add(panel);
		this.setVisible(true);
	}
}
