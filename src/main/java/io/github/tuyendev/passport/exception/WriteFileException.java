package io.github.tuyendev.passport.exception;

public class WriteFileException extends LogicException {

    private static final long serialVersionUID = 4472358740814564030L;

    public WriteFileException(String message, Object... args) {
        super(message, args);
    }

    public WriteFileException(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }
}
