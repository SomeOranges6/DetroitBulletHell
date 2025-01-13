package main.gameplay;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class SpriteManager {
    private BufferedImage spriteSheet;
    private final int tileWidth = 32;
    private final int tileHeight = 32;
    private final int frameCount = 3;
    private int currentFrame = 0;
    private long lastFrameTime = 0;
    private final int frameDelay = 150; // Milliseconds between frames
    private boolean isMoving = false;

    public SpriteManager(String spriteSheetPath) {
        try {
            spriteSheet = ImageIO.read(getClass().getResource(spriteSheetPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawSprite(Graphics g, double facingAngle, int x, int y) {
        int row = getRowFromAngle(facingAngle);
        int frameX = (isMoving ? currentFrame : 1) * tileWidth; // Use second frame when idle
        int frameY = row * tileHeight;

        // Draw the current frame
        g.drawImage(spriteSheet, x, y, x + tileWidth + 15, y + tileHeight + 15,
                    frameX, frameY, frameX + tileWidth, frameY + tileHeight, null);

        // Update animation frame if moving
        if (isMoving) {
            updateAnimationFrame();
        }
    }

    private int getRowFromAngle(double facingAngle) {
        if (facingAngle == Math.toRadians(270)) return 3; // Up
        if (facingAngle == Math.toRadians(90)) return 0;  // Down
        if (facingAngle == Math.toRadians(180)) return 1; // Left
        return 2; // Right
    }

    private void updateAnimationFrame() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime >= frameDelay) {
            currentFrame = (currentFrame + 1) % frameCount; // Loop through frames
            lastFrameTime = currentTime;
        }
    }

    public void setMoving(boolean moving) {
        if (!moving) {
            currentFrame = 1; // Reset to the second frame when idle
        }
        isMoving = moving;
    }
}