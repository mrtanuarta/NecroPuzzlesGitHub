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
    private MainApp mainApp;

    public VictoryScreen(MainApp mainApp, int levelNum) {
        this.mainApp = mainApp;
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
                loadNextLevel(levelNum);
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
                System.exit(0);
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

    // Method to load the next level
    private void loadNextLevel(int levelNum) {
        System.out.println("Loading the next level...");
        mainApp.startGame(levelNum + 1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Victory Screen");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.add(new VictoryScreen(new MainApp(), 1));
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
