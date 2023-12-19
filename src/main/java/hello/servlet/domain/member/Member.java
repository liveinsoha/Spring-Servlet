package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Member {

    Long id;
    String username;
    int age;

    public Member(String username, int age){
        this.username = username;
        this.age=age;
    }
}
