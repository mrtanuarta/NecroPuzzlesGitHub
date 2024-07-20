package approject;

import tileCode.tileManager;
import java.awt.*;
import javax.swing.JPanel;
import java.util.logging.Logger;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int size = 4;
    public int tileSize = originalTileSize * size;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;
    public tileManager tileM;
    keyHandler keyH = new keyHandler();
    Thread gameThread;
    private Level currentLevel;
    private final int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocus();

        loadLevel(4);
    }

    public keyHandler getKeyHandler() {
        return keyH;
    }

    public void loadLevel(int levelNumber) {
        switch (levelNumber) {
            case 1:
                currentLevel = new Level1(this);
                break;
            case 2:
                currentLevel = new Level2(this);
                break;
            case 3:
                currentLevel = new Level3(this);
                break;
            case 4:
                currentLevel = new Level4(this);
                break;
            
            default:
                throw new IllegalArgumentException("Invalid level number");
        }
        this.tileM = new tileManager(this, levelNumber);
        System.out.println("Loaded level: " + levelNumber);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;

                if (remainingTime > 0) {
                    Thread.sleep((long) remainingTime);
                }

                nextDrawTime += drawInterval;
                while (nextDrawTime < System.nanoTime()) {
                    nextDrawTime += drawInterval;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }

    public void update() {
        if (currentLevel != null) {
            currentLevel.update();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (currentLevel != null) {
            currentLevel.draw(g2);
        }
        g2.dispose();
    }
}
