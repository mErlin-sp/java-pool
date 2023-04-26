import javax.swing.*;

//
// By Cay S. Horstman
//

public class Bounce {

    public static BounceFrame frame;

    public static void main(String[] args) {
        frame = new BounceFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        System.out.println("Thread name = " + Thread.currentThread().getName());

    }
}
