package ArtBridge.ArtBridgelogin.test;

import ArtBridge.ArtBridgelogin.domain.Auction;
import ArtBridge.ArtBridgelogin.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;

    //Todo: CREATE
    @Transactional
    public Auction createAuction(Auction auction) {
        return auctionRepository.create(auction);
    }


    //Todo: READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Auction> readAllAuction(){
        return auctionRepository.readAll();
    }
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Auction readOne(int seq) {
        return auctionRepository.readOne(seq);
    }


    //Todo: UPDATE
    @Transactional
    public Auction updateAuction(int seq, Auction updatedAuction) {
        auctionRepository.updateAuction(seq, updatedAuction);
        return auctionRepository.readOne(seq);
    }


    //Todo: DELETE
    @Transactional
    public void deleteAuction(int seq) {
        auctionRepository.deleteById(seq);
    }

    @Transactional
    public List<Auction> readAuctionsBySameAuthor(Long authorId) {
        return auctionRepository.readAuctionsBySameAuthor(authorId);
    }
}
