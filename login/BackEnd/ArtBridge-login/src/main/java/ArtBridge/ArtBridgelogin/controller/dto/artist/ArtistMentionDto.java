package ArtBridge.ArtBridgelogin.controller.dto.artist;

import lombok.Data;

@Data
public class ArtistMentionDto {

    private Long artistMentionSeq;
    private String subject;
    private String content;
    private String artistId;

}
