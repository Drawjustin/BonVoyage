package ArtBridge.ArtBridgelogin.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "artistComment")
@Data
public class ArtistComment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_comment_seq")
    private Long artistCommentSeq;


    @Column(name = "artist_Seq")
    private Long artistSeq;

    @Column(name = "artist_mention_content")
    private String artistMentionContent;

    @Column(name = "artist_metion_subject")
    private String artistSubjectMention;

    @Column(name = "artis_mention_created_date")
    private LocalDateTime artistMentionCreatedDate;

    @Column(name = "artist_mention_isremoved")
    private boolean artistMentionIsremoved;

    @Column(name = "arist_mention_deleted_date")
    private LocalDateTime artistMentionDeletedDate;

}
