package approject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
    //So when the key is pressed it will set the boolean above to true
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = true;
            System.out.println("W Pressed");
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
            System.out.println("S Pressed");
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
            System.out.println("A Pressed");
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
            System.out.println("D Pressed");
        }
    }
    //dont really mind this shit
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
    //this is used to reset the keys
    public void resetKeys() {
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
    }
}
