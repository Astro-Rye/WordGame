class Person {
    //-- 1:  create a class called Person.java [x]
    //-- 2: add two private instance variables: firstName and lastName
    private String firstName;
    private String lastName;

    //-- 3: add public getters and setters for both
    // --- getter ---
    public String getFirstName() {
        return firstName;
    }
    // --- setter ---
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Constructors
    // first name
    public Person(String firstName){
        this.firstName = firstName;
        this.lastName = "";
    }
    public Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public static void main(String[] args) {
        
    }
}