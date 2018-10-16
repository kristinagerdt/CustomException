public class UnderageCustomerException extends Exception {

    private int age;

    public UnderageCustomerException(String message, int age) {
        super(message);
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
