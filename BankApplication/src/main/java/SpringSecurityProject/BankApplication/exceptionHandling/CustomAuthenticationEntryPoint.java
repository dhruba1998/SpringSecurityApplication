package SpringSecurityProject.BankApplication.exceptionHandling;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /* This class will be used when an exception occurred during authentication like user not found or password mismatch
        The best message to display during such exception is "Bad Credential"
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        response.setHeader("Bank-Application-Error-Reason", "Authentication failed");
//        response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        LocalDateTime localDateTime = LocalDateTime.now();
        String message = (authException!=null && authException.getMessage()!=null) ? authException.getMessage() : "Unauthenticated";
        String path = request.getRequestURI();

        String jsonResponse = String.format("{ \n \"timestamp\" : \"%s\",\n \"errorCode\" : %d, \n \"message\" : \"%s\", \n \"path\" : \"%s\", \n}",
                localDateTime,HttpStatus.UNAUTHORIZED.value(),message,path);

        response.getWriter().write(jsonResponse);


    }
}


