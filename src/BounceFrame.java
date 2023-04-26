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
        this.setTitle("Bounce program");

        this.ballCanvas = new BallCanvas();
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.ballCanvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");

        buttonStart.addActionListener(e -> {

            Ball b = new Ball(ballCanvas);
            ballCanvas.add(b);

            BallThread thread = new BallThread(b);
            thread.start();
            System.out.println("Thread name = " + thread.getName());
        });

        buttonStop.addActionListener(e -> System.exit(0));


        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);

        ballCountLabel = new JLabel();
        ballCountLabel.setText("Ball Count: 0");
        buttonPanel.add(ballCountLabel,BorderLayout.EAST);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
