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

        // write action
        addPlayersBtn.addActionListener(this::onAddPlayer);
        setHostbtn.addActionListener(this::onSetHostAndPhrase);
        turnBtn.addActionListener(this::onTurn);

        pack();
        setLocationRelativeTo(null);

    }
    private void onAddPlayer(ActionEvent e){
        String first = JOptionPane.showInputDialog(this, "Players first name");
        if(first == null || first.isBlank()) return;

        String last = JOptionPane.showInputDialog(this, "Last name (optional): ");
        Players p = (last != null && !last.isBlank()) ? new Players(first, last) : new Players(first);
        players.add(p);
        updatePlayersLabel();
    }
    private void updatePlayersLabel() {
        if(players.isEmpty()) {
            playersLbl.setText("Players: (none)");
            return;
        }
        StringBuilder sb = new StringBuilder("Players: ");
        for(int i = 0; i < players.size(); i++) {
            Players p = players.get(i);
            sb.append(p.getFirstName());
            if(p.getLastName() != null && !p.getLastName().isBlank()) sb.append(" ").append(p.getLastName());
            if(i < players.size() -1) sb.append(", ");
        }
        playersLbl.setText(sb.toString());
    }
    private void onSetHostAndPhrase(ActionEvent e){}
    private void onTurn(ActionEvent e){}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI().setVisible(true));
    }
}
