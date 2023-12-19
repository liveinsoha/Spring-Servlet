package hello.web.servletMvc;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);
        memberRepository.save(member);

        request.setAttribute("member", member); //request내부에 데이터를 저장할 수 있다. 모델에 데이터를 보관
        //여기 모델에 저장한 데이터는 jsp파일에서 request.getAttribute로 꺼낼 수 있고, 반환 타입은 Object이다.
        //이러면 코드가 많이 길어지기 떄문에 jsp에서 ${member.id}식으로 모델에 저장된 값을 조회할 수 있다.
        //프로퍼티접금근법이고 ${member.username}이면 -> getUsername을 호출하여 접근하는 방식이다.

        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
    //이 부분은 브라우저로부터 저장 입력 결과를 보여달라는 action ="save", method="post"라는 요청을 받아.
    //보여주는 역할까지는 하지 않고, 저장하고 모델에 저장 객체의 정보를 담아 view(jsp파일)로 넘긴다.
    //view는 서블릿으로부터 모델을 받아 보델에 담긴 데이터를 그거로 화면을 출력한다.
}
