package main;

import javax.swing.*;

import main.TitleScreen.DrawingPanel;

import java.awt.*;
import java.awt.event.*;

public class HowToPlay {
	JFrame frame;
	DrawingPanel panel;
	JLabel label1, label2, label3, label4, label5;

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HowToPlay();
            }
        });
	}
	
	HowToPlay(){
		frame = new JFrame("How To Play");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set full screen
        frame.setUndecorated(true);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(frame);

        panel = new DrawingPanel();
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
		
		panel.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_U) {
					new TitleScreen();
					frame.dispose();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
		});
		
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class DrawingPanel extends JPanel {
		DrawingPanel(){
			this.setBackground(Color.BLACK);
			this.setPreferredSize(new Dimension(1920,1080));
			this.setFocusable(true);
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			g2d.setColor(Color.RED);
			Font tutorialFont = new Font("Arial", Font.BOLD, 40);
            g2d.setFont(tutorialFont);
            
            g2d.drawString("Press A to Attack and go back to Title Screen", 20, 100);
            g2d.drawString("Press B to Switch Weapons", 20, 300);
            g2d.drawString("Press X to Lock Your Shooting Direction", 20, 500);
		}
	}
}