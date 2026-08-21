import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class aMainMenu extends JFrame {

    private static int playerNumber = 0;

    public aMainMenu() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("How many Players?", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JButton btn1 = new JButton("2 Players");
        JButton btn2 = new JButton("3 Players");
        JButton btn3 = new JButton("4 Players");

        class MyActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn1)
                    playerNumber = 2;
                if (e.getSource() == btn2)
                    playerNumber = 3;
                if (e.getSource() == btn3)
                    playerNumber = 4;

                dispose();
                new aPlayerNameFrame(playerNumber);
            }
        }

        MyActionListener listener = new MyActionListener();
        btn1.addActionListener(listener);
        btn2.addActionListener(listener);
        btn3.addActionListener(listener);

        buttonPanel.add(btn1);
        buttonPanel.add(btn2);
        buttonPanel.add(btn3);

        add(buttonPanel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

}