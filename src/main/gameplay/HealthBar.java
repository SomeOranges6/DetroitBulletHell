package main.gameplay;

import main.BulletHellLogic;
import java.awt.*;

/**
 * HealthBar class for displaying the player's health at the bottom center of the screen.
 */
public class HealthBar {

    private final int barWidth;
    private final int barHeight;
    private final Color barColor;
    private final Color backgroundColor;

    public HealthBar(int barWidth, int barHeight, Color barColor, Color backgroundColor) {
        this.barWidth = barWidth;
        this.barHeight = barHeight;
        this.barColor = barColor;
        this.backgroundColor = backgroundColor;
    }

    /**
     * Draws the health bar on the screen.
     *
     * @param g2 Graphics2D object used for rendering.
     */
    public void draw(Graphics2D g2) {
        int screenWidth = BulletHellLogic.screenWidth;
        int screenHeight = BulletHellLogic.screenHeight;

        // Calculate the position of the health bar (bottom center)
        int barX = (screenWidth - barWidth) / 2;
        int barY = screenHeight - barHeight - 20; // Offset 20px from the bottom edge

        // Calculate the health percentage and current bar width
        int maxHealth = BulletHellLogic.player.character.health();
        int currentHealth = BulletHellLogic.player.health;
        double healthPercentage = Math.max(0, (double) currentHealth / maxHealth);
        int currentBarWidth = (int) (barWidth * healthPercentage);

        // Draw the background of the health bar
        g2.setColor(backgroundColor);
        g2.fillRect(barX, barY, barWidth, barHeight);

        // Draw the health portion of the bar
        g2.setColor(barColor);
        g2.fillRect(barX, barY, currentBarWidth, barHeight);

        // Draw the border around the bar
        g2.setColor(Color.BLACK);
        g2.drawRect(barX, barY, barWidth, barHeight);
    }
}
