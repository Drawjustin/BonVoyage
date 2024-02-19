package ArtBridge.ArtBridgelogin.domain;

import ArtBridge.ArtBridgelogin.domain.Connection.AuctionLike;
import ArtBridge.ArtBridgelogin.domain.Connection.SaleLike;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.*;
import java.util.List;

@Entity
@Table(name = "member")
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_seq")
    private Long memberSeq;

    @Column(name = "is_artist")
    private boolean isArtist;

    @Column(name = "member_id", length = 30, nullable = false)
    private String memberId;

    @Column(name = "member_name", length = 30, nullable = false)
    private String memberName;

    @Column(name = "member_pwd", length = 30, nullable = false)
    private String memberPwd;

    @Column(name = "member_nickname", length = 30, nullable = false)
    private String memberNickname;

    @Column(name = "member_email", length = 50, nullable = false)
    private String memberEmail;

    @Column(name = "member_contact", length = 11, nullable = false)
    private String memberContact;

    @Column(name = "member_point", nullable = false)
    private Long memberPoint;

    @Column(name = "member_isdeleted", nullable = false)
    private boolean memberIsDeleted;

    @Column(name = "member_deleted_date")
    private LocalDateTime memberDeletedDate;

    @Column(name = "member_created_date", nullable = false)
    private LocalDateTime memberCreatedDate;

    //    ----------------------------------------------------

    // One-to-Many relationship with Review
    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Review> reviews;

    // One-to-Many relationship with OrderDetail
    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<OrderDetail> orderDetails;

    // One-to-Many relationship with ArtistHomepageComment
    @OneToMany(mappedBy = "member")
    @JsonManagedReference("artist")
    private List<ArtistHomepageComment> artistHomepageComments;

    // One-to-Many relationship with ArtistMentionComment
    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<ArtistMentionComment> artistMentionComments;

    // One-to-Many relationship with Wish
    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Wish> wishes;

    // One-to-Many relationship with SaleLike
    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<SaleLike> saleLikes;

    // One-to-Many relationship with AuctionLike
    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<AuctionLike> auctionLikes;

    // One-to-Many relationship with MemberAuctionBidding
    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<MemberAuctionBidding> memberAuctionBiddings;

    // One-to-Many relationship with AuctionPointDetail
    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<AuctionPointDetail> auctionPointDetails;
}