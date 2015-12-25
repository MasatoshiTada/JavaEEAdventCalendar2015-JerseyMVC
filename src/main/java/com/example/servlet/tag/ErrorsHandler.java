package com.example.servlet.tag;

import org.glassfish.jersey.server.validation.ValidationError;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by tada on 2015/12/24.
 */
public class ErrorsHandler extends SimpleTagSupport {

    private String errorClass;

    public void setErrorClass(String errorClass) {
        this.errorClass = errorClass;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        Object model = getJspContext().findAttribute("model");

        if (model instanceof Exception) {
            out.println("<ul class=\"" + errorClass + "\">");
            Exception exception = (Exception) model;
            out.println("<li>");
            out.println(exception.getMessage());
            out.println("</li>");
            out.println("</ul>");
        } else if (isValidationErrorList(model)) {
            out.println("<ul class=\"" + errorClass + "\">");
            List<ValidationError> validationErrors = (List<ValidationError>) model;
            for (ValidationError error : validationErrors) {
                out.println("<li>");
                out.println(error.getMessage());
                out.println("</li>");
            }
            out.println("</ul>");
        }

    }

    private boolean isValidationErrorList(Object model) {
        if (model instanceof List) {
            List list = (List) model;
            if ( ! list.isEmpty()) {
                Object firstElement = list.get(0);
                if (firstElement instanceof ValidationError) {
                    return true;
                }
            }
        }
        return false;
    }
}
