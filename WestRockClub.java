package WestRocklClub;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WestRockClub extends JFrame implements ActionListener {
    private JTextField nameField;
    private JTextField ageField;
    private JTextField ticketDigitsField;

    public WestRockClub() {
        setTitle("West Rock Club");
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("This is West Rock Club");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel detailsLabel = new JLabel("Please fill in this information");
        detailsLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        detailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        nameField.setToolTipText("Enter your name");

        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField(20);
        ageField.setToolTipText("Enter your age");

        JLabel ticketDigitsLabel = new JLabel("Ticket Digits:");
        ticketDigitsField = new JTextField(20);
        ticketDigitsField.setToolTipText("Enter the digits of your ticket");

        JButton checkButton = new JButton("Check Entry");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        Dimension inputDimension = new Dimension(350, 35);

        titleLabel.setPreferredSize(new Dimension(400, 40));
        detailsLabel.setPreferredSize(new Dimension(400, 40));
        nameLabel.setPreferredSize(inputDimension);
        ageLabel.setPreferredSize(inputDimension);
        ticketDigitsLabel.setPreferredSize(inputDimension);

        nameField.setPreferredSize(inputDimension);
        ageField.setPreferredSize(inputDimension);
        ticketDigitsField.setPreferredSize(inputDimension);

        checkButton.setPreferredSize(inputDimension);

        mainPanel.add(titleLabel);
        mainPanel.add(detailsLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(createCenteredComponent(nameLabel));
        mainPanel.add(createCenteredComponent(nameField));
        mainPanel.add(createCenteredComponent(ageLabel));
        mainPanel.add(createCenteredComponent(ageField));
        mainPanel.add(createCenteredComponent(ticketDigitsLabel));
        mainPanel.add(createCenteredComponent(ticketDigitsField));
        mainPanel.add(createCenteredComponent(checkButton));
        mainPanel.add(Box.createVerticalStrut(10));

        setContentPane(mainPanel);

        checkButton.addActionListener(this);
    }

    private Component createCenteredComponent(Component component) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(component);
        return panel;
    }

    private void checkEntry() {
        String name = nameField.getText();
        String ageText = ageField.getText();
        String ticketDigits = ticketDigitsField.getText();

        if (name.isEmpty() || ageText.isEmpty() || ticketDigits.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields and enter the ticket digits.", "Entry Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int age = Integer.parseInt(ageText);

            if (age < 18 || age > 50) {
                JOptionPane.showMessageDialog(this, "Sorry, " + name + ". You are not allowed to enter.", "Entry Denied", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String ticketMessage = "";

                if (ticketDigits.length() == 5) {
                    ticketMessage = "You're entering with a Regular ticket.";
                } else if (ticketDigits.length() == 10) {
                    ticketMessage = "You're entering with a VIP ticket.";
                } else if (ticketDigits.length() == 15) {
                    ticketMessage = "You're entering with a VVIP ticket.";
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid ticket digits.", "Entry Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String entryMessage = "Welcome, " + name + "! " + ticketMessage;
                JOptionPane.showMessageDialog(this, entryMessage, "Welcome!", JOptionPane.INFORMATION_MESSAGE);

                if (age <= 20) {
                    JOptionPane.showMessageDialog(this, "You are only allowed to drink mocktail and soft drink.");
                } else {
                    JOptionPane.showMessageDialog(this, "You are allowed to drink any alcoholic drink.");
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input for age.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WestRockClub frame = new WestRockClub();
            frame.setVisible(true);
            frame.pack();
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Check Entry")) {
            checkEntry();
        }
    }
}



