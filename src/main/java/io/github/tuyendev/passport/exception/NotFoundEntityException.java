package io.github.tuyendev.passport.exception;

public class NotFoundEntityException extends LogicException {

    private static final long serialVersionUID = -3794384801974700696L;

    public NotFoundEntityException(String message, Object... args) {
        super(message, args);
    }

    public NotFoundEntityException(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }
}
