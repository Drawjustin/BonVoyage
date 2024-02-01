package ArtBridge.ArtBridgelogin.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "artist_homepage_comment")
@Data
public class ArtistHomepageComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_homepage_comment_seq")
    private Long artistHomepageCommentSeq;

    @Column(name = "artist_seq", insertable = false, updatable = false)
    private String artistSeq;

    @Column(name = "member_seq", insertable = false, updatable = false)
    private String memberSeq;

    @Column(name = "artist_homepage_comment_content", updatable = false)
    private String artistHompageCommentContent;

    @Column(name = "artist_homepage_comment_created_date")
    private LocalDateTime artistHompageCommentContentCreatedDate;

    @Column(name = "artist_homepage_comment_isdeleted")
    private boolean artistHomepageCommentIsdeleted;

    @Column(name = "artist_homepage_comment_deleted_date")
    private LocalDateTime artistHompageCommentContentDeletedDate;


//    @ManyToOne
//    @JoinColumn(name = "artist_seq", nullable = false)
//    private Artist artist;
//
//    @ManyToOne
//    @JoinColumn(name = "member_seq", nullable = false)
//    private Member member;

}