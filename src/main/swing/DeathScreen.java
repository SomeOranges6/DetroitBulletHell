package main.swing;

import main.BulletHellLogic;

import javax.swing.*;

import java.awt.*;


public class DeathScreen extends JPanel {
	
	public DeathScreen(){
		this.setBackground(Color.RED);
		this.setPreferredSize(BulletHellLogic.screenDim);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Font deathFont = new Font("Arial", Font.BOLD, 40);
		g2d.setFont(deathFont);
		g2d.drawString("YOU DIED", 675, 200);

		Font subtitleFont = new Font("Arial", Font.BOLD, 20);
		g2d.setFont(subtitleFont);
		g2d.drawString("Press A to Restart", 675, 400);
		g2d.drawString("Press X to Exit", 675, 500);

	}
}