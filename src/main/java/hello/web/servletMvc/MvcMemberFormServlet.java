package hello.web.servletMvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String viewPath = "/WEB-INF/views/new-form.jsp"; //이 jsp로 이동을 한다.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);// 컨트롤러에서 뷰로 이동할 때
        dispatcher.forward(request,response); //서블릿에서 jsp를 호출하는 부분
    }
//컨트롤러가 하는 역할이 두드러지지는 않지만 mvc패턴에서는 반드시 컨트롤러를 거쳐 뷰로 이동하는 것을 원칙으로 한다.
    /*
    * 이 코드는 Java에서 작성된 서블릿(Servlet) 클래스의 service 메서드를 보여줍니다. 이 서블릿은 HTTP 요청을 처리하고, 해당 요청에 대한 응답을
    * 생성하는 역할을 합니다.
@Override: 이 어노테이션은 해당 메서드가 부모 클래스나 인터페이스에서 상속받은 메서드를 오버라이드했음을 나타냅니다. 여기서는 service 메서드를 HttpServlet 클래스에서 상속받아 오버라이드한 것입니다.

protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException:
* 이 메서드는 클라이언트로부터의 HTTP 요청을 처리하고, 그에 따른 응답을 생성하는 역할을 합니다. HttpServletRequest 객체는 클라이언트의 요청 정보를
* 담고 있고, HttpServletResponse 객체는 서버에서 클라이언트로 응답을 보내는 데 사용됩니다.

String viewPath = "/WEB-INF/views/new-form/jsp";: 이 부분에서는 클라이언트에게 보여줄 JSP 페이지의 경로를 지정하고 있습니다.
* 일반적으로 JSP 파일은 /WEB-INF 디렉터리 아래에 위치하여 직접적인 접근을 막아 보안을 강화할 수 있습니다.

RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);: 지정된 경로의 JSP 페이지로 포워딩하기 위해
* RequestDispatcher 객체를 생성합니다. RequestDispatcher는 서블릿에서 다른 자원으로 제어를 전달하는 데 사용됩니다.

dispatcher.forward(request, response);: 최종적으로 forward 메서드를 호출하여 현재 요청을 지정된 JSP 페이지로 전달합니다.
* 이렇게 하면 클라이언트는 새로운 경로의 JSP 페이지를 볼 수 있게 됩니다. 이때, request와 response 객체는 그대로 전달되어 JSP
* 페이지에도 이전 서블릿에서 설정한 데이터 및 응답을 사용할 수 있습니다.*/

}
