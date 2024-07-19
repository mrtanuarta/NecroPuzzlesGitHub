/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import approject.GamePanel;
import approject.keyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class player extends entity {
    private int Moves = 0;
    private long lastMoveTime = 0;
    private final int cooldown = 500;
    GamePanel gp;
    keyHandler keyH;

    //Just a constructor to connect the main game panel
    public player(GamePanel gp, keyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        getPlayerImage();
        setDefaultValues();
    }
    //this is the default values where the player will spawn, DONT TOUCH THE SPEED
    public void setDefaultValues() {
        x = 640;
        y = 256;
        speed = 64;
    }
    //to get the player image & animation
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/PlayerUp1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/PlayerUp2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/PlayerLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/PlayerLeft2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/PlayerRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/PlayerRight2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/PlayerDown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/PlayerDown2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Image file not found. Please check the file path.");
            e.printStackTrace();
        }
    }
    //This is where the player movement is declared
    public void update() {
        //This is too instantiate at the start when the player havent moven the default would be down
        if (direction == null) {
            direction = "down";
        }
        //The current time for the cooldown
        long currentTime = System.currentTimeMillis();
        //Basically the frame changes 3 times every second, declaring it for the sprite
        spriteAnim();
        //This is for the player movement
        if (currentTime - lastMoveTime >= cooldown) {
            int newX = x, newY = y;
            boolean moved = false;

            if (keyH.upPressed) {
                newY -= speed;
                direction = "up";
                moved = true;
            }
            if (keyH.downPressed) {
                newY += speed;
                direction = "down";
                moved = true;
            }
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
            //To check if it is able to move, if it hits a hitbox then this wont be updated
            if (moved && canMove(newX, newY)) {
                x = newX;
                y = newY;
                lastMoveTime = currentTime; // Update last move time
                moveUpdate();
            }
            //check for death
            if (moved && isDead(newX, newY)) {
                System.out.println("bozo ded");
            }
            //check for victory
            if (moved && isVictory(newX, newY)) {
                System.out.println("yay W");
            }
            //just reseting the pressed
            keyH.upPressed = keyH.downPressed = keyH.leftPressed = keyH.rightPressed = false;
        }
    }
    //so if its able to move or no
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

    //updating the moves so it shows how much move are there
    private void moveUpdate() {
        Moves++;
        System.out.println("Current Moves: " + Moves);
    }
    //draw the sprite
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
    //yeah for the sprite again
    public void spriteAnim() {
        spriteCounter++;
        if (spriteCounter > 20) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }
}


