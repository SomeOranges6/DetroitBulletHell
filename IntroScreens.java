package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class IntroScreens {
    JFrame frame1, frame2;
    DrawingPanel titlePanel, tutorialPanel;
    boolean titleVisible = true;
    boolean tutorialVisible = false;
    BufferedImage titleScreen;

    public static void main(String[] args){
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new IntroScreens();
            }
        });
    }
    
    static BufferedImage loadImage(String filename) {
    	BufferedImage img = null;
    	try{
    		img = ImageIO.read(new File("7iodT64k.jpg"));
    	} catch (IOException e) {
    		System.out.println(e.toString());
    		JOptionPane.showMessageDialog(null, "An image failed to load: " + filename, "Error", JOptionPane.ERROR_MESSAGE);
    	}
    	//DEBUG
    	//if (img == null) System.out.println("null");
    	//else System.out.printf("w=%d, h=%d%n",img.getWidth(), img.getHeight());
    	return img;
    }

    IntroScreens(){
        frame1 = new JFrame("Detroit");
        frame1.setSize(new Dimension(850, 650));
        frame1.setLocationRelativeTo(null);
        
        titlePanel = new DrawingPanel();
        titlePanel.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 85) {
					titlePanel.setVisible(false);
					titleVisible = false;
					tutorialVisible = true;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
        });
        
        frame1.setContentPane(titlePanel);
    	frame1.pack();
    	frame1.setVisible(true);
    	frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame2 = new JFrame("How To Play");
        frame2.setSize(new Dimension(850, 650));
        frame2.setLocationRelativeTo(null);
        
        
        tutorialPanel = new DrawingPanel();
		tutorialPanel.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 85) {
					tutorialVisible = false;
					tutorialPanel.setVisible(false);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
		});
		
        frame2.setContentPane(tutorialPanel);
    	frame2.pack();
    	frame2.setVisible(true);
    	frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    
    	class DrawingPanel extends JPanel {
    	
    	DrawingPanel(){
            this.setBackground(Color.BLACK);
            this.setPreferredSize(new Dimension(800, 600));
        }
    
        public void paintComponent(Graphics g) {
        	super.paintComponent(g);
    		Graphics2D g2d = (Graphics2D)g;
    		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    		this.setFocusable(true);

    		g2d.setColor(new Color(237, 58, 66));
        	if (titleVisible = true) {
        		titleScreen = loadImage("7iodT64k.jpg");
        		g2d.drawImage(titleScreen, 0, 0, getWidth(), getHeight(), null);
        	
        	    Font subtitleFont = new Font("Arial", Font.BOLD, 20);
        	    g2d.setFont(subtitleFont);
        	    g2d.drawString("Press U To Begin", 300, 400);
        	}
            
        	else if (tutorialVisible = true) {

    			Font tutorialFont = new Font("Arial", Font.BOLD, 40);
    	        g2d.setFont(tutorialFont);
    	            
    	        g2d.drawString("Press U to Attack and Start Game", 20, 100);
    	        g2d.drawString("Press I to Switch Weapons", 20, 300);
    	        g2d.drawString("Press J to Lock Your Shooting Direction", 20, 500);
        	}
    	}
    }
}