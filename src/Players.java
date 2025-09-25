public class Players extends Person {
    private static final int STARTING_MONEY = 1000;
    private int money;

    public Players(String firstName) {
        super(firstName);
        this.money = STARTING_MONEY;
    }
    public Players(String firstName, String lastName) {
        super(firstName, lastName);
        this.money = STARTING_MONEY;
    }

    // getters & setters for returning money and getting money
    public int getMoney() { return money; }
    public void setMoney(int money) { this.money = money; }

    // override - displays Players name and returns their starting allotment
    @Override
    public String toString() {
        String fullName = (getFirstName() + " " + getLastName().trim());
        return fullName + " - Money: $ " + getMoney();
    }
}




