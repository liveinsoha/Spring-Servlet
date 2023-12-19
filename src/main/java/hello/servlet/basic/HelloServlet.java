package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") //서블릿 이름과 url매핑은 겹치면 안된다
public class HelloServlet extends HttpServlet {

    @Override//해당 url로 들어오면 service 메서드가 실행된다.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //HttpServletRequest는 표준 인터페이스이고 이를 구현한 구현체들을 톰캣에서 구현해서 넣어준다.

        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String param = request.getParameter("param");
        System.out.println("param = " + param);

        response.setContentType("text/plain"); //http메시지 헤더에 들어가는 설정 내용
        response.setCharacterEncoding("utf-8");

        response.getWriter().write("hello " + param); //http 메시지 바디에 들어가는 내용
    }
    //서블릿의 역할은 우리가 http메시지를 파싱해서 직접 파라미터 값을 추출한다는 건 꽤나 번거로운 작업인데 이것을 대신해준다.
}
