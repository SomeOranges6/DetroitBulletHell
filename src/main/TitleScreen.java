package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TitleScreen {
    JFrame frame;
    DrawingPanel panel;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(TitleScreen::new);
    }

    TitleScreen() {
        frame = new JFrame("Bass Pro Shoots");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set full screen
        frame.setUndecorated(true);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(frame);

        panel = new DrawingPanel();
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);

        // Add key listener for input
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_U) {
                    frame.dispose();
                    new BulletHellLogic(); // Start the game
                } else if (e.getKeyCode() == KeyEvent.VK_K) {
                	new  HowToPlay();
                }else if (e.getKeyCode() == KeyEvent.VK_J) {
                    System.exit(0); // Exit the game
                }
            }
        });
        panel.setFocusable(true);
        panel.requestFocusInWindow();
    }

    class DrawingPanel extends JPanel {
        private Image titleImage;

        DrawingPanel() {
            this.setBackground(Color.BLACK);
            this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());

            // Load the title screen image
            try {
                titleImage = ImageIO.read(getClass().getResource("/assets/titleScreen.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to load title screen image.");
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw the title screen image
            if (titleImage != null) {
                g2d.drawImage(titleImage, 0, 0, getWidth(), getHeight(), null);
            }

            // Draw the options
            Font optionsFont = new Font("Arial", Font.BOLD, 30);
            g2d.setColor(Color.RED);
            g2d.setFont(optionsFont);
            g2d.drawString("Press A to Start", getWidth() / 2 - 150, getHeight() - 200);
            g2d.drawString("Press Y to See Controls", getWidth() / 2 - 150, getHeight() - 150);
            g2d.drawString("Press X to Exit", getWidth() / 2 - 150, getHeight() - 100);
        }
    }
}
