package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.controller.dto.member.MemberDto;
import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void testCreateMember() {
        // 테스트용 MemberDto 생성
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId("testId");
        memberDto.setMemberName("테스트 사용자");
        memberDto.setMemberPwd("testPassword");

        // 테스트용 Member 생성
        Member member = new Member();
        member.setMemberId("testId");
        member.setMemberName("테스트 사용자");
        member.setMemberPwd("testPassword");

        // MemberId로 조회 시 존재하지 않는 경우를 가정하고 설정
        when(memberRepository.findByMemberId("testId")).thenReturn(Optional.empty());
        // create 메서드가 호출될 때 테스트용 Member 반환
        when(memberRepository.create(any(Member.class))).thenReturn(member);

        // 테스트용 MemberDto를 이용해 createMember 메서드 호출
        MemberDto result = memberService.createMember(memberDto);

        // 결과 검증
        assertNotNull(result);
        assertEquals(memberDto.getMemberId(), result.getMemberId());
        assertEquals(memberDto.getMemberName(), result.getMemberName());
        assertEquals(memberDto.getMemberPwd(), result.getMemberPwd());

        // findByMemberId가 1번 호출되었는지 검증
        verify(memberRepository, times(1)).findByMemberId("testId");
        // create가 1번 호출되었는지 검증
        verify(memberRepository, times(1)).create(any(Member.class));
    }

    @Test
    void testReadAllMembers() {
        // 테스트용 Member 리스트 생성
        List<Member> members = new ArrayList<>();
        members.add(new Member());
        members.add(new Member());

        // readAll이 호출될 때 테스트용 Member 리스트 반환
        when(memberRepository.readAll()).thenReturn(members);

        // readAllMembers 메서드 호출
        List<MemberDto> result = memberService.readAllMembers();

        // 결과 검증
        assertNotNull(result);
        assertEquals(2, result.size());

        // readAll이 1번 호출되었는지 검증
        verify(memberRepository, times(1)).readAll();
    }

    @Test
    void testReadOne() {
        // 테스트용 Member 생성
        Member member = new Member();
        member.setMemberId("testId");
        member.setMemberName("테스트 사용자");

        // findByMemberId가 호출될 때 테스트용 Member 반환
        when(memberRepository.findByMemberId("testId")).thenReturn(Optional.of(member));

        // readOne 메서드 호출
        MemberDto result = memberService.readOne("testId");

        // 결과 검증
        assertNotNull(result);
        assertEquals("testId", result.getMemberId());
        assertEquals("테스트 사용자", result.getMemberName());

        // findByMemberId가 1번 호출되었는지 검증
        verify(memberRepository, times(1)).findByMemberId("testId");
    }

    @Test
    void testLogin() {
        // 로그인에 사용될 Member 생성
        Member testMember = new Member();
        testMember.setMemberId("testId");
        testMember.setMemberPwd("testPassword");

        // findByMemberIdAndMemberPwd가 호출될 때 테스트용 Member 반환
        when(memberRepository.findByMemberIdAndMemberPwd("testId", "testPassword")).thenReturn(Optional.of(testMember));

        // login 메서드 호출
        assertTrue(memberService.login("testId", "testPassword"));

        // findByMemberIdAndMemberPwd가 1번 호출되었는지 검증
        verify(memberRepository, times(1)).findByMemberIdAndMemberPwd("testId", "testPassword");
    }

    @Test
    void testUpdateMember() {
        // 업데이트에 사용될 테스트용 MemberDto 생성
        MemberDto updatedMemberDto = new MemberDto();
        updatedMemberDto.setMemberName("업데이트된 이름");

        // 기존에 존재하는 Member를 생성하고 필요한 필드 설정
        Member existingMember = new Member();
        existingMember.setMemberId("testId");
        existingMember.setMemberName("테스트 사용자");

        // findByMemberId가 호출될 때 기존 Member 반환
        when(memberRepository.findByMemberId("testId")).thenReturn(Optional.of(existingMember));
        // updateMember가 호출될 때 업데이트된 Member 반환
        when(memberRepository.updateMember("testId", existingMember)).thenReturn(existingMember);

        // updateMember 메서드 호출
        MemberDto result = memberService.updateMember("testId", updatedMemberDto);

        // 결과 검증
        assertNotNull(result);
        assertEquals("testId", result.getMemberId());
        assertEquals("업데이트된 이름", result.getMemberName());

        // findByMemberId가 1번 호출되었는지 검증
        verify(memberRepository, times(1)).findByMemberId("testId");
        // updateMember가 1번 호출되었는지 검증
        verify(memberRepository, times(1)).updateMember("testId", existingMember);
    }

//    @Test
//    void testDeleteMember() {
//        // 가상의 MemberDto를 생성하여 readOne 메서드의 반환값으로 설정
//        MemberDto mockMemberDto = new MemberDto();
//        mockMemberDto.setMemberId("testId");
//        when(memberService.readOne("testId")).thenReturn(mockMemberDto);
//
//        // deleteByMemberId가 호출될 때 true 반환하도록 설정
//        when(memberRepository.deleteByMemberId("testId")).thenReturn(true);
//
//        // deleteMember 메서드 호출
//        boolean result = memberService.deleteMember("testId");
//
//        // 결과가 true인지 확인
//        assertTrue(result);
//
//        // readOne 메서드가 1번 호출되었는지 검증
//        verify(memberService, times(1)).readOne("testId");
//
//        // deleteByMemberId 메서드가 1번 호출되었는지 검증
//        verify(memberRepository, times(1)).deleteByMemberId("testId");
//    }
}