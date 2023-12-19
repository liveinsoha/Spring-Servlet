package hello.web.springMvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringControllerV3 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    /*
     * 어노테이션 기반의 컨트롤러는 인터페이스로 구현되어 유연하게 설계되어 있기 떄문에 ModelAndView를 리턴해도 되고
     * 문자열을 리턴해도 된다*/


    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";
    }
    /*
     * 어노테이션 기반의 컨트롤러는 인터페이스로 구현되어 유연하게 설계되어 있기 때문에
     * Request, Response를 받을 수 있고 파라미터만 직접 받을 수 있다.!!
     * 자료형 파싱까지 지원해준다.*/

    /*
     * **@RequestParam 사용**
     * 스프링은 HTTP 요청 파라미터를 `@RequestParam` 으로 받을 수 있다.
     * `@RequestParam("username")` 은 `request.getParameter("username")` 와 거의 같은 코드라 생각하면 된다.
     * */

    //Model을 파라미터로 받아서 이 모델에 view로 넘길 데이터를 Key-value형태로 넘겨준다. Model은 들어와서 담기기만 할 뿐,
    // 그리고 DispatcherServlet이 이 handle메서드에서 반환한 Model과 view 정보를 기반으로 ViewResolver와 View를 통하여 렌더링 한다.

    @PostMapping("/save") //@RequestParam value속성이 "age"라면 파라미터명 "age"에 바인딩 된 값을 받을 수 있다.
    public String save(@RequestParam("username") String username, @RequestParam(value = "age") int aged, Model model) {
        Member member = new Member(username, aged);
        memberRepository.save(member);
        model.addAttribute("member", member);
        return "save-result";
    }

    /*
     * 예를 들어서 URL이 `/new-form` 이고, HTTP Method가 GET인 경우를 모두 만족하는 매핑을 하려면 다음과 같이
     * 처리하면 된다.
     * ```java
     * @RequestMapping(value = "/new-form", method = RequestMethod.GET)
     * ```
     * 이것을 `@GetMapping` , `@PostMapping` 으로 더 편리하게 사용할 수 있다.
     * 참고로 Get, Post, Put, Delete, Patch 모두 애노테이션이 준비되어 있다.*/
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "memberList";
    }
}
