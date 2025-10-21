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
}
