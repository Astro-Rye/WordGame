import java.util.Random;

public class Physical implements Award{
        private static String [] prizes = {
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
