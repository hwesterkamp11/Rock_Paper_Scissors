import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    private int playerWins = 0, computerWins = 0, ties = 0;
    private JTextArea resultsArea;
    private JTextField playerWinsField, computerWinsField, tiesField;
    private String[] choices = {"Rock", "Paper", "Scissors"};
    private Random rand = new Random();

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gamePanel = new JPanel();
        gamePanel.setBorder(BorderFactory.createTitledBorder("Choose Your Move"));
        gamePanel.setLayout(new FlowLayout());

        gamePanel.add(createButton("Rock", "images/rock.jpg"));
        gamePanel.add(createButton("Paper", "images/paper.jpg"));
        gamePanel.add(createButton("Scissors", "images/scissors.jpg"));
        gamePanel.add(createQuitButton());

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(3,2));
        statsPanel.add(new JLabel("Player Wins:"));
        playerWinsField = new JTextField("0",5);
        statsPanel.add(playerWinsField);
        statsPanel.add(new JLabel("Computer Wins:"));
        computerWinsField = new JTextField("0",5);
        statsPanel.add(computerWinsField);
        statsPanel.add(new JLabel("Ties:"));
        tiesField = new JTextField("0",5);
        statsPanel.add(tiesField);

        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BorderLayout());
        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        resultsPanel.add(scrollPane, BorderLayout.CENTER);

        add(gamePanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.CENTER);
        add(resultsPanel, BorderLayout.SOUTH);
    }

    private JButton createButton(String name, String imagePath) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizedImage);
        JButton button = new JButton(name, icon);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playGame(name);
            }
        });
        return button;
    }

    private JButton createQuitButton() {
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return quitButton;
    }

    private void playGame(String playerChoice) {
        String computerChoice = choices[rand.nextInt(3)];
        String result = determineWinner(playerChoice, computerChoice);
        if(result.equals("Player wins")) {
            playerWins++;
        } else if(result.equals("Computer wins")) {
            computerWins++;
        } else {
            ties++;
        }

        playerWinsField.setText(String.valueOf(playerWins));
        computerWinsField.setText(String.valueOf(computerWins));
        tiesField.setText(String.valueOf(ties));
        resultsArea.append(playerChoice + " vs " + computerChoice + " - " + result + "\n");
    }

    private String determineWinner(String playerChoice, String computerChoice) {
        if(playerChoice.equals(computerChoice)) {
            return "It's a tie";
        }
        if((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) || (playerChoice.equals("Paper") && computerChoice.equals("Rock"))  || (playerChoice.equals("Scissors") && computerChoice.equals("Player"))) {
            return "Player wins";
        }
        return "Computer wins";
    }
}