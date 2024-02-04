package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.AuctionPointDetail;
import ArtBridge.ArtBridgelogin.repository.AuctionPointDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionPointDetailService {

    @Autowired
    private AuctionPointDetailRepository auctionPointDetailRepository;

    @Transactional(readOnly = true)
    public List<AuctionPointDetail> getAllAuctionPointDetail() {
        return auctionPointDetailRepository.findAll();
    }

    @Transactional(readOnly = true)
    public AuctionPointDetail findOne(int seq) {
        return auctionPointDetailRepository.findOne(seq);
    }

    @Transactional
    public AuctionPointDetail create(AuctionPointDetail auctionPointDetail) {
        return auctionPointDetailRepository.create(auctionPointDetail);
    }

    @Transactional
    public void updateWinner(int seq, boolean isWin) {
        AuctionPointDetail auctionPointDetail = auctionPointDetailRepository.findOne(seq);
        if (auctionPointDetail != null) {
            auctionPointDetail.setAuctionPointDetailIsWin(isWin);
            auctionPointDetailRepository.create(auctionPointDetail);
        } else {
            throw new IllegalArgumentException("해당 seq의 AuctionPointDetail이 존재하지 않습니다.");
        }
    }
}
