import java.util.Scanner;

public class GamePlay {
    private static Players player;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // only for name + play again
        // Host first, then generate the random number
        Hosts host = new Hosts("Pat");
        host.randomizeNum();

        System.out.println("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.println("Would you like to enter a last name? yes/no ");
        String response = scanner.nextLine().trim().toLowerCase();

        if(response.equals("yes")) {
            System.out.println("Enter your last name: ");
            String lastName = scanner.nextLine();
            player = new Players(firstName, lastName);
        } else {
            player = new Players(firstName);
        }

        Turn turn = new Turn();
        // outer loop, play again
        boolean playAgain = true;
        while(playAgain) {
            // inner loop - keep guessing until correct
            boolean guessedCorrectly = false;
            while(!guessedCorrectly) {
                guessedCorrectly = turn.takeTurn(player, host);
            }

            System.out.println("Play Again? (y/n): ");
            String again = input.nextLine().trim().toLowerCase();

            if(again.startsWith("y")) {
                host.randomizeNum(); // a new number or the* secret number for the next game should be generated
            } else {
                playAgain = false;
            }
        }
    }
}