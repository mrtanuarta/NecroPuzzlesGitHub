package approject;

import entity.player;
import entity.zombie;
import entity.button;
import entity.spike;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Level {
    protected GamePanel gp;
    protected player Player;
    protected List<zombie> zombies;
    protected List<spike> spikes;
    protected List<button> buttons;

    public Level(GamePanel gp, int levelNumber) {
        this.gp = gp;
        this.zombies = new ArrayList<>();
        this.spikes = new ArrayList<>();
        this.buttons = new ArrayList<>();
        initializeLevel();
    }

    protected abstract void initializeLevel();

    public void update() {
        Player.update();
        for (zombie zombie : zombies) {
            zombie.update();
        }
        for (spike spike : spikes) {
            spike.update();
        }
        for (button button : buttons) {
            button.update();
        }
        Player.zombieCanMove = false;
        Player.checkTileUpdates(Player.x, Player.y);
    }

    public void draw(Graphics2D g2) {
        gp.tileM.draw(g2);
        Player.draw(g2);
        for (zombie zombie : zombies) {
            zombie.draw(g2);
        }
    }
}
