package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuctionRepository {


    private final EntityManager em;
    private QAuction qAuction = QAuction.auction;
    private QMemberAuctionBidding qMemberAuctionBidding = QMemberAuctionBidding.memberAuctionBidding;

    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }


    public Auction create(Auction auction) {
        em.persist(auction);
        return auction;
    }

    public Auction readOne(int seq){
        return em.find(Auction.class, seq);
    }

    public Item readItemByAuctionSeq(int seq){
        return queryFactory.selectFrom(QItem.item)
                .join(QAuction.auction)
                .on(QAuction.auction.item.itemSeq.eq(QItem.item.itemSeq))
                .where(QAuction.auction.auctionSeq.eq(seq))
                .fetchOne();
    }

    public List<Auction> readAll() {
        return queryFactory
                .selectFrom(qAuction)
                .fetch();
    }
    public Artist readArtistByItemSeq(int itemseq) {
        return queryFactory.select(QArtist.artist)
                .from(QItem.item)
                .join(QItem.item.artist, QArtist.artist)
                .where(QItem.item.itemSeq.eq(itemseq))
                .fetchOne();

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

        // 수정 작업 수행
        queryFactory.update(QAuction.auction)
                .set(QAuction.auction.auctionScheduledTime, updatedAuction.getAuctionScheduledTime())
                .set(QAuction.auction.auctionStatus, updatedAuction.getAuctionStatus())
                .set(QAuction.auction.auctionStartPoint, updatedAuction.getAuctionStartPoint())
                .set(QAuction.auction.auctionAskPoint, updatedAuction.getAuctionAskPoint())
                .where(QAuction.auction.auctionSeq.eq(seq))
                .execute();

        return auction;
    }


//    public void deleteById(int seq) {
//        queryFactory
//                .delete(qAuction)
//                .where(qAuction.auctionSeq.eq(seq))
//                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
//                .execute();
//    }

    public void deleteById(int auctionSeq) {
        Auction auction = em.find(Auction.class, auctionSeq);
        em.remove(auction);
    }


    public List<Auction> readAuctionsBySameAuthor(Long authorId) {
        return queryFactory
                .selectFrom(qAuction)
                .where(qAuction.item.artist.artistSeq.eq(authorId))
                .fetch();
    }

    public void deleteAuctionByMember(int seq) {
        queryFactory
                .delete(qAuction)
                .where(qMemberAuctionBidding.auction.auctionSeq.eq(seq))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .execute();
    }
    public void deleteAuctionByArtist(int seq) {
        queryFactory
                .delete(qAuction)
                .where(qMemberAuctionBidding.auction.auctionSeq.eq(seq))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .execute();
    }



}
