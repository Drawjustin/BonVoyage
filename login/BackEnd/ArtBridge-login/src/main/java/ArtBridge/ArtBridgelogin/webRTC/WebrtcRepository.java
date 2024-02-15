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

    public AuctionPointDetail createBid(AuctionPointDetail bidRequest) {
        em.persist(bidRequest);
        return bidRequest;
//        try {
//            // semaphore.tryAcquire() 메소드로 3초 동안에만 허용되도록 시도
//            if (semaphore.tryAcquire(3, TimeUnit.SECONDS)) {
//                // 3초 동안 대기
//                Thread.sleep(3000);
//
//                // 3초 동안에 들어온 입력을 무시하는 로직 추가
//                System.out.println("3초 동안에 들어온 입력을 무시합니다.");
//
//            } else {
//                // 3초 안에 입력을 받지 않았을 경우 처리
//                // 여기에 실제 입력 처리 로직을 추가하면 됩니다.
//                System.out.println("3초 동안에 받은 입력을 무시합니다.");
//
//                // 입력을 무시하고 반환
//                return;
//            }
//
//            // 경매에 대한 입찰을 생성하는 로직을 여기에 추가
//            em.lock(bidRequest, LockModeType.PESSIMISTIC_WRITE);
//
//            try {
//                // 경매에 대한 입찰을 데이터베이스에 등록
//                em.persist(bidRequest);
//
//                // 트랜잭션 내용을 즉시 데이터베이스에 전송 (버퍼에 저장하지 않음)
//                em.flush();
//            } finally {
//                // 락을 해제
//                em.clear();
//            }
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        } finally {
//            // semaphore.release() 메소드를 호출하여 락 해제
//            semaphore.release();
//        }
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
