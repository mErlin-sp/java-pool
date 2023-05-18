import java.awt.*;

public class BallThread extends Thread {
    private final Ball b;

    public BallThread(Ball b) {
        System.out.println(Thread.currentThread());
        this.b = b;

        if (b.color == Ball.BallColor.Red) {
            this.setPriority(10);
        } else if (b.color == Ball.BallColor.Blue) {
            this.setPriority(1);
        } else {
            this.setPriority(1);
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < 10000; i++) {
                b.move();
                if (!Bounce.hidePockets && b.isInPocket()) {
                    System.out.println("Ball in pocket!");
                    Bounce.frame.incrementBallCounter();
                    b.destroy();
                    break;
                }
                Thread.sleep(5);
            }
        } catch (InterruptedException ignored) {

        }
    }
}
