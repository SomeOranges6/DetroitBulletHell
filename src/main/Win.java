package main;

import javax.swing.*;

import main.HowToPlay.DrawingPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Win {
	JFrame frame;
	DrawingPanel panel;
	boolean deathVisible = true;

	public static void main(String[] args) {
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new Win();
	            }
	        });
	}
	
	Win(){
		frame = new JFrame("Win");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set full screen
        frame.setUndecorated(true);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(frame);

        panel = new DrawingPanel();
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
	}
	
	class DrawingPanel extends JPanel implements KeyListener{
		
		DrawingPanel(){
            this.setBackground(Color.cyan);
            this.setPreferredSize(new Dimension(1920, 1080));
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            this.setAlignmentX(CENTER_ALIGNMENT);
            this.addKeyListener(this);
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
            g2d.drawString("Press A to Restart", 675, 400);
            g2d.drawString("Press X to Exit", 675, 500);
            
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int press = e.getKeyCode();
			if (press == KeyEvent.VK_J) {
				frame.setVisible(false);
				deathVisible = false;
				System.exit(0);
			} else if(press == KeyEvent.VK_U){
				frame.setVisible(false);
				deathVisible = false;
				new BulletHellLogic();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}