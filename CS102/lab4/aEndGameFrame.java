import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class aEndGameFrame extends JFrame {

    public aEndGameFrame(Player[] players, String[] names) {

        setTitle("Game Over");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel finalResult = new JLabel("Final Results", SwingConstants.CENTER);

        add(finalResult, BorderLayout.NORTH);

        int highestScore = 0;

        for (int i = 0; i < players.length; i++) {
            if (players[i].getPoints() > highestScore) {
                highestScore = players[i].getPoints();
            }
        }

 
        ArrayList<String> winnerList = new ArrayList<>();

        for (int i = 0; i < players.length; i++) {
            if (players[i].getPoints() == highestScore) {
                winnerList.add(players[i].getName());
            }
        }

        
        String winnerText = "";
        for (int i = 0; i < winnerList.size(); i++) {
            winnerText += winnerList.get(i);
            if (i != winnerList.size() - 1) {
                winnerText += ", ";
            }
        }

       
        JPanel centerPanel = new JPanel(new GridLayout(players.length + 2, 1, 5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel winnerLabel = new JLabel("Winner(s): " + winnerText, SwingConstants.CENTER);
       
        centerPanel.add(winnerLabel);

        
        centerPanel.add(new JLabel(" "));

        
        for (int i = 0; i < players.length; i++) {
            Player p = players[i];
            JLabel scoreLabel = new JLabel(p.getName() + ": " + p.getPoints() + " points", SwingConstants.CENTER);
            scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            centerPanel.add(scoreLabel);
        }

        add(centerPanel, BorderLayout.CENTER);

       
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(16, 31, 16, 31));

        JButton playAgainButton = new JButton("Play Again");
        JButton mainMenuButton = new JButton("Main Menu");

        
        mainMenuButton.setFont(new Font("Arial", Font.BOLD, 14));

        buttonPanel.add(playAgainButton);
        buttonPanel.add(mainMenuButton);

        add(buttonPanel, BorderLayout.SOUTH);

        class EndGameActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == playAgainButton) {
                    dispose();
                    new aGameFrame(names);
                } else if (e.getSource() == mainMenuButton) {
                    dispose();
                    new aMainMenu();
                }
            }
        }
        EndGameActionListener listner = new EndGameActionListener();
        playAgainButton.addActionListener(listner);
        mainMenuButton.addActionListener(listner);

        setVisible(true);
    }
}
