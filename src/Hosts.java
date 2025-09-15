public class Hosts extends Person {
    public Hosts(String firstName){
        super(firstName);
    }

    public Hosts(String firstName, String lastName){
        super(firstName, lastName);
    }

    public void randomizeNum() {
        Numbers n = new Numbers();
        n.generateNumber();
    }
}
