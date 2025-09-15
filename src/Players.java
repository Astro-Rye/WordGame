public class Players extends Person {
    private static final int STARTING_MONEY = 1000;
    private int money;

    public Players(String firstName) {
        super(firstName);

    }
    public Players(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString(){
return getFirstName() +""+getLastName() + getMoney();}
}




