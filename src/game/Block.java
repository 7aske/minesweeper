package game;

import settings.Settings;

import javax.swing.*;
import java.awt.*;

public class Block extends JButton {
	private Settings settings;
	private int type;
	boolean flagged = false;
	private int bombCount;
	private boolean open = false;
	private int x;
	private int y;

	public Block(int type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.setPreferredSize(settings.blockSize);
		this.setOpaque(true);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
		this.setFont(new Font("TimesRoman", Font.BOLD, 24));
	}

	public void click() {
		this.open = true;
		if (this.type == 0) {
			this.setBackground(new Color(0.6f, 0.6f, 0.6f));
			if (this.bombCount > 0) {
				this.setText(Integer.toString(this.bombCount));

			}
		} else if (this.type == 1) {
			this.setBackground(new Color(1.0f, 0.0f, 0.0f));
			this.setText("X");

		}
	}
	public boolean getFlag(){
		return this.flagged;
	}
	public void flag() {
		if (!this.clicked()) {
			if (this.flagged) {
				this.flagged = false;
				this.setText("");
			} else {
				this.flagged = true;
				this.setText("F");
			}
		}

	}

	public boolean clicked() {
		return this.open;
	}

	public int getType() {
		return type;
	}

	public int getGridX() {
		return x;
	}

	public int getGridY() {
		return y;
	}

	public int getBombCount() {
		return this.bombCount;
	}

	public void setType(int type, int bombs) {
		this.type = type;
		this.bombCount = bombs;
	}

	public void setType(int type) {
		this.type = type;
	}
}
