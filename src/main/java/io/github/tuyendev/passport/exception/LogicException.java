package io.github.tuyendev.passport.exception;

import static io.github.tuyendev.passport.extras.Translator.val;

public class LogicException extends RuntimeException {

    private static final long serialVersionUID = -1100294025073434155L;

    public LogicException(String message, Object... args) {
        super(val(message, args));
    }

    public LogicException(Throwable cause, String message, Object... args) {
        super(val(message, args), cause);
    }

}
