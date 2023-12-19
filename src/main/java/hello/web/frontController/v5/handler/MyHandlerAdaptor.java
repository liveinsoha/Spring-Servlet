package hello.web.frontController.v5.handler;

import hello.web.frontController.ModelView;
import hello.web.frontController.v3.ControllerV3;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public interface MyHandlerAdaptor {

    boolean support(Object handler);

    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler);
}
