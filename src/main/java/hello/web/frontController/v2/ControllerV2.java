package hello.web.frontController.v2;

import hello.web.frontController.MyView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ControllerV2 {

    public MyView process(HttpServletRequest request, HttpServletResponse response);
}
