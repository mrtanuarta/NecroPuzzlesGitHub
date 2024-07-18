package approject;

import entity.player;
import entity.zombie;

public class Level1 extends Level {
    public Level1(GamePanel gp) {
        super(gp, 1); // Pass level number to superclass
    }

    @Override
    protected void initializeLevel() {
        Player = new player(gp, gp.getKeyHandler(), 640, 256, "down");
        zombies.add(new zombie(gp, Player, tileM, "moving", 640, 384, "left"));
        zombies.add(new zombie(gp, Player, tileM, "static", 448, 256, "right"));
    }
}

// Create additional classes for Level3, Level4, etc., as needed.
