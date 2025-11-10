import javax.swing.*;
import java.awt.*;


public class AnimationPanel extends JPanel {

    private int x = -50; // starting x position
    private int y = 30; // vertical position
    private int dx = 5;
    private boolean running = false;
    private Timer timer;

    public AnimationPanel () {
        setPreferredSize(new Dimension(400, 100));

        timer = new Timer(30, e -> {
            if (!running) return;
            x += dx;

            if( x > getWidth()) {
                running = false;
                timer.stop();
            }
            repaint();
        });
    }

    public void startAnimation () {
        x = -50; // reset off screen
        running = true;

        if(!timer.isRunning()) {
            timer.start();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(running) {
            g.setColor(Color.RED);
            g.fillRect(x, y, 400, 100);
            setBackground(Color.black);// red square whammy
        }
    }
}
