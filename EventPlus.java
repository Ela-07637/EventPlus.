
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class EventPlus extends JFrame {

    // EVENTPLUS COLOR THEME
    private static final Color BURGUNDY = new Color(94, 25, 41);
    private static final Color DARK_BURGUNDY = new Color(65, 15, 29);
    private static final Color PINK = new Color(244, 190, 202);
    private static final Color LIGHT_PINK = new Color(253, 242, 244);
    private static final Color SOFT_PINK = new Color(255, 248, 250);
    private static final Color WHITE = Color.WHITE;
    private static final Color DARK_TEXT = new Color(55, 45, 48);
    private static final Color GRAY_TEXT = new Color(125, 110, 115);
    private static final Color BORDER_COLOR = new Color(238, 218, 224);

    private Font normalFont = new Font("SansSerif", Font.PLAIN, 14);
    private Font boldFont = new Font("SansSerif", Font.BOLD, 14);

    public EventPlus() {

        setTitle("EventPlus | Event Management System");
        setSize(950, 900);
        setMinimumSize(new Dimension(700, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(LIGHT_PINK);
        mainPanel.setBorder(new EmptyBorder(0, 0, 30, 0));

        // ================= HEADER =================

        GradientHeader headerPanel = new GradientHeader();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(35, 30, 35, 30));
        headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel smallLabel = new JLabel("EVENT PLANNING MADE SIMPLE");
        smallLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
        smallLabel.setForeground(PINK);
        smallLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("EventPlus");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 43));
        titleLabel.setForeground(WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel(
            "Where Every Event Meets Your Imagination."
        );
        subtitleLabel.setFont(new Font("Serif", Font.ITALIC, 17));
        subtitleLabel.setForeground(new Color(255, 225, 233));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(smallLabel);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(8));
        headerPanel.add(subtitleLabel);

        mainPanel.add(headerPanel);

        // INTRODUCTION
        JPanel introPanel = new JPanel();
        introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.Y_AXIS));
        introPanel.setOpaque(false);
        introPanel.setBorder(new EmptyBorder(25, 35, 15, 35));
        introPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel welcomeLabel = new JLabel("Create Your Event");
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcomeLabel.setForeground(BURGUNDY);

        JLabel instructionLabel = new JLabel(
            "Complete the details below to prepare your event reservation."
        );
        instructionLabel.setFont(normalFont);
        instructionLabel.setForeground(GRAY_TEXT);

        welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        instructionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        introPanel.add(welcomeLabel);
        introPanel.add(Box.createVerticalStrut(5));
        introPanel.add(instructionLabel);

        mainPanel.add(introPanel);

        // ================= STEP 1 =================

        JPanel step1 = createStepCard(
            "01", "Customer & Coordinator",
            "Provide the basic information for this reservation."
        );

        JTextField customerNameField = createTextField();
        JTextField adminField = createTextField();

        addField(step1, "CUSTOMER FULL NAME", customerNameField);
        addField(step1, "ASSIGNED ADMIN / COORDINATOR", adminField);

        mainPanel.add(step1);

        // ================= STEP 2 =================

        JPanel step2 = createStepCard(
            "02", "Event Type & Celebration Title",
            "Choose the type of event and provide its official title."
        );

        String[] eventTypes = {
            "Wedding",
            "Birthday / Debut",
            "Corporate Seminar",
            "Concert / Live Show",
            "Baptism",
            "Reunion",
            "Others"
        };

        JComboBox<String> eventTypeBox = new JComboBox<>(eventTypes);
        styleComboBox(eventTypeBox);

        JTextField customEventField = createTextField();
        customEventField.setEnabled(false);

        JTextField eventTitleField = createTextField();

        eventTypeBox.addActionListener(e -> {
            boolean isOthers =
                "Others".equals(eventTypeBox.getSelectedItem());

            customEventField.setEnabled(isOthers);
            customEventField.setBackground(
                isOthers ? WHITE : new Color(240, 235, 237)
            );
        });

        addField(step2, "SELECT EVENT TYPE", eventTypeBox);
        addField(step2, "IF OTHERS, SPECIFY CUSTOM EVENT", customEventField);
        addField(step2, "OFFICIAL EVENT TITLE", eventTitleField);

        mainPanel.add(step2);

        // ================= STEP 3 =================

        JPanel step3 = createStepCard(
            "03", "Event Budget & Package Suggestions",
            "Set your target budget to view the suggested package."
        );

        JCheckBox noBudgetCheck = new JCheckBox(
            "No Budget (Custom Freedom)"
        );
        noBudgetCheck.setFont(normalFont);
        noBudgetCheck.setForeground(DARK_TEXT);
        noBudgetCheck.setBackground(WHITE);
        noBudgetCheck.setFocusPainted(false);
        noBudgetCheck.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField budgetField = createTextField();

        JPanel suggestionPanel = new JPanel(new BorderLayout());
        suggestionPanel.setBackground(SOFT_PINK);
        suggestionPanel.setBorder(
            BorderFactory.createCompoundBorder(
                new LineBorder(PINK, 1, true),
                new EmptyBorder(15, 15, 15, 15)
            )
        );

        JLabel suggestionTitle = new JLabel("PACKAGE RECOMMENDATION");
        suggestionTitle.setFont(new Font("SansSerif", Font.BOLD, 11));
        suggestionTitle.setForeground(BURGUNDY);

        JLabel packageSuggestion = new JLabel(
            "Enter your budget to see package suggestions."
        );
        packageSuggestion.setFont(boldFont);
        packageSuggestion.setForeground(DARK_TEXT);

        JPanel suggestionContent = new JPanel();
        suggestionContent.setOpaque(false);
        suggestionContent.setLayout(
            new BoxLayout(suggestionContent, BoxLayout.Y_AXIS)
        );
        suggestionContent.add(suggestionTitle);
        suggestionContent.add(Box.createVerticalStrut(7));
        suggestionContent.add(packageSuggestion);

        suggestionPanel.add(suggestionContent, BorderLayout.CENTER);
        suggestionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        suggestionPanel.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 85)
        );

        noBudgetCheck.addActionListener(e -> {
            boolean noBudget = noBudgetCheck.isSelected();

            budgetField.setEnabled(!noBudget);

            if (noBudget) {
                budgetField.setText("");
                packageSuggestion.setText(
                    "Ultimate Custom Freedom (Advisor Active)"
                );
            } else {
                packageSuggestion.setText(
                    "Enter your budget to see package suggestions."
                );
            }
        });

        budgetField.addActionListener(e ->
            updatePackageSuggestion(budgetField, packageSuggestion)
        );

        budgetField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                if (!budgetField.getText().trim().isEmpty()) {
                    updatePackageSuggestion(
                        budgetField, packageSuggestion
                    );
                }
            }
        });

        step3.add(noBudgetCheck);
        step3.add(Box.createVerticalStrut(12));
        addField(step3, "TARGET EVENT BUDGET (PHP ₱)", budgetField);
        step3.add(Box.createVerticalStrut(12));
        step3.add(suggestionPanel);

        mainPanel.add(step3);

        // ================= STEP 4 =================

        JPanel step4 = createStepCard(
            "04", "Schedule & Timing",
            "Set the preferred date, time, and duration of your event."
        );

        JTextField dateField = createTextField();
        dateField.setText("MM/DD/YYYY");

        JTextField timeField = createTextField();
        timeField.setText("e.g. 14:00");

        JSpinner durationSpinner = new JSpinner(
            new SpinnerNumberModel(3, 1, 24, 1)
        );
        durationSpinner.setFont(normalFont);
        durationSpinner.setPreferredSize(new Dimension(120, 38));
        durationSpinner.setBorder(new LineBorder(BORDER_COLOR, 1, true));

        addField(step4, "EVENT DATE", dateField);
        addField(step4, "EVENT TIME", timeField);
        addField(step4, "DURATION (HOURS)", durationSpinner);

        JPanel statusPanel = new JPanel(new FlowLayout(
            FlowLayout.LEFT, 8, 8
        ));
        statusPanel.setBackground(new Color(239, 249, 241));
        statusPanel.setBorder(
            new LineBorder(new Color(190, 225, 197), 1, true)
        );
        statusPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel statusIcon = new JLabel("●");
        statusIcon.setForeground(new Color(40, 145, 75));

        JLabel conflictLabel = new JLabel(
            "Live Conflict Status: Clear (Date available)"
        );
        conflictLabel.setFont(normalFont);
        conflictLabel.setForeground(new Color(40, 120, 65));

        statusPanel.add(statusIcon);
        statusPanel.add(conflictLabel);

        step4.add(Box.createVerticalStrut(10));
        step4.add(statusPanel);

        mainPanel.add(step4);

        // ================= STEP 5 =================

        JPanel step5 = createStepCard(
            "05", "Guests & Venue Match",
            "Adjust the guest count to see the suggested venue."
        );

        JSlider guestSlider = new JSlider(10, 1000, 50);
        guestSlider.setBackground(WHITE);
        guestSlider.setForeground(BURGUNDY);
        guestSlider.setMajorTickSpacing(200);
        guestSlider.setMinorTickSpacing(50);
        guestSlider.setPaintTicks(true);
        guestSlider.setPaintLabels(true);

        JLabel guestCountLabel = new JLabel("50 Guests");
        guestCountLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        guestCountLabel.setForeground(BURGUNDY);

        guestCountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel venuePanel = new JPanel();
        venuePanel.setLayout(new BoxLayout(venuePanel, BoxLayout.Y_AXIS));
        venuePanel.setBackground(SOFT_PINK);
        venuePanel.setBorder(
            BorderFactory.createCompoundBorder(
                new LineBorder(PINK, 1, true),
                new EmptyBorder(15, 15, 15, 15)
            )
        );
        venuePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        venuePanel.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 100)
        );

        JLabel venueHeading = new JLabel("RECOMMENDED VENUE");
        venueHeading.setFont(new Font("SansSerif", Font.BOLD, 11));
        venueHeading.setForeground(GRAY_TEXT);

        JLabel venueMatchLabel = new JLabel(
            "Rosewood Intimate Lounge"
        );
        venueMatchLabel.setFont(new Font("SansSerif", Font.BOLD, 17));
        venueMatchLabel.setForeground(BURGUNDY);

        venuePanel.add(venueHeading);
        venuePanel.add(Box.createVerticalStrut(7));
        venuePanel.add(venueMatchLabel);

        guestSlider.addChangeListener(e -> {
            int guests = guestSlider.getValue();

            guestCountLabel.setText(guests + " Guests");

            if (guests <= 100) {
                venueMatchLabel.setText(
                    "Rosewood Intimate Lounge"
                );
            } else if (guests <= 300) {
                venueMatchLabel.setText(
                    "Burgundy Grand Hall"
                );
            } else {
                venueMatchLabel.setText(
                    "EventPlus Convention Center"
                );
            }
        });

        step5.add(guestCountLabel);
        step5.add(Box.createVerticalStrut(10));
        step5.add(guestSlider);
        step5.add(Box.createVerticalStrut(15));
        step5.add(venuePanel);

        mainPanel.add(step5);

        // ================= STEP 6 =================

        JPanel step6 = createStepCard(
            "06", "Booking & Digital Pass",
            "Review your reservation details and generate your booking summary."
        );

        JButton bookButton = new JButton(
            "CONFIRM BOOKING & GENERATE PASS"
        );
        styleButton(bookButton);

        JTextArea summaryArea = new JTextArea(12, 35);
        summaryArea.setEditable(false);
        summaryArea.setLineWrap(true);
        summaryArea.setWrapStyleWord(true);
        summaryArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        summaryArea.setForeground(DARK_TEXT);
        summaryArea.setBackground(SOFT_PINK);
        summaryArea.setBorder(new EmptyBorder(15, 15, 15, 15));
        summaryArea.setText(
            "Your booking summary will appear here.\n\n" +
            "Complete the event details above, then click\n" +
            "\"Confirm Booking & Generate Pass\"."
        );

        JScrollPane summaryScroll = new JScrollPane(summaryArea);
        summaryScroll.setBorder(
            new LineBorder(BORDER_COLOR, 1, true)
        );
        summaryScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryScroll.setPreferredSize(new Dimension(600, 230));
        summaryScroll.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 230)
        );

        bookButton.addActionListener(e -> {

            String type =
                "Others".equals(eventTypeBox.getSelectedItem())
                ? customEventField.getText()
                : (String) eventTypeBox.getSelectedItem();

            String summary =
                "========================================\n" +
                "          EVENTPLUS RESERVATION         \n" +
                "========================================\n\n" +
                "CUSTOMER INFORMATION\n" +
                "Customer: " + customerNameField.getText() + "\n" +
                "Coordinator: " + adminField.getText() + "\n\n" +
                "EVENT DETAILS\n" +
                "Event Type: " + type + "\n" +
                "Event Title: " + eventTitleField.getText() + "\n" +
                "Date: " + dateField.getText() + "\n" +
                "Time: " + timeField.getText() + "\n" +
                "Duration: " + durationSpinner.getValue() + " hour(s)\n" +
                "Guests: " + guestSlider.getValue() + "\n" +
                "Venue: " + venueMatchLabel.getText() + "\n\n" +
                "BUDGET\n" +
                (noBudgetCheck.isSelected()
                    ? "Budget: Custom Freedom\n"
                    : "Budget: PHP " + budgetField.getText() + "\n") +
                "Package: " + packageSuggestion.getText() + "\n\n" +
                "----------------------------------------\n" +
                "STATUS: CONFIRMED\n" +
                "----------------------------------------\n\n" +
                "          DIGITAL PASS DESIGN           \n\n" +
                "          █▀▀▀▀▀█ ▀▀▀█▀▀▀ █▀▀▀▀▀█        \n" +
                "          █ ███ █ █ ▄▀▄ █ █ ███ █        \n" +
                "          █ ▀▀▀ █ █▄█ ▀▄▀ █ ▀▀▀ █        \n" +
                "          ▀▀▀▀▀▀▀ ▀ ▀ ▀ ▀ ▀▀▀▀▀▀▀        \n\n" +
                "========================================\n" +
                "       Thank you for choosing EventPlus!\n" +
                "========================================";

            summaryArea.setText(summary);
            summaryArea.setCaretPosition(0);
        });

        step6.add(bookButton);
        step6.add(Box.createVerticalStrut(18));
        step6.add(summaryScroll);

        mainPanel.add(step6);

        // FOOTER
        JPanel footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setOpaque(false);
        footer.setBorder(new EmptyBorder(25, 10, 10, 10));
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel footerTitle = new JLabel("EventPlus");
        footerTitle.setFont(new Font("Serif", Font.BOLD, 20));
        footerTitle.setForeground(BURGUNDY);
        footerTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel footerText = new JLabel(
            "Making every celebration special."
        );
        footerText.setFont(new Font("SansSerif", Font.ITALIC, 12));
        footerText.setForeground(GRAY_TEXT);
        footerText.setAlignmentX(Component.CENTER_ALIGNMENT);

        footer.add(footerTitle);
        footer.add(Box.createVerticalStrut(5));
        footer.add(footerText);

        mainPanel.add(footer);

        // ================= SCROLL PANEL =================

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(18);
        scrollPane.getViewport().setBackground(LIGHT_PINK);

        add(scrollPane);
    }

    // ================= HELPER METHODS =================

    private JPanel createStepCard(
        String number, String title, String description
    ) {

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(WHITE);

        card.setBorder(
            BorderFactory.createCompoundBorder(
                new EmptyBorder(10, 30, 10, 30),
                BorderFactory.createCompoundBorder(
                    new LineBorder(BORDER_COLOR, 1, true),
                    new EmptyBorder(20, 22, 22, 22)
                )
            )
        );

        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.setMaximumSize(new Dimension(850, Integer.MAX_VALUE));

        JPanel heading = new JPanel(new BorderLayout(12, 0));
        heading.setBackground(WHITE);
        heading.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel numberLabel = new JLabel(number);
        numberLabel.setOpaque(true);
        numberLabel.setBackground(BURGUNDY);
        numberLabel.setForeground(WHITE);
        numberLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numberLabel.setPreferredSize(new Dimension(42, 42));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(WHITE);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(BURGUNDY);

        JLabel descriptionLabel = new JLabel(description);
        descriptionLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        descriptionLabel.setForeground(GRAY_TEXT);

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(4));
        titlePanel.add(descriptionLabel);

        heading.add(numberLabel, BorderLayout.WEST);
        heading.add(titlePanel, BorderLayout.CENTER);

        card.add(heading);
        card.add(Box.createVerticalStrut(22));

        return card;
    }

    private void addField(
        JPanel panel, String labelText, JComponent field
    ) {

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 11));
        label.setForeground(GRAY_TEXT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        if (field instanceof JTextField) {
            field.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 42)
            );
        } else if (field instanceof JComboBox) {
            field.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 42)
            );
        } else {
            field.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 42)
            );
        }

        panel.add(label);
        panel.add(Box.createVerticalStrut(7));
        panel.add(field);
        panel.add(Box.createVerticalStrut(17));
    }

    private JTextField createTextField() {

        JTextField field = new JTextField();

        field.setFont(normalFont);
        field.setForeground(DARK_TEXT);
        field.setBackground(WHITE);
        field.setCaretColor(BURGUNDY);
        field.setBorder(
            BorderFactory.createCompoundBorder(
                new LineBorder(BORDER_COLOR, 1, true),
                new EmptyBorder(8, 12, 8, 12)
            )
        );

        return field;
    }

    private void styleComboBox(JComboBox<String> combo) {

        combo.setFont(normalFont);
        combo.setBackground(WHITE);
        combo.setForeground(DARK_TEXT);
        combo.setBorder(new LineBorder(BORDER_COLOR, 1, true));
    }

    private void styleButton(JButton button) {

        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(BURGUNDY);
        button.setForeground(WHITE);
        button.setFocusPainted(false);
        button.setBorder(
            new EmptyBorder(15, 22, 15, 22)
        );
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);

        button.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                button.setBackground(DARK_BURGUNDY);
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(BURGUNDY);
            }
        });
    }

    private void updatePackageSuggestion(
        JTextField budgetField, JLabel packageSuggestion
    ) {

        try {
            double budget = Double.parseDouble(
                budgetField.getText().trim()
            );

            if (budget < 0) {
                packageSuggestion.setText(
                    "Please enter a valid budget."
                );
            } else if (budget < 50000) {
                packageSuggestion.setText(
                    "VALUE SAVER - Essential Elegance"
                );
            } else if (budget < 150000) {
                packageSuggestion.setText(
                    "PREMIUM - Grand Celebration"
                );
            } else {
                packageSuggestion.setText(
                    "LUXURY - The Royal Gala"
                );
            }

        } catch (NumberFormatException ex) {
            packageSuggestion.setText(
                "Please enter a valid number."
            );
        }
    }

    // ================= GRADIENT HEADER =================

    private class GradientHeader extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );

            GradientPaint gradient = new GradientPaint(
                0, 0, DARK_BURGUNDY,
                getWidth(), getHeight(), BURGUNDY
            );

            g2.setPaint(gradient);
            g2.fillRect(0, 0, getWidth(), getHeight());

            // Decorative circles
            g2.setColor(new Color(255, 255, 255, 18));
            g2.fillOval(-70, -80, 220, 220);
            g2.fillOval(getWidth() - 130, 35, 180, 180);

            g2.dispose();
        }
    }

    // ================= MAIN =================

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new EventPlus().setVisible(true);
        });
    }
}
