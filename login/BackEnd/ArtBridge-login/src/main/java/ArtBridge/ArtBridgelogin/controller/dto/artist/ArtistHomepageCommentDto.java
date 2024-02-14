package ArtBridge.ArtBridgelogin.controller.dto.artist;

import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.Member;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArtistHomepageCommentDto {

    private Long artistHomepageCommentSeq;
    private String artistHompageCommentContent;
    private LocalDateTime artistHompageCommentContentCreatedDate;
    private boolean artistHomepageCommentIsdeleted;
    private LocalDateTime artistHompageCommentContentDeletedDate;
    private Long member;
    private Long artist;



}
