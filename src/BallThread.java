public class BallThread extends Thread {
    private final Ball b;
    private final int steps;

    public BallThread(Ball b, int steps) {
        this.b = b;
        this.steps = steps;

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
            for (int i = 1; i < steps; i++) {
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
        } finally {
            b.destroy();
        }
    }
}
