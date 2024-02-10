package ArtBridge.ArtBridgelogin.controller;

import ArtBridge.ArtBridgelogin.domain.Auction;
import ArtBridge.ArtBridgelogin.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping
    public List<Auction> readAlLAuction() {return auctionService.readAllAuction();}

    @GetMapping("/{id}")
    public Auction readAuctionById(@PathVariable int seq) {return auctionService.readOne(seq);}

    @PostMapping("/new")
    public Auction createAuction(@RequestBody Auction Auction) {
        return auctionService.createAuction(Auction);
    }

    @PutMapping("/{id}")
    public Auction updateAuction(@PathVariable int seq, @RequestBody Auction updatedAuction) {
        return auctionService.updateAuction(seq, updatedAuction);
    }

    @DeleteMapping("/{id}")
    public void deleteAuction(@PathVariable int seq) {
        auctionService.deleteAuction(seq);
    }

    @GetMapping("/mypage/{authorId}")
    public List<Auction> readAuctionsByAuthor(@PathVariable Long authorId) {
        return auctionService.readAuctionsBySameAuthor(authorId);
    }


}
