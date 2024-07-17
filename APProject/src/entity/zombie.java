package entity;
import tileCode.tileManager;
import approject.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class zombie extends entity {
    GamePanel gp;
    String direction = "left"; // Default direction set to "left"
    private final player Player;
    private final tileManager tileM;

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
            left1 = ImageIO.read(getClass().getResourceAsStream("/zombie/ZombieSprite1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/zombie/Zombie11.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/zombie/ZombieSprite3.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/zombie/Zombie31.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/zombie/ZombieSprite4.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/zombie/Zombie41.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/zombie/ZombieSprite2.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/zombie/Zombie21.png"));
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
            else if (direction.equals("up") && checkUp(x, y)) { // if right tile is a path tile
                newY -= speed;
                updateUpDeathTile(x, y);
            }
            else if (direction.equals("down") && checkDown(x, y)) { // if right tile is a path tile
                newY += speed;
                updateDownDeathTile(x, y);
            }

            else if (direction.equals("left") && !checkLeft(newX, newY)){
                direction = "right";
                updateRightDeathTile(x-64, y);
            }
            else if (direction.equals("right") && !checkRight(newX, newY)){
                direction = "left";
                updateLeftDeathTile(x+64, y);
            }
            else if (direction.equals("up") && !checkUp(newX, newY)){
                direction = "down";
                updateDownDeathTile(x, y-64);
            }
            else if (direction.equals("down") && !checkDown(newX, newY)){
                direction = "up";
                updateUpDeathTile(x, y+64);
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

    public boolean checkUp(int x, int y) {
        int col = x / gp.tileSize;
        int row = (y - speed) / gp.tileSize;

        // Check if the new position is within the bounds of the map
        if (col < 0 || col >= gp.maxScreenCol || row < 0 || row >= gp.maxScreenRow) {
            return false;
        }

        // Check for zombie path at the new position
        return gp.tileM.isZombiePath(col, row);
    }

    public boolean checkDown(int x, int y) {
        int col = x / gp.tileSize;
        int row = (y + speed) / gp.tileSize;

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

    public void updateUpDeathTile(int x, int y) {
        int col = x / gp.tileSize;
        int row = (y - speed) / gp.tileSize;

        if (gp.tileM.isZombiePath(col, row)){
            tileM.updateTile(col, row, 25);
        }
        else if (gp.tileM.isPlayerPath(col, row)){
            tileM.updateTile(col, row, 35);
        }

        if (gp.tileM.isZombiePath(col, row-1)){
            tileM.updateTile(col, row-1, 25);
        }
        else if (gp.tileM.isPlayerPath(col, row-1)){
            tileM.updateTile(col, row-1, 35);
        }

        if (gp.tileM.isZombiePath(col, row+1)){
            tileM.updateTile(col, row+1, 24);
        }
        else if (gp.tileM.isTileDeath(col, row+1)){
            tileM.updateTile(col, row+1, 34);
        }
    }

    public void updateDownDeathTile(int x, int y) {
        int col = x / gp.tileSize;
        int row = (y + speed) / gp.tileSize;

        if (gp.tileM.isZombiePath(col, row)){
            tileM.updateTile(col, row, 25);
        }
        else if (gp.tileM.isPlayerPath(col, row)){
            tileM.updateTile(col, row, 35);
        }

        if (gp.tileM.isZombiePath(col, row+1)){
            tileM.updateTile(col, row+1, 25);
        }
        else if (gp.tileM.isPlayerPath(col, row+1)){
            tileM.updateTile(col, row+1, 35);
        }

        if (gp.tileM.isZombiePath(col, row-1)){
            tileM.updateTile(col, row-1, 24);
        }
        else if (gp.tileM.isTileDeath(col, row-1)){
            tileM.updateTile(col, row-1, 34);
        }
    }


    // Draw the sprite
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "left" -> image = (spriteNum == 1) ? left1 : left2;
            case "right" -> image = (spriteNum == 1) ? right1 : right2;
            case "up" -> image = (spriteNum == 1) ? up1 : up2;
            case "down" -> image = (spriteNum == 1) ? down1 : down2;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    // Sprite animation
    public void spriteAnim() {
        spriteCounter++;
        if (spriteCounter > 40) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }
}