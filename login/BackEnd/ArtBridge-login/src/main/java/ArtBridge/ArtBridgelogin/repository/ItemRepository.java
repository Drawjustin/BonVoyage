package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    private final EntityManager em;

    private final QItem qItem = QItem.item;
    private final QArtist qArtist = QArtist.artist;
    private final QMember qMember = QMember.member;
    private final QWish qWish = QWish.wish;
    private final QOrderDetail qOrderDetail = QOrderDetail.orderDetail;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }

    public ItemRepository(EntityManager em) {
        this.em = em;
    }

    // 아이템 생성 메서드
    public Item create(Item item) {
        em.persist(item);
        return item;
    }

    // 모든 아이템 조회 메서드
    public List<Item> readAll() {
        return queryFactory
                .selectFrom(qItem)
                .fetch();
    }

    public List<Item> readAllSortedByName() {
        return queryFactory.selectFrom(qItem)
                .orderBy(qItem.itemName.asc())
                .fetch();
    }

    public List<Item> readAllSortedByPrice() {
        return queryFactory.selectFrom(qItem)
                .orderBy(qItem.itemSellPrice.asc())
                .fetch();
    }
    public Wish readByMemberSeq(int itemSeq,Long memberSeq){
        return queryFactory.selectFrom(qWish)
                .where(qWish.item.itemSeq.eq(itemSeq).and(qWish.member.memberSeq.eq(memberSeq)))
                .fetchOne();
    }
    // 아이템 일련번호로 조회 메서드
    public Item readBySeq(int itemSeq) {
        return queryFactory
                .selectFrom(qItem)
                .where(qItem.itemSeq.eq(itemSeq))
                .fetchOne();
    }

    // 아이템 삭제 메서드
    public void deleteById(int itemSeq) {
        queryFactory
                .delete(qItem)
                .where(qItem.itemSeq.eq(itemSeq))
                .execute();
    }

    public List<Item> readItemsSortedByName() {
        return queryFactory.selectFrom(qItem)
                .orderBy(qItem.itemName.asc())
                .fetch();
    }

    public List<Item> readItemsSortedByPrice() {
        return queryFactory.selectFrom(qItem)
                .orderBy(qItem.itemSellPrice.asc())
                .fetch();
    }

    public List<Item> readItemsByArtistAndSort(Long artistSeq, String sort) {
        if ("name".equalsIgnoreCase(sort)) {
            return queryFactory
                    .selectFrom(qItem)
                    .where(qItem.artist.artistSeq.eq(artistSeq))
                    .orderBy(qItem.itemName.asc())
                    .fetch();
        } else if ("price".equalsIgnoreCase(sort)) {
            return queryFactory
                    .selectFrom(qItem)
                    .where(qItem.artist.artistSeq.eq(artistSeq))
                    .orderBy(qItem.itemSellPrice.asc())
                    .fetch();
        } else {
            return queryFactory
                    .selectFrom(qItem)
                    .where(qItem.artist.artistSeq.eq(artistSeq))
                    .fetch();
        }
    }
}
