
package entity;
import approject.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class button extends entity {
    GamePanel gp;
    String type, state;
    private final player Player;

    // Just a constructor to connect the main game panel
    public button(GamePanel gp, player Player, String type, int x, int y, String state) {
        this.gp = gp;
        this.Player = Player;
        this.type = type;
        this.x = x;
        this.y = y;
        this.state = state;
        getButtonImage();
    }


    // To get the zombie image & animation
    public void getButtonImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/zombie/ZombieSprite1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/entitySprites/zombie/Zombie11.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Image file not found. Please check the file path.");
            e.printStackTrace();
        }
    }

    public void update() {
        // Basically the frame changes 3 times every second, declaring it for the sprite
        spriteAnim();

        if (Player.zombieCanMove) {
            if (type.equals("cycle")){
                if (state.equals("up")){
                    state = "down";
                }
                if (state.equals("down")){
                    state = "up";
                }
            }
            if (type.equals("button")) {

            }
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
