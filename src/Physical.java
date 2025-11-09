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
          "C:\\Users\\JCarrillo8\\rscJavaProjects\\WordGame\\WordGame\\resources\\car.jpg",
           "C:\\Users\\JCarrillo8\\rscJavaProjects\\WordGame\\WordGame\\resources\\car.jpg",
           "C:\\Users\\JCarrillo8\\rscJavaProjects\\WordGame\\WordGame\\resources\\car.jpg",
           "C:\\Users\\JCarrillo8\\rscJavaProjects\\WordGame\\WordGame\\resources\\car.jpg",
           "C:\\Users\\JCarrillo8\\rscJavaProjects\\WordGame\\WordGame\\resources\\car.jpg"
        };
    private final Random rand = new Random();

    int getRandomPrizeIndex(){
         return rand.nextInt(PRIZES.length);
    }
    @Override
    public int displayWinnings(Players players, boolean correct) {
       int index = getRandomPrizeIndex();
       String prizeName = PRIZES[index];

       String imagePath = PRIZE_IMAGES[index];
       java.io.File f = new java.io.File(imagePath);
        System.out.println("Trying image path: " + f.getAbsolutePath());
        System.out.println("Exists? " + f.exists());

        ImageIcon icon = null;
        try {
            icon = new ImageIcon(imagePath);
        } catch ( Exception e ) {
            // if image fails to load, skip the icon
            e.printStackTrace();
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
