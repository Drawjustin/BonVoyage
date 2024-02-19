package ArtBridge.ArtBridgelogin.controller.dto.review;

import ArtBridge.ArtBridgelogin.controller.dto.item.ItemDto;
import lombok.Data;

@Data
public class ReviewDto {

    private int seq;
    private String memberId;
    private String title;
    private String content;
    private String artistId;
    private int itemSeq;

}
