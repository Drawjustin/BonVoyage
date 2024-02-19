package ArtBridge.ArtBridgelogin.webRTC;

import ArtBridge.ArtBridgelogin.controller.dto.member.MemberDto;
import ArtBridge.ArtBridgelogin.controller.dto.webRTC.AuctionPointDetailDto;
import ArtBridge.ArtBridgelogin.domain.Auction;
import ArtBridge.ArtBridgelogin.domain.AuctionPointDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/bid")
public class WebrtcController {
    @Autowired
    WebrtcService webrtcService;

    // 입찰
    @PostMapping("/price")
    public ResponseEntity<?> createBid(@RequestBody AuctionPointDetailDto bidRequest) {
        try {
            AuctionPointDetailDto createdDetail = webrtcService.createBid(bidRequest);
            return ResponseEntity.ok(createdDetail);
        }catch (Exception e) {
            return new ResponseEntity<>("Create Bid Error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{seq}")
    public ResponseEntity<?> readBidListByAuctionSeq(@PathVariable("seq") Integer seq) {
        try {
            List<AuctionPointDetailDto> bids = webrtcService.readBidListByAuctionSeq(seq);
            return ResponseEntity.ok(bids);
        }
        catch (Exception e){
            return new ResponseEntity<>("Read Bid List Error", HttpStatus.BAD_REQUEST);
        }
    }

    // 경매 낙찰자 찾기
    @GetMapping("/{seq}/winner")
    public ResponseEntity<?> readWinnerBySeq(@PathVariable("seq") Integer seq) {
        try {
            MemberDto winner = webrtcService.readWinner(seq);
            return ResponseEntity.ok(winner);
        }
        catch (Exception e){
            return ResponseEntity.ok("read Winner error");
        }
    }

    // 현재 경매 호가 조회
    @GetMapping("/{seq}/price")
    public ResponseEntity<?> readCurrentPrice(@PathVariable("seq") Integer seq) {

        try{
            System.out.println(seq);
            double currentPrice = webrtcService.readCurrentPrice(seq);
            return ResponseEntity.ok(currentPrice);

        }
        catch (Exception e){
            return ResponseEntity.ok("read CurrentPrice error");
        }

    }

    // 경매 정보 조회
    @GetMapping("/{seq}/windate")
    public ResponseEntity<?> updateAuctionDetails(@PathVariable("seq") Integer seq) {
        try{
            webrtcService.updateAuctionDetails(seq);
            //webrtcService.updateAuctionDetails(seq)
            return ResponseEntity.ok("read AuctionDetails successful");
        }
        catch (Exception e){
            return ResponseEntity.ok("read AuctionDetails error");
        }

    }




//    @PutMapping("/{seq}/winner")
//    public ResponseEntity<?> updateAuctionDetails(@PathVariable("seq") Integer seq, @RequestBody AuctionPointDetail bidRequest) {
//        try{
//            return webrtcService.updateAuctionDetails(seq,bidRequest);
//
//        }
//        catch (Exception e){
//            return ResponseEntity.ok("read AuctionDetails error");
//        }
//
//    }

}
