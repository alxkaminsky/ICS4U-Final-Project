import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(0, 0, 507,535);
        frame.setTitle("Alex Kaminsky - Hacker Brick Breaker");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BrickBreakerGame game = new BrickBreakerGame();
        frame.add(game);

        frame.setVisible(true);

    }

}
