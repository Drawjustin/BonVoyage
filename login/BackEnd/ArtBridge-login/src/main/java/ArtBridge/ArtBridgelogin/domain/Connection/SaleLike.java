package ArtBridge.ArtBridgelogin.domain.Connection;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Table(name="sale_like")
@Data
public class SaleLike {

    @Column(name = "item_seq", nullable = false)
    private int itemSeq;

    @Column(name = "member_seq", nullable = false)
    private int memberSeq;



}
