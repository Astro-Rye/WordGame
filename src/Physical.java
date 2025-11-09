import javax.swing.*;
import java.util.Random;

public class Physical implements Award{
        private static String [] PRIZES = {
                "Vacation",
                "Car",
                "Refrigerator",
                "Disneyland Trip",
                "Cruise"
        };

        // Match array: same index as prize
    private static final String[] PRIZE_IMAGES = {
          "WordGame/resources/vacation.jpeg",
           "WordGame/resources/car.jpeg",
           "WordGame/resources/fridge.jpeg",
           "WordGame/resources/disney.jpeg",
           "WordGame/resources/cruise.jpeg"
        };
    private final Random rand = new Random();

    int getRandomPrizeIndex(){
         return rand.nextInt(PRIZES.length);
    }
    @Override
    public int displayWinnings(Players players, boolean correct) {
       int index = getRandomPrizeIndex();
       String prizeName = PRIZES[index];

        ImageIcon icon = null;
        String imagePath = PRIZE_IMAGES[index];

        try {
            icon = new ImageIcon(imagePath);
        } catch ( Exception e ) {
            // if image fails to load, skip the icon
        }
        if(correct) {
            String msg = players.getFirstName() + "won: " + prizeName + "!";
            JOptionPane.showMessageDialog(
                    null,
                    msg,
                    "Physical Prize Won!",
                    JOptionPane.INFORMATION_MESSAGE,
                    icon
            );
        } else {
            String msg = players.getFirstName() + ", you lost this round.\nYou *could* have won " + prizeName + ".";
            JOptionPane.showMessageDialog(
                    null,
                    msg,
                    "So Close!",
                    JOptionPane.INFORMATION_MESSAGE,
                    icon
            );
        }
        // Physical prizes doont change money for now ....  [come back after]
        return 0;
    }
}
