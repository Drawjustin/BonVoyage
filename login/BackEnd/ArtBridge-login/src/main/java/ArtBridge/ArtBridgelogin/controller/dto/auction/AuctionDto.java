package ArtBridge.ArtBridgelogin.controller.dto.auction;

import ArtBridge.ArtBridgelogin.controller.dto.item.ItemDto;
import ArtBridge.ArtBridgelogin.domain.AuctionPointDetail;
import ArtBridge.ArtBridgelogin.domain.Connection.AuctionLike;
import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.domain.MemberAuctionBidding;
import ArtBridge.ArtBridgelogin.domain.OpenVidu.Meeting;
import ArtBridge.ArtBridgelogin.domain.OrderDetail;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
