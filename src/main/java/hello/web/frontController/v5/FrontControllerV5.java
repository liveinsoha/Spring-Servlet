package hello.web.frontController.v5;

import hello.web.frontController.ModelView;
import hello.web.frontController.MyView;
import hello.web.frontController.v3.ControllerV3;
import hello.web.frontController.v3.controller.MemberFormControllerV3;
import hello.web.frontController.v3.controller.MemberListControllerV3;
import hello.web.frontController.v3.controller.MemberSaveControllerV3;
import hello.web.frontController.v4.controller.MemberFormControllerV4;
import hello.web.frontController.v4.controller.MemberListControllerV4;
import hello.web.frontController.v4.controller.MemberSaveControllerV4;
import hello.web.frontController.v5.handler.MyHandlerAdaptor;
import hello.web.frontController.v5.handler.MyHandlerAdaptorV3;
import hello.web.frontController.v5.handler.MyHandlerAdaptorV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerV5 extends HttpServlet {

    List<MyHandlerAdaptor> myHandlerAdaptors = new ArrayList<>();
    Map<String, Object> handlerMap = new HashMap<>();

    public FrontControllerV5() {
        initHandlerAdaptors();
        initHandlerMap();
    }

    private void initHandlerAdaptors() {
        myHandlerAdaptors.add(new MyHandlerAdaptorV3());
        myHandlerAdaptors.add(new MyHandlerAdaptorV4());
    }

    private void initHandlerMap() {
        handlerMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        handlerMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
        handlerMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = getHandler(request);
        MyHandlerAdaptor handlerAdaptor = (MyHandlerAdaptor) getHandlerAdaptor(handler);
        ModelView modelView = handlerAdaptor.handle(request, response, handler);
        MyView myView = viewResolver(modelView.getViewPath());
        myView.render(modelView.getModel(), request, response);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        Object handler = handlerMap.get(requestURI);
        return handler;
    }

    private Object getHandlerAdaptor(Object handler) {
        MyHandlerAdaptor handlerAdaptor = myHandlerAdaptors.stream()
                .filter(myHandlerAdaptor -> myHandlerAdaptor.support(handler))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("없음"));
        return handlerAdaptor;
    }

    private MyView viewResolver(String viewPath) {
        return new MyView("/WEB-INF/views/" + viewPath + ".jsp");
        //뷰의 위치가 변경되어도 컨트롤러를 손 볼 필요는 없다. 공통적으로 viewResolver에서 처리가 가능하기 떄문
    }
}
