import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GUI extends JFrame {

    // Model
    private final ArrayList<Players> players = new ArrayList<>();
    private Hosts host;
    private Phrases round;
    private int playerIdx = 0;

    // UI
    private final JLabel playersLbl = new JLabel("Players: (none) ");
    private final JButton addPlayersBtn = new JButton("Add Player");

    private final JLabel hostLbl = new JLabel("Host: (none)");
    private final JButton setHostbtn = new JButton("Set Host & Phrase");

    private final JLabel phraseLbl = new JLabel("Phrase: (not set)");
    private final JButton turnBtn = new JButton("Start / Next Turn");

    public GUI(){
        super("WordGame - Lesson 7");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new GridLayout(0, 1, 8, 8));
        root.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        root.add(playersLbl);
        root.add(addPlayersBtn);
        root.add(hostLbl);
        root.add(setHostbtn);
        root.add(phraseLbl);
        root.add(turnBtn);
        setContentPane(root);
    }
}
