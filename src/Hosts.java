public class Hosts extends Person {
    // The host will start a round by providing a phrase
    public Phrases startRoundWithPhrase(String phrase) {
        Phrases.setGamePhrase(phrase);
        return new Phrases();
    }

    public Hosts(String firstName){
        super(firstName);
    }

    public Hosts(String firstName, String lastName){
        super(firstName, lastName);
    }


}
