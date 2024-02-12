package ArtBridge.ArtBridgelogin.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_detail")
@Data
public class OrderDetail{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_seq")
    private Long orderDetailSeq;

    @Column(name = "sorting_code_detail_seq", nullable = false)
    private Integer sortingCodeDetailSeq;

    @Column(name = "order_detail_totalpoint", nullable = false)
    private Long orderDetailTotalPoint;

    @Column(name = "order_detail_date", nullable = false)
    private LocalDateTime orderDetailDate;

    @Column(name = "order_detail_address", nullable = false, length = 255)
    private String orderDetailAddress;

    @Column(name = "order_detail_phonenumber", nullable = false, length = 11)
    private String orderDetailPhoneNumber;

    @Column(name = "order_detail_delivery_point", nullable = false)
    private Integer orderDetailDeliveryPoint;

    @Column(name = "order_detail_contract", nullable = false, length = 1000)
    private String orderDetailContract;

    @Column(name = "order_detail_commission", nullable = false)
    private Integer orderDetailCommission;

    @Column(name = "order_detail_isauction", nullable = false)
    private boolean orderDetailIsAuction;

    @Column(name = "order_detail_iscanceled")
    private Boolean orderDetailIsCanceled;

    @Column(name = "order_detail_canceled_date")
    private LocalDateTime orderDetailCanceledDate;

    //    ----------------------------------------------------

    // Many-to-One relationship with Member
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "member_seq")
    private Member member;

    // Many-to-One relationship with Artist
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "artist_seq")
    private Artist artist;

    // Many-to-One relationship with Item
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "item_seq")
    private Item item;

    // Many-to-One relationship with Auction
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "auction_seq")
    private Auction auction;
}
