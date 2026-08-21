import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class aGameFrame extends JFrame {

    private final int GRID_SIZE = 11;

    private String[] playerNames;
    private int allPlayers;
    private Player[] playersOfGame;
    private Board gameBoard;

    private JButton[][] cellButtons;
    private boolean[][] movableCellButton;

    private int currentPlayerNumber = 0;

    private JLabel turnShower;
    private JPanel scorJPanel;

    private JPanel sequencePanel;

    private Color[] playerCircleeColour = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW };

    public aGameFrame(String[] names) {
        setTitle("Game");
        setSize(900, 700);

        this.playerNames = names;
        this.allPlayers = names.length;
        gameBoard = new Board(allPlayers, GRID_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        playersOfGame = new Player[allPlayers];

        for (int i = 0; i < allPlayers; i++) {
            int rows = 0;
            int cols = 0;
            if (i == 0) {
                rows = 0;
                cols = 0;
            } else if (i == 1) {
                rows = 0;
                cols = GRID_SIZE - 1;
            } else if (i == 2) {
                rows = GRID_SIZE - 1;
                cols = 0;
            } else if (i == 3) {
                rows = GRID_SIZE - 1;
                cols = GRID_SIZE - 1;
            }

            playersOfGame[i] = new Player(names[i], i + 1, rows, cols);
        }

        // OYUNCU SIRASINI KARISTIR
        List<Player> playerList = Arrays.asList(playersOfGame);
        Collections.shuffle(playerList);
        playersOfGame = playerList.toArray(new Player[0]);

        JLabel title = new JLabel("Game", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // OYUN TAHTASI
        JPanel boardPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        cellButtons = new JButton[GRID_SIZE][GRID_SIZE];
        movableCellButton = new boolean[GRID_SIZE][GRID_SIZE];

        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {

                JButton gameButtn = new JButton();
                gameButtn.setFont(new Font("Arial", Font.BOLD, 16));
                int rowPos = r;
                int colPos = c;
                class myActionListener implements ActionListener {
                    public void actionPerformed(ActionEvent e) {

                        // TIKLAMA
                        if (!movableCellButton[rowPos][colPos]) {
                            JOptionPane.showMessageDialog(null,
                                    "You cannot move there!",
                                    "Invalid Move",
                                    JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        if (!playersOfGame[currentPlayerNumber].isActive()) {
                            return;
                        }

                        int startRow = playersOfGame[currentPlayerNumber].getRow();
                        int startCol = playersOfGame[currentPlayerNumber].getCol();

                        int cellValue = gameBoard.getCell(rowPos, colPos);

                        if (cellValue >= 1 && cellValue <= 4
                                && cellValue != playersOfGame[currentPlayerNumber].getId()) {
                            for (int i = 0; i < allPlayers; i++) {
                                if (playersOfGame[i].getId() == cellValue) {
                                    playersOfGame[i].eleminate();

                                }
                            }
                            playersOfGame[currentPlayerNumber].addPoints(5);
                        }

                        if (cellValue == 6){
                            playersOfGame[currentPlayerNumber].addPoints(1);
                        }
                        if (cellValue == 7){
                            playersOfGame[currentPlayerNumber].addPoints(2);
                        }

                        gameBoard.setCell(startRow, startCol, 0);
                        gameBoard.setCell(rowPos, colPos, playersOfGame[currentPlayerNumber].getId());
                        playersOfGame[currentPlayerNumber].setRow(rowPos);
                        playersOfGame[currentPlayerNumber].setCol(colPos);

                        // SKORLARI GUNCELLE
                        scorJPanel.removeAll();
                        for (int k = 0; k < playersOfGame.length; k++) {

                            Player p = playersOfGame[k];

                            int colorIdx = -1;
                            for (int i = 0; i < allPlayers; i++) {
                                if (p.getId() == i + 1) {
                                    colorIdx = i;
                                    break;
                                }
                            }

                            JLabel scoreLine = new JLabel(p.getPoints() + " - " + p.getName());
                            scoreLine.setForeground(playerCircleeColour[colorIdx]);
                            scoreLine.setFont(new Font("Arial", Font.PLAIN, 14));
                            scorJPanel.add(scoreLine);
                        }

                        scorJPanel.revalidate();
                        scorJPanel.repaint();

                        // OYUN BITTI MI KONTROL
                        int remainingObjects = gameBoard.getNumberOfElement("pickableObject");
                        int activePlayers = 0;
                        for (int i = 0; i < playersOfGame.length; i++) {

                            if (playersOfGame[i].isActive()) {
                                activePlayers++;
                            }
                        }

                        if (remainingObjects == 0 || activePlayers <= 1) {
                            dispose();
                            new aEndGameFrame(playersOfGame, names);

                        }

                        // SONRAKI OYUNCU
                        int firstIndex = currentPlayerNumber;
                        boolean x = true;
                        while (x) {
                            currentPlayerNumber = (currentPlayerNumber + 1) % allPlayers;
                            if (playersOfGame[currentPlayerNumber].isActive()) {
                                x = false;
                            }
                            if (currentPlayerNumber == firstIndex) {
                                x = false;
                            }
                        }

                        // SIRA YAZISINI GUNCELLE

                        int circlePlayerColor = -1;
                        for (int i = 0; i < allPlayers; i++) {
                            if (playersOfGame[currentPlayerNumber].getId() == i + 1) {
                                circlePlayerColor = i;
                                break;
                            }
                        }
                        turnShower.setText("Turn: " + playersOfGame[currentPlayerNumber].getName());
                        turnShower.setForeground(playerCircleeColour[circlePlayerColor]);

                        // UCGEN ISARETINI AYARLA
                        for (int i = 0; i < allPlayers; i++) {
                            Component[] items = sequencePanel.getComponents();
                            if (i < items.length && items[i] instanceof JPanel) {
                                JPanel container = (JPanel) items[i];
                                Component[] innerItems = container.getComponents();
                                if (innerItems.length >= 2 && innerItems[1] instanceof JLabel) {
                                    JLabel marker = (JLabel) innerItems[1];
                                    if (i == currentPlayerNumber) {
                                        marker.setText("▲");
                                    } else {
                                        marker.setText(" ");
                                    }
                                }
                            }
                        }
                        sequencePanel.revalidate();
                        sequencePanel.repaint();

                        // GIDILEBILIR YERLERI BUL
                        for (int i = 0; i < GRID_SIZE; i++) {
                            for (int j = 0; j < GRID_SIZE; j++) {
                                movableCellButton[i][j] = false;
                            }
                        }

                       
                            int playerRow = playersOfGame[currentPlayerNumber].getRow();
                            int playerCol = playersOfGame[currentPlayerNumber].getCol();
                            int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

                            for (int d = 0; d < directions.length; d++) {

                                int dirRow = directions[d][0];
                                int dirCol = directions[d][1];

                                for (int distance = 1; distance < GRID_SIZE; distance++) {

                                    int targetRow = playerRow + dirRow * distance;
                                    int targetCol = playerCol + dirCol * distance;

                                    if (targetRow < 0 || targetRow >= GRID_SIZE || targetCol < 0
                                            || targetCol >= GRID_SIZE) {
                                        break;
                                    }

                                    int cellContent = gameBoard.getCell(targetRow, targetCol);
                                    if (cellContent == 5) {
                                        break;
                                    }
                                    //8→ 9← 10↑ 11↓
                                    if (cellContent==8) {
                                        if(!(dirRow==0&&dirCol==1)){
                                            break;
                                        }
                                    }
                                    if (cellContent==9) {
                                        if(!(dirRow==0&&dirCol==-1)){
                                            break;
                                        }
                                    }
                                    if (cellContent==10) {
                                        if(!(dirRow==-1&&dirCol==0)){
                                            break;
                                        }
                                    }
                                    if (cellContent==11) {
                                        if(!(dirRow==1&&dirCol==0)){
                                            break;
                                        }
                                    }
                                    

                                    movableCellButton[targetRow][targetCol] = true;
                                    if (cellContent >= 1 && cellContent <= 4
                                            && cellContent != playersOfGame[currentPlayerNumber].getId()) {
                                             break;
                                    }
                                    
                                }
                            }


                        // TAHTAYI TEKRAR CIZ
                        resetAllButtons();
                        for (int r = 0; r < GRID_SIZE; r++) {
                            for (int c = 0; c < GRID_SIZE; c++) {
                                JButton bt = cellButtons[r][c];
                                int cellVal = gameBoard.getCell(r, c);

                                bt.setBackground(Color.WHITE);
                                bt.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                                if (cellVal == 5) {
                                    Color cc = new Color(91, 101, 111);
                                    bt.setBackground(cc);
                                } else if (cellVal == 6 || cellVal == 7) {
                                    Color cc = new Color(255, 220, 140);
                                    bt.setBackground(cc);
                                } else if (cellVal >= 1 && cellVal <= 4) {
                                    bt.setText("●");
                                    bt.setFont(new Font("Arial", Font.BOLD, 29));
                                    bt.setForeground(playerCircleeColour[cellVal - 1]);

                                }else if(gameBoard.getCell(r, c) == 8){
                                    Color cc = new Color(91, 101, 111);
                                    bt.setBackground(cc);
                                    cellButtons[r][c].setText("→");
                                    cellButtons[r][c].setFont(new Font("Arial", Font.BOLD, 28));
                                } else if(gameBoard.getCell(r, c) == 9){
                                    Color cc = new Color(91, 101, 111);
                                    bt.setBackground(cc);
                                    cellButtons[r][c].setText("←");
                                    cellButtons[r][c].setFont(new Font("Arial", Font.BOLD, 28));
                                } else if(gameBoard.getCell(r, c) == 10){
                                    Color cc = new Color(91, 101, 111);
                                    bt.setBackground(cc);
                                    cellButtons[r][c].setText("↑");
                                    cellButtons[r][c].setFont(new Font("Arial", Font.BOLD, 28));
                                } else if(gameBoard.getCell(r, c) == 11){
                                    Color cc = new Color(91, 101, 111);
                                    bt.setBackground(cc);
                                    cellButtons[r][c].setText("↓");
                                    cellButtons[r][c].setFont(new Font("Arial", Font.BOLD, 28));
                                }

                                if (movableCellButton[r][c]) {
                                    if (cellVal == 0 || cellVal == 6 || cellVal == 7||cellVal == 8||cellVal == 9) {
                                        bt.setText("●");
                                        bt.setFont(new Font("Arial", Font.BOLD, 17));
                                        bt.setForeground(Color.GREEN.darker());
                                    }
                                }
                            }
                        }
                    }
                }
                myActionListener listener = new myActionListener();
                gameButtn.addActionListener(listener);
                cellButtons[r][c] = gameButtn;
                boardPanel.add(gameButtn);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        // SAG PANEL
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(11, 11, 11, 11));

        turnShower = new JLabel();
        turnShower.setFont(new Font("Arial", Font.BOLD, 17));
        sidePanel.add(turnShower);
        sidePanel.add(Box.createVerticalStrut(11));

        JLabel sequenceTitle = new JLabel("Playing order:");
        sequenceTitle.setFont(new Font("Arial", Font.BOLD, 15));
        sidePanel.add(sequenceTitle);

        sequencePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        for (int i = 0; i < allPlayers; i++) {
            int playerColorIdx = -1;
            for (int j = 0; j < allPlayers; j++) {
                if (playersOfGame[i].getId() == j + 1) {
                    playerColorIdx = j;
                    break;
                }
            }

            int finalColorIdx = playerColorIdx;
            int finalSequenceNum = i + 1;

            JPanel circlePanel = new JPanel() {
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    g.setColor(playerCircleeColour[finalColorIdx]);
                    g.fillOval(5, 5, 40, 40);

                    g.setColor(Color.BLACK);

                    g.drawOval(5, 5, 40, 40);

                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.BOLD, 20));
                    FontMetrics fm = g.getFontMetrics();
                    String num = String.valueOf(finalSequenceNum);
                    int x = 25 - fm.stringWidth(num) / 2;
                    int y = 25 + fm.getAscent() / 2 - 2;
                    g.drawString(num, x, y);
                }
            };
            circlePanel.setPreferredSize(new Dimension(50, 50));
            circlePanel.setOpaque(false);

            JPanel playerContainer = new JPanel();
            playerContainer.setLayout(new BoxLayout(playerContainer, BoxLayout.Y_AXIS));

            JLabel triangleMarker = new JLabel(" ");
            triangleMarker.setFont(new Font("Arial", Font.BOLD, 14));
            triangleMarker.setHorizontalAlignment(SwingConstants.CENTER);

            playerContainer.add(circlePanel);
            playerContainer.add(triangleMarker);

            sequencePanel.add(playerContainer);
        }
        sidePanel.add(sequencePanel);

        sidePanel.add(Box.createVerticalStrut(10));

        JLabel scoreTitle = new JLabel("Scores:");
        scoreTitle.setFont(new Font("Arial", Font.BOLD, 14));
        sidePanel.add(scoreTitle);

        JPanel scoreBgPanel = new JPanel();
        scoreBgPanel.setBackground(new Color(240, 240, 240));
        scoreBgPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        scoreBgPanel.setLayout(new BorderLayout());

        scorJPanel = new JPanel();
        scorJPanel.setLayout(new BoxLayout(scorJPanel, BoxLayout.Y_AXIS));
        scorJPanel.setOpaque(false);
        scorJPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        scoreBgPanel.add(scorJPanel, BorderLayout.CENTER);
        sidePanel.add(scoreBgPanel);

        add(sidePanel, BorderLayout.EAST);

        setVisible(true);

        // ILK SIRA GUNCELLEME
        Player currentPlayer = playersOfGame[currentPlayerNumber];
        int playerColorIdx = -1;
        for (int i = 0; i < allPlayers; i++) {
            if (currentPlayer.getId() == i + 1) {
                playerColorIdx = i;
                break;
            }
        }
        turnShower.setText("Turn: " + currentPlayer.getName());
        turnShower.setForeground(playerCircleeColour[playerColorIdx]);

        // ILK UCGEN AYARI
        for (int i = 0; i < allPlayers; i++) {
            Component[] items = sequencePanel.getComponents();
            if (i < items.length && items[i] instanceof JPanel) {
                JPanel container = (JPanel) items[i];
                Component[] innerItems = container.getComponents();
                if (innerItems.length >= 2 && innerItems[1] instanceof JLabel) {
                    JLabel marker = (JLabel) innerItems[1];
                    marker.setText(i == currentPlayerNumber ? "▲" : " ");
                }
            }
        }

        // ILK HAREKET HESAPLAMA
        for (int i = 0; i < GRID_SIZE; i++){
            for (int j = 0; j < GRID_SIZE; j++){
                movableCellButton[i][j] = false;
            }
        }

        if (playersOfGame[currentPlayerNumber].isActive()) {
            int playerRow = playersOfGame[currentPlayerNumber].getRow();
            int playerCol = playersOfGame[currentPlayerNumber].getCol();
            int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

            for (int d = 0; d < directions.length; d++) {

                int dirRow = directions[d][0];
                int dirCol = directions[d][1];

                for (int distance = 1; distance < GRID_SIZE; distance++) {

                    int targetRow = playerRow + dirRow * distance;
                    int targetCol = playerCol + dirCol * distance;

                    if (targetRow < 0 || targetRow >= GRID_SIZE || targetCol < 0
                            || targetCol >= GRID_SIZE) {
                        break;
                    }

                    int cellContent = gameBoard.getCell(targetRow, targetCol);
                    if (cellContent == 5) {
                        break;
                    }
                    //8→ 9← 10↑ 11↓
                    if (cellContent==8) {
                        if(!(dirRow==0&&dirCol==1)){
                            break;
                        }
                    }
                    if (cellContent==9) {
                        if(!(dirRow==0&&dirCol==-1)){
                            break;
                        }
                    }
                    if (cellContent==10) {
                        if(!(dirRow==1&&dirCol==0)){
                            break;
                        }
                    }
                    if (cellContent==11) {
                        if(!(dirRow==-1&&dirCol==0)){
                            break;
                        }
                    }
                    

                    movableCellButton[targetRow][targetCol] = true;

                    if (cellContent >= 1 && cellContent <= 4
                        && cellContent != playersOfGame[currentPlayerNumber].getId()) {
                    break;
                }
                }
            }
        }

        // ILK TAHTA CIZIMI
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {

                cellButtons[r][c].setBackground(Color.WHITE);
                cellButtons[r][c].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cellButtons[r][c].setFont(new Font("Arial", Font.BOLD, 16));

                if (gameBoard.getCell(r, c) == 5) {
                    Color coloour = new Color(90, 100, 110);
                    cellButtons[r][c].setBackground(coloour);
                } else if (gameBoard.getCell(r, c) == 6 || gameBoard.getCell(r, c) == 7) {
                    Color coloour = new Color(255, 220, 140);
                    cellButtons[r][c].setBackground(coloour);
                } else if (gameBoard.getCell(r, c) >= 1 && gameBoard.getCell(r, c) <= 4) {

                    cellButtons[r][c].setText("●");
                    cellButtons[r][c].setFont(new Font("Arial", Font.BOLD, 28));
                    cellButtons[r][c].setForeground(playerCircleeColour[gameBoard.getCell(r, c) - 1]);
                    cellButtons[r][c].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                } else if(gameBoard.getCell(r, c) == 8){
                    Color cc = new Color(91, 101, 111);
                    cellButtons[r][c].setBackground(cc);
                    cellButtons[r][c].setText("→");
                    cellButtons[r][c].setFont(new Font("Arial", Font.BOLD, 28));
                } else if(gameBoard.getCell(r, c) == 9){
                    Color cc = new Color(91, 101, 111);
                    cellButtons[r][c].setBackground(cc);
                    cellButtons[r][c].setText("←");
                    cellButtons[r][c].setFont(new Font("Arial", Font.BOLD, 28));
                } else if(gameBoard.getCell(r, c) == 10){
                    Color cc = new Color(91, 101, 111);
                    cellButtons[r][c].setBackground(cc);
                    cellButtons[r][c].setText("↑");
                    cellButtons[r][c].setFont(new Font("Arial", Font.BOLD, 28));
                } else if(gameBoard.getCell(r, c) == 11){
                    Color cc = new Color(91, 101, 111);
                    cellButtons[r][c].setBackground(cc);
                    cellButtons[r][c].setText("↓");
                    cellButtons[r][c].setFont(new Font("Arial", Font.BOLD, 28));
                }




                if (movableCellButton[r][c]) {
                    if (gameBoard.getCell(r, c) == 0 || gameBoard.getCell(r, c) == 6 || gameBoard.getCell(r, c) == 7) {
                        cellButtons[r][c].setText("");
                        cellButtons[r][c].setText("●");
                        cellButtons[r][c].setFont(new Font("Arial", Font.BOLD, 16));
                        cellButtons[r][c].setForeground(Color.GREEN.darker());
                    }
                }
            }
        }

        for (int k = 0; k < playersOfGame.length; k++) {

            int colorIdx = -1;
            for (int i = 0; i < allPlayers; i++) {
                if (playersOfGame[k].getId() == i + 1) {
                    colorIdx = i;
                    break;
                }
            }

            JLabel scoreLine = new JLabel(playersOfGame[k].getPoints() + " - " + playersOfGame[k].getName());
            scoreLine.setForeground(playerCircleeColour[colorIdx]);
            scoreLine.setFont(new Font("Arial", Font.PLAIN, 14));
            scorJPanel.add(scoreLine);
        }

    }

    private void resetAllButtons() {
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                JButton bt = cellButtons[r][c];
                bt.setText("");
                bt.setForeground(Color.BLACK);
                bt.setBackground(Color.WHITE);
                bt.setFont(new Font("Arial", Font.BOLD, 16));
                bt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        }
    }
}