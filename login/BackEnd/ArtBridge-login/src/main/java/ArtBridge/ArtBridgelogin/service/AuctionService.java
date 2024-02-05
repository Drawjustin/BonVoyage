package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.Auction;
import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;

    @Transactional(readOnly = true)
    public List<Auction> getAllAuction() {
        return auctionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Auction findOne(int seq) {
        return auctionRepository.findOne(seq);
    }

    @Transactional
    public Auction createAuction(Auction auction) {
        return auctionRepository.create(auction);
    }

    @Transactional
    public Auction updateAuction(int seq, Auction updatedAuction) {
        auctionRepository.updateAuction(seq, updatedAuction);
        return auctionRepository.findOne(seq);
    }


    @Transactional
    public void deleteAuction(int seq) {
        auctionRepository.deleteById(seq);
    }

    public List<Auction> getAuctionsBySameAuthor(Long authorId) {
        return auctionRepository.getAuctionsBySameAuthor(authorId);
    }

    public void deleteAuctionByAuthor(int seq) {
        auctionRepository.deleteAuctionByMember(seq);
    }
}
