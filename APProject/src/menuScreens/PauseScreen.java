package menuScreens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PauseScreen extends JFrame {
    
    private static final int gameWidth = 1024;
    private static final int gameHeight = 768;
    
    public PauseScreen() {
        setTitle("NecroZombie");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(gameWidth, gameHeight);
        setLocationRelativeTo(null); // Center the frame on screen
        
        // Create the panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 0, 20)); // 4 rows, 1 column, 20px vertical gap
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Increased padding
        
        // Create buttons
        JButton resumeButton = createButton("Resume");
        JButton restartButton = createButton("Restart");
        JButton quitButton = createButton("Back to Menu");
        
        // Add buttons to panel
        buttonPanel.add(resumeButton);
        buttonPanel.add(restartButton);
        buttonPanel.add(quitButton);
        
        // Create label for "Paused" text
        JLabel pausedLabel = new JLabel("Paused", SwingConstants.CENTER);
        pausedLabel.setForeground(Color.WHITE);
        pausedLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Larger font size
        
        // Create panel for the "Paused" label
        JPanel pausedPanel = new JPanel(new BorderLayout());
        pausedPanel.setBackground(Color.BLACK);
        pausedPanel.add(pausedLabel, BorderLayout.CENTER);
        pausedPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0)); // Adjusted padding
        
        // Add panels to main content pane
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.BLACK);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(pausedPanel, BorderLayout.NORTH);
        
        setContentPane(contentPane);
        setVisible(true);
    }
    
    // Method to create a styled JButton
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 24)); // Larger font size
        button.setMargin(new Insets(20, 40, 20, 40)); // Increased padding
        return button;
    }
    
    public static void main(String[] args) {
        // Ensure the Swing components are created on the Event Dispatch Thread (EDT)
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PauseScreen();
            }
        });
    }
}
