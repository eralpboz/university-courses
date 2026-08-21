import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class aPlayerNameFrame extends JFrame {

    Color[] colors = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW };
    private JTextField[] nameFields;

    private final int WIDTH = 400;
    private final int HEIGHT = 300;

    public aPlayerNameFrame(int playerNumber) {
        setTitle("Player Names");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel label1 = new JLabel("Enter Player Names", SwingConstants.CENTER);
        add(label1, BorderLayout.NORTH);

        JPanel shapesNames = new JPanel(new GridLayout(playerNumber, 2, 10, 10));

        nameFields = new JTextField[playerNumber];

        for (int i = 0; i < playerNumber; i++) {
            Circle circle = new Circle(colors[i]);
            shapesNames.add(circle);
            nameFields[i] = new JTextField();
            shapesNames.add(nameFields[i]);
        }

        JPanel bottomPanel = new JPanel();
        JButton back = new JButton("Back to Menu");
        JButton start = new JButton("Start");

        class MyActionListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == back) {
                    dispose();
                    new aMainMenu();
                } else if (e.getSource() == start) {

                    for (int i = 0; i < nameFields.length; i++) {
                        String text = nameFields[i].getText().trim();
                        if (text.isEmpty()) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "All players must have a name!",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                    
                    for (int i = 0; i < nameFields.length; i++) {
                        String name1 = nameFields[i].getText();
                        for (int j = i + 1; j < nameFields.length; j++) {
                            String name2 = nameFields[j].getText();
                            if (name1.equals(name2)) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "All players must have unique names!",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    }

                    
                    String[] names = new String[nameFields.length];
                    for (int i = 0; i < nameFields.length; i++) {
                        names[i] = nameFields[i].getText();
                    }

                    JOptionPane.showMessageDialog(null, "Names accepted! Game starting...");
                    dispose();
                    new aGameFrame(names);
                }
            }
        }

        MyActionListener listener = new MyActionListener();
        back.addActionListener(listener);
        start.addActionListener(listener);

        add(shapesNames, BorderLayout.CENTER);

        bottomPanel.add(back);
        bottomPanel.add(start);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    class Circle extends JPanel {
        Color color;

        Circle(Color c) {
            this.color = c;
            setPreferredSize(new Dimension(30, 30));
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(color);
            g.fillOval(5, 5, 20, 20);
        }
    }
}