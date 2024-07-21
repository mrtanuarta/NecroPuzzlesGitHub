package menuScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VictoryScreen extends JPanel {

    private static final String IMAGE_PATH = "/menuResources/Win.png";
    private static final String FONT_FAMILY = "Arial";
    private static final double LOGO_WIDTH = 400;
    private static final double FONT_SIZE_LABEL = 24;
    MainApp mainApp;
    int levelNum;

    public VictoryScreen(MainApp mainApp, int levelNum) {
        this.mainApp = mainApp;
        this.levelNum = levelNum;
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Create the logo box
        JPanel logoBox = createLogoBox();

        // Create "YOU WIN" label
        JLabel youWinLabel = new JLabel("YOU WIN");
        youWinLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 48));
        youWinLabel.setForeground(Color.GREEN);
        youWinLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Panel to hold the "YOU WIN" label
        JPanel youWinBox = new JPanel();
        youWinBox.setBackground(Color.BLACK);
        youWinBox.setLayout(new BorderLayout());
        youWinBox.add(youWinLabel, BorderLayout.CENTER);

        // Create "Next Level" button
        JButton nextLevelButton = new JButton("Next Level");
        nextLevelButton.setFont(new Font(FONT_FAMILY, Font.PLAIN, 24));
        nextLevelButton.setBackground(Color.WHITE);
        nextLevelButton.setForeground(Color.BLACK);
        nextLevelButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        nextLevelButton.setPreferredSize(new Dimension(200, 50));
        nextLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Next Level clicked!");
                loadNextLevel();
            }
        });

        // Create "Quit" button
        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font(FONT_FAMILY, Font.PLAIN, 24));
        quitButton.setBackground(Color.WHITE);
        quitButton.setForeground(Color.BLACK);
        quitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        quitButton.setPreferredSize(new Dimension(200, 50));
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showMenu();
            }
        });

        // Panel to hold the buttons
        JPanel buttonBox = new JPanel();
        buttonBox.setBackground(Color.BLACK);
        buttonBox.setLayout(new GridLayout(2, 1, 20, 20));
        buttonBox.add(nextLevelButton);
        buttonBox.add(quitButton);

        // Add components to the main panel
        add(logoBox, BorderLayout.NORTH);
        add(youWinBox, BorderLayout.CENTER);
        add(buttonBox, BorderLayout.SOUTH);
    }

    private JPanel createLogoBox() {
        JPanel logoBox = new JPanel();
        logoBox.setBackground(Color.BLACK);
        logoBox.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        ImageIcon logoImage = new ImageIcon(getClass().getResource(IMAGE_PATH));
        if (logoImage.getImageLoadStatus() == MediaTracker.COMPLETE) {
            Image image = logoImage.getImage().getScaledInstance((int) LOGO_WIDTH, -1, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(image));
            logoBox.add(logoLabel);
        } else {
            System.err.println("Image file not found: " + IMAGE_PATH);
            JLabel imageNotFoundLabel = new JLabel("Image not found");
            imageNotFoundLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, (int) FONT_SIZE_LABEL));
            imageNotFoundLabel.setForeground(Color.RED);
            logoBox.add(imageNotFoundLabel);
        }

        return logoBox;
    }
    public void loadNextLevel(){
        mainApp.startGame(levelNum + 1);
    }
    public void updateLevelNumber(int levelNum) {
        this.levelNum = levelNum;
    }
}
