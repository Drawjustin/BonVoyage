package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.Auction;
import ArtBridge.ArtBridgelogin.domain.QArtist;
import ArtBridge.ArtBridgelogin.domain.QAuction;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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


    public Auction create(Auction auction) {
        em.persist(auction);
        return auction;
    }


    public Auction findOne(int seq) {
        return queryFactory
                .selectFrom(qAuction)
                .where(qAuction.auctionSeq.eq(seq))
                .fetchOne();
    }


    public List<Auction> findAll() {
        return queryFactory
                .selectFrom(qAuction)
                .fetch();
    }


    public Auction updateAuction(int seq, Auction updatedAuction) {
        // Pessimistic write lock 설정
        Optional<Auction> auctionOptional = Optional.ofNullable(
                queryFactory
                        .selectFrom(QAuction.auction)
                        .where(QAuction.auction.auctionSeq.eq(seq))
                        .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                        .fetchOne()
        );

        Auction auction = auctionOptional.orElseThrow(() ->
                new NoSuchElementException("Auction with seq " + seq + " not found"));

        // 업데이트할 정보를 새로운 정보로 설정
        auction.setAuctionScheduledTime(updatedAuction.getAuctionScheduledTime());
        auction.setAuctionStatus(updatedAuction.getAuctionStatus());
        auction.setAuctionStartPoint(updatedAuction.getAuctionStartPoint());
        auction.setAuctionAskPoint(updatedAuction.getAuctionAskPoint());
        auction.setAuctionSellPoint(updatedAuction.getAuctionSellPoint());

        // 수정 작업 수행
        queryFactory.update(QAuction.auction)
                .set(QAuction.auction.auctionScheduledTime, updatedAuction.getAuctionScheduledTime())
                .set(QAuction.auction.auctionStatus, updatedAuction.getAuctionStatus())
                .set(QAuction.auction.auctionStartPoint, updatedAuction.getAuctionStartPoint())
                .set(QAuction.auction.auctionAskPoint, updatedAuction.getAuctionAskPoint())
                .set(QAuction.auction.auctionSellPoint, updatedAuction.getAuctionSellPoint())
                .where(QAuction.auction.auctionSeq.eq(seq))
                .execute();

        return auction;
    }


    public void deleteById(int seq) {
        queryFactory
                .delete(qAuction)
                .where(qAuction.auctionSeq.eq(seq))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .execute();
    }
}
