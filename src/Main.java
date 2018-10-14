import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private static List<Customer> rightCustomers = new LinkedList<>();

    public static void main(String[] args) {
        Customer c1 = new Customer("Vasya", 16, new BankAccount("DE2117999912356"));
        Customer c2 = new Customer("Ivan", 17, new BankAccount("DE21179999123564"));
        Customer c3 = new Customer("Stepan", 43, new BankAccount("FR21179999123564"));
        Customer c4 = new Customer("Oleg", 15, new BankAccount("DE2117999912356475"));

        List<Customer> customers = new LinkedList<>();
        Collections.addAll(customers, c1, c2, c3, c4);

        for (Customer customer : customers) {
            addToListRightCustomers(customer);
        }
        System.out.println("\nList of right customers");
        System.out.println(rightCustomers);
    }

    public static void addToListRightCustomers(Customer customer) {
        if (validateCustomer(customer)) {
            rightCustomers.add(customer);
        }
    }

    public static boolean validateCustomer(Customer customer) {
        boolean isRightCustomer = true;
        List<String> exceptionList = new LinkedList<>();

        try {
            validateAge(customer.getAge());
        } catch (UnderageCustomerException e) {
            isRightCustomer = false;
            exceptionList.add(e.getMessage() + ", age=" + e.getAge());
        }

        try {
            validateCountryCode(customer.getAccount().getIban());
        } catch (InvalidCountryCodeException e) {
            isRightCustomer = false;
            exceptionList.add(e.getMessage() + ", country=" + e.getCountryCode());
        }

        try {
            validateIbanLength(customer.getAccount().getIban());
        } catch (WrongIbanLengthException e) {
            isRightCustomer = false;
            exceptionList.add(e.getMessage() + ", length=" + e.getIbanLength());
        }

        if (!isRightCustomer) {
            printException(exceptionList, customer.getName());
        }

        return isRightCustomer;
    }

    public static void validateAge(int age) throws UnderageCustomerException {
        if (age < 17) {
            throw new UnderageCustomerException("Underage customer", age);
        }
    }

    public static void validateCountryCode(String iban) throws InvalidCountryCodeException {
        String currCountryCode = iban.substring(0, 2);
        if (!currCountryCode.equals("DE")) {
            throw new InvalidCountryCodeException("Invalid country code", currCountryCode);
        }
    }

    public static void validateIbanLength(String iban) throws WrongIbanLengthException {
        int currIbanLength = iban.length();
        if (currIbanLength != 16) {
            throw new WrongIbanLengthException("Wrong length of iban", currIbanLength);
        }
    }

    public static void printException(List<String> exceptionList, String name) {
        System.out.print(name + ": ");
        String collect = String.join(". ", exceptionList);
        System.out.println(collect);
    }
}