package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.AuctionPointDetail;
import ArtBridge.ArtBridgelogin.domain.QAuctionPointDetail;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
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


    @Transactional
    public AuctionPointDetail create(AuctionPointDetail auctionPointDetail){
        em.persist(auctionPointDetail);
        return auctionPointDetail;
    }

    @Transactional(readOnly = true)
<<<<<<< HEAD
    public AuctionPointDetail findOne(Long id){return em.find(AuctionPointDetail.class, id);}
 
=======
    public AuctionPointDetail findOne(int seq){
        return queryFactory.selectFrom(auctionPointDetail)
                .where(auctionPointDetail.auctionPointDetailSeq.eq(seq))
                .fetchOne();
    }

>>>>>>> ece6102412c50642288b6a4665018a3647f6a951
    @Transactional(readOnly = true)
    public List<AuctionPointDetail> findAll(){
        return queryFactory.selectFrom(auctionPointDetail)
                .fetch();
    }

    @Transactional
    public void updateWinner(int seq, boolean isWin){
        queryFactory.update(auctionPointDetail)
                .set(auctionPointDetail.auctionPointDetailIsWin, isWin)
                .where(auctionPointDetail.auctionPointDetailSeq.eq(seq))
                .execute();
    }
}
