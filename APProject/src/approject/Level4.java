/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package approject;

import entity.player;
import entity.zombie;

public class Level4 extends Level {
    public Level4(GamePanel gp) {
        super(gp, 4);
    }
    @Override
    protected void initializeLevel() {
        Player = new player(gp, gp.getKeyHandler(), 768, 192, "down");
        zombies.add(new zombie(gp, Player, "rotate", 512, 192, "up", "right"));
        zombies.add(new zombie(gp, Player, "rotate", 640, 192, "right", "left"));
        zombies.add(new zombie(gp, Player, "rotate", 704, 320, "left", "right"));
        zombies.add(new zombie(gp, Player, "rotate", 448, 320, "right", "left"));
        zombies.add(new zombie(gp, Player, "rotate", 512, 448, "left", "right"));
        zombies.add(new zombie(gp, Player, "moving", 640, 448, "right", "left"));
        zombies.add(new zombie(gp, Player, "moving", 640, 512, "left", "left"));
        zombies.add(new zombie(gp, Player, "moving", 576, 384, "up", "left"));
        zombies.add(new zombie(gp, Player, "moving", 320, 256, "up", "left"));
      
    }
}
