package ArtBridge.ArtBridgelogin.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "kakopay_request")
@Data
public class kakaopay_answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kakopay_request_seq")
    private int kakopayRequestSeq;
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
    @Column(name = "total_amount", nullable = false)
    private int totalAmount;
    @Column(name = "tax_free_amount",nullable = true)
    private int taxFreeAmount;
    @Column(name = "approval_url", length = 255, nullable = true)
    private String approvalUrl;
    @Column(name = "cancel_url", length = 255, nullable = true)
    private String cancelUrl;
    @Column(name = "fail_url", length = 255, nullable = true)
    private String failUrl;
}
