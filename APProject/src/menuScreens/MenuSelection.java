package menuScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MenuSelection {
    private JPanel menuPanel;
    private MainApp mainApp;
    private List<JLabel> currentOptions = new ArrayList<>();
    private List<JLabel> options = new ArrayList<>();

    public MenuSelection(MainApp mainApp) {
        this.mainApp = mainApp;
        initializeMenu();
    }

    private void initializeMenu() {
        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setBackground(Color.BLACK);

        // Logo Panel
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.BLACK);
        logoPanel.setPreferredSize(new Dimension(800, 350));
        logoPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0)); // Add top margin
        JLabel logoLabel = createLogoLabel("/menuResources/NecroPuzzles.png"); // Replace with your image path
        logoPanel.add(logoLabel);

        // Options Panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setBackground(Color.BLACK);
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        String[] menuOptions = {"New Game", "Level Selection", "Resume", "Settings", "Quit"};
        for (String option : menuOptions) {
            JLabel optionLabel = createOptionLabel(option);
            options.add(optionLabel);
            JLabel currentOption = createCurrentOptionLabel();
            currentOptions.add(currentOption);

            JPanel optionBox = new JPanel();
            optionBox.setBackground(Color.BLACK);
            optionBox.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            optionBox.add(currentOption);
            optionBox.add(optionLabel);
            optionsPanel.add(optionBox);
        }

        // Add components to menu panel
        menuPanel.add(logoPanel, BorderLayout.NORTH);
        menuPanel.add(optionsPanel, BorderLayout.CENTER);

        // Select the first option
        selectCurrentOption(0);
    }

    private JLabel createLogoLabel(String imagePath) {
        ImageIcon logoIcon = new ImageIcon(getClass().getResource(imagePath));
        JLabel logoLabel = new JLabel(logoIcon);
        if (logoIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.err.println("Image file not found: " + imagePath);
            logoLabel = new JLabel("Logo not found");
            logoLabel.setForeground(Color.RED);
            logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        }
        return logoLabel;
    }

    private JLabel createOptionLabel(String option) {
        JLabel optionLabel = new JLabel(option.toUpperCase());
        optionLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        optionLabel.setForeground(Color.WHITE);
        optionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleOptionClick(optionLabel);
            }
        });
        return optionLabel;
    }

    private JLabel createCurrentOptionLabel() {
        JLabel currentOption = new JLabel("> ");
        currentOption.setFont(new Font("Arial", Font.BOLD, 30));
        currentOption.setForeground(Color.BLACK);
        return currentOption;
    }

    private void handleOptionClick(JLabel source) {
        int index = options.indexOf(source);
        selectCurrentOption(index);
        System.out.println(source.getText() + " clicked");

        switch (source.getText()) {
            case "NEW GAME":
                mainApp.startGame(1);
                break;
            case "LEVEL SELECTION":
                mainApp.showLevelSelection();
                break;
            case "QUIT":
                System.exit(0);
                break;
        }
    }

    private void selectCurrentOption(int index) {
        for (JLabel label : currentOptions) {
            label.setForeground(Color.BLACK);
        }
        currentOptions.get(index).setForeground(Color.WHITE);
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }
}
