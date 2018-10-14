public class InvalidCountryCodeException extends Exception {

    private String countryCode;

    public InvalidCountryCodeException(String message, String countryCode) {
        super(message);
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
