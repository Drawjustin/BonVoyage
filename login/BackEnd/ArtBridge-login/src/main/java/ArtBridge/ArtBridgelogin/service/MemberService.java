package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Member findOne(Long seq) {
        return memberRepository.findOne(seq);
    }

    @Transactional
    public Member createMember(Member member) {
        return memberRepository.create(member);
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public ResponseEntity<?> findMemberId(String memberId){
        return memberRepository.findMemberById(memberId);
    }

    @Transactional
    public Member updateMember(String id, Member updatedMember) {
        return memberRepository.updateMember(id, updatedMember);
    }

    @Transactional
    public ResponseEntity<?> login(@RequestParam("id") String userId, @RequestParam("pw") String password) {
        Member member = (Member) memberRepository.findMemberById(userId).getBody();

        if (member != null) {
            if (member.getMemberPwd().equals(password)) {
                // 로그인 성공 시 Member 정보와 함께 응답
                return ResponseEntity.ok(member);
            } else {
                // 비밀번호가 일치하지 않을 경우 401 상태 코드와 실패 메시지 응답
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 일치하지 않습니다");
            }
        } else {
            // 회원을 찾을 수 없을 경우 401 상태 코드와 실패 메시지 응답
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("회원을 찾을 수 없습니다");
        }
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

}
