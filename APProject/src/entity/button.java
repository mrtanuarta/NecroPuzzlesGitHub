
package entity;
import approject.GamePanel;

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
    }

    public void update() {
        if (Player.zombieCanMove) {
            if (type.equals("player")) {
                if (Player.x == x && Player.y == y && state == "up") {
                    state = "down";
                    System.out.println("Button pushed");
                }
            }
//            else if (type.equals("box")) {
//
//            }
            //will continue the box type if we have time
            chgBtnState(state);
        }


    }

    public void chgBtnState (String state) {
        int col = x / gp.tileSize;
        int row = y / gp.tileSize;
        if (state.equals("up")){
            gp.tileM.updateTile(col, row, 44);
        }
        else if (state.equals("down")){
            gp.tileM.updateTile(col, row, 43);
        }
    }
}
