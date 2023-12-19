package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("[파라미터 조회]");
        Enumeration<String> parameterNames = request.getParameterNames();
        parameterNames
                .asIterator().
                forEachRemaining(parameterName -> System.out.println(parameterName + " = " + request.getParameter(parameterName)));

        System.out.println();

        String name1 = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("name1 = " + name1);
        System.out.println("age = " + age);

        System.out.println();

        String[] names = request.getParameterValues("username");
        for (String name : names) {
            System.out.println("request = " + name);
        }

        response.getWriter().write("ok");
    }
}
