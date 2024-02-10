package ArtBridge.ArtBridgelogin.domain.Connection;

import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.domain.Member;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Entity
@Table(name="sale_like")
@Data
public class SaleLike {

//    @Column(name = "item_seq", nullable = false)
//    private int itemSeq;
//
//    @Column(name = "member_seq", nullable = false)
//    private int memberSeq;

    // Many-to-One relationship with Member
    @Id
    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;

    // Many-to-One relationship with Item
    @Id
    @ManyToOne
    @JoinColumn(name = "item_seq")
    private Item item;

}
