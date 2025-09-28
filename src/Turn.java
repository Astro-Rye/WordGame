import java.util.Scanner;
public class Turn {

    private final Scanner scanner = new Scanner(System.in);

  public boolean takeTurn(Players player, Hosts host){
      // host prompts players (simulates a show host speaking)
      System.out.println(host.getFirstName() + ": " + player.getFirstName() + ", enter your guess(0-100): ");

      int guess = scanner.nextInt(); // moved from GamePlay
      scanner.nextLine();

      Numbers nums = new Numbers();
      boolean guessedCorrectly = nums.compareNumber(guess);

      // decide the prize type
      java.util.Random rng = new java.util.Random();
      int coin = rng.nextInt(2);
      boolean moneyPrize = (coin == 0);

      // the right award
      Award award;
      if(moneyPrize) {
          award = new Money();
      } else {
          award = new Physical();
      }

      // show winnings and get the int change to money
      int delta = award.displayWinnings(player, guessedCorrectly);
      // update players money
      player.setMoney(player.getMoney() + delta);

      // show player status
      System.out.println(player);

      return guessedCorrectly;
  }
}
