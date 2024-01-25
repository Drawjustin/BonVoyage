package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.member.Member;
import ArtBridge.ArtBridgelogin.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


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

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setMemberName("이동훈");
        member.setMemberId("alexander");
        member.setMemberPwd("1234");
        member.setMemberContact("0102236308");
        member.setMemberEmail("luice0310@naver.com");
        member.setMemberCreatedDate(LocalDateTime.now());
        member.setMemberNickname("송윤재킬러");
        member.setMemberPoint(0);

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setMemberName("이동훈");
        member1.setMemberId("alexander");
        member1.setMemberPwd("1234");
        member1.setMemberContact("0102236308");
        member1.setMemberEmail("luice0310@naver.com");
        member1.setMemberCreatedDate(LocalDateTime.now());
        member1.setMemberNickname("송윤재킬러");
        member1.setMemberPoint(0);

        Member member2 = new Member();
        member2.setMemberName("이동훈");
        member2.setMemberId("alexander");
        member2.setMemberPwd("1234");
        member2.setMemberContact("0102236308");
        member2.setMemberEmail("luice0310@naver.com");
        member2.setMemberCreatedDate(LocalDateTime.now());
        member2.setMemberNickname("송윤재킬러");
        member2.setMemberPoint(0);


        //when
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야 한다!!!

        //then
        assertEquals(member1, member2);
    }
}