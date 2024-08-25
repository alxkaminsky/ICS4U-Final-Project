import java.awt.*;

public class Ball {
    // Position & Motion
    private int x; // top left x val
    private int y; // top left y val
    private int dx;
    private int dy;

    // Properties
    private final Color color;
    private int size; // ball's diameter

    // State
    private boolean isStopped;

    public Ball(int x, int y, int dx, int dy, int size) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        this.color = Color.white;
        this.isStopped = false;
    }

    // Move the ball
    public void move(Graphics g) {
        if (x < 5 || x > 495 - size) {
            dx = -dx;
        }
        if (y < 5) {
            dy = -dy;
        } else if (y > 495 - size - 15) {
            dy = 0;
            dx = 0;
            isStopped = true;
        }

        x += dx;
        y += dy;

        draw(g);
    }

    // Collision detection with bricks
    public int collidesWith(Brick b) {
            Rectangle ball = new Rectangle(x, y, size, size);
            Rectangle brick;

            brick = new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight());

            if (ball.intersects(brick)) {
                y += 5; // prevent multiple collisions
                x-=5;
                b.hit(1);

                if (x+size < b.getX() || x> b.getX() + b.getWidth())
                    dx = -dx;
                else
                    dy = -dy;

                return 1;
            }
        return 0;

    }
        // Collision detection with paddle
        public void collidesWith (Paddle p){
            Rectangle ball = new Rectangle(x, y, size, size);
            Rectangle brick;

            brick = new Rectangle(p.getX(), p.getY(), p.getWidth(), p.getHeight());

            if (ball.intersects(brick)) {
                y -= 5; // prevent bll form getting stuck collisions
                x-=5;
                if (x+size < p.getX() || x> p.getX() + p.getWidth())
                    dx = -dx;
                else
                    dy = -dy;
            }
        }

        public void draw (Graphics g){
            g.setColor(Color.white);
            g.fillOval(x, y, size, size);
        }

        // Getters
        public int getX () {
            return x;
        }

        public int getY () {
            return y;
        }

        public int getDx () {
            return dx;
        }

        public int getDy () {
            return dy;
        }

        public int getSize () {
            return size;
        }

        public boolean isStopped () {
            return isStopped;
        }

        // Setters
        public void setX ( int x){
            this.x = x;
        }

        public void setY ( int y){
            this.y = y;
        }

        public void setDx ( int dx){
            this.dx = dx;
        }

        public void setDy ( int dy){
            this.dy = dy;
        }

        public void setSize ( int size){
            this.size = size;
        }
    }
