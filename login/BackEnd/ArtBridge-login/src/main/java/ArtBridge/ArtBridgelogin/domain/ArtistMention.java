package ArtBridge.ArtBridgelogin.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "artist_mention")
@Data
public class ArtistMention implements Serializable {

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

    @Column(name = "artist_mention_isdeleted")
    private boolean artistMentionIsdeleted;

    @Column(name = "artist_mention_deleted_date")
    private LocalDateTime artistMentionDeletedDate;

    @Column(name = "artist_mention_isremoved")
    private boolean artistIsRemoved;

    //    ----------------------------------------------------

    @JsonManagedReference
    @OneToMany(mappedBy = "artistMention")
    private List<ArtistMentionComment> artistMentionComments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "artist_seq")
    private Artist artist;
}
