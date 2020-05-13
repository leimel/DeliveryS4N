package co.com.sucorrientazo.delivery.exceptions;

public class DeliveryException extends RuntimeException {
    private final String detail;

    public DeliveryException(final String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }
}
