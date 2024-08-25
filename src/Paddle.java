import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle {
    // Position & Motion
    private int x;
    private int y;

    // Properties
    private final int width;
    private final int height;
    private final int speed;
    private final Color color;

    // Key states
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public Paddle(int x, int y, int speed, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    // Move the paddle left
    public void moveLeft() {
        x -= speed;
        if (x < 10)
            x = 10;
    }

    // Move the paddle right
    public void moveRight() {
        x += speed;
        if (x > 370)
            x = 370;
    }

    // Initialize key states
    public void movements() {
        leftPressed = false;
        rightPressed = false;
    }

    // Update the key states based on key events
    public void paddleKey(int keyCode, boolean pressed) {
        if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed = pressed;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed = pressed;
        }
    }

    // Update method to be called in the game loop
    public void update() {
        if (leftPressed) {
            moveLeft();
        }
        if (rightPressed) {
            moveRight();
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public int getSpeed() {
        return speed;
    }

    // Setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
