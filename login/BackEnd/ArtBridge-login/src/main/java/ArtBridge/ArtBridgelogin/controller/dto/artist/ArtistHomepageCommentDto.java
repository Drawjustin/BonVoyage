package ArtBridge.ArtBridgelogin.controller.dto.artist;

import lombok.Data;

@Data
public class ArtistHomepageCommentDto {

    private Long artistHomepageCommentSeq;
    private String artistHompageCommentContent;
    private String memberId;
    private String artistId;

}
