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
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


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

    private final Semaphore semaphore = new Semaphore(1);

    public void createBid(Long seq, AuctionPointDetail bidRequest) {
        try {
            // semaphore.acquire() 메소드를 호출하여 처음 입력을 받을 때만 락 획득
            semaphore.acquire();

            // 3초 동안 대기
            Thread.sleep(3000);

            // 3초 동안에 들어온 입력들을 무시하도록 처리
            // (실제로는 이 부분을 무시할 입력을 처리하지 않도록 수정해야 함)

            // 경매에 대한 입찰을 데이터베이스에 등록
            em.persist(bidRequest);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // 처음 입력을 받을 때만 semaphore.release() 메소드를 호출하여 락 해제
            semaphore.release();
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
