package org.devalurum.stockprice.exception;

public class NotFoundExchange extends RuntimeException {
    private final long id;

    public NotFoundExchange(long id) {
        this.id = id;
    }

    public NotFoundExchange(Exception e, long id) {
        super(e);
        this.id = id;
    }

    public NotFoundExchange(String message, Exception e, long id) {
        super(message, e);
        this.id = id;
    }
}
