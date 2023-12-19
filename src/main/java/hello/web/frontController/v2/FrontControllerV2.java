package hello.web.frontController.v2;

import hello.web.frontController.v2.controller.MemberFormControllerV2;
import hello.web.frontController.v2.controller.MemberListControllerV2;
import hello.web.frontController.v2.controller.MemberSaveControllerV2;
import hello.web.frontController.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerV2 extends HttpServlet {

    Map<String,ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/memberList", new MemberListControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV2 controllerV2 = controllerMap.get(requestURI);
        if(controllerV2 == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView myView = controllerV2.process(request, response);
        myView.render(request,response);
    }
}
