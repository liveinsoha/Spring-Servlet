package hello.web.frontController.v1;

import hello.web.frontController.v1.controller.MemberFormControllerV1;
import hello.web.frontController.v1.controller.MemberListControllerV1;
import hello.web.frontController.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV1", urlPatterns = "/front-controller/v1/*")
//이렇게 두면 서버내의 이 url하위의 요청이 들어오면 무조건 이 서블릿이 호출이 된다
public class FrontControllerV1 extends HttpServlet {

    Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        ControllerV1 controller = controllerMap.get(url);
        if(controller==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controller.process(request, response);

    }

    /* 프론트 컨트롤러가 요청에 맞는 컨트롤러를 찾아서 호출한다.
    프론트 컨트롤러(애는 HttpServlet을 상속한다) -> HTTP요청 정보를 HttpServletRequest, HttpServletResponse 객체로 담아야하므로..
    프로트 컨트롤러를 제외한 나머지 컨트롤러는 서블릿을 사용하지 않아도 된다. 프론트 컨트롤러가 http요청을 식별하고 그에 맞는 컨트롤러를 호출하고,
    그 컨트롤러로 response와 request를 넘겨준다
    스프링 웹 MVC의 핵심도 바로 **FrontController**
    스프링 웹 MVC의 **DispatcherServlet**이 FrontController 패턴으로 구현되어 있음
*/

}
