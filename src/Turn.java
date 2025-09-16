import java.util.Scanner;
public class Turn {
private static final int WIN_AMOUNT = 200; // pick your values
    private static final int LOSS_AMOUNT = 50; // pick your values
    private final Scanner scanner = new Scanner(System.in);

  public boolean takeTurn(Players player, Hosts host){
      // host prompts players (simulates a show host spekaing)
      System.out.println(host.getFirstName() + ": " + player.getFirstName() + ", enter your guess(0-100): ");

      int guess = scanner.nextInt(); // moved from GamePlay

      Numbers nums = new Numbers();
      boolean guessedCorrectly = nums.compareNumber(guess);
        if(guessedCorrectly){
            player.setMoney(player.getMoney() + WIN_AMOUNT);
            System.out.println("Congratulations! " + player);
            return true;
        } else {
            player.setMoney(player.getMoney() - LOSS_AMOUNT);
            System.out.println(player);
            return false;
        }
  }
}
