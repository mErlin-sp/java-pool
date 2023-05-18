import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BounceFrame extends JFrame {

    private final BallCanvas ballCanvas;
    public static final int WIDTH = 900;
    public static final int HEIGHT = 450;
    private static int ballCounter = 0;
    private final JLabel ballCountLabel;

    public void incrementBallCounter() {
        ballCounter++;
        ballCountLabel.setText("Ball Count: " + ballCounter);
    }

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Java Pool");

        this.ballCanvas = new BallCanvas();
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.ballCanvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonRed = new JButton("Add Red Ball");
        JButton buttonBlue = new JButton("Add Blue Ball");
        JButton buttonExp1 = new JButton("Experiment 1");
        JButton buttonExp2 = new JButton("Experiment 2");
        JButton buttonExp3 = new JButton("Experiment 3");

        JButton buttonHidePockets = new JButton("Hide Pockets");

        JButton buttonStop = new JButton("Stop");

        buttonRed.addActionListener(e -> {
            Ball b = new Ball(ballCanvas, Ball.BallColor.Red, true);
            ballCanvas.add(b);

            BallThread thread = new BallThread(b);
            thread.setPriority(10);
            thread.start();
            System.out.println("Thread name = " + thread.getName());
        });

        buttonBlue.addActionListener(e -> {
            for (int i = 0; i < 10; i++) {
                Ball b = new Ball(ballCanvas, Ball.BallColor.Blue, true);
                ballCanvas.add(b);

                BallThread thread = new BallThread(b);
                thread.setPriority(1);
                thread.start();
                System.out.println("Thread name = " + thread.getName());
            }
        });

        buttonExp1.addActionListener(e -> {
            List<BallThread> ballThreads = new ArrayList<>();

            // Create 10 blue balls
            for (int i = 0; i < 10; i++) {
                Ball b = new Ball(ballCanvas, Ball.BallColor.Blue, false);
                ballCanvas.add(b);
                ballThreads.add(new BallThread(b));
            }

            // Create 1 red ball
            Ball b = new Ball(ballCanvas, Ball.BallColor.Red, false);
            ballCanvas.add(b);
            ballThreads.add(new BallThread(b));

            ballThreads.forEach(Thread::start);

        });

        buttonExp2.addActionListener(e -> {
            List<BallThread> ballThreads = new ArrayList<>();

            // Create 50 blue balls
            for (int i = 0; i < 50; i++) {
                Ball b = new Ball(ballCanvas, Ball.BallColor.Blue, false);
                ballCanvas.add(b);
                ballThreads.add(new BallThread(b));
            }

            // Create 1 red ball
            Ball b = new Ball(ballCanvas, Ball.BallColor.Red, false);
            ballCanvas.add(b);
            ballThreads.add(new BallThread(b));

            ballThreads.forEach(Thread::start);
        });

        buttonExp3.addActionListener(e -> {
            List<BallThread> ballThreads = new ArrayList<>();

            // Create 100 blue balls
            for (int i = 0; i < 100; i++) {
                Ball b = new Ball(ballCanvas, Ball.BallColor.Blue, false);
                ballCanvas.add(b);
                ballThreads.add(new BallThread(b));
            }

            // Create 1 red ball
            Ball b = new Ball(ballCanvas, Ball.BallColor.Red, false);
            ballCanvas.add(b);
            ballThreads.add(new BallThread(b));

            ballThreads.forEach(Thread::start);
        });

        buttonHidePockets.addActionListener(e -> {
            Bounce.hidePockets = true;
            this.ballCanvas.repaint();
            buttonHidePockets.setVisible(false);
        });


        buttonStop.addActionListener(e -> System.exit(0));


        buttonPanel.add(buttonRed);
        buttonPanel.add(buttonBlue);
        buttonPanel.add(buttonExp1);
        buttonPanel.add(buttonExp2);
        buttonPanel.add(buttonExp3);
        buttonPanel.add(buttonHidePockets);
        buttonPanel.add(buttonStop);

        ballCountLabel = new JLabel();
        ballCountLabel.setText("Ball Count: 0");
        buttonPanel.add(ballCountLabel, BorderLayout.EAST);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
