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
    public AuctionPointDetail findOne(Long id) {
        return auctionPointDetailRepository.findOne(id);
    }

    @Transactional
    public AuctionPointDetail create(AuctionPointDetail auctionPointDetail) {
        return auctionPointDetailRepository.create(auctionPointDetail);
    }

    @Transactional
    public AuctionPointDetail updateAuctionPointDetail(Long id, AuctionPointDetail updatedAuctionPointDetail) {
        AuctionPointDetail existingAuctionPointDetail = auctionPointDetailRepository.findOne(id);

        if (existingAuctionPointDetail != null) {
            // 업데이트할 정보를 새로운 정보로 설정
//            existingAuctionPointDetail.setAuctionPointDetailContent(updatedAuctionPointDetail.getAuctionPointDetailContent());
//            existingAuctionPointDetail.setAuctionPointDetailSubject(updatedAuctionPointDetail.getAuctionPointDetailSubject());
            // 저장
            auctionPointDetailRepository.create(existingAuctionPointDetail);
            return existingAuctionPointDetail;
        } else {
            // 예외 처리 또는 적절한 로직 추가
            return null;
        }
    }

    @Transactional
    public void deleteAuctionPointDetail(Long id) {
        auctionPointDetailRepository.deleteById(id);
    }
}
