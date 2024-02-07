package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.*;
import ArtBridge.ArtBridgelogin.repository.AuctionPointDetailRepository;
import ArtBridge.ArtBridgelogin.repository.AuctionRepository;
import ArtBridge.ArtBridgelogin.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class AuctionPointDetailServiceTest {

    @Autowired
    AuctionPointDetailService auctionPointDetailService;
    @Autowired
    AuctionPointDetailRepository auctionPointDetailRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AuctionRepository auctionRepository;
    private AuctionPointDetail auctionPointDetail;

    @BeforeEach
    public void setup() {
        Member member = memberRepository.readOne(3L);
        Auction auction = auctionRepository.readOne(7);

        auctionPointDetail = new AuctionPointDetail();
        auctionPointDetail.setAuctionPointDetailPoint(100);
        auctionPointDetail.setAuctionPointDate(LocalDateTime.now());
        auctionPointDetail.setMember(member);
        auctionPointDetail.setAuction(auction);
        auctionPointDetail.setAuctionPointDetailIsWin(false);
    }

    @Test
    public void getAllAuctionPointDetailTest() {
        List<AuctionPointDetail> list = auctionPointDetailService.readAllAuctionPointDetail();
        assertNotNull(list);
    }

    @Test
    public void readOneTest() {
        AuctionPointDetail created = auctionPointDetailService.create(auctionPointDetail);

        AuctionPointDetail found = auctionPointDetailService.readOne(created.getAuctionPointDetailSeq());
        assertEquals(created.getAuctionPointDetailSeq(), found.getAuctionPointDetailSeq());
    }

    @Test
    public void createTest() {
        AuctionPointDetail created = auctionPointDetailService.create(auctionPointDetail);
        assertNotNull(created.getAuctionPointDetailSeq());
    }

    @Test
    public void updateWinnerTest() {
        AuctionPointDetail created = auctionPointDetailService.create(auctionPointDetail);

        auctionPointDetailService.updateWinner(created.getAuctionPointDetailSeq(), true);

        AuctionPointDetail updated = auctionPointDetailService.readOne(created.getAuctionPointDetailSeq());

        assertTrue(updated.getAuctionPointDetailIsWin());
    }
}
