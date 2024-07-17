package entity;
import tileCode.tileManager;
import approject.GamePanel;
import approject.keyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class zombie extends entity {
    GamePanel gp;
    BufferedImage left, right;
    String direction = "left"; // Default direction set to "left"
    int x, y, speed;
    int spriteCounter = 0;
    int spriteNum = 1;
    private player Player;
    private tileManager tileM;

    // Just a constructor to connect the main game panel
    public zombie(GamePanel gp, player Player, tileManager tileM) {
        this.gp = gp;
        this.Player = Player;
        this.tileM = tileM;
        getZombieImage();
        setDefaultValues();
    }

    // This is the default values where the zombie will spawn, DONT TOUCH THE SPEED
    public void setDefaultValues() {
        x = 640;
        y = 384;
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
        // Basically the frame changes 3 times every second, declaring it for the sprite
        spriteAnim();

        int newX = x, newY = y;

        if (Player.zombieCanMove) {
            if (direction.equals("left") && checkLeft(x, y)) { // if left tile is a path tile
                newX -= speed;
                updateLeftDeathTile(x, y);
            }
            else if (direction.equals("right") && checkRight(x, y)) { // if right tile is a path tile
                newX += speed;
                updateRightDeathTile(x, y);
            }

            else if (!checkLeft(newX, newY)){
                direction = "right";
                updateRightDeathTile(x-64, y);
            }
            else if (!checkRight(newX, newY)){
                direction = "left";
                updateLeftDeathTile(x+64, y);
            }

            x = newX;
            y = newY;
            Player.zombieCanMove = false;
            Player.checkTileUpdates(Player.x, Player.y);
        }
    }

    public boolean checkLeft(int x, int y) {
        int col = (x - speed) / gp.tileSize;
        int row = y / gp.tileSize;

        // Check if the new position is within the bounds of the map
        if (col < 0 || col >= gp.maxScreenCol || row < 0 || row >= gp.maxScreenRow) {
            return false;
        }

        // Check for zombie path at the new position
        return gp.tileM.isZombiePath(col, row);
    }

    public boolean checkRight(int x, int y) {
        int col = (x + speed) / gp.tileSize;
        int row = y / gp.tileSize;

        // Check if the new position is within the bounds of the map
        if (col < 0 || col >= gp.maxScreenCol || row < 0 || row >= gp.maxScreenRow) {
            return false;
        }

        // Check for zombie path at the new position
        return gp.tileM.isZombiePath(col, row);
    }

    public void updateLeftDeathTile(int x, int y) {
        int col = (x - speed) / gp.tileSize;
        int row = y / gp.tileSize;

        if (gp.tileM.isZombiePath(col, row)){
            tileM.updateTile(col, row, 25);
        }
        else if (gp.tileM.isPlayerPath(col, row)){
            tileM.updateTile(col, row, 35);
        }

        if (gp.tileM.isZombiePath(col-1, row)){
            tileM.updateTile(col-1, row, 25);
        }
        else if (gp.tileM.isPlayerPath(col-1, row)){
            tileM.updateTile(col-1, row, 35);
        }

        if (gp.tileM.isZombiePath(col+1, row)){
            tileM.updateTile(col+1, row, 24);
        }
        else if (gp.tileM.isTileDeath(col+1, row)){
            tileM.updateTile(col+1, row, 34);
        }
    }

    public void updateRightDeathTile(int x, int y) {
        int col = (x + speed) / gp.tileSize;
        int row = y / gp.tileSize;

        if (gp.tileM.isZombiePath(col, row)){
            tileM.updateTile(col, row, 25);
        }
        else if (gp.tileM.isPlayerPath(col, row)){
            tileM.updateTile(col, row, 35);
        }

        if (gp.tileM.isZombiePath(col+1, row)){
            tileM.updateTile(col+1, row, 25);
        }
        else if (gp.tileM.isPlayerPath(col+1, row)){
            tileM.updateTile(col+1, row, 35);
        }

        if (gp.tileM.isZombiePath(col-1, row)){
            tileM.updateTile(col-1, row, 24);
        }
        else if (gp.tileM.isTileDeath(col-1, row)){
            tileM.updateTile(col-1, row, 34);
        }
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