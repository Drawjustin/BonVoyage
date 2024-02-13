package ArtBridge.ArtBridgelogin.controller.dto.review;

import ArtBridge.ArtBridgelogin.controller.dto.item.ItemDto;
import lombok.Data;

@Data
public class ReviewDto {

    private int seq;
    private String title;
    private String content;
//    public void setItem(ItemDto item) {
//        this.item = item;
//    }
//    private ItemDto item;

}
