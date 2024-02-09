package ArtBridge.ArtBridgelogin.Controller;

import java.util.*;

import ArtBridge.ArtBridgelogin.Controller.form.MemberLoginForm;
import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/members") // 엔드포인트 경로를 일관성 있게 변경
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> readAllMembers() {
        return memberService.readAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readMemberById(@PathVariable String id) {
        return memberService.readMemberId(id);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody MemberLoginForm memberLoginForm) {
        System.out.println(memberLoginForm);
        return memberService.login(memberLoginForm.getId(),memberLoginForm.getPw());}

    @PostMapping("/new")
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable String id, @RequestBody Member updatedMember) {
        return memberService.updateMember(id, updatedMember);
    }
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}
