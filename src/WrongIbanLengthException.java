public class WrongIbanLengthException extends Exception {

    private int ibanLength;

    public WrongIbanLengthException(String message, int ibanLength) {
        super(message);
        this.ibanLength = ibanLength;
    }

    public int getIbanLength() {
        return ibanLength;
    }
}
