package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.AuctionPointDetail;
import ArtBridge.ArtBridgelogin.domain.QAuctionPointDetail;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static ArtBridge.ArtBridgelogin.domain.QAuctionPointDetail.auctionPointDetail;

@Repository
@RequiredArgsConstructor
public class AuctionPointDetailRepository {

    private final EntityManager em;
    private QAuctionPointDetail qAuctionPointDetail = auctionPointDetail;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }


    public AuctionPointDetail create(AuctionPointDetail auctionPointDetail){
        em.persist(auctionPointDetail);
        return auctionPointDetail;
    }

    public AuctionPointDetail readOne(Long id){return em.find(AuctionPointDetail.class, id);}
 
    public AuctionPointDetail readOne(int seq){
        return queryFactory.selectFrom(auctionPointDetail)
                .where(auctionPointDetail.auctionPointDetailSeq.eq(seq))
                .fetchOne();
    }

    public List<AuctionPointDetail> readAll(){
        return queryFactory.selectFrom(auctionPointDetail)
                .fetch();
    }

    public void updateWinner(int seq, boolean isWin){
        queryFactory.update(auctionPointDetail)
                .set(auctionPointDetail.auctionPointDetailIsWin, isWin)
                .where(auctionPointDetail.auctionPointDetailSeq.eq(seq))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .execute();
    }
}
