package ArtBridge.ArtBridgelogin.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_detail")
@Data
public class OrderDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_seq")
    private Long orderDetailSeq;

    @Column(name = "member_seq", nullable = false)
    private Integer memberSeq;

    @Column(name = "artist_seq", nullable = false)
    private Integer artistSeq;

    @Column(name = "item_seq", nullable = false)
    private Integer itemSeq;

    @Column(name = "auction_seq")
    private Integer auctionSeq;

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

}
