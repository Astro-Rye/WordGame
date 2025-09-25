import java.util.Random;
public class Numbers {
    // create a class called 'Numbers'[x]

    // 2. Add a private integer field called 'randomNum'[]
    private static int randomNum;

    // 3. public getters and setters
    // getters
    //public int getRandomNum() {
      //  return randomNum;
    //}
    public static int getRandomNum() {return randomNum;}
    // setters
    public void setRandomNum(int randomNum){
        this.randomNum = randomNum;
    }
    public void generateNumber(){
        Random random = new Random();
        this.randomNum = random.nextInt(101);
    }

    //
    public boolean compareNumber(int guess){
        if(guess == randomNum) {
            System.out.println("Congratulations, you guessed the number!");
            return true;
        } else if (guess > randomNum) {
            System.out.println("Im sorry. That guess was too high");
        } else {
            System.out.println("Im sorry. That guess was too low");
        }
        return false;
    }
}