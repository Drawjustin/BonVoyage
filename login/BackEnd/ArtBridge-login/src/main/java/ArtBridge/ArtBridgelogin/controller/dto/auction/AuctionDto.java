package ArtBridge.ArtBridgelogin.controller.dto.auction;

import ArtBridge.ArtBridgelogin.controller.dto.item.ItemDto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
public class AuctionDto {

    private Integer auctionSeq;
    private LocalDateTime auctionScheduledTime;
    private Integer auctionStatus;
    private Integer auctionStartPoint;
    private Integer auctionAskPoint;
    private Long auctionSellPoint;
    private Integer auctionWinner;
    private LocalDateTime auctionCreatedDate;
    private LocalDateTime auctionCanceledDate;
    private Boolean auctionIsMiscarried;
    private LocalDateTime auctionMiscarriedDate;
    private LocalDateTime auctionWinDate;

    public void setItem(ItemDto item) {
        this.item = item;
    }

    private ItemDto item;

}
