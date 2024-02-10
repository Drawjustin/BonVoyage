package ArtBridge.ArtBridgelogin.controller.dto.member;

import ArtBridge.ArtBridgelogin.domain.Member;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
public class MemberDto {

    private Long memberSeq;
    private boolean isArtist;
    private String memberId;
    private String memberName;
    private String memberPwd;
    private String memberNickname;
    private String memberEmail;
    private String memberContact;
    private Long memberPoint;
    private boolean memberIsDeleted;
    private LocalDateTime memberDeletedDate;
    private LocalDateTime memberCreatedDate;

    public MemberDto() {
        // 기본 생성자
    }

    public MemberDto(Member member) {
        // Member 객체를 MemberDto로 매핑하는 생성자
        this.memberSeq = member.getMemberSeq();
        this.isArtist = member.isArtist();
        this.memberId = member.getMemberId();
        this.memberName = member.getMemberName();
        this.memberPwd = member.getMemberPwd();
        this.memberNickname = member.getMemberNickname();
        this.memberEmail = member.getMemberEmail();
        this.memberContact = member.getMemberContact();
        this.memberPoint = member.getMemberPoint();
        this.memberIsDeleted = member.isMemberIsDeleted();
        this.memberDeletedDate = member.getMemberDeletedDate();
        this.memberCreatedDate = member.getMemberCreatedDate();
    }
}