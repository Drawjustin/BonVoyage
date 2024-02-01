package ArtBridge.ArtBridgelogin.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "auction_point_detail")
@Data
public class AuctionPointDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_point_detail_seq")
    private Integer auctionPointDetailSeq;

    @Column(name = "auction_seq", nullable = false)
    private Integer auctionSeq;

    @Column(name = "member_seq", nullable = false)
    private Integer memberSeq;

    @Column(name = "auction_point_detail_point", nullable = false)
    private Integer auctionPointDetailPoint;

    @Column(name = "auction_point_detail_iswin", nullable = false)
    private Boolean auctionPointDetailIsWin;

    @Column(name = "auction_point_date", nullable = false)
    private LocalDateTime auctionPointDate;

}