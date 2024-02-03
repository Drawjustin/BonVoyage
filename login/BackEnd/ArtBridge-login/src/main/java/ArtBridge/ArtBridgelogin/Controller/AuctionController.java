package ArtBridge.ArtBridgelogin.Controller;

import ArtBridge.ArtBridgelogin.domain.Auction;
import ArtBridge.ArtBridgelogin.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RestController("/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping
    public List<Auction> getAlLAuction() {return auctionService.getAllAuction();}

    @GetMapping("/{id}")
    public Auction findAuctionById(@PathVariable int seq) {return auctionService.findOne(seq);}

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
}
