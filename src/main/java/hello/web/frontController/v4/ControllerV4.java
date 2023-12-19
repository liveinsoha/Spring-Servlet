package hello.web.frontController.v4;

import java.util.Map;

public interface ControllerV4 {

    String process(Map<String, String> paramMap, Map<String, Object> model);
    //기존에는 ModelView를 반환하던 것에서 model을 받아서 여기에 값을 매핑하여 넣는다.
    //그리고 렌더링할 view의 이름만 리턴한다.
}
