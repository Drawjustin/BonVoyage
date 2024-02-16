package ArtBridge.ArtBridgelogin.controller.dto.artist;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArtistMentionDto {

    private Long artistMentionSeq;
    private String subject;
    private String content;
    private String artistId;
    private LocalDateTime createdDate;

}
