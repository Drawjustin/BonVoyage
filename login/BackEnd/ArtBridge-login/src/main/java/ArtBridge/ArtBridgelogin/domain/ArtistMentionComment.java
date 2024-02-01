package ArtBridge.ArtBridgelogin.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "artist_mention_comment")
@Data
public class ArtistMentionComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_mention_comment_seq")
    private Long artistMentionCommentSeq;

    @Column(name = "member_seq")
    private Member memberSeq;

    @Column(name = "artist_mention_seq")
    private String artistMentionSeq;

    @Column(name = "artist_homepage_comment_content")
    private String artistMentionCommentContent;

    @Column(name = "artist_mention_comment_isdeleted")
    private boolean artistMentionCommentIsdeleted;

    @Column(name = "artist_mention_comment_created_date")
    private LocalDateTime artistMentionCommentCreatedDate;

    @Column(name = "artist_mention_comment_deleted_date")
    private LocalDateTime artistMentionCommentDeletedDate;

}