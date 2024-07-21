package entity;

import gamePanel.GamePanel;
import gamePanel.keyHandler;
import gamePanel.Level;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class player extends entity {
    private int Moves = 0;
    private long lastMoveTime = 0;
    private final int cooldown = 500;
    protected boolean moved = false;
    public static boolean zombieCanMove = false;
    GamePanel gp;
    keyHandler keyH;

    public player(GamePanel gp, keyHandler keyH, int x, int y, String direction) {
        this.gp = gp;
        this.keyH = keyH;
        getPlayerImage();
        this.x = x;
        this.y = y;
        speed = 64;
        this.direction = direction;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/player/PlayerUp1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/player/PlayerUp2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/player/PlayerLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/player/PlayerLeft2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/player/PlayerRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/player/PlayerRight2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/player/PlayerDown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/player/PlayerDown2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Image file not found. Please check the file path.");
            e.printStackTrace();
        }
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        spriteAnim();

        if (currentTime - lastMoveTime >= cooldown) {
            int newX = x, newY = y;
            moved = false;

            if (keyH.upPressed) {
                newY -= speed;
                direction = "up";
                moved = true;
            } else if (keyH.downPressed) {
                newY += speed;
                direction = "down";
                moved = true;
            } else if (keyH.leftPressed) {
                newX -= speed;
                direction = "left";
                moved = true;
            } else if (keyH.rightPressed) {
                newX += speed;
                direction = "right";
                moved = true;
            }

            if (moved && canMove(newX, newY)) {
                zombieCanMove = true;
                x = newX;
                y = newY;
                lastMoveTime = currentTime;
                moveUpdate();
            }

            keyH.upPressed = keyH.downPressed = keyH.leftPressed = keyH.rightPressed = false;
        }
    }

    private boolean canMove(int newX, int newY) {
        int col = newX / gp.tileSize;
        int row = newY / gp.tileSize;

        if (col < 0 || col >= gp.maxScreenCol || row < 0 || row >= gp.maxScreenRow) {
            return false;
        }

        return !gp.tileM.isTileCollision(col, row);
    }

    private boolean zombieInteracted = false; // New flag to track interaction
    public void checkTileUpdates(int x, int y) {
        if (!zombieInteracted && isDead(x, y)) {
            System.out.println("bozo ded");
            zombieInteracted = true; // Set flag to true after interaction occurs
            gp.showDeathScreen(gp.getCurrentLevel().levelNumber); // Show the death screen
        }
        if (!zombieInteracted && isVictory(x, y)) {
            System.out.println("yay W");
            zombieInteracted = true; // Set flag to true after interaction occurs
            gp.showVictoryScreen(gp.getCurrentLevel().levelNumber);
        }
    }

    private boolean isDead(int newX, int newY) {
        int col = newX / gp.tileSize;
        int row = newY / gp.tileSize;
         
        return gp.tileM.isTileDeath(col, row);
    }

    private boolean isVictory(int newX, int newY) {
        int col = newX / gp.tileSize;
        int row = newY / gp.tileSize;

        return gp.tileM.isTileVictory(col, row);
    }

    private void moveUpdate() {
        Moves++;
        System.out.println("Current Moves: " + Moves);
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                image = (spriteNum == 1) ? up1 : up2;
                break;
            case "down":
                image = (spriteNum == 1) ? down1 : down2;
                break;
            case "left":
                image = (spriteNum == 1) ? left1 : left2;
                break;
            case "right":
                image = (spriteNum == 1) ? right1 : right2;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    public void spriteAnim() {
        spriteCounter++;
        if (spriteCounter > 20) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }
}