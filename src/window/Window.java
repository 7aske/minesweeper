package window;

import game.Game;
import settings.Settings;

import javax.swing.*;

public class Window extends JFrame {
	private Settings settings = new Settings();
	private JPanel panel = new JPanel();
	private Game game = new Game(this.panel);

	public Window() {
//		this.setSize((game.getW()+4) * settings.blockSize.width, (game.getH() + 5) * settings.blockSize.height);
		this.setSize(settings.windowSize);
		this.setLocationRelativeTo(null);
		this.setTitle("Minesweeper");
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.add(panel);
		this.setVisible(true);
	}
}
