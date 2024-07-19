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
        zombies.add(new zombie(gp, Player, "rotate", 704, 512, "right", "left"));
        zombies.add(new zombie(gp, Player, "rotate", 576, 256, "left", "left"));
        zombies.add(new zombie(gp, Player, "moving", 512, 448, "right", "left"));
        zombies.add(new zombie(gp, Player, "moving", 256, 320, "down"));
        zombies.add(new zombie(gp, Player, "moving", 320, 256, "down"));
    }
}
