package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Death {
	JFrame frame;
	DrawingPanel panel;
	boolean deathVisible = true;

	public static void main(String[] args) {
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new Death();
	            }
	        });
	}
	
	Death(){
		frame = new JFrame("Game Over");
		frame.setSize(new Dimension(850, 650));
        frame.setLocationRelativeTo(null);
        panel = new DrawingPanel();
        
        frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class DrawingPanel extends JPanel implements KeyListener{
		
		DrawingPanel(){
            this.setBackground(Color.RED);
            this.setPreferredSize(new Dimension(800, 600));
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            this.setAlignmentX(CENTER_ALIGNMENT);
            this.addKeyListener(this);
        }
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			Font deathFont = new Font("Arial", Font.BOLD, 40);
			g2d.setFont(deathFont);
			g2d.drawString("YOU DIED", 300, 200);
			
			Font subtitleFont = new Font("Arial", Font.BOLD, 20);
            g2d.setFont(subtitleFont);
            g2d.drawString("Press E To Have Your IP Address Leaked", 200, 400);
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int press = e.getKeyCode();
			if (press == KeyEvent.VK_E) {
				frame.setVisible(false);
				deathVisible = false;
				//System.exit(0); //closes the program automatically
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}