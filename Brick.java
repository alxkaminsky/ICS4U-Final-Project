import java.awt.*;

public class Brick {
    // Position & Motion
    private int x;
    private int y;
    private int width;
    private int height;

    // Durability
    private final int durabilityTotal;
    private int durabilityRemain;

    // Properties
    private Color color;

    // Status
    private boolean isDestroyed;

    public Brick(int x, int y, int width, int height, Color color, int durability) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.durabilityTotal = durability;
        this.durabilityRemain = durability;
        this.isDestroyed = false;
    }

    // Hit the brick
    public void hit(int damage) {
        durabilityRemain -= damage;
        if (durabilityRemain == 0) {
            isDestroyed = true;
        }
    }

    public void draw(Graphics g) {
        // Brick
        if (durabilityRemain==3){
            g.drawImage(BrickBreakerGame.brick, x, y, null);
        } else if (durabilityRemain==2) {
            g.drawImage(BrickBreakerGame.brick2,x,y,null);
        }
        else
            g.drawImage(BrickBreakerGame.brick1,x,y,null);
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

    public int getDurabilityTotal() {
        return durabilityTotal;
    }

    public int getDurabilityRemain() {
        return durabilityRemain;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    // Setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setDurabilityRemain(int durabilityRemain) {
        // Make sure durabilityRemain is within [0, durabilityTotal]
        if (durabilityRemain > durabilityTotal) {
            this.durabilityRemain = durabilityTotal;
        } else this.durabilityRemain = Math.max(durabilityRemain, 0);
    }
}
