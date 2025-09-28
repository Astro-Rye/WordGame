public class Money implements Award {

    private static final int WIN_AMOUNT = 200;
    private static final int LOSS_AMOUNT = 50;


    @Override
    public int displayWinnings(Players players, boolean correct) {
        if(correct){
            System.out.println("Players name: " + players.getFirstName() + " " + players.getLastName());
            System.out.println("You've won!");
            return WIN_AMOUNT;
        } else {
            System.out.println("Players name: " + players.getFirstName() + " " + players.getLastName());
            System.out.println("You've lost :(");
            return -LOSS_AMOUNT;
        }
    }
}
