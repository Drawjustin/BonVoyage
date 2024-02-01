package ArtBridge.ArtBridgelogin.domain.Connection;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Table(name="auction_like")
@Data
public class AuctionLike {

    @Column(name = "auction_seq", nullable = false)
    private Integer auctionSeq;

    @Column(name = "member_seq", nullable = false)
    private Long    memberSeq;


}
