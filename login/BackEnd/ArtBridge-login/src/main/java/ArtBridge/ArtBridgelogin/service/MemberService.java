package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

    public Member createMember(Member member) {
        return memberRepository.create(member);
    }

    @Transactional
    public Member updateMember(Long id, Member updatedMember) {
        Member existingMember = memberRepository.findOne(id);

        if (existingMember != null) {
            // 업데이트할 정보를 새로운 정보로 설정
            existingMember.setMemberName(updatedMember.getMemberName());
            existingMember.setMemberPwd(updatedMember.getMemberPwd());
            existingMember.setMemberNickname(updatedMember.getMemberNickname());
            existingMember.setMemberEmail(updatedMember.getMemberEmail());
            existingMember.setMemberContact(updatedMember.getMemberContact());
            existingMember.setMemberPoint(updatedMember.getMemberPoint());
            existingMember.setMemberIsDeleted(updatedMember.isMemberIsDeleted());
            existingMember.setMemberDeletedDate(updatedMember.getMemberDeletedDate());
            existingMember.setMemberCreatedDate(updatedMember.getMemberCreatedDate());

            // 저장
            memberRepository.create(existingMember);
            return existingMember;
        } else {
            // 예외 처리 또는 적절한 로직 추가
            return null;
        }
    }
    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> login(@RequestParam("id") String userId, @RequestParam("pw") String password) {
        // 로그인 처리 로직

        Member foundMember = memberRepository.findMemberId(userId);

        if (foundMember != null && foundMember.getMemberPwd().equals(password)) {
            // 로그인 성공 시 Member 정보와 함께 응답
            return ResponseEntity.ok(foundMember);
        } else {
            // 로그인 실패 시 401 상태 코드와 실패 메시지 응답
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("바보 멍텅구리 로그인 실패했잔요");
        }
    }
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

}
