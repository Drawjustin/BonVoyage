package ArtBridge.ArtBridgelogin.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="review")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_seq", nullable = false)
    private Integer reviewSeq;

    @Column(name = "review_content",nullable = false)
    private String reviewContent;

    @Column(name = "review_visit",length = 255, nullable = false)
    private String reviewVisit;

    @Column(name = "review_created_date", nullable = false)
    private LocalDateTime reviewCreatedDate;

    // Many-to-One relationship with Member
    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;
}
