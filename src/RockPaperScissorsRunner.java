import javax.swing.*;

public class RockPaperScissorsRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                RockPaperScissorsFrame frame = new RockPaperScissorsFrame();
                frame.setVisible(true);
            }
        });
    }
}