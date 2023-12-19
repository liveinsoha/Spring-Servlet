package hello.web.frontController.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.web.frontController.ModelView;
import hello.web.frontController.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paraMap) {
        ModelView modelView = new ModelView("memberList");
        List<Member> members = memberRepository.findAll();
        modelView.addParam("members", members);
        return modelView;
    }
}
