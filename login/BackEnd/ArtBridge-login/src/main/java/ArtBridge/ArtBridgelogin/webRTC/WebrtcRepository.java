package ArtBridge.ArtBridgelogin.webRTC;

import ArtBridge.ArtBridgelogin.domain.AuctionPointDetail;
import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.domain.QAuctionPointDetail;
import ArtBridge.ArtBridgelogin.domain.QMember;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
public class WebrtcRepository {
    @PersistenceContext
    private final EntityManager em;

    private QAuctionPointDetail qAuctionPointDetail = QAuctionPointDetail.auctionPointDetail;
    private QMember qMember = QMember.member;

    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }
    public WebrtcRepository(EntityManager em) {
        this.em = em;
    }
    public void createBid(Long seq, AuctionPointDetail bidRequest) {
        // 경매에 대한 입찰을 생성하는 로직을 여기에 추가
        em.persist(bidRequest);
    }

    public Member readWinner(Integer seq) {
        // 경매에서 낙찰자를 조회하는 로직을 여기에 추가
        return queryFactory.selectFrom(qMember) // 멤버 엔티티 선택
                .where(qAuctionPointDetail.auction.auctionSeq.eq(seq)
                        .and(qAuctionPointDetail.auctionPointDetailIsWin.eq(true)))
                .fetchOne(); // 첫 번째 결과 반환
    }

    public AuctionPointDetail readCurrentPrice(Integer seq) {
        // 경매의 현재 가격을 조회하는 로직을 여기에 추가

        return queryFactory.selectFrom(qAuctionPointDetail)
                .where(qAuctionPointDetail.auction.auctionSeq.eq(seq))
                .orderBy(qAuctionPointDetail.auctionPointDetailPoint.desc())
                .fetchFirst(); // 결과를 하나만 가져오기
    }

    public Member readAuctionDetails(Integer seq) {
        // 경매의 상세 정보를 조회하는 로직을 여기에 추가
        return queryFactory.selectFrom(qMember) // 멤버 엔티티 선택
                .where(qAuctionPointDetail.auction.auctionSeq.eq(seq)
                        .and(qAuctionPointDetail.auctionPointDetailIsWin.eq(true)))
                .fetchOne(); // 첫 번째 결과 반환
    }

    public void updateAuctionDetails(AuctionPointDetail bidRequest) {
        //업데이트


    }
}
