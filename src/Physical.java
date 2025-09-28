import java.util.Random;

public class Physical implements Award{
        private static String [] prizes = {
                "Vacation",
                "Car",
                "Refrigerator",
                "Disneyland Trip",
                "Cruise"
        };
    private final Random num = new Random();
    int getRandomPrize(){
         return num.nextInt(prizes.length);
    }
    @Override
    public int displayWinnings(Players players, boolean correct) {
        String name = (players.getFirstName() + " " + players.getLastName().trim());
        String prize = prizes[getRandomPrize()];

        if(correct) {
            System.out.println(name + ", you won a " + prize + "!");
        } else {
            System.out.println(name + ", you lost. You could have won a " + prize + ".");
        }
        return 0;
    }
}
