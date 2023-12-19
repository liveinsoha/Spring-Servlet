package hello.web.frontController.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV1 {

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
