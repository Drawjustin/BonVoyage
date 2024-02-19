package ArtBridge.ArtBridgelogin.controller;

import ArtBridge.ArtBridgelogin.controller.dto.auction.AuctionDto;
import ArtBridge.ArtBridgelogin.domain.Auction;
import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping
    public ResponseEntity<List<AuctionDto>> readAlLAuction() {
        try {
            List<AuctionDto> auctionDtos = auctionService.readAllAuction();
            return ResponseEntity.ok(auctionDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{seq}")
    public ResponseEntity<AuctionDto> readAuctionById(@PathVariable(value="seq") int seq) {
        try {
            AuctionDto auctionDto = auctionService.readOne(seq);
            return ResponseEntity.ok(auctionDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<?> createAuction(@RequestBody AuctionDto auctionDto) {
        try {
            AuctionDto createdAuction = auctionService.createAuction(auctionDto);
            return ResponseEntity.ok(createdAuction);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/item/{seq}")
    public Item readItemByAuctionSeq(@PathVariable(value="seq") int seq) {
        return auctionService.readItemByAuctionSeq(seq);
    }

    @PutMapping("/{seq}")
    public ResponseEntity<AuctionDto> updateAuction(@PathVariable(value="seq") int seq, @RequestBody AuctionDto updatedAuctionDto) {
        try {
            AuctionDto auctionDto = auctionService.updateAuction(seq, updatedAuctionDto);
            return ResponseEntity.ok(auctionDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{seq}")
    public void deleteAuction(@PathVariable(value="seq") int seq) {
        auctionService.deleteAuction(seq);
    }

    @GetMapping("/mypage/{authorId}")
    public List<Auction> readAuctionsByAuthor(@PathVariable(value="authorId") Long authorId) {
        return auctionService.readAuctionsBySameAuthor(authorId);
    }


}
