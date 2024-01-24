package ArtBridge.ArtBridgelogin.domain.member;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "member")
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_seq")
    private int memberSeq;

    @Column(name = "member_id", length = 30, nullable = false)
    private String memberId;

    @Column(name = "member_name", length = 30, nullable = false)
    private String memberName;

    @Column(name = "member_pwd", length = 255, nullable = false)
    private String memberPwd;

    @Column(name = "member_nickname", length = 30, nullable = false)
    private String memberNickname;

    @Column(name = "member_email", length = 50, nullable = false)
    private String memberEmail;

    @Column(name = "member_contact", length = 11, nullable = false)
    private String memberContact;

    @Column(name = "member_point", nullable = false)
    private long memberPoint;

    @Column(name = "member_isdeleted", nullable = false)
    private boolean memberIsDeleted;

    @Column(name = "member_deleted_date")
    private Date memberDeletedDate;

    @Column(name = "member_created_date", nullable = false)
    private Date memberCreatedDate;

}