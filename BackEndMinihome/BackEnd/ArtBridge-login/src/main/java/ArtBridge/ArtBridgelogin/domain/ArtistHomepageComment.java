package ArtBridge.ArtBridgelogin.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "artist_homepage_comment_content", updatable = false)
    private String artistHomepageCommentContent;

    @Column(name = "artist_homepage_comment_created_date")
    private LocalDateTime artistHomepageCommentContentCreatedDate;

    @Column(name = "artist_homepage_comment_isdeleted")
    private boolean artistHomepageCommentIsdeleted;

    @Column(name = "artist_homepage_comment_deleted_date")
    private LocalDateTime artistHomepageCommentContentDeletedDate;

    //    ----------------------------------------------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "artist_seq")
    private Artist artist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "member_seq")
    private Member member;

}
