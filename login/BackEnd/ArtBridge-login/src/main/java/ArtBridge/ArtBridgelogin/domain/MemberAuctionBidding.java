package ArtBridge.ArtBridgelogin.domain;

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
    @Column(name = "member_seq", nullable = false)
    private Integer memberSeq;
    @Column(name = "auction_seq",nullable = false)
    private Integer auctionSeq;
    @Column(name = "member_auction_bidding_deposit",length = 255, nullable = false)
    private Integer memberAuctionBiddingDeposit;
    @Column(name = "member_auction_bidding_created_date", nullable = false)
    private LocalDateTime memberAuctionBiddingCreatedDate;
    @Column(name = "member_auction_bidding_deleted_date", nullable = true)
    private LocalDateTime memberAuctionBiddingDeletedDate;
    @Column(name = "member_auction_bidding_isdeleted", nullable = true)
    private boolean memberAuctionBiddingIsdeleted;

}
