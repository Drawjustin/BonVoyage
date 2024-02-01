package ArtBridge.ArtBridgelogin.domain;

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
    private Integer reviewCommentSeq;

    @Column(name = "member_seq", nullable = false)
    private Integer memberSeq;

    @Column(name = "review_seq",nullable = false)
    private Integer reviewSeq;

    @Column(name = "review_comment_content",length = 255, nullable = false)
    private String reviewCommentContent;

    @Column(name = "review_comment_created_date", nullable = false)
    private LocalDateTime reviewCommentCreatedDate;
}
