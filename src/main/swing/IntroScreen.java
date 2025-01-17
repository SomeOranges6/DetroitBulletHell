package main.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import main.BulletHellLogic;
import main.swing.*;

import static main.BulletHellLogic.window;

public class IntroScreen extends JPanel {
    BufferedImage titleScreen;
    
    BufferedImage loadImage(String filename) {
    	BufferedImage img = null;
    	try{
    		String titleScreen = "/assets/7iodT64k.jpg";
    		img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(titleScreen)));
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    		JOptionPane.showMessageDialog(null, "An image failed to load: " + filename, "Error", JOptionPane.ERROR_MESSAGE);
    	}
    	//DEBUG
    	//if (img == null) System.out.println("null");
    	//else System.out.printf("w=%d, h=%d%n",img.getWidth(), img.getHeight());
    	return img;
    }

    public IntroScreen(){
		this.setPreferredSize(BulletHellLogic.screenDim);
		this.setDoubleBuffered(true);
		this.setBackground(Color.black);
		this.setFocusable(true);
	}
    
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(new Color(237, 58, 66));
		titleScreen = loadImage("/assets/7iodT64k.jpg");
		g2d.drawImage(titleScreen, 0, 0, getWidth(), getHeight(), null);

		Font subtitleFont = new Font("Arial", Font.BOLD, 20);
		g2d.setFont(subtitleFont);
		g2d.drawString("Press A To Begin", 300, 400);
	}

	public void dispose(){
		window.remove(this);
	}

}