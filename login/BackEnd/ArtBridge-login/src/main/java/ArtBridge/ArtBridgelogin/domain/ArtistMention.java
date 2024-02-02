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

    @Column(name = "artist_metion_subject", nullable = false)
    private String artistMentionSubject;

    @Column(name = "artis_mention_created_date")
    private LocalDateTime artistMentionCreatedDate;

    @Column(name = "artist_mention_isremoved")
    private boolean artistMentionIsremoved;

    @Column(name = "arist_mention_deleted_date")
    private LocalDateTime artistMentionDeletedDate;

    // One-to-Many relationship with ArtistMentionComment
    @OneToMany(mappedBy = "artistMention")
    private List<ArtistMentionComment> artistMentionComments;

    // Many-to-One relationship with Artist
    @ManyToOne
    @JoinColumn(name = "artist_seq")
    private Artist artist;
}
