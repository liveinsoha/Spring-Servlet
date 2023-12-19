package hello.member;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clear();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setUsername("kim");
        member.setAge(20);

        Member save = memberRepository.save(member);

        Member result = memberRepository.findOne(member.getId());

        assertThat(save).isEqualTo(result);
    }

    @Test
    void findAll() {
        Member member1 = new Member();
        member1.setUsername("kim");
        member1.setAge(20);

        Member member2 = new Member();
        member2.setUsername("lee");
        member2.setAge(30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
    }
}