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
                .payload(Map.of("message", val("common.message.success"))).build();
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
                .payload(ErrorContent.build(val("common.message.error.validation"), e))
                .build();
    }

    public static Response failed(AccessDeniedException e) {
        return Response.builder().status(HttpStatus.FORBIDDEN.value())
                .metadata(Metadata.errorBlock(e))
                .payload(ErrorContent.build("common.message.error.forbidden-access"))
                .build();
    }

    public static Response failed(HttpRequestMethodNotSupportedException e) {
        return Response.builder().status(HttpStatus.NOT_ACCEPTABLE.value())
                .metadata(Metadata.errorBlock(e))
                .payload(ErrorContent.build("common.message.error.unsupported-method"))
                .build();
    }

    public static Response failed(ServletException e) {
        return Response.builder().status(HttpStatus.BAD_REQUEST.value())
                .metadata(Metadata.errorBlock(e))
                .payload(ErrorContent.build("common.message.error.servlet"))
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
            return "common.message.error.authentication.insufficient";
        }
        if (e instanceof AccountExpiredException) {
            return "common.message.error.authentication.account-expired";
        }
        if (e instanceof CredentialsExpiredException) {
            return "common.message.error.authentication.account-credential-expired";
        }
        if (e instanceof DisabledException) {
            return "common.message.error.authentication.account-disabled";
        }
        if (e instanceof LockedException) {
            return "common.message.error.authentication.account-locked";
        }
        if (e instanceof AccountStatusException) {
            return "common.message.error.authentication.account-inaccessible";
        }
        if (e instanceof BadCredentialsException) {
            return "common.message.error.authentication.bad-credential";
        }
        return "common.message.error.authentication";
    }

    public static Response failed(DataAccessException e) {
        return Response.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .metadata(Metadata.errorBlock(e))
                .payload(ErrorContent.build(val("common.message.error.database")))
                .build();
    }

    public static Response failed(RuntimeException e) {
        return Response.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .metadata(Metadata.errorBlock(e))
                .payload(ErrorContent.build(val("common.message.error.runtime-unhandled")))
                .build();
    }

    public static Response unexpected(Exception e) {
        return Response.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .metadata(Metadata.errorBlock(e))
                .payload(ErrorContent.build(val("common.message.error.unhandled")))
                .build();
    }

    public static Response error(Error e) {
        return Response.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .metadata(Metadata.errorBlock(e))
                .payload(ErrorContent.build(val("common.message.error.system")))
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
