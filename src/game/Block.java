package game;

import settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Block extends JButton {
	private Settings settings;
	public Block(int value){
		this.setPreferredSize(settings.BLOCK_SIZE);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
		this.setOpaque(true);
		Random rand = new Random();
		int r = 211;
		int g = 211;
		int b = 211;

		Color color = new Color(r,g,b);

		this.setBackground(color);

		this.addActionListener(e->{
			final int rl = 128;
			final int gl = 128;
			final int bl = 128;
			this.setColor(new Color(rl,gl,bl));
			if (value == -1)
				this.setText("B");
			else if (value == 0)
				this.setText("");
			else
				this.setText(Integer.toString(value));
		});

	}
	public void setColor(Color color){
		this.setBackground(color);
	}
}
