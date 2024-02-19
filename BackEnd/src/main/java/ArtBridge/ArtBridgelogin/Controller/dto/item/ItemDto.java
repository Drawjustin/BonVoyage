package ArtBridge.ArtBridgelogin.controller.dto.item;

import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistDto;
import lombok.Data;

@Data
public class ItemDto {
    private int itemSeq;
    private String itemName;
    private String explain;
    private int itemWidth;
    private int itemHeight;
    private int itemLike;
    private Long itemSellPrice;
    private boolean itemIsSold;
    private String artistId;

}