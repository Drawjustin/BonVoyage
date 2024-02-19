package ArtBridge.ArtBridgelogin.controller.dto.webRTC;

import ArtBridge.ArtBridgelogin.controller.dto.auction.AuctionDto;
import ArtBridge.ArtBridgelogin.controller.dto.member.MemberDto;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
public class AuctionPointDetailDto {

    private Integer auctionPointDetailSeq;

    private Integer auctionPointDetailPoint;


    private Boolean auctionPointDetailIsWin;

    private LocalDateTime auctionPointDate;

    private Long memberSeq;

    private int auctionSeq;

}
