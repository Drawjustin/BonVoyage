package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.Auction;
import ArtBridge.ArtBridgelogin.domain.QArtist;
import ArtBridge.ArtBridgelogin.domain.QAuction;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuctionRepository {


    private final EntityManager em;
    private QAuction qAuction = QAuction.auction;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Transactional
    public Auction create(Auction auction) {
        em.persist(auction);
        return auction;
    }

    @Transactional(readOnly = true)
    public Auction findOne(int seq) {
        return queryFactory
                .selectFrom(qAuction)
                .where(qAuction.auctionSeq.eq(seq))
                .fetchOne();
    }

    @Transactional(readOnly = true)
    public List<Auction> findAll() {
        return queryFactory
                .selectFrom(qAuction)
                .fetch();
    }

    @Transactional
    public void updateAuction(Auction updatedAuction) {
        queryFactory
                .update(qAuction)
                .where(qAuction.auctionSeq.eq(updatedAuction.getAuctionSeq()))
                .set(qAuction.auctionScheduledTime, updatedAuction.getAuctionScheduledTime())
                .set(qAuction.auctionStatus, updatedAuction.getAuctionStatus())
                .set(qAuction.auctionStartPoint, updatedAuction.getAuctionStartPoint())
                .set(qAuction.auctionAskPoint, updatedAuction.getAuctionAskPoint())
                .set(qAuction.auctionSellPoint, updatedAuction.getAuctionSellPoint())
                .execute();
    }

    @Transactional
    public void deleteById(int seq) {
        queryFactory
                .delete(qAuction)
                .where(qAuction.auctionSeq.eq(seq))
                .execute();
    }
}
