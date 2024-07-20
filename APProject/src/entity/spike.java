package entity;
import approject.GamePanel;

public final class spike extends entity {
    GamePanel gp;
    String type, state;
    private final player Player;
    private final button Button;


    // Just a constructor to connect the main game panel
    public spike(GamePanel gp, player Player, String type, int x, int y, String state, button Button) {
        this.gp = gp;
        this.Player = Player;
        this.type = type;
        this.x = x;
        this.y = y;
        this.state = state;
        this.Button = Button;
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
            else if (Button.state.equals("down")) {
                if (state.equals("up")){
                    state = "down";
                }
            }
            chgSpikeState(state);
        }
    }

    public void chgSpikeState (String state) {
        int col = x / gp.tileSize;
        int row = y / gp.tileSize;
        if (state.equals("up")){
            gp.tileM.updateTile(col, row, 55);
        }
        else if (state.equals("down")){
            gp.tileM.updateTile(col, row, 45);
        }
    }
}
