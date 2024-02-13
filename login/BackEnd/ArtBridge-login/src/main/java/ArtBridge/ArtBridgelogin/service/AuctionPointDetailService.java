package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.AuctionPointDetail;
import ArtBridge.ArtBridgelogin.repository.AuctionPointDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionPointDetailService {

    @Autowired
    private AuctionPointDetailRepository auctionPointDetailRepository;

    //TODO: CREATE
    @Transactional
    public AuctionPointDetail create(AuctionPointDetail auctionPointDetail) {
        return auctionPointDetailRepository.create(auctionPointDetail);
    }

    //TODO: READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<AuctionPointDetail> readAllAuctionPointDetail() {
        return auctionPointDetailRepository.readAll();
    }
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public AuctionPointDetail readOne(int seq) {
        return auctionPointDetailRepository.readOne(seq);
    }

    //TODO: UPDATE
    @Transactional
    public void updateWinner(int seq, boolean isWin) {
        AuctionPointDetail auctionPointDetail = auctionPointDetailRepository.readOne(seq);
        if (auctionPointDetail != null) {
            auctionPointDetail.setAuctionPointDetailIsWin(isWin);
            auctionPointDetailRepository.create(auctionPointDetail);
        } else {
            throw new IllegalArgumentException("해당 seq의 AuctionPointDetail이 존재하지 않습니다.");
        }
    }


    //TODO: DELETE NONE

}
