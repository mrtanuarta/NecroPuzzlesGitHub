
package entity;
import approject.GamePanel;

public final class spike extends entity {
    GamePanel gp;
    String type, state;
    private final player Player;

    // Just a constructor to connect the main game panel
    public spike(GamePanel gp, player Player, String type, int x, int y, String state) {
        this.gp = gp;
        this.Player = Player;
        this.type = type;
        this.x = x;
        this.y = y;
        this.state = state;
    }

    public void update() {
        if (Player.zombieCanMove) {
            if (type.equals("cycle")){
                if (state.equals("up")){
                    state = "down";
                }
                else if (state.equals("down")){
                    state = "up";
                }
            }
            else if (type.equals("button")) {
                if ()
            }
        }


    }
}
