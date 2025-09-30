import java.util.Random;
import java.util.Scanner;
public class Turn {

    private final Scanner scanner = new Scanner(System.in);

  public boolean takeTurn(Players player, Hosts host, Phrases round){
      System.out.println(player.getFirstName() + ", enter ONE letter: ");
      String guess = scanner.nextLine();

      boolean correct;
      try {
          int revealed = round.findLetters(guess);
          correct = (revealed > 0);
          System.out.println("Phrase: " + round.getPlayingPhrase());
      } catch (MultipleLettersException e) {
          return false;
      } catch (IllegalArgumentException e) {
          System.out.println("Please enter a single letter(A-Z)");
          return false;
      }


      Random rng = new Random();
      boolean moneyPrize = rng.nextBoolean();
      Award award = moneyPrize ? new Money() : new Physical();
      int delta = award.displayWinnings(player, correct);
      player.setMoney(player.getMoney() + delta);
      System.out.println(player);

      if(round.isSolved()) {
          System.out.println("The phrase is solved! :)");
          return true;
      }
      return false;
  }
}
