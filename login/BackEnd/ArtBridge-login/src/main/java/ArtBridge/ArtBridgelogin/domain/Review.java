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

    @Column(name = "member_seq", nullable = false)
    private Integer memberSeq;

    @Column(name = "review_content",nullable = false)
    private Integer reviewContent;

    @Column(name = "review_visit",length = 255, nullable = false)
    private String reviewVisit;

    @Column(name = "review_created_date", nullable = false)
    private LocalDateTime reviewCreatedDate;
}
