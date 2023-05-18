import javax.swing.*;

//
// By Cay S. Horstman
// Modified by Oleksandr Popov
//

public class Bounce {

    public static BounceFrame frame;

    public static volatile boolean hidePockets = false;

    public static void main(String[] args) {
        frame = new BounceFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}
