import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.KeyEvent;


public class GUI extends JFrame {

    // Model
    private final ArrayList<Players> players = new ArrayList<>();
    private Hosts host;
    private Phrases round;
    private int playerIdx = 0;

    // UI
    private final JLabel playersLbl = new JLabel("Players: (none) ");
    private final JButton addPlayersBtn = new JButton("Add Player");

    // New UI Fields
    private final JCheckBox saveMessagesChk = new JCheckBox("Save Messages", true);
    private final JTextArea messagesArea = new JTextArea(8, 50); // grows with scroll
    private final JScrollPane messagesScroll = new JScrollPane(messagesArea);



    private final JLabel hostLbl = new JLabel("Host: (none)");
    private final JButton setHostbtn = new JButton("Set Host & Phrase");

    private final JLabel phraseLbl = new JLabel("Phrase: (not set)");
    private final JButton turnBtn = new JButton("Start / Next Turn");

    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenu editMenu = new JMenu("Edit");

    JButton button = new JButton();


    public GUI(){
        super("WordGame - Lesson 7");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buildMenuBar();

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
    private void onSetHostAndPhrase(ActionEvent e){
        String hostFirst = JOptionPane.showInputDialog(this, "Host first name: ");
        if(hostFirst == null || hostFirst.isBlank()) return;

        String hostLast = JOptionPane.showInputDialog(this, "Host last name (optional):");
        host = (hostLast != null && !hostLast.isBlank()) ? new Hosts(hostFirst, hostLast) : new Hosts(hostFirst);
        hostLbl.setText("Host: " + host.getFirstName() + ((host.getLastName() != null && !host.getLastName().isBlank()) ? " " + host.getLastName() : ""));

        String phrase = JOptionPane.showInputDialog(this, "Enter phrase for this round:");
        if(phrase == null) return;

        round = host.startRoundWithPhrase(phrase); // your existing method
        phraseLbl.setText("Phrase: " + round.getPlayingPhrase());
        playerIdx = 0; // reset to first player for new phrase

    }
    private void onTurn(ActionEvent e){
        if(players.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Add at least one player first.");
            return;
        }
        if(host == null || round == null) {
            JOptionPane.showMessageDialog(this, "Set host and phrase first. ");
            return;
        }
        Players current = players.get(playerIdx);
        String guess = JOptionPane.showInputDialog(this, current.getFirstName() + ", enter ONE letter: ");
        if(guess == null) return; // canceled

        boolean correct;
        try{
            int revealed = round.findLetters(guess);
            correct = (revealed > 0);
        } catch (MultipleLettersException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a single letter (A-Z).");
            return;
        }
        // Decide Prize (re-uses Award system)
        java.util.Random rng = new java.util.Random();
        Award award = rng.nextBoolean() ? new Money() : new Physical();
        int delta = award.displayWinnings(current, correct);
        current.setMoney(current.getMoney() + delta);

        // Update UI
        phraseLbl.setText("Phrase: " + round.getPlayingPhrase());
        JOptionPane.showMessageDialog(this, current.toString());

        if(round.isSolved()) {
            int again = JOptionPane.showConfirmDialog(this, "Solved! Play another round", "Play again", JOptionPane.YES_NO_OPTION);

            if(again == JOptionPane.YES_OPTION) {
                String phrase = JOptionPane.showInputDialog(this, host.getFirstName() + ", new phrase:");
                if(phrase != null) {
                    round = host.startRoundWithPhrase(phrase);
                    phraseLbl.setText("Phrase: " + round.getPlayingPhrase());
                    playerIdx = 0;
                }
            }
            return;
        }
        // Next player
        playerIdx = (playerIdx + 1) % players.size();

    }

    private void buildMenuBar() {
        JMenuBar bar = new JMenuBar();

        // Game Menu
        JMenu game = new JMenu("Game");
        JMenuItem addPlayerItem = new JMenuItem("Add Player..");
        JMenuItem setHostPhraseItem = new JMenuItem("Set Host & Phrase");
        JMenuItem nextTurnItem = new JMenuItem("Start / Next Turn");
        JMenuItem exitItem = new JMenuItem("Exit");

        addPlayerItem.addActionListener(this::onAddPlayer);
        setHostPhraseItem.addActionListener(this::onTurn);
        nextTurnItem.addActionListener(this::onTurn);
        exitItem.addActionListener(e -> dispose());

        game.add(addPlayerItem);
        game.add(setHostPhraseItem);
        game.add(nextTurnItem);
        game.add(exitItem);

        // About Menu
        JMenu about = new JMenu("About");
        about.setMnemonic(KeyEvent.VK_A);

        JMenuItem layoutItem = new JMenuItem("Layout..");
        layoutItem.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Layout choice: BorderLayout (NORTH = info labels, CENTER = main area, SOUTH = messages).\n " +
                        "simple, readable, and easy to extend next week. ",
                        "Layout", JOptionPane.INFORMATION_MESSAGE)
        );
        about.add(layoutItem);
        bar.add(game);
        bar.add(about);
        setJMenuBar(bar);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI().setVisible(true));
    }
}
