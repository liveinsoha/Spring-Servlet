package hello.web.frontController.v3;

import hello.web.frontController.ModelView;

import java.util.Map;
import java.util.Objects;

public interface ControllerV3 {

    ModelView process(Map<String, String > paraMap);

    //컨트롤러 입장에서 요청 파라미터 정보가 필요한 것이지 request, response가 필요한 것이 아니기 때문에,
    //컨트롤러에서 response와 request 종속성을 없앴다.
    //이 구조라면 서블릿 기술을 몰라도 controller를 호출할 수 있다!


}
