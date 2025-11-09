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

    private final JCheckBox saveMessagesChk = new JCheckBox("Save Messages", true);
    private final JTextArea messagesArea = new JTextArea(8, 50); // grows with scroll
    private final JScrollPane messagesScroll = new JScrollPane(messagesArea);



    private final JLabel hostLbl = new JLabel("Host: (none)");


    private final JLabel phraseLbl = new JLabel("Phrase: (not set)");

    private static final String CORRECT_SOUND_PATH ="C:\\Users\\JCarrillo8\\rscJavaProjects\\WordGame\\WordGame\\resources\\correct.mp3";
    private static final String WRONG_SOUND_PATH ="C:\\Users\\JCarrillo8\\rscJavaProjects\\WordGame\\WordGame\\resources\\wrong.mp3";



    public GUI(){
        super("WordGame - Lesson 8");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        buildMenuBar();
        buildInterface(); // new layout

        pack();
        setLocationRelativeTo(null);
        // write action
    }
    // builder method
    private void buildInterface() {
        // root with border-layout
        JPanel root = new JPanel(new BorderLayout(8,8));
        root.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // North: Stacked Labels
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.add(playersLbl);
        info.add(hostLbl);
        info.add(phraseLbl);
        root.add(info, BorderLayout.NORTH);

        // CENTER : placeholder panel ( future canvas / big phrase )
        JPanel center = new JPanel();
        center.add(new JLabel("Game area ( future expansion"));
        root.add(center, BorderLayout.CENTER);

        // SOUTH: messages + Save Messages checkbox
        messagesArea.setEditable(false);
        messagesScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        saveMessagesChk.setToolTipText("If checked, new messages append: if unchecked, each message replaces the previous.");

        JPanel south = new JPanel(new BorderLayout(6, 6));
        south.add(messagesScroll, BorderLayout.CENTER);
        south.add(saveMessagesChk, BorderLayout.SOUTH);

        root.add(south, BorderLayout.SOUTH);

        setContentPane(root);
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
        clearMessages();
        log("New round started by " + host.getFirstName() + ".");
        log("Phrase: " + round.getPlayingPhrase());
    }
    private void onTurn(ActionEvent e){
        if (players.isEmpty()) {log("Please add at least one player first."); return; }
        if(host == null || round == null) { log("Please set host and phrase first."); return; }

        Players current = players.get(playerIdx);
        String guess = JOptionPane.showInputDialog(this, current.getFirstName() + ", enter ONE letter: ");
        if(guess == null) return; // canceled

        boolean correct;

        try{
            int revealed = round.findLetters(guess);
            correct = (revealed > 0);
        } catch (MultipleLettersException ex) {
            log(ex.getMessage()); // More than  one letter was entered
            return;
        } catch (IllegalArgumentException ex) {
            log("Please enter a single letter (A-Z)");
            return;
        }
        // add sound
        // play sound based on correctness
        if(correct) {
            SoundPlayer.play(CORRECT_SOUND_PATH);
        } else {
            SoundPlayer.play(WRONG_SOUND_PATH);
        }
        // Decide Prize (re-uses Award system)
        java.util.Random rng = new java.util.Random();
        Award award = rng.nextBoolean() ? new Money() : new Physical();
        int delta = award.displayWinnings(current, correct);
        current.setMoney(current.getMoney() + delta);

        // Update UI
        phraseLbl.setText("Phrase: " + round.getPlayingPhrase());

        log(current.toString());

        if(round.isSolved()) {
            log("The phrase has been solved!");
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

        // Game Menu (Alt+G)
        JMenu game = new JMenu("Game");
        game.setMnemonic(KeyEvent.VK_G);

        JMenuItem addPlayerItem = new JMenuItem("Add Player..");
        JMenuItem setHostPhraseItem = new JMenuItem("Set Host & Phrase");
        JMenuItem nextTurnItem = new JMenuItem("Start / Next Turn");
        JMenuItem exitItem = new JMenuItem("Exit");

        addPlayerItem.addActionListener(this::onAddPlayer);
        setHostPhraseItem.addActionListener(this::onSetHostAndPhrase);
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

    private void log(String text){
        if(saveMessagesChk.isSelected()) {
            messagesArea.append(text + "\n");
            messagesArea.setCaretPosition(messagesArea.getDocument().getLength());
        } else {
            messagesArea.setText(text + "\n");
        }
    }
    private void clearMessages(){
        messagesArea.setText("");
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI().setVisible(true));
    }
}
