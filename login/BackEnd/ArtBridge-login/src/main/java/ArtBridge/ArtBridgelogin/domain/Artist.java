package ArtBridge.ArtBridgelogin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "artist")
@Data
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_seq")
    private Long artistSeq;


    @Column(name = "artist_name", length = 30, nullable = false)
    private String artistName;

    @Column(name = "artist_id", length = 30, nullable = false)
    private String artistId;

    @Column(name = "artist_pwd", length = 30, nullable = false)
    private String artistPwd;

    @Column(name = "artist_nickname", length = 30, nullable = false)
    private String artistNickname;

    @Column(name = "artist_email", length = 30, nullable = false)
    private String artistEmail;

    @Column(name = "artist_contact", length = 50, nullable = false)
    private String artistContact;

    @Column(name = "artist_point", length = 32, nullable = false, columnDefinition = "INT DEFAULT 0")
    private Long artistPoint;

    @Column(name = "artist_history")
    private String artistHistory;

    @Column(name = "artist_isdeleted", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean artistIsdeleted;

    @Column(name = "artist_deleted_date")
    private LocalDateTime artistDeletedDate;

    @Column(name = "artist_created_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime artistCreatedDate;
    // 다른 필드 및 메서드들...
    @PrePersist
    protected void onCreate() {
        if (artistCreatedDate == null) {
            artistCreatedDate = LocalDateTime.now();
        }
    }

    @OneToMany(mappedBy = "artist")
    private List<ArtistMention> artistMentions;

//    @OneToMany(mappedBy = "artist")
//    private List<ArtistHomepageComment> artistHomepageComments;
}