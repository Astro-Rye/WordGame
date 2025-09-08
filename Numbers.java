public class Numbers {
    // create a class called 'Numbers'[x]

    // 2. Add a private integer field called 'randomNum'[]
    private int randomNum;

    // 3. public getters and setters
    // getters
    public int getRandomNum() {
        return randomNum;
    }
    // setters
    public void setRandomNum(int randomNum){
        this.setRandomNum = setRandomNum;
    }
    public void generateNumber(){
        Random random = new Random();
        this.randomNum = rand.nextInt(101);
    }

    //
    public boolean compareNumber(int guess){
        if(guess == randomNum) {
            System.out.println("Congratulations, you guessed the number!");
            return true;
        } else if (guess > randomNum) {
            System.out.println("Im soryr. That guess was too high");
        } else {
            System.out.println("Im sorry. That guess was too low");
        }
        return false;
    }
}