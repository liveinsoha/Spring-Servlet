package hello.web.frontController.v3;

import hello.web.frontController.ModelView;
import hello.web.frontController.MyView;
import hello.web.frontController.v3.controller.MemberFormControllerV3;
import hello.web.frontController.v3.controller.MemberListControllerV3;
import hello.web.frontController.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerV3 extends HttpServlet {

    Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV3 controllerV3 = controllerMap.get(requestURI);
        if (controllerV3 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Map<String, String> paraMap = createParaMap(request);
        ModelView modelView = controllerV3.process(paraMap);

        MyView myView = viewResolver(modelView.getViewPath());
        myView.render(modelView.getModel(), request, response); //뷰는 렌더링할 때 모델이 필요하므로,모델을 넘긴다.
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

    //공통적으로 FrontController에서 하는 일이 많아졌다. 대신에 각각의 Controller에서 하는 일은 편해졌다.
    //각각 컨트롤러가 뷰로부터 필요한 정보는 파라미터 정보일 뿐이다. response, request정보도 사실 필요없다.
    //파라미터를 받아 비즈니스 로직을 처리하고, 뷰에 전달할 데이터를 ModelView에 담아서 넘긴다.
    // 이떄, ModelView에 담기는 정보는 렌더링할 View의 논리위치와 바인딩할 파라미터 정보이다. atrribute(Model)
    //최종적으로 프론트 컨트롤러는 view의 논리위치와 바인딩 파라미터 정보를 통해 MyView에게 일을 시킨다.


}
