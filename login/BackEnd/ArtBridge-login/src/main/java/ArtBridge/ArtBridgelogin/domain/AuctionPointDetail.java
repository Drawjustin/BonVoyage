package ArtBridge.ArtBridgelogin.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "auction_point_detail")
<<<<<<< HEAD
public class AuctionPointDetail {
=======
public class AuctionPointDetail{
>>>>>>> a1e172f1635e08154339f50868515d94ec85465d

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_point_detail_seq")
    private Integer auctionPointDetailSeq;

    @Column(name = "auction_point_detail_point", nullable = false)
    private Integer auctionPointDetailPoint;

    @Column(name = "auction_point_detail_iswin", nullable = false)
    private Boolean auctionPointDetailIsWin;

    @Column(name = "auction_point_date", nullable = false)
    private LocalDateTime auctionPointDate;

    //    ----------------------------------------------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "member_seq")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "auction_seq")
    private Auction auction;

}
