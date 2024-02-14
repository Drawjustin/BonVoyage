package ArtBridge.ArtBridgelogin.webRTC;

import ArtBridge.ArtBridgelogin.controller.dto.webRTC.AuctionPointDetailDto;
import ArtBridge.ArtBridgelogin.domain.AuctionPointDetail;
import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.domain.QAuctionPointDetail;
import ArtBridge.ArtBridgelogin.domain.QMember;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


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

        // 데이터베이스에 락을 걸어 다른 트랜잭션이 해당 레코드에 접근하지 못하도록 함
        em.lock(bidRequest, LockModeType.PESSIMISTIC_WRITE);

        try {
            // 3초 동안 대기
            Thread.sleep(3000);

            // 경매에 대한 입찰을 데이터베이스에 등록
            em.persist(bidRequest);

            // 트랜잭션 커밋 시 락을 해제
            em.flush();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // 락을 해제
            em.clear();
        }
    }


    public Member readWinner(Integer seq) {
        // 경매에서 낙찰자를 조회하는 로직을 여기에 추가
        return queryFactory.select(qMember)
                .from(qAuctionPointDetail)
                .innerJoin(qAuctionPointDetail.member, qMember)
                .where(qAuctionPointDetail.auction.auctionSeq.eq(seq)
                        .and(qAuctionPointDetail.auctionPointDetailIsWin.eq(true)))
                .fetchOne();

    }

    public AuctionPointDetail readCurrentPrice(Integer seq) {
        // 경매의 현재 가격을 조회하는 로직을 여기에 추가

        return queryFactory.selectFrom(qAuctionPointDetail)
                .where(qAuctionPointDetail.auction.auctionSeq.eq(seq))
                .orderBy(qAuctionPointDetail.auctionPointDetailPoint.desc())
                .fetchOne(); // 결과를 하나만 가져오기
    }

    public void updateAuctionDetails(Integer seq) {
        //업데이트
        AuctionPointDetail highestBid = queryFactory.selectFrom(qAuctionPointDetail)
                .where(qAuctionPointDetail.auction.auctionSeq.eq(seq))
                .orderBy(qAuctionPointDetail.auctionPointDetailPoint.desc())
                .fetchFirst();

        // 조회된 입찰이 존재하고 있다면 해당 입찰의 isWin을 true로 업데이트
        if (highestBid != null) {
            highestBid.setAuctionPointDetailIsWin(true);
            em.merge(highestBid);
        }


    }

    public List<AuctionPointDetail> readBidListByAuctionSeq(int seq) {
        return queryFactory.selectFrom(qAuctionPointDetail)
                .where(qAuctionPointDetail.auction.auctionSeq.eq(seq))
                .orderBy(qAuctionPointDetail.auctionPointDate.desc())
                .fetch();
    }
}
