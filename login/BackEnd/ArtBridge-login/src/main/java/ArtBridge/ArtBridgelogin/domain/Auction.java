package ArtBridge.ArtBridgelogin.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "auction")
@Data
public class Auction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_seq")
    private Integer auctionSeq;



    @Column(name = "auction_scheduled_time", nullable = false)
    private LocalDateTime auctionScheduledTime;

    @Column(name = "auction_status", nullable = false)
    private Integer auctionStatus;

    @Column(name = "auction_start_point", nullable = false)
    private Integer auctionStartPoint;

    @Column(name = "auction_ask_point", nullable = false, columnDefinition = "DEFAULT 10")
    private Integer auctionAskPoint;

    @Column(name = "auction_sell_point")
    private Long auctionSellPoint;

    @Column(name = "auction_winner")
    private Integer auctionWinner;

    @Column(name = "auction_created_date", nullable = false)
    private LocalDateTime auctionCreatedDate;

    @Column(name = "auction_canceled_date")
    private LocalDateTime auctionCanceledDate;

    @Column(name = "auction_ismiscarried", columnDefinition = "DEFAULT false")
    private Boolean auctionIsMiscarried;

    @Column(name = "auction_miscarried_date")
    private LocalDateTime auctionMiscarriedDate;

    @Column(name = "auction_win_date")
    private LocalDateTime auctionWinDate;
}

