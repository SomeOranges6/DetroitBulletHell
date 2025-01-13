import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class TitleScreen{
    JFrame frame;
    DrawingPanel panel;
    JLabel title, startInstruction;
    boolean setVisible;

    public static void main(String[] args){
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TitleScreen();
            }
        });
    }

    TitleScreen(){
        frame = new JFrame("Bass Pro Shoots");
        frame.setSize(new Dimension(850, 650));
        frame.setLocationRelativeTo(null);
        panel = new DrawingPanel();
        panel.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_U) {
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
            this.setPreferredSize(new Dimension(800, 600));
            this.setFocusable(true);
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Font titleFont = new Font("Arial", Font.BOLD, 50);
            g2d.setColor(new Color(237, 58, 66));
            g2d.setFont(titleFont);
            g2d.drawString("Bass Pro Shoots", 200, 200); //obviously to be changed later

            Font subtitleFont = new Font("Arial", Font.BOLD, 20);
            g2d.setFont(subtitleFont);
            g2d.drawString("Press U To Begin", 300, 400);
        }
    }
}