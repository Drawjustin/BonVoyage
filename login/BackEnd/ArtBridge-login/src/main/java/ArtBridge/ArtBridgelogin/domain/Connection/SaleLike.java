package ArtBridge.ArtBridgelogin.domain.Connection;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Table(name="sale_like")
@Data
public class SaleLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_seq", nullable = false)
    private Integer itemSeq;
    @Column(name = "member_seq", nullable = false)
    private Integer memberSeq;



}
