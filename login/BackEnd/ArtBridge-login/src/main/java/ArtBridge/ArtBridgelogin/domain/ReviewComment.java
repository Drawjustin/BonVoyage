package ArtBridge.ArtBridgelogin.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="review_comment")
@Data
public class ReviewComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_comment_seq", nullable = false)
    private Long reviewCommentSeq;

    @Column(name = "review_comment_content",length = 255, nullable = false)
    private String reviewCommentContent;

    @Column(name = "review_comment_created_date", nullable = false)
    private LocalDateTime reviewCommentCreatedDate;

    //    ----------------------------------------------------

    // Many-to-One relationship with Member
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "member_seq")
    private Member member;

    // Many-to-One relationship with Review
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "review_seq")
    private Review review;
}
