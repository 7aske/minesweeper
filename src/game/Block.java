package game;

import settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Block extends JButton {
	private Settings settings;
	public Block(String text){
		this.setPreferredSize(settings.blockSize);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
		this.setOpaque(true);
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();

		Color color = new Color(r,g,b);

		this.setBackground(color);
		this.setText(text);

		this.addActionListener(e->{
			final float rl = rand.nextFloat();
			final float gl = rand.nextFloat();
			final float bl = rand.nextFloat();
			this.setColor(new Color(rl,gl,bl));
		});

	}
	public void setColor(Color color){
		this.setBackground(color);
	}
}
