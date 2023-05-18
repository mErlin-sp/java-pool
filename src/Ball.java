import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball {

    private final BallCanvas canvas;
    final BallColor color;
    public static final int XSIZE = 20;
    public static final int YSIZE = 20;
    private int x;
    private int y;
    private int dx = 2;
    private int dy = 2;

    public enum BallColor {
        Red, Blue
    }


    public Ball(BallCanvas c, BallColor color, boolean randomStart) {
        this.canvas = c;
        this.color = color;

        x = 100;
        y = 100;

        if (randomStart) {
            do {
                if (Math.random() < 0.5) {
                    x = new Random().nextInt(this.canvas.getWidth());
                    y = 0;
                } else {
                    x = 0;
                    y = new Random().nextInt(this.canvas.getHeight());
                }
            } while (isInPocket());
        }
    }

    public void draw(Graphics2D g2) {
        if (this.color == BallColor.Red) {
            g2.setColor(Color.RED);
        } else if (this.color == BallColor.Blue) {
            g2.setColor(Color.BLUE);
        } else {
            g2.setColor(Color.darkGray);
        }

        g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
    }

    public void move() {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= this.canvas.getWidth()) {
            x = this.canvas.getWidth() - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= this.canvas.getHeight()) {
            y = this.canvas.getHeight() - YSIZE;
            dy = -dy;
        }
        this.canvas.repaint();
    }

    public boolean isInPocket() {
//        if (true) {
//            return false;
//        }
        if (x < (XSIZE * 2) && y < (XSIZE * 2)) {
            return true;
        } else if (x > (((double) this.canvas.getWidth() / 2) - (double) (XSIZE)) && x < (((double) this.canvas.getWidth() / 2) + (double) (XSIZE)) && y < (XSIZE * 2)) {
            return true;
        } else if (x > (this.canvas.getWidth() - (XSIZE * 2)) && y < (XSIZE * 2)) {
            return true;
        } else if (x < (XSIZE * 2) && y > (this.canvas.getHeight() - (XSIZE * 2))) {
            return true;
        } else if (x > (((double) this.canvas.getWidth() / 2) - (double) (XSIZE)) && x < (((double) this.canvas.getWidth() / 2) + (double) (XSIZE)) && y > (this.canvas.getHeight() - (XSIZE * 2))) {
            return true;
        } else if (x > (this.canvas.getWidth() - (XSIZE * 2)) && y > (this.canvas.getHeight() - (XSIZE * 2))) {
            return true;
        } else {
            return false;
        }
    }

    public void destroy() {
        this.canvas.remove(this);
        this.canvas.repaint();
    }

}
