package ArtBridge.ArtBridgelogin.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="member_auction_bidding")
@Data
public class MemberAuctionBidding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_auction_bidding_seq", nullable = false)
    private Long memberAuctionBiddingSeq;

    @Column(name = "member_auction_bidding_deposit",length = 255, nullable = false)
    private Integer memberAuctionBiddingDeposit;

    @Column(name = "member_auction_bidding_created_date", nullable = false)
    private LocalDateTime memberAuctionBiddingCreatedDate;

    @Column(name = "member_auction_bidding_deleted_date", nullable = true)
    private LocalDateTime memberAuctionBiddingDeletedDate;

    @Column(name = "member_auction_bidding_isdeleted", nullable = true)
    private boolean memberAuctionBiddingIsdeleted;

    //    ----------------------------------------------------

    // Many-to-One relationship with Member
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "member_seq")
    private Member member;

    // Many-to-One relationship with Auction
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "auction_seq")
    private Auction auction;
}
