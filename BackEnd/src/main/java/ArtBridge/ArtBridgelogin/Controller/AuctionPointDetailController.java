package ArtBridge.ArtBridgelogin.controller;

import ArtBridge.ArtBridgelogin.domain.AuctionPointDetail;
import ArtBridge.ArtBridgelogin.service.AuctionPointDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auctionPointDetail")
public class AuctionPointDetailController {

    @Autowired
    private AuctionPointDetailService auctionPointDetailService;

    @GetMapping
    public List<AuctionPointDetail> readAlLPointDetail() {
        return auctionPointDetailService.readAllAuctionPointDetail();
    }

    @GetMapping("/{id}")
    public AuctionPointDetail readDetailBySeq(@PathVariable int seq) {
        return auctionPointDetailService.readOne(seq);
    }

    @PostMapping("/new")
    public AuctionPointDetail createAuction(@RequestBody AuctionPointDetail auctionPointDetail) {
        return auctionPointDetailService.create(auctionPointDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuction(@PathVariable int seq, @RequestBody boolean isWin) {
        auctionPointDetailService.updateWinner(seq, isWin);

        //성공 시 http 코드 200 반환
        return ResponseEntity.ok().build();
    }
}
