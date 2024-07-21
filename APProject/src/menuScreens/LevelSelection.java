package menuScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class LevelSelection {
    private JPanel levelPanel;
    private MainApp mainApp;
    private List<JLabel> currentLevels = new ArrayList<>();
    private List<JLabel> levels = new ArrayList<>();

    public LevelSelection(MainApp mainApp) {
        this.mainApp = mainApp;
        initializeLevels();
    }

    private void initializeLevels() {
        levelPanel = new JPanel();
        levelPanel.setLayout(new BorderLayout());
        levelPanel.setBackground(Color.BLACK);

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setPreferredSize(new Dimension(800, 100));
        JLabel titleLabel = new JLabel("GAME LEVELS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        // Levels Panel
        JPanel levelsPanel = new JPanel();
        levelsPanel.setBackground(Color.BLACK);
        levelsPanel.setLayout(new BoxLayout(levelsPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < 5; i++) {
            JLabel levelLabel = createLevelLabel(i + 1);
            levels.add(levelLabel);
            JLabel currentLevel = createCurrentLevelLabel();
            currentLevels.add(currentLevel);

            JPanel levelBox = new JPanel();
            levelBox.setBackground(Color.BLACK);
            levelBox.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            levelBox.add(currentLevel);
            levelBox.add(levelLabel);
            levelsPanel.add(levelBox);
        }

        // Back Label
        JLabel backLabel = new JLabel("BACK");
        backLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        backLabel.setForeground(Color.WHITE);
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleBackClick();
            }
        });

        // Back Panel
        JPanel backPanel = new JPanel();
        backPanel.setBackground(Color.BLACK);
        backPanel.setPreferredSize(new Dimension(800, 100));
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        backPanel.add(backLabel);

        // Add components to level panel
        levelPanel.add(titlePanel, BorderLayout.NORTH);
        levelPanel.add(levelsPanel, BorderLayout.CENTER);
        levelPanel.add(backPanel, BorderLayout.SOUTH);

        // Select the first level
        selectCurrentLevel(0);
    }

    private JLabel createLevelLabel(int levelNumber) {
        JLabel levelLabel = new JLabel("LEVEL " + levelNumber);
        levelLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        levelLabel.setForeground(Color.WHITE);
        levelLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleLevelClick(levelLabel);
            }
        });
        return levelLabel;
    }

    private JLabel createCurrentLevelLabel() {
        JLabel currentLevel = new JLabel(">");
        currentLevel.setFont(new Font("Arial", Font.BOLD, 30));
        currentLevel.setForeground(Color.BLACK);
        return currentLevel;
    }

    private void handleLevelClick(JLabel source) {
        int index = levels.indexOf(source);
        selectCurrentLevel(index);
        System.out.println(source.getText() + " clicked");
        mainApp.startGame(index + 1);
    }

    private void handleBackClick() {
        System.out.println("Back clicked");
        mainApp.showMenu();
    }

    private void selectCurrentLevel(int index) {
        for (JLabel label : currentLevels) {
            label.setForeground(Color.BLACK);
        }
        currentLevels.get(index).setForeground(Color.WHITE);
    }

    public JPanel getLevelPanel() {
        return levelPanel;
    }
}
