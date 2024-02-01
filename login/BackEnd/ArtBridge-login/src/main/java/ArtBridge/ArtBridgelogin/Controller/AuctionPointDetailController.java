package ArtBridge.ArtBridgelogin.Controller;

import ArtBridge.ArtBridgelogin.domain.Auction;
import ArtBridge.ArtBridgelogin.domain.AuctionPointDetail;
import ArtBridge.ArtBridgelogin.service.AuctionPointDetailService;
import ArtBridge.ArtBridgelogin.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RestController("/auctionPointDetail")
public class AuctionPointDetailController {

    @Autowired
    private AuctionPointDetailService auctionPointDetailService;

    @GetMapping
    public List<AuctionPointDetail> getAlLAuction() {return auctionPointDetailService.getAllAuctionPointDetail();}

    @GetMapping("/{id}")
    public AuctionPointDetail AuctionById(@PathVariable Long id) {return auctionPointDetailService.findOne(id);}

    @PostMapping("/new")
    public AuctionPointDetail createAuction(@RequestBody AuctionPointDetail autionPointDetail) {
        return auctionPointDetailService.create(autionPointDetail);
    }

    @PutMapping("/{id}")
    public AuctionPointDetail updateAuction(@PathVariable Long id, @RequestBody AuctionPointDetail updatedAuctionPointDetail) {
        return auctionPointDetailService.updateAuctionPointDetail(id, updatedAuctionPointDetail);
    }

    @DeleteMapping("/{id}")
    public void deleteAuction(@PathVariable Long id) {
        auctionPointDetailService.deleteAuctionPointDetail(id);
    }
}
