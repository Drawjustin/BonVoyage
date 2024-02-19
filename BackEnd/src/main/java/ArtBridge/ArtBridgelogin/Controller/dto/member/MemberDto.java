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

}