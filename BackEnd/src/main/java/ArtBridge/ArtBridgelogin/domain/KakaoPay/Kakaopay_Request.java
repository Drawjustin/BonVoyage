package ArtBridge.ArtBridgelogin.domain.KakaoPay;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "kakaopay_request")
@Data
public class Kakaopay_Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kakaopay_request_seq")
    private Integer kakaopayRequestSeq;
    @Column(name = "cid", length = 10, nullable = false)
    private String cid;
    @Column(name = "partner_order_id", length = 100, nullable = false)
    private String partnerOrderId;
    @Column(name = "partner_user_id", length = 100, nullable = false)
    private String partnerUserId;
    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;
    @Column(name = "quantity", length = 100, nullable = false)
    private String quantity;
    @Column(name = "total_amount", length = 100, nullable = false)
    private Integer totalAmount;
    @Column(name = "tax_free_amount", length = 100, nullable = true)
    private Integer taxFreeAmount;
    @Column(name = "approval_url", length = 255, nullable = true)
    private String approvalUrl;
    @Column(name = "cancel_url", length = 255, nullable = true)
    private String cancelUrl;
    @Column(name = "fail_url", length = 255, nullable = true)
    private String failUrl;


}
