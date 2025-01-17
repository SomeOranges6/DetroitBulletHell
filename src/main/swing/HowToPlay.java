package main.swing;

import main.BulletHellLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static main.BulletHellLogic.renderActionCommand;
import static main.BulletHellLogic.window;

public class HowToPlay extends JPanel{
	public HowToPlay(){
		this.setPreferredSize(BulletHellLogic.screenDim);
		this.setDoubleBuffered(true);
		this.setBackground(Color.black);

		this.setFocusable(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(Color.RED);
		Font tutorialFont = new Font("Arial", Font.BOLD, 40);
		g2d.setFont(tutorialFont);

		g2d.drawString("Press U to Attack and Start Game", 20, 100);
		g2d.drawString("Press I to Switch Weapons", 20, 300);
		g2d.drawString("Press J to Lock Your Shooting Direction", 20, 500);
	}

	public void dispose(){
		BulletHellLogic.window.remove(this);
	}

}