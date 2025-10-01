import java.util.Scanner;

public class GamePlay {
    // instance variables of Players
    private static Players[] currentPlayers = new Players[3]; // holds 3 elements of type player

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // only for name + play again
        // Host first, then generate the random number
        Hosts host = new Hosts("Pat");

        for (int i = 0; i < currentPlayers.length; i++) {
            System.out.println("Enter first name for player " + (i+1) +  " :");
            String first = input.nextLine().trim();

            System.out.println("Add a last name? (yes/no): ");
            String resp = input.nextLine().trim().toLowerCase();

            if(resp.equals("yes")) {
                System.out.println("Enter a last name: ");
                String last = input.nextLine().trim();
                currentPlayers[i] = new Players(first, last);
            } else {
                currentPlayers [i] = new Players(first);
            }
        }

            Turn turn = new Turn();
        // outer loop, play again
            boolean playAgain = true;

            // inner loop - keep guessing until correct
            boolean guessedCorrectly = false;


            while(playAgain) {
                System.out.println(host.getFirstName() + ", enter a phrase for this round: ");
                String phrase = input.nextLine();
                Phrases round = host.startRoundWithPhrase(phrase);
                System.out.println("Phrase: " + round.getPlayingPhrase());

                boolean solved = false;
                int idx = 0;

               while(!solved) {
                   Players current = currentPlayers[idx];
                   solved = turn.takeTurn(current, host, round);
                   idx = (idx + 1) % currentPlayers.length;

               }
                System.out.println("Play Again? (y/n): ");
                String again = input.nextLine().trim().toLowerCase();
                playAgain = again.startsWith("y");
            }
    }
}