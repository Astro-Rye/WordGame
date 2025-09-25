class Person {
    //-- 1:  create a class called Person.java [x]
    //-- 2: add two private instance variables: firstName and lastName
    private String firstName;
    private String lastName;

    //-- 3: add public getters and setters for both
    // --- getter & setter --- for firstName
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    // getter & setter for LastName
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Constructors
    // constructor for the first name
    public Person(String firstName){
        this.firstName = firstName;
        this.lastName = ""; // sets the last name to default
    }
    public Person(String firstName, String lastName){
        this.firstName = firstName; // accepts
        this.lastName = lastName;
    }
}