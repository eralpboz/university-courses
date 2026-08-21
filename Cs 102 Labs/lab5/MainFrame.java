package lab5;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import lab5.MainPanel.MouseMode;


public class MainFrame extends JFrame {
    private String[] buttonNames = { "Add Node", "Add Path", "Remove Node", "Remove Path", "Set Start", "Set Finish",
            "Move Node", "Find Path", "Add Revision Node" };
    private JButton[] buttons;

    public MainFrame() {
        setLayout(new BorderLayout());
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainPanel panel = new MainPanel();
        add(panel, BorderLayout.CENTER);

        buttons = new JButton[9];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(buttonNames[i]);
        }
        class MyActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buttons[0]) {
                    panel.setMode(MouseMode.ADD_NODE);
                } else if (e.getSource() == buttons[1]) {
                    panel.setMode(MouseMode.ADD_PATH);
                } else if (e.getSource() == buttons[2]) {
                    panel.setMode(MouseMode.REMOVE_NODE);
                } else if (e.getSource() == buttons[3]) {
                    panel.setMode(MouseMode.REMOVE_PATH);
                } else if (e.getSource() == buttons[4]) {
                    panel.setMode(MouseMode.SET_START);
                } else if (e.getSource() == buttons[5]) {
                    panel.setMode(MouseMode.SET_FINISH);
                } else if (e.getSource() == buttons[6]) {
                    panel.setMode(MouseMode.MOVE_NODE);
                } else if (e.getSource() == buttons[7]) {
                    
                    panel.findShortestPath(panel.findStartNode(),panel.findFinishNode());
                }else if(e.getSource() == buttons[8]){
                    panel.setMode(MouseMode.ADD_REVISION_NODE);
                }

            }
        }

        JPanel buttonSide = new JPanel(new GridLayout(9, 1, 10, 5));
        MyActionListener listener=new MyActionListener();
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(listener);
            buttonSide.add(buttons[i]);
        }

        JPanel northPanel = new JPanel(new BorderLayout());

        JLabel title1 = new JLabel("  Closest Path Finder");
        JLabel title2 = new JLabel("Tools        ");
        title1.setFont(new Font("Arial", Font.BOLD, 16));
        title2.setFont(new Font("Arial", Font.BOLD, 16));

        title1.setHorizontalAlignment(SwingConstants.CENTER);
        title2.setHorizontalAlignment(SwingConstants.RIGHT);

        northPanel.add(title1, BorderLayout.CENTER);
        northPanel.add(title2, BorderLayout.EAST);

        northPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        add(northPanel, BorderLayout.NORTH);

        add(buttonSide, BorderLayout.EAST);

        setVisible(true);

    }
}