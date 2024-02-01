package ArtBridge.ArtBridgelogin.Controller;

import ArtBridge.ArtBridgelogin.domain.Auction;
import ArtBridge.ArtBridgelogin.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping
    public List<Auction> getAlLAuction() {return auctionService.getAllAuction();}

    @GetMapping("/{id}")
    public Auction AuctionById(@PathVariable Long id) {return auctionService.findOne(id);}

    @PostMapping("/new")
    public Auction createAuction(@RequestBody Auction Auction) {
        return auctionService.createAuction(Auction);
    }

    @PutMapping("/{id}")
    public Auction updateAuction(@PathVariable Long id, @RequestBody Auction updatedAuction) {
        return auctionService.updateAuction(id, updatedAuction);
    }

    @DeleteMapping("/{id}")
    public void deleteAuction(@PathVariable Long id) {
        auctionService.deleteAuction(id);
    }
}
