package ArtBridge.ArtBridgelogin.domain.Connection;

import ArtBridge.ArtBridgelogin.domain.Auction;
import ArtBridge.ArtBridgelogin.domain.Member;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Entity
@Table(name="auction_like")
@Data
public class AuctionLike {

//    @Column(name = "auction_seq", nullable = false)
//    private Integer auctionSeq;
//
//    @Column(name = "member_seq", nullable = false)
//    private Long    memberSeq;

    // Many-to-One relationship with Member
    @Id
    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;

    // Many-to-One relationship with Auction
    @Id
    @ManyToOne
    @JoinColumn(name = "auction_seq")
    private Auction auction;

}
