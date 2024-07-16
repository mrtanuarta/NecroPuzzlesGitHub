package entity;
import approject.GamePanel;
import approject.keyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class zombie extends entity {
    private int Moves = 0;
    private long lastMoveTime = 0;
    private final int cooldown = 500;
    GamePanel gp;
    keyHandler keyH;
    BufferedImage left, right;
    String direction = "down"; // Default direction set to "down"
    int x, y, speed;
    int spriteCounter = 0;
    int spriteNum = 1;

    // Just a constructor to connect the main game panel
    public zombie(GamePanel gp, keyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        getZombieImage();
        setDefaultValues();
    }

    // This is the default values where the zombie will spawn, DONT TOUCH THE SPEED
    public void setDefaultValues() {
        x = 0;
        y = 256;
        speed = 64;
    }

    // To get the zombie image & animation
    public void getZombieImage() {
        try {
            left = ImageIO.read(getClass().getResourceAsStream("/zombie/ZombieSprite1.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/zombie/ZombieSprite3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Image file not found. Please check the file path.");
            e.printStackTrace();
        }
    }

    // This is where the zombie movement is declared
    public void update() {
        // The current time for the cooldown
        long currentTime = System.currentTimeMillis();
        // Basically the frame changes 3 times every second, declaring it for the sprite
        spriteAnim();
        // This is for the zombie movement
        if (currentTime - lastMoveTime >= cooldown) {
            int newX = x, newY = y;
            boolean moved = false;

            if (keyH.leftPressed) {
                newX -= speed;
                direction = "left";
                moved = true;
            }
            if (keyH.rightPressed) {
                newX += speed;
                direction = "right";
                moved = true;
            }
            // To check if it is able to move, if it hits a hitbox then this won't be updated
            if (moved && canMove(newX, newY)) {
                x = newX;
                y = newY;
                lastMoveTime = currentTime; // Update last move time
                moveUpdate();
            }

            // Just resetting the pressed keys
            keyH.leftPressed = keyH.rightPressed = false;
        }
    }

    // Check if it's able to move or not
    private boolean canMove(int newX, int newY) {
        int col = newX / gp.tileSize;
        int row = newY / gp.tileSize;

        // Check if the new position is within the bounds of the map
        if (col < 0 || col >= gp.maxScreenCol || row < 0 || row >= gp.maxScreenRow) {
            return false;
        }

        // Check for collision at the new position
        return !gp.tileM.isTileCollision(col, row);
    }

    // Updating the moves so it shows how many moves there are
    private void moveUpdate() {
        Moves++;
        System.out.println("Current Moves: " + Moves);
    }

    // Draw the sprite
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "left":
                image = (spriteNum == 1) ? left : right;
                break;
            case "right":
                image = (spriteNum == 1) ? right : left;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    // Sprite animation
    public void spriteAnim() {
        spriteCounter++;
        if (spriteCounter > 20) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }
}

