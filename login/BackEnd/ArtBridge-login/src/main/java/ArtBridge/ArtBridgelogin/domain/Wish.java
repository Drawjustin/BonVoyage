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
    @Column(name = "member_seq", nullable = false)
    private Integer memberSeq;
    @Column(name = "item_seq",nullable = false)
    private Integer itemSeq;
}
