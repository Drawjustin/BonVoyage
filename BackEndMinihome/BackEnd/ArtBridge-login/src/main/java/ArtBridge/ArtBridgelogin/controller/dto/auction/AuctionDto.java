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
    private Integer auctionWinner;
    private LocalDateTime auctionCreatedDate;
    private LocalDateTime auctionCanceledDate;
    private Boolean auctionIsMiscarried;
    private LocalDateTime auctionMiscarriedDate;
    private Long auctionSellPoint;
    private LocalDateTime auctionWinDate;
    private String auctionSessionId;
    private Integer itemSeq;

}
