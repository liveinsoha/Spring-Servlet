package hello.servlet.basic;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setAge(20);
        helloData.setUsername("lee");

        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);

    }
}
/*
ChatGPT
이 코드는 Java Servlet을 사용하여 JSON 형식의 응답을 생성하는 예제입니다. 코드는 서블릿 클래스인 ResponseJsonServlet을 정의하고 있습니다. 여기서 HelloData 클래스를 사용하여 데이터를 만들고 Jackson 라이브러리의 ObjectMapper를 사용하여 JSON 문자열로 변환한 다음, 이를 HttpServletResponse를 통해 클라이언트로 보내고 있습니다.

코드의 각 부분에 대한 설명은 다음과 같습니다:

@WebServlet 어노테이션:

@WebServlet 어노테이션은 서블릿을 선언하기 위한 것으로, 서블릿 이름과 URL 패턴을 지정합니다.
name 속성은 서블릿의 이름을 지정하고, urlPatterns 속성은 이 서블릿이 처리할 URL 패턴을 지정합니다.
위 코드에서는 "responseJsonServlet"이라는 이름으로 "/response-json" URL 패턴을 처리하는 서블릿을 정의하고 있습니다.
ResponseJsonServlet 클래스:

이 클래스는 HttpServlet을 상속받아 서블릿을 정의합니다.
objectMapper 변수는 Jackson 라이브러리의 ObjectMapper 클래스로 JSON과 Java 객체 간의 변환을 담당하는데 사용됩니다.
service 메서드:

HttpServlet 클래스에서 상속받은 service 메서드를 오버라이딩합니다. 이 메서드는 클라이언트의 요청을 처리하는 로직을 포함합니다.
HttpServletRequest 객체는 클라이언트의 HTTP 요청 정보를 제공하며, HttpServletResponse 객체는 서블릿이 생성한 응답을 클라이언트에게 전송하는 데 사용됩니다.
응답 설정:

response.setContentType("application/json"): 응답의 컨텐츠 타입을 JSON으로 설정합니다.
response.setCharacterEncoding("utf-8"): 응답의 문자 인코딩을 UTF-8로 설정합니다.
데이터 생성 및 JSON 변환:

HelloData 객체를 생성하고, 이 객체의 데이터를 설정합니다 (나이는 20, 사용자 이름은 "lee").
objectMapper.writeValueAsString(helloData): HelloData 객체를 JSON 문자열로 변환합니다.
응답 전송:

response.getWriter().write(result): 생성된 JSON 문자열을 응답으로 클라이언트에게 전송합니다.
이 코드는 간단한 형태의 JSON 응답을 생성하는 서블릿을 보여주고 있습니다. 클라이언트가 "/response-json" 경로로 요청을 보내면, 서블릿은 JSON 형식의 응답을 생성하여 반환합니다.

 */