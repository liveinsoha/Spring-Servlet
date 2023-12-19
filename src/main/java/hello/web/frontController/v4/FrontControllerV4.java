package hello.web.frontController.v4;

import hello.web.frontController.ModelView;
import hello.web.frontController.MyView;
import hello.web.frontController.v3.ControllerV3;
import hello.web.frontController.v3.controller.MemberFormControllerV3;
import hello.web.frontController.v3.controller.MemberListControllerV3;
import hello.web.frontController.v3.controller.MemberSaveControllerV3;
import hello.web.frontController.v4.controller.MemberFormControllerV4;
import hello.web.frontController.v4.controller.MemberListControllerV4;
import hello.web.frontController.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "frontControllerV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerV4 extends HttpServlet {

    Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV4 controllerV4 = controllerMap.get(requestURI);
        if (controllerV4 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        /*좋은 프레임워크는 아키텍처도 중요하지만,
        그와 더불어 실제 개발하는 개발자가 단순하고 편리하게 사용할 수 있어야한다. 소위 실용성이 있어야 한다.*/
        /*기존 구조에서 모델을 파라미터로 넘기고, 뷰의 논리 이름을 반환한다는 작은 아이디어를 적용했을 뿐인데,
        컨트롤러를 구현하는 개발자 입장에서 보면 이제 군더더기 없는 코드를 작성할 수 있다.*/
        Map<String, String> paraMap = createParaMap(request);
        Map<String, Object> model = new HashMap<>();
        String viewName = controllerV4.process(paraMap, model);
        MyView myView = viewResolver(viewName);
        myView.render(model, request, response);
    }

    private Map<String, String> createParaMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(name -> paramMap.put(name, request.getParameter(name)));

        return paramMap;
    }

    //컨트롤러는 뷰의 논리이름만을 반환하고 프론트 컨트롤레에서 실제 물리 위치 이름을 처리한다
    private MyView viewResolver(String viewPath) {
        return new MyView("/WEB-INF/views/" + viewPath + ".jsp");
        //뷰의 위치가 변경되어도 컨트롤러를 손 볼 필요는 없다. 공통적으로 viewResolver에서 처리가 가능하기 떄문
    }
}