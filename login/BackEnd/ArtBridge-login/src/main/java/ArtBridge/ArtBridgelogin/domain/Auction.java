package ArtBridge.ArtBridgelogin.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "auction")
@Data
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_seq")
    private Long auctionSeq;

    @Column(name = "item_seq")
    private Long itemSeq;

    @Column(name = "auction_scheduled_time", length = 30, nullable = false)
    private String itemName;
    @Column(name = "auction_status", length = 30, nullable = false)
    private String itemName;
    @Column(name = "auction_start_point", length = 30, nullable = false)
    private String itemName;
    @Column(name = "auction_ask_point", length = 30, nullable = false)
    private String itemName;
    @Column(name = "auction_sell_point", length = 30, nullable = false)
    private String itemName;
    @Column(name = "auction_winner", length = 30, nullable = false)
    private String itemName;
    @Column(name = "auction_created_date", length = 30, nullable = false)
    private String itemName;
    @Column(name = "auction_canceled_date", length = 30, nullable = false)
    private String itemName;
    @Column(name = "auction_ismiscarried", length = 30, nullable = false)
    private String itemName;
    @Column(name = "auction_miscarried_date", length = 30, nullable = false)
    private String itemName;
    @Column(name = "auction_win_date", length = 30, nullable = false)
    private String itemName;






}
