package ArtBridge.ArtBridgelogin.controller.dto.wish;

import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.domain.Member;
import lombok.Data;

@Data
public class WishDto {

    private Integer wishSeq;
    private Long member;
    private int item;
}
