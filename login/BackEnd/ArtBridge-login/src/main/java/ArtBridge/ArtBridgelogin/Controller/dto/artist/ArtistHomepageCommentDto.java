package ArtBridge.ArtBridgelogin.controller.dto.artist;

import lombok.Data;

@Data
public class ArtistHomepageCommentDto {

    private Long artistHomepageCommentSeq;
    private String artistHomepageCommentContent;
    private Long memberSeq;
    private Long artistSeq;

}
