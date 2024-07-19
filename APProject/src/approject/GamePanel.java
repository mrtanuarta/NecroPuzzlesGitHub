package approject;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import entity.player;
import tileCode.tileManager;
/**
 *
 * @author Acer
 */
public class GamePanel extends JPanel implements Runnable {
    //Just some final integers
    final int originalTileSize = 16;
    final int size = 4;

    public int tileSize = originalTileSize * size;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;
    public tileManager tileM = new tileManager(this);
    keyHandler keyH = new keyHandler();
    Thread gameThread;
    player Player = new player(this , keyH);
    private final int FPS = 60;
    //Sets up screen
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocus();
    }
    //Starts the time
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    //The run thingy just sets up the fps so it limits the screen to like 60 fps
    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;  // Convert to milliseconds

                if (remainingTime > 0) {
                    Thread.sleep((long) remainingTime);
                }

                nextDrawTime += drawInterval;

                // Handle if we're running behind
                while (nextDrawTime < System.nanoTime()) {
                    nextDrawTime += drawInterval;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //Ok guys for the person continuing my legacy of this 500 lines of code i made by myself here is your task
    //make another class called the zombie class which will check the direction its looking at and turn the tile into a death tile 
    //make the zombie move every moves added and if the next tile it hit is a hitbox make it turn back
    //make a victory tile as well so if they win they win horray
    //update
    public void update() {
        //update the player
        Player.update();
    }
    //render it
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        Player.draw(g2);
        
        g2.dispose();
    }
}
