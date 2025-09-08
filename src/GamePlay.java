import java.util.Scanner;
public class GamePlay {
    private static Person player;

    public static void main(String[] args) {
        System.out.println("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.println("Would you like to enter a last name? yes/no ");
        String response = scanner.nextLine().trim().toLowerCase();

        if(response.equals("yes")) {
            System.out.println("Enter your last name: ");
            String lastName = scanner.nextLne();

            player = new Person(firstName, lastName);
        } else {
            player = new Person(firstName);
        }

        // Create  a numbers object and generate a number
        Numbers game = new Numbers();
        game.generateNumber();

        // guessing loop
        boolean guessedCorrectly = false;
        while (!guessedCorrectly) {
            System.out.println(player.getFirstName() + ", enter your guess (0-100): ");
            int guess = scanner.nextInt();
            guessedCorrectly = game.compareNumber(guess);
        }
        scanner.close();
    }
}