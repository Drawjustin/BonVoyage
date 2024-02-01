package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.Auction;
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
    public Auction findOne(Long id) {
        return auctionRepository.findOne(id);
    }

    @Transactional
    public Auction createAuction(Auction auction) {
        return auctionRepository.create(auction);
    }

    @Transactional
    public Auction updateAuction(Long id, Auction updatedAuction) {
        Auction existingAuction = auctionRepository.findOne(id);

        if (existingAuction != null) {
            // 업데이트할 정보를 새로운 정보로 설정
//            existingAuction.setAuctionContent(updatedAuction.getAuctionContent());
//            existingAuction.setAuctionSubject(updatedAuction.getAuctionSubject());
            // 저장
            auctionRepository.create(existingAuction);
            return existingAuction;
        } else {
            // 예외 처리 또는 적절한 로직 추가
            return null;
        }
    }

    @Transactional
    public void deleteAuction(Long id) {
        auctionRepository.deleteById(id);
    }
}
