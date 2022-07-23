package io.github.tuyendev.passport.exception;

import io.github.tuyendev.passport.extras.Translator;
import io.github.tuyendev.passport.extras.response.Response;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final HttpServletRequest httpServletRequest;

    public GlobalExceptionHandler(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    private static boolean acceptJson(HttpServletRequest httpServletRequest) {
        final String acceptHeader = httpServletRequest.getHeader("Accept");
        return acceptHeader.contains(MediaType.APPLICATION_JSON_VALUE);
    }

    private static Object handleResponseBasedOnRequest(HttpServletRequest httpServletRequest, Throwable ex) {
        if (acceptJson(httpServletRequest)) {
            return ResponseEntity.internalServerError().body(getJsonResponseBody(ex));
        }
        return getViewResponse(ex);
    }

    private static Response getJsonResponseBody(Throwable ex) {
        if (ex instanceof LogicException) {
            return Response.failed((LogicException) ex);
        }
        if (ex instanceof MethodArgumentNotValidException) {
            return Response.failed((MethodArgumentNotValidException) ex);
        }
        if (ex instanceof AuthenticationException) {
            return Response.failed((AuthenticationException) ex);
        }
        if (ex instanceof DataAccessException) {
            return Response.failed((DataAccessException) ex);
        }
        if (ex instanceof AccessDeniedException) {
            return Response.failed((AccessDeniedException) ex);
        }
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return Response.failed((HttpRequestMethodNotSupportedException) ex);
        }
        if (ex instanceof ServletException) {
            return Response.failed((ServletException) ex);
        }
        if (ex instanceof RuntimeException) {
            return Response.failed((RuntimeException) ex);
        }
        if (ex instanceof Exception) {
            return Response.unexpected((Exception) ex);
        }
        if (ex instanceof Error) {
            return Response.error((Error) ex);
        }
        return null;
    }

    private static ModelAndView getViewResponse(Throwable ex) {
        if (ex instanceof LogicException) {
            return new ModelAndView("/error/400",
                    Map.of("message", Translator.val("app.common.exception.runtime-unhandled",
                            "title", Translator.val("app.common.views.exception.400.title"))));
        }
        if (ex instanceof MethodArgumentNotValidException) {
            return new ModelAndView("/error/400", Map.of("message", Translator.val("app.common.exception.validation",
                    "title", Translator.val("app.common.views.exception.400.title"))));
        }
        if (ex instanceof AuthenticationException) {
            return new ModelAndView("/error/401", Map.of("message", Translator.val(Response.getAuthenticationMessage((AuthenticationException) ex)),
                    "title", Translator.val("app.common.views.exception.401.title")));
        }
        if (ex instanceof DataAccessException) {
            return new ModelAndView("/error/500", Map.of("message", Translator.val("app.common.exception.cannot-access-data"),
                    "title", Translator.val("app.common.views.exception.500.title")));
        }
        if (ex instanceof AccessDeniedException) {
            return new ModelAndView("/error/403", Map.of("message", Translator.val("app.common.exception.forbidden-access"),
                    "title", Translator.val("app.common.views.exception.403.title")));
        }
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return new ModelAndView("/error/404", Map.of("message", Translator.val("app.common.exception.unsupported-method"),
                    "title", Translator.val("app.common.views.exception.404.title")));
        }
        if (ex instanceof ServletException) {
            return new ModelAndView("/error/500", Map.of("message", Translator.val("app.common.exception.servlet"),
                    "title", Translator.val("app.common.views.exception.500.title")));
        }
        if (ex instanceof RuntimeException) {
            return new ModelAndView("/error/500", Map.of("message", Translator.val("app.common.exception.runtime-unhandled"),
                    "title", Translator.val("app.common.views.exception.500.title")));
        }
        if (ex instanceof Exception) {
            return new ModelAndView("/error/500", Map.of("message", Translator.val("app.common.exception.unhandled"),
                    "title", Translator.val("app.common.views.exception.500.title")));
        }
        if (ex instanceof Error) {
            return new ModelAndView("/error/500", Map.of("message", Translator.val("app.common.exception.system"),
                    "title", Translator.val("app.common.views.exception.500.title")));
        }
        return null;
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleRuntimeException(RuntimeException ex) {
        return handleResponseBasedOnRequest(httpServletRequest, ex);
    }

    @ExceptionHandler({LogicException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleBusinessException(LogicException ex) {
        return handleResponseBasedOnRequest(httpServletRequest, ex);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return handleResponseBasedOnRequest(httpServletRequest, ex);
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleAuthenticationException(AuthenticationException ex) {
        return handleResponseBasedOnRequest(httpServletRequest, ex);
    }

    @ExceptionHandler({DataAccessException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleDataAccessException(DataAccessException ex) {
        return handleResponseBasedOnRequest(httpServletRequest, ex);
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Object handleAccessDeniedException(AccessDeniedException ex) {
        return handleResponseBasedOnRequest(httpServletRequest, ex);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Object handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return handleResponseBasedOnRequest(httpServletRequest, ex);
    }

    @ExceptionHandler({ServletException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleServletException(ServletException ex) {
        return handleResponseBasedOnRequest(httpServletRequest, ex);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleUnexpectedException(Exception ex) {
        return handleResponseBasedOnRequest(httpServletRequest, ex);
    }

    @ExceptionHandler({Error.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleSystemError(Error ex) {
        return handleResponseBasedOnRequest(httpServletRequest, ex);
    }
}
