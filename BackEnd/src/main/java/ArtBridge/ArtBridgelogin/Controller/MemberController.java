package ArtBridge.ArtBridgelogin.controller;

import ArtBridge.ArtBridgelogin.controller.dto.LoginReturnForm;
import ArtBridge.ArtBridgelogin.controller.dto.member.MemberDto;
import ArtBridge.ArtBridgelogin.controller.dto.member.MemberLoginForm;
import ArtBridge.ArtBridgelogin.service.MemberService;
import ArtBridge.ArtBridgelogin.service.errorMessage.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/members") // 엔드포인트 경로를 일관성 있게 변경
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberDto>> readAllMembers() {
        try {
            List<MemberDto> memberDtos = memberService.readAllMembers();
            if (memberDtos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok().body(memberDtos);
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readMemberById(@PathVariable String id) {
        MemberDto memberDto = memberService.readOne(id);

        if (memberDto != null) {
            return ResponseEntity.ok(memberDto);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberLoginForm memberLoginForm) {
        LoginReturnForm loginResult = memberService.login(memberLoginForm.getId(), memberLoginForm.getPw());

        if (loginResult.getId() != null) {
            return ResponseEntity.ok().body(loginResult);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.");
        }
    }

    @PostMapping("/new")
    public ResponseEntity<?> createMember(@RequestBody MemberDto memberDto) {
        MemberDto createdMember = memberService.createMember(memberDto);

        if (createdMember != null) {
            return ResponseEntity.ok("Member Created" + createdMember);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create member");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable String id, @RequestBody MemberDto updatedMember) {
        MemberDto memberDto = memberService.updateMember(id, updatedMember);

        if (memberDto != null) {
            return ResponseEntity.ok(memberDto);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Member not found with ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable String id) {
        boolean deleted = memberService.deleteMember(id);

        if (deleted) {
            return ResponseEntity.ok("Member deleted");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Member not found with ID: " + id);
        }
    }
}
