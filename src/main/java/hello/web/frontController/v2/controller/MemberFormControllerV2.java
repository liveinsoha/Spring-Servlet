package hello.web.frontController.v2.controller;

import hello.web.frontController.MyView;
import hello.web.frontController.v2.ControllerV2;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberFormControllerV2 implements ControllerV2 {


    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) {
        String viewPath = "/WEB-INF/views/new-form.jsp";
       return new MyView(viewPath);
    }
}
