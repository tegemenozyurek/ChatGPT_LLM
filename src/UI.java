import javax.swing.*;
import java.awt.*;

public class UI {

    private JFrame frame; // Define JFrame as a class variable
    private JTextArea textArea; // Class variable for the text area
    public String input = "";//

    public String rule0 = "Return an error message if input is irrelevant, ";
    public String rule1 = "[Answer as an essay writer]";
    public String rule2 = "[Answer as a Turkish-to-German translator]";
    public String rule3 = "[Answer as a Turkish-to-English translator]";

    public UI() {
        frame = new JFrame("ChatGPT API");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Set the window size
        frame.setLayout(new BorderLayout()); // Use BorderLayout for component arrangement
    }

    public void SelectionScreen() {
        // Clear the frame
        frame.getContentPane().removeAll(); // Remove existing components

        // Create the title label
        JLabel titleLabel = new JLabel("ChatGPT API Project", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Set font size and style
        frame.add(titleLabel, BorderLayout.NORTH); // Add title to the top of the frame

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Use BoxLayout to arrange buttons vertically

        // Create three buttons
        JButton button1 = new JButton("Essay Writer");
        JButton button2 = new JButton("Translator (TR-->DE)");
        JButton button3 = new JButton("Translator (TR-->EN)");

        // Set the size and maximum size of the buttons
        Dimension buttonSize = new Dimension(500, 60); // Button size
        button1.setPreferredSize(buttonSize);
        button1.setMaximumSize(buttonSize);
        button2.setPreferredSize(buttonSize);
        button2.setMaximumSize(buttonSize);
        button3.setPreferredSize(buttonSize);
        button3.setMaximumSize(buttonSize);

        // Add action listeners to buttons
        button1.addActionListener(e -> ASCII_ArtScreen());
        button2.addActionListener(e -> ChatbotScreen());
        button3.addActionListener(e -> TranslatorScreen());

        // Add buttons and spacing to the panel
        buttonPanel.add(Box.createHorizontalGlue()); // Add left spacing
        buttonPanel.add(button1);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between buttons
        buttonPanel.add(button2);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between buttons
        buttonPanel.add(button3);
        buttonPanel.add(Box.createHorizontalGlue()); // Add right spacing

        // Create another panel to center the button panel
        JPanel centerPanel = new JPanel(new GridBagLayout()); // Create a centered panel
        centerPanel.add(buttonPanel); // Add the button panel

        // Add the centered panel to the frame
        frame.add(centerPanel, BorderLayout.CENTER);

        // Refresh the frame
        frame.revalidate(); // Revalidate components
        frame.repaint(); // Redraw the frame

        // Set the frame visibility
        frame.setVisible(true);
    }

    private void addTextBox() {
        // Create a larger square text area
        textArea = new JTextArea(20, 50); // 10 rows, 40 columns
        textArea.setFont(new Font("Arial", Font.PLAIN, 15)); // Set font size
        textArea.setText(""); // Fill it with "A"
        textArea.setEditable(false); // Make it non-editable
        textArea.setBackground(Color.LIGHT_GRAY); // Set background color
        textArea.setLineWrap(true); // Enable line wrapping
        textArea.setWrapStyleWord(true); // Wrap at word boundaries

        // Wrap the text area in a scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Center the scroll pane in a panel
        JPanel textBoxPanel = new JPanel(new GridBagLayout());
        textBoxPanel.add(scrollPane); // Add the scroll pane to the panel

        // Add the text box panel to the center of the frame
        frame.add(textBoxPanel, BorderLayout.CENTER);
    }

    public void ASCII_ArtScreen() {
        ChatGPTClient.chatGPT("Chat Rule: Your name is Master Yi for this chat.");
        // Clear the frame
        frame.getContentPane().removeAll(); // Remove existing components

        // Create the title label
        JLabel titleLabel = new JLabel("Essay Writer", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Set font size and style

        // Create a text input field with a larger width
        JTextField inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(500, 40)); // Set preferred size for wider input

        // Create an "OK" button with the same height as the text field
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(100, 40)); // Set the button's size with the same height as the text field

        // Action listener for OK button
        okButton.addActionListener(e -> {
            input = inputField.getText(); // Assign text to input
            inputField.setText(""); // Clear the text field

            // Update the text area with ChatGPT response
            textArea.setText(ChatGPTClient.chatGPT(rule1 + ": " + input));
        });

        // Create a panel for the text field and the "OK" button
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputPanel.add(inputField);
        inputPanel.add(okButton);

        // Create the "Go Back" button
        JButton backButton = new JButton("Go Back");
        backButton.setPreferredSize(new Dimension(100, 30)); // Set smaller button size
        backButton.addActionListener(e -> SelectionScreen()); // Action to go back to selection screen

        // Create a panel for the back button
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Use FlowLayout to align left
        backPanel.add(backButton); // Add the back button to the panel

        // Add components to the frame
        JPanel titlePanel = new JPanel(new BorderLayout()); // Create a panel for title and back button
        titlePanel.add(backPanel, BorderLayout.WEST); // Add back button to the left
        titlePanel.add(titleLabel, BorderLayout.CENTER); // Add title in the center

        // Add title and input field + button to the frame
        frame.add(titlePanel, BorderLayout.NORTH); // Add the title panel at the top
        frame.add(inputPanel, BorderLayout.SOUTH); // Add the input field and button at the bottom

        // Add the text box in the center of the screen
        addTextBox();

        // Refresh the frame
        frame.revalidate(); // Revalidate components
        frame.repaint(); // Redraw the frame
    }

    public void ChatbotScreen() {
        // Clear the frame
        frame.getContentPane().removeAll(); // Remove existing components

        // Create the title label
        JLabel titleLabel = new JLabel("Translator TR --> DE", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Set font size and style

        // Create a text input field with a larger width
        JTextField inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(500, 40)); // Set preferred size for wider input

        // Create an "OK" button with the same height as the text field
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(100, 40)); // Set the button's size with the same height as the text field

        // Action listener for OK button
        okButton.addActionListener(e -> {
            input = inputField.getText(); // Assign text to input
            inputField.setText(""); // Clear the text field

            // Update the text area with ChatGPT response
            textArea.setText(ChatGPTClient.chatGPT(rule2 + ": " + input));
        });

        // Create a panel for the text field and the "OK" button
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputPanel.add(inputField);
        inputPanel.add(okButton);

        // Create the "Go Back" button
        JButton backButton = new JButton("Go Back");
        backButton.setPreferredSize(new Dimension(100, 30)); // Set smaller button size
        backButton.addActionListener(e -> SelectionScreen()); // Action to go back to selection screen

        // Create a panel for the back button
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Use FlowLayout to align left
        backPanel.add(backButton); // Add the back button to the panel

        // Add components to the frame
        JPanel titlePanel = new JPanel(new BorderLayout()); // Create a panel for title and back button
        titlePanel.add(backPanel, BorderLayout.WEST); // Add back button to the left
        titlePanel.add(titleLabel, BorderLayout.CENTER); // Add title in the center

        // Add title and input field + button to the frame
        frame.add(titlePanel, BorderLayout.NORTH); // Add the title panel at the top
        frame.add(inputPanel, BorderLayout.SOUTH); // Add the input field and button at the bottom

        // Add the text box in the center of the screen
        addTextBox();

        // Refresh the frame
        frame.revalidate(); // Revalidate components
        frame.repaint(); // Redraw the frame
    }

    public void TranslatorScreen() {
        // Clear the frame
        frame.getContentPane().removeAll(); // Remove existing components

        // Create the title label
        JLabel titleLabel = new JLabel("Translator TR --> EN", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Set font size and style

        // Create a text input field with a larger width
        JTextField inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(500, 40)); // Set preferred size for wider input

        // Create an "OK" button with the same height as the text field
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(100, 40)); // Set the button's size with the same height as the text field

        // Action listener for OK button
        okButton.addActionListener(e -> {
            input = inputField.getText(); // Assign text to input
            inputField.setText(""); // Clear the text field

            // Update the text area with ChatGPT response
            textArea.setText(ChatGPTClient.chatGPT(rule3 + ": " + input));
        });

        // Create a panel for the text field and the "OK" button
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputPanel.add(inputField);
        inputPanel.add(okButton);

        // Create the "Go Back" button
        JButton backButton = new JButton("Go Back");
        backButton.setPreferredSize(new Dimension(100, 30)); // Set smaller button size
        backButton.addActionListener(e -> SelectionScreen()); // Action to go back to selection screen

        // Create a panel for the back button
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Use FlowLayout to align left
        backPanel.add(backButton); // Add the back button to the panel

        // Add components to the frame
        JPanel titlePanel = new JPanel(new BorderLayout()); // Create a panel for title and back button
        titlePanel.add(backPanel, BorderLayout.WEST); // Add back button to the left
        titlePanel.add(titleLabel, BorderLayout.CENTER); // Add title in the center

        // Add title and input field + button to the frame
        frame.add(titlePanel, BorderLayout.NORTH); // Add the title panel at the top
        frame.add(inputPanel, BorderLayout.SOUTH); // Add the input field and button at the bottom

        // Add the text box in the center of the screen
        addTextBox();

        // Refresh the frame
        frame.revalidate(); // Revalidate components
        frame.repaint(); // Redraw the frame
    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.SelectionScreen(); // Start with the selection screen
    }
}
