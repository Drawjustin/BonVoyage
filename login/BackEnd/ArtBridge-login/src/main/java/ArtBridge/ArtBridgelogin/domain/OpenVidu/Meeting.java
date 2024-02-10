package ArtBridge.ArtBridgelogin.domain.OpenVidu;

import ArtBridge.ArtBridgelogin.domain.Auction;
import ArtBridge.ArtBridgelogin.domain.Item;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_seq")
    private int meetingSeq;

    @Column(name = "meeting_session_id", length = 100, nullable = false)
    private String meetingSessionId;

    @ManyToOne
    @JoinColumn(name = "auction_seq")
    private Auction auction;
}
