package main.swing;

import main.BulletHellLogic;

import javax.swing.*;

import java.awt.*;


public class WinScreen extends JPanel{

	public WinScreen(){
		this.setBackground(Color.CYAN);
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
		g2d.drawString("You Win", 675, 200);

		Font subtitleFont = new Font("Arial", Font.BOLD, 20);
		g2d.setFont(subtitleFont);
		g2d.drawString("Press X to Exit", 675, 500);
	}

}