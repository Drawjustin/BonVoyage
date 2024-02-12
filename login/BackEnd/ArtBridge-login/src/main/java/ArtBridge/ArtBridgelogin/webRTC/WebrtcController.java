package ArtBridge.ArtBridgelogin.webRTC;

import ArtBridge.ArtBridgelogin.controller.dto.member.MemberDto;
import ArtBridge.ArtBridgelogin.domain.AuctionPointDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auction")
public class WebrtcController {
    @Autowired
    WebrtcService webrtcService;

    @PostMapping("/{seq}/bid")
    public ResponseEntity<String> createBid(@PathVariable("seq") Long seq, @RequestBody AuctionPointDetail bidRequest) {
        try {
            webrtcService.createBid(seq, bidRequest);
            return ResponseEntity.ok("Create Bid successfully.");

        }catch (Exception e){
            return ResponseEntity.ok("Create Bid Error");
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
            double currentPrice = webrtcService.readCurrentPrice(seq);
            return ResponseEntity.ok(currentPrice);

        }
        catch (Exception e){
            return ResponseEntity.ok("read CurrentPrice error");
        }

    }

    // 경매 정보 조회
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
