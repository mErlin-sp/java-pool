import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class BallCanvas extends JPanel {
    private final ArrayList<Ball> balls = new ArrayList<>();

    public void add(Ball b) {
        this.balls.add(b);
    }

    public void remove(Ball b) {
        this.balls.remove(b);
    }

    private void drawPockets(Graphics2D g2) {
//        System.out.println("Canvas width: " + this.getWidth());
//        System.out.println("Canvas height: " + this.getHeight());

        g2.fill(new Ellipse2D.Double(0, 0, Ball.XSIZE * 2, Ball.XSIZE * 2));
        g2.fill(new Ellipse2D.Double(((double) this.getWidth() / 2) - (double) (Ball.XSIZE), 0, Ball.XSIZE * 2, Ball.XSIZE * 2));
        g2.fill(new Ellipse2D.Double(this.getWidth() - (Ball.XSIZE * 2), 0, Ball.XSIZE * 2, Ball.XSIZE * 2));

        g2.fill(new Ellipse2D.Double(0, this.getHeight() - (Ball.XSIZE * 2), Ball.XSIZE * 2, Ball.XSIZE * 2));
        g2.fill(new Ellipse2D.Double(((double) this.getWidth() / 2) - (double) (Ball.XSIZE), this.getHeight() - (Ball.XSIZE * 2), Ball.XSIZE * 2, Ball.XSIZE * 2));
        g2.fill(new Ellipse2D.Double(this.getWidth() - (Ball.XSIZE * 2), this.getHeight() - (Ball.XSIZE * 2), Ball.XSIZE * 2, Ball.XSIZE * 2));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawPockets(g2);
        for (Ball b : balls) {
            b.draw(g2);
        }
    }
}
