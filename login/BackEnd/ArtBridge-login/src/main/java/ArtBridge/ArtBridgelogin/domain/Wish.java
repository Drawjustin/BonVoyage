package ArtBridge.ArtBridgelogin.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="wish")
@Data
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_seq", nullable = false)
    private Integer wishSeq;

    // Many-to-One relationship with Member
    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;

    // Many-to-One relationship with Item
    @ManyToOne
    @JoinColumn(name = "item_seq")
    private Item item;
}
