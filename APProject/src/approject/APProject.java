
package approject;

import javax.swing.JFrame;


public class APProject {
    //Everything Here just sets up your screen
    public static void main(String[] args) {
        JFrame window = new JFrame();
        GamePanel gamePanel = new GamePanel();
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("NecroPPuzzles");
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();
       
    }
}
