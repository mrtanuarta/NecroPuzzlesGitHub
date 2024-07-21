package menuScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeathScreen extends JFrame {

    private static final String IMAGE_PATH = "/Images/Dead.png";
    private static final String FONT_FAMILY = "Arial";
    private static final double LOGO_WIDTH = 400;
    private static final double FONT_SIZE_LABEL = 24;

    public DeathScreen() {
        setTitle("Death Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);

        // Create the logo box
        JPanel logoBox = createLogoBox();

        // Create "YOU LOSE" label
        JLabel youLoseLabel = new JLabel("YOU LOSE");
        youLoseLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 48));
        youLoseLabel.setForeground(Color.RED);
        youLoseLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Panel to hold the "YOU LOSE" label
        JPanel youLoseBox = new JPanel();
        youLoseBox.setBackground(Color.BLACK);
        youLoseBox.setLayout(new BorderLayout());
        youLoseBox.add(youLoseLabel, BorderLayout.CENTER);

        // Create "Restart" button
        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font(FONT_FAMILY, Font.PLAIN, 24));
        restartButton.setBackground(Color.WHITE);
        restartButton.setForeground(Color.BLACK);
        restartButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        restartButton.setPreferredSize(new Dimension(200, 50));
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Restart clicked!");
                restartLevel(); // Call the method to restart the level
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
        buttonBox.add(restartButton);
        buttonBox.add(quitButton);

        // Add components to the main frame
        add(logoBox, BorderLayout.NORTH);
        add(youLoseBox, BorderLayout.CENTER);
        add(buttonBox, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
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

    // Method to restart the level
    private void restartLevel() {
        // Here you can implement the logic to restart the level
        // For example, you could dispose the current frame and create a new instance
        dispose();
        new DeathScreen();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DeathScreen();
            }
        });
    }
}
