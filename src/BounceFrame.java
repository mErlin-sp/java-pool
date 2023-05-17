import javax.swing.*;
import java.awt.*;

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
        JButton buttonStop = new JButton("Stop");

        buttonRed.addActionListener(e -> {
            Ball b = new Ball(ballCanvas, Ball.BallColor.Red);
            ballCanvas.add(b);

            BallThread thread = new BallThread(b);
            thread.setPriority(10);
            thread.start();
            System.out.println("Thread name = " + thread.getName());
        });

        buttonBlue.addActionListener(e -> {
            for (int i = 0; i < 10; i++) {
                Ball b = new Ball(ballCanvas, Ball.BallColor.Blue);
                ballCanvas.add(b);

                BallThread thread = new BallThread(b);
                thread.setPriority(1);
                thread.start();
                System.out.println("Thread name = " + thread.getName());
            }
        });


        buttonStop.addActionListener(e -> System.exit(0));


        buttonPanel.add(buttonRed);
        buttonPanel.add(buttonBlue);
        buttonPanel.add(buttonStop);

        ballCountLabel = new JLabel();
        ballCountLabel.setText("Ball Count: 0");
        buttonPanel.add(ballCountLabel, BorderLayout.EAST);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
