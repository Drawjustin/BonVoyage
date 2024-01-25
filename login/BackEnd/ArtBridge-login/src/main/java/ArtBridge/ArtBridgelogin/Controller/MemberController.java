package ArtBridge.ArtBridgelogin.Controller;

import ArtBridge.ArtBridgelogin.domain.member.Member;
import ArtBridge.ArtBridgelogin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/members") // 엔드포인트 경로를 일관성 있게 변경
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/new") // 엔드포인트 경로를 일관성 있게 변경
    public ResponseEntity<String> create(@RequestBody MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            // 유효성 검사 오류가 있다면, 잘못된 요청 상태를 반환
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효성 검사 오류");
        }

        // MemberForm에서 Member 엔티티로 변환
        Member member = convertToEntity(form);

        // JPA를 통해 회원 저장
        memberService.join(member);

        // 회원이 성공적으로 생성되었다면, 성공 상태를 반환
        return ResponseEntity.status(HttpStatus.CREATED).body("회원이 성공적으로 생성되었습니다");
    }

    // MemberForm에서 Member 엔티티로 변환하는 메서드
    private Member convertToEntity(MemberForm form) {
        Member member = new Member();
        member.setMemberName(form.getName());
        member.setMemberId(form.getId());
        member.setMemberPwd(form.getPw());
        member.setMemberNickname(form.getNickname());
        member.setMemberEmail(form.getEmail());
        member.setMemberContact(form.getPhonenumber());
        member.setMemberPoint(0);
        member.setMemberCreatedDate(LocalDateTime.now());
        return member;
    }

    @GetMapping("/list") // 엔드포인트 경로를 일관성 있게 변경
    public List<Member> list() {
        return memberService.findMembers();
    }
}
