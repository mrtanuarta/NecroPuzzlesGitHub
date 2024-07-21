/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package approject;

import entity.player;
import entity.zombie;
import entity.button;
import entity.spike;


public class Level3 extends Level {
    public Level3(GamePanel gp) {
        super(gp, 3); // Pass level number to superclass
    }
    @Override
    protected void initializeLevel() {
        Player = new player(gp, gp.getKeyHandler(), 640, 256, "down");
        zombies.add(new zombie(gp, Player, "moving", 512, 384, "right", "left"));
        zombies.add(new zombie(gp, Player, "rotate", 448, 256, "right", "left"));
        zombies.add(new zombie(gp, Player, "moving", 576, 192, "right", "left"));
        zombies.add(new zombie(gp, Player, "moving", 512, 512, "right", "left"));
        zombies.add(new zombie(gp, Player, "rotate", 704, 320, "down", "right"));
    }
}
