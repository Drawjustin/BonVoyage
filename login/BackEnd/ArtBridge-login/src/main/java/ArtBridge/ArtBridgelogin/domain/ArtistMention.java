package ArtBridge.ArtBridgelogin.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "artist_mention")
@Data
public class ArtistMention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_mention_seq")
    private Long artistMentionSeq;

    @Column(name = "artist_mention_content", nullable = false)
    private String artistMentionContent;

    @Column(name = "artist_mention_subject", nullable = false)
    private String artistMentionSubject;

    @Column(name = "artist_mention_created_date")
    private LocalDateTime artistMentionCreatedDate;

    @Column(name = "artist_mention_isremoved")
    private boolean artistMentionIsremoved;

    @Column(name = "artist_mention_deleted_date")
    private LocalDateTime artistMentionDeletedDate;

    @OneToMany(mappedBy = "artistMention")
    private List<ArtistMentionComment> artistMentionComments;

    @ManyToOne
    @JoinColumn(name = "artist_seq")
    private Artist artist;
}
