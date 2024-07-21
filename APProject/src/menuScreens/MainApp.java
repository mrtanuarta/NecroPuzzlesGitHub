package menuScreens;

import gamePanel.GamePanel;

import javax.swing.*;
import java.awt.*;

public class MainApp {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private MenuSelection menuSelection;
    private LevelSelection levelSelection;
    private GamePanel gamePanel;
    private DeathScreen deathScreen;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp().createAndShowGUI());
    }

    private void createAndShowGUI() {
        frame = new JFrame("Game Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setResizable(false); // Make the window non-resizable

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        menuSelection = new MenuSelection(this);
        levelSelection = new LevelSelection(this);
        gamePanel = new GamePanel(this); // Pass the main app instance to the game panel

        cardPanel.add(menuSelection.getMenuPanel(), "Menu");
        cardPanel.add(levelSelection.getLevelPanel(), "LevelSelection");
        cardPanel.add(gamePanel, "Game");

        frame.add(cardPanel);
        frame.setVisible(true);

        showMenu();
    }

    public void showMenu() {
        cardLayout.show(cardPanel, "Menu");
    }

    public void showLevelSelection() {
        cardLayout.show(cardPanel, "LevelSelection");
    }

    public void startGame(int levelNumber) {
        gamePanel.loadLevel(levelNumber);
        cardLayout.show(cardPanel, "Game");
        gamePanel.requestFocus();
    }

    public void showDeathScreen() {
        if (deathScreen == null) {
            deathScreen = new DeathScreen(this); // Pass the main app instance to the death screen
        }
        cardPanel.add(deathScreen, "DeathScreen");
        cardLayout.show(cardPanel, "DeathScreen");
    }

    public void restartGame() {
        deathScreen = null; // Reset the death screen
        showMenu();
    }
}
