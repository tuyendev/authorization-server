package io.github.tuyendev.passport.extras.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.tuyendev.passport.exception.LogicException;
import lombok.*;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.ServletException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static io.github.tuyendev.passport.extras.Translator.val;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 3807699489176814824L;

    private int status;

    private Metadata metadata;

    private T payload;

    public static Response ok() {
        return Response.builder()
                .status(HttpStatus.OK.value())
                .metadata(Metadata.successBlock())
                .payload(Map.of("message", val("app.common.success"))).build();
    }

    public static <T> Response ok(T payload) {
        return Response.builder()
                .status(HttpStatus.OK.value())
                .metadata(Metadata.successBlock())
                .payload(payload).build();
    }

    public static Response failed(LogicException e) {
        return Response.builder().status(HttpStatus.BAD_REQUEST.value())
                .metadata(Metadata.errorBlock(e))
                .payload(ErrorContent.build(e.getMessage()))
                .build();
    }

    public static Response failed(MethodArgumentNotValidException e) {
        return Response.builder().status(HttpStatus.BAD_REQUEST.value())
                .metadata(Metadata.errorBlock(e))
                .payload(ErrorContent.build(val("app.common.exception.validation"), e))
                .build();
    }

    public static Response failed(AccessDeniedException e) {
        return Response.builder().status(HttpStatus.FORBIDDEN.value())
                .metadata(Metadata.errorBlock(e))
                .payload(ErrorContent.build("app.common.exception.forbidden-access"))
                .build();
    }

    public static Response failed(HttpRequestMethodNotSupportedException e) {
        return Response.builder().status(HttpStatus.NOT_ACCEPTABLE.value())
                .metadata(Metadata.errorBlock(e))
                .payload(ErrorContent.build("app.common.exception.unsupported-method"))
                .build();
    }

    public static Response failed(ServletException e) {
        return Response.builder().status(HttpStatus.BAD_REQUEST.value())
                .metadata(Metadata.errorBlock(e))
                .payload(ErrorContent.build("app.common.exception.servlet"))
                .build();
    }

    public static Response failed(AuthenticationException e) {
        return Response.builder().status(HttpStatus.UNAUTHORIZED.value())
                .metadata(Metadata.errorBlock(e))
                .payload(ErrorContent.build(val(getAuthenticationMessage(e)), e.getMessage()))
                .build();
    }

    private static String getAuthenticationMessage(AuthenticationException e) {
        if (e instanceof InsufficientAuthenticationException) {
            return "app.common.exception.authentication.insufficient";
        }
        if (e instanceof AccountExpiredException) {
            return "app.common.exception.authentication.account-expired";
        }
        if (e instanceof CredentialsExpiredException) {
            return "app.common.exception.authentication.account-credential-expired";
        }
        if (e instanceof DisabledException) {
            return "app.common.exception.authentication.account-disabled";
        }
        if (e instanceof LockedException) {
            return "app.common.exception.authentication.account-locked";
        }
        if (e instanceof AccountStatusException) {
            return "app.common.exception.authentication.account-inaccessible";
        }
        if (e instanceof BadCredentialsException) {
            return "app.common.exception.authentication.bad-credential";
        }
        return "app.common.exception.authentication";
    }

    public static Response failed(DataAccessException e) {
        return Response.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .metadata(Metadata.errorBlock(e))
                .payload(ErrorContent.build(val("app.common.exception.cannot-access-data"), e.getMessage()))
                .build();
    }


    public static Response failed(RuntimeException e) {
        return Response.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .metadata(Metadata.errorBlock(e))
                .payload(ErrorContent.build(val("app.common.exception.runtime-unhandled")))
                .build();
    }

    public static Response unexpected(Exception e) {
        return Response.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .metadata(Metadata.errorBlock(e))
                .payload(ErrorContent.build(val("app.common.exception.unhandled")))
                .build();
    }

    public static Response error(Error e) {
        return Response.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .metadata(Metadata.errorBlock(e))
                .payload(ErrorContent.build(val("app.common.exception.system")))
                .build();
    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public final static class ErrorContent implements Serializable {

        private String message;

        private Object details;

        public static ErrorContent build(String message, MethodArgumentNotValidException ex) {
            return new ErrorContent(message, getFailedValidationFields(ex));
        }

        private static Map<String, String> getFailedValidationFields(
                MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return errors;
        }

        public static ErrorContent build(String message) {
            return new ErrorContent(message, null);
        }

        public static ErrorContent build(String message, Object details) {
            return new ErrorContent(message, details);
        }
    }
}
