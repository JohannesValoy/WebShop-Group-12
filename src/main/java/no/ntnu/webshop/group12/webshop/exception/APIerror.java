package no.ntnu.webshop.group12.webshop.exception;


import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;


public class APIerror extends DefaultErrorAttributes {

    private String message;

    private static final ErrorAttributeOptions errorAttributeOptions = ErrorAttributeOptions.defaults();

    public APIerror(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getErrorAttributes(
      WebRequest webRequest) {
        Map<String, Object> errorAttributes = 
          super.getErrorAttributes(webRequest, errorAttributeOptions);
        errorAttributes.put("message", message);
        errorAttributes.put("locale", webRequest.getLocale()
            .toString());
        errorAttributes.remove("error");
        errorAttributes.remove("status");
        return errorAttributes;
    }

    public Map<String, Object> getErrorAttributes() {
        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("message", message);
        return errorAttributes;
    }
}
