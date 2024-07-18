package approject;

import entity.player;
import entity.zombie;

public class Level2 extends Level {
    public Level2(GamePanel gp) {
        super(gp, 2); // Pass level number to superclass
    }

    @Override
    protected void initializeLevel() {
        Player = new player(gp, gp.getKeyHandler(), 640, 256, "down");
        // Add specific zombies for level 2
        zombies.add(new zombie(gp, Player, tileM, "rotate", 640, 384, "right", "right"));
        zombies.add(new zombie(gp, Player, tileM, "rotate", 448, 256, "right", "right"));
    }
}
