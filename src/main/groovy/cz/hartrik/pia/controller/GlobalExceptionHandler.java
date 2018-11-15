package cz.hartrik.pia.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Contains global exception handlers.
 *
 * @version 2017-04-14
 * @author Patrik Harag
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_VIEW = "error";

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handle404()   {
        ModelAndView model = new ModelAndView(ERROR_VIEW);
        model.getModel().put("message", "Page not found!");
        return model;
    }

    @ExceptionHandler({
            MissingServletRequestParameterException.class,
            MissingServletRequestPartException.class,
            ServletRequestBindingException.class,
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class,
            TypeMismatchException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handle400()   {
        ModelAndView model = new ModelAndView(ERROR_VIEW);
        model.getModel().put("message", "Bad request :)");
        return model;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ModelAndView handle405()   {
        ModelAndView model = new ModelAndView(ERROR_VIEW);
        model.getModel().put("message", "Method not allowed!");
        return model;
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ModelAndView handle415()   {
        ModelAndView model = new ModelAndView(ERROR_VIEW);
        model.getModel().put("message", "Unsupported media type!");
        return model;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleUnknownException(RuntimeException e) {
        // server errors :(
        e.printStackTrace(System.err);

        ModelAndView model = new ModelAndView(ERROR_VIEW);
        model.getModel().put("message", "Server error!");
        return model;
    }

//    @ExceptionHandler({ConverterException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ModelAndView handleConverterException(Exception e)   {
//        Throwable cause = e.getCause();
//
//        if (!(cause instanceof StupidClientException)) {
//            // this might be serious!
//            e.printStackTrace(System.err);
//        }
//
//        ModelAndView model = new ModelAndView(ERROR_VIEW);
//        String msg = "Exception while converting: " + cause.getMessage();
//
//        msg = HtmlUtils.htmlEscape(msg);
//        msg = msg.replace("\n", "<br>");
//
//        model.getModel().put("message", msg);
//        return model;
//    }

}
