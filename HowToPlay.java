import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HowToPlay {
	JFrame frame;
	DrawingPanel panel;
	JLabel label1, label2, label3, label4, label5;

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TitleScreen();
            }
        });
	}
	
	HowToPlay(){
		frame = new JFrame("How To Play");
		frame.setSize(new Dimension(850, 650));
		frame.setLocationRelativeTo(null);
		panel = new DrawingPanel();
		
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class DrawingPanel extends JPanel {
		DrawingPanel(){
			this.setBackground(Color.BLACK);
			this.setPreferredSize(new Dimension(800, 600));
		}
		public void paintComponent(Graphics g) {
			super.PaintComponent(g);
			Graphics2D g2d = (Graphics2D)g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			Font tutorialFont = new Font("Arial", Font.BOLD, 20);
            g2d.setFont(subtitleFont);
            
            g2d.drawString("Press U to Attack", ALLBITS, ABORT);
            g2d.drawString("Press Y to Switch Weapons", ALLBITS, ABORT);
            
		}
	}

}
