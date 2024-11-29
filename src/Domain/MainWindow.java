package Domain;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainWindow extends JFrame {
    private final String TITLE = "Encoder";
    private final Encoder encoder;

    private static final int WIDTH = 450;
    private static final int HEIGHT = 450;
    private static boolean resize = false;

    private JTextArea textField;
    private JTextField keyField;
    private JRadioButton flagEncrypt;
    private JButton button;

    public MainWindow(Encoder encoder) {
        this.encoder = encoder;
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(resize);
        setTitle(TITLE);
        setLocationRelativeTo(null);

        panelCenter();
        panelKey();
        buttonStart();
        panelBottom();

        setVisible(true);
    }

    private void panelCenter() {
        String labelName = "Введите сообщение:";
        JPanel center = new JPanel(new GridLayout(2, 1));
        JLabel label = new JLabel(labelName);
        textField = new JTextArea();
        center.add(label);
        center.add(textField);
        add(center, BorderLayout.CENTER);
    }

    private void panelKey() {
        String labelName = "Введите ключ:";
        JPanel sidePanel = new JPanel(new GridLayout(3, 1));
        JLabel label = new JLabel(labelName);
        JSeparator seperator = new JSeparator();
        keyField = new JTextField();
        sidePanel.add(label);
        sidePanel.add(keyField);
        sidePanel.add(seperator);
        add(sidePanel, BorderLayout.EAST);
    }

    private void buttonStart() {
        String labelName = "Старт";
        button = new JButton(labelName);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int key;
                String text, code;

                try {
                    key = Integer.parseInt(keyField.getText());
                } catch (NumberFormatException ex) {
                    key = 0;
                }
                text = textField.getText();

                if (flagEncrypt.isSelected()) {
                    code = encoder.decrypt(text, key);
                } else {
                    code = encoder.encrypt(text, key);
                }
                windowTotal(text, code);
            }
        });
    }

    private void panelBottom() {
        String labelName = " Расшифровать ";

        JPanel panel = new JPanel(new GridLayout(1, 3));
        JLabel label = new JLabel(" ");
        flagEncrypt = new JRadioButton(labelName);

        panel.add(label);
        panel.add(flagEncrypt);
        panel.add(button);
        add(panel, BorderLayout.SOUTH);
    }

    private void windowTotal(String text, String code) {
        String labelName = "Total";
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setTitle(labelName);
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frame.setVisible(true);

        JTextArea area = new JTextArea(text);
        JTextArea area2 = new JTextArea(code);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(area);
        panel.add(area2);
        frame.add(panel);
    }
}