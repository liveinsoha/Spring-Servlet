package hello.web.frontController.v5.handler;

import hello.web.frontController.ModelView;
import hello.web.frontController.v4.ControllerV4;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class MyHandlerAdaptorV4 implements MyHandlerAdaptor {
    @Override
    public boolean support(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ControllerV4 controllerV4 = (ControllerV4) handler;
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        String viewPath = controllerV4.process(paramMap, model);

        ModelView modelView = new ModelView(viewPath);
        addModel(modelView, model);
        return modelView;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    private void addModel(ModelView modelView, Map<String, Object> model) {
        model.forEach(modelView::addParam);
    }
}
