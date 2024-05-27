import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class BrickBreakerGame extends JPanel implements ActionListener, KeyListener {
    private final Timer timer;
    private int score = 0;
    private int bricksRemaining;
    private int screen = 0;
    private boolean level2;
    private Ball ball;
    private Ball ball2;
    private Paddle paddle;
    private final Brick[][] bricks = new Brick[5][10];
    private boolean play = false;
    private Image title;
    private Image titleBricks;
    private Image logo;
    private Image GameOver;
    public static Image brick;
    public static Image brick2;
    public static Image brick1;
    private  Clip backgroundMusic;

    public BrickBreakerGame() {
        try {
            //images, gifs, and music
            titleBricks= ImageIO.read(new File("TitleBricks.png"));
            brick= ImageIO.read(new File("Brick.png"));
            brick2= ImageIO.read(new File("Brick2.png"));
            brick1= ImageIO.read(new File("Brick1.png"));
            logo= ImageIO.read(new File("logo.png"));
            GameOver= Toolkit.getDefaultToolkit().createImage("GameOver.gif");
            title= Toolkit.getDefaultToolkit().createImage("title.gif");
        }
        catch (IOException e) {}

        timer = new Timer(10, this);
        start();
    }
    public void start() {
        // Initialize game
        score = 0;
        level2=false;

        // create ball
        ball = new Ball(250, 300, 2, 3, 20);
        ball2 = new Ball(250, 300, 1, 1, 20);

        // Create bricks
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                bricks[i][j] = new Brick(j * 50, (i *20)+30, 50, 20, Color.white, (int) (3*Math.random()+1));
            }
        }
        // Create paddle
        paddle = new Paddle(225, 470, 10, 120, 10, Color.green);
        paddle.movements();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);
        timer.start();
        //Get and start music
        backgroundMusic = loadSoundClip("/assets/audio.wav");
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

   protected Clip loadSoundClip(String soundFilePath) {
        Clip soundClip;
        try {
            URL url = this.getClass().getResource(soundFilePath);
            assert url != null;
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            soundClip = AudioSystem.getClip();
            soundClip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException("Error loading sound clip: " + soundFilePath, e);
        }
        return soundClip;
    }

    public void paint(Graphics g) {

        if (screen == 0) {
            g.drawImage(title, 0, 0, null);
            g.drawImage(titleBricks, 0, 0, null);
            g.setColor(Color.white);
            if(System.currentTimeMillis() % 1000 < 500) {
                g.setFont(new Font("Courier", Font.PLAIN, 15));
                g.drawString("press space to play ", 160, 320);
            }
        }
        else if (screen == 1) {
            g.setColor(Color.black);
            g.fillRect(0, 0, 500, 500);
            g.drawImage(logo, 170, 170, null);
            play = true;
            if (!level2) {
                if(ball.isStopped()) {
                    screen++;
                    backgroundMusic.stop();
                }
            }
            else{
                if(ball.isStopped()||ball2.isStopped()) {
                    screen++;
                    backgroundMusic.stop();
                }
            }

        }
        else if(screen==2) {
            g.setColor(Color.black);
            g.drawImage(GameOver,0,0,null);
            g.setColor(Color.white);
            g.setFont(new Font("Courier", Font.PLAIN, 17));
            g.drawString("Score: " + score, 210, 250);

            if(System.currentTimeMillis() % 1000 < 500) {
                g.setFont(new Font("Courier", Font.PLAIN, 15));
                g.drawString("press space to play again", 145, 380);
             }
        }
        if (play && screen == 1) {
            ball.move(g);
            if(level2){
                ball2.move(g);
                ball2.collidesWith(paddle);
            }
            paddle.draw(g);
            ball.collidesWith(paddle);
            int bricksTotal = 0;
            //Draw bricks
            for (Brick[] brick : bricks) {
                for (Brick value : brick) {
                    if (value!=null&&!value.isDestroyed()) {
                        bricksTotal++;
                        value.draw(g);
                        score += ball.collidesWith(value);
                        if (level2){
                            score+=ball2.collidesWith(value);
                        }
                    }
                }
            }
            bricksRemaining = bricksTotal;
            if (bricksRemaining<=49){
                level2=true;
            }
            g.setColor(Color.green);
            g.setFont(new Font("Courier", Font.PLAIN, 20));
            g.drawString("score: " + (score < 10 ? "00" : score < 100 ? "0" : "") + score, 373, 23);
            paddle.update();

            if (bricksRemaining <= 0) {
                screen++;
            }
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (screen == 0) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                screen++;
            }
        }
        if(screen ==1){
            paddle.paddleKey(e.getKeyCode(), true);
        }
        if (screen==2){
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                screen=1;
                start();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        // Update key states for paddle
        paddle.paddleKey(e.getKeyCode(), false);
    }

    public void keyTyped(KeyEvent e) {
    }
}
