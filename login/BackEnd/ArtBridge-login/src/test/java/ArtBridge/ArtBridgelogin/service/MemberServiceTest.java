package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.member.Member;
import ArtBridge.ArtBridgelogin.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    private Member createMember(String name, String id, String pwd, String contact, String email, String nickname, int point) {
        Member member = new Member();
        member.setMemberName(name);
        member.setMemberId(id);
        member.setMemberPwd(pwd);
        member.setMemberContact(contact);
        member.setMemberEmail(email);
        member.setMemberCreatedDate(LocalDateTime.now());
        member.setMemberNickname(nickname);
        member.setMemberPoint(point);
        return member;
    }

    private Member member;

    @BeforeEach
    public void setUp() {
        member = createMember("이동훈", "alexander", "1234", "0102236308", "luice0310@naver.com", "송윤재킬러", 0);
    }

    @Test
    public void 회원가입() throws Exception {
        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        memberService.join(member);

        //when & then
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member);
        });
    }
}
