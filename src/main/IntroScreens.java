package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IntroScreens {
    JFrame frame1, frame2;
    DrawingPanel titlePanel, tutorialPanel;
    JLabel title, startInstruction;
    boolean titleVisible = true;
    boolean tutorialVisible = false;

    public static void main(String[] args){
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new IntroScreens();
            }
        });
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
        		g2d.setColor(new Color(237, 58, 66));
        			
        		Font titleFont = new Font("Arial", Font.BOLD, 50);
        	    g2d.setColor(new Color(237, 58, 66));
        	    g2d.setFont(titleFont);
        	    g2d.drawString("Detroit", 300, 200);
        	
        	    Font subtitleFont = new Font("Arial", Font.BOLD, 20);
        	    g2d.setFont(subtitleFont);
        	    g2d.drawString("Press U To Begin", 300, 400);
        	}
            
        	else if (tutorialVisible = true) {
    			g2d.setColor(new Color(237, 58, 66));

    			Font tutorialFont = new Font("Arial", Font.BOLD, 40);
    	        g2d.setFont(tutorialFont);
    	            
    	        g2d.drawString("Press U to Attack and Start Game", 20, 100);
    	        g2d.drawString("Press I to Switch Weapons", 20, 300);
    	        g2d.drawString("Press J to Lock Your Shooting Direction", 20, 500);
        	}
    	}
    }
}