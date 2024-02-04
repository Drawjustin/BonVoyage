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

    public Member findOne(Long seq) {
        return memberRepository.findOne(seq);
    }

    public Member createMember(Member member) {
        return memberRepository.create(member);
    }

    @Transactional
    public ResponseEntity<?> findMemberId(String memberId){
        Optional<Member> memberOptional = memberRepository.findMemberById(memberId);

        return memberOptional
                .map(member -> ResponseEntity.ok().body(member))
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public Member updateMember(String id, Member updatedMember) {
        return memberRepository.updateMember(id, updatedMember);
    }
    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> login(@RequestParam("id") String userId, @RequestParam("pw") String password) {
        // 로그인 처리 로직

        Optional<Member> foundMember = memberRepository.findMemberById(userId);

        if (foundMember.isPresent()) {
            Member member = foundMember.get();
            if (member.getMemberPwd().equals(password)) {
                // 로그인 성공 시 Member 정보와 함께 응답
                return ResponseEntity.ok(member);
            }
        }
        // 로그인 실패 시 401 상태 코드와 실패 메시지 응답
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("바보 멍텅구리 로그인 실패했잔요");
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

}
