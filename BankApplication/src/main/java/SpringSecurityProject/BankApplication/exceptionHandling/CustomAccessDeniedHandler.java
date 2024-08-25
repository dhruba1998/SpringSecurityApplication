package SpringSecurityProject.BankApplication.exceptionHandling;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /*
    This class is used to handled exceptions occurred during authorization is failed like normal user tries to access admin portal.
     */

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        response.setHeader("Bank-Application-Error-Reason", "Authorization failed");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        LocalDateTime localDateTime = LocalDateTime.now();
        String message = (accessDeniedException!=null && accessDeniedException.getMessage()!=null) ? accessDeniedException.getMessage() : "Unauthorized";
        String path = request.getRequestURI();

        String jsonResponse = String.format("{ \n \"timestamp\" : \"%s\",\n \"errorCode\" : %d, \n \"message\" : \"%s\", \n \"path\" : \"%s\", \n}",
                localDateTime,HttpStatus.FORBIDDEN.value(),message,path);

        response.getWriter().write(jsonResponse);

    }
}
