package hello.web.frontController.v5.handler;

import hello.web.frontController.ModelView;
import hello.web.frontController.v3.ControllerV3;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class MyHandlerAdaptorV3 implements MyHandlerAdaptor {
    @Override
    public boolean support(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ControllerV3 controllerV3 = (ControllerV3) handler;
        Map<String, String> params = getParams(request);
        ModelView modelView = controllerV3.process(params);
        return modelView;
    }

    private Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

}
