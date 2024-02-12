package ArtBridge.ArtBridgelogin.webRTC;

import ArtBridge.ArtBridgelogin.controller.dto.member.MemberDto;
import ArtBridge.ArtBridgelogin.controller.dto.webRTC.AuctionPointDetailDto;
import ArtBridge.ArtBridgelogin.domain.Auction;
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
    public ResponseEntity<String> createBid(@PathVariable("seq") Long seq, @RequestBody AuctionPointDetailDto bidRequest) {
        try {
            System.out.println("1231231231");
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
            System.out.println(seq);
            double currentPrice = webrtcService.readCurrentPrice(seq);
            return ResponseEntity.ok(currentPrice);

        }
        catch (Exception e){
            return ResponseEntity.ok("read CurrentPrice error");
        }

    }

    // 경매 정보 조회
<<<<<<< HEAD
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




=======
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
>>>>>>> a1e172f1635e08154339f50868515d94ec85465d

}
