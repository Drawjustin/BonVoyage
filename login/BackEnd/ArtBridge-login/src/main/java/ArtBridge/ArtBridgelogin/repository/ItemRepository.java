package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.domain.QItem;
import ArtBridge.ArtBridgelogin.domain.QMember;
import ArtBridge.ArtBridgelogin.domain.QOrderDetail;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepository {

    @PersistenceContext
    private final EntityManager em;

    private final QItem qItem = QItem.item;

    private final QMember qMember = QMember.member;
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
    public List<Item> findAll() {
        return queryFactory
                .selectFrom(qItem)
                .fetch();
    }

    // 아이템 일련번호로 조회 메서드
    public Item findBySeq(int itemSeq) {
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

    // 인기 아이템 조회 메서드
    public List<Item> findPopularItems() {
        return queryFactory
                .selectFrom(qItem)
                .orderBy(qItem.itemLike.desc())
                .fetch();
    }

    // 최신 아이템 조회 메서드
    public List<Item> findLastedItems() {
        return queryFactory
                .selectFrom(qItem)
                .orderBy(qItem.itemCreatedDate.desc())
                .fetch();
    }

    public Item findAndUpdateItem(int itemSeq, Item updatedItem) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(QItem.item)
                        .where(QItem.item.itemSeq.eq(itemSeq))
                        .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                        .fetchOne()
        ).map(existingItem -> {
            // Update fields directly
            existingItem.setItemName(updatedItem.getItemName());
            existingItem.setItemHeight(updatedItem.getItemHeight());
            existingItem.setItemWidth(updatedItem.getItemWidth());
            existingItem.setItemIsSold(updatedItem.isItemIsSold());
            existingItem.setItemSellPrice(updatedItem.getItemSellPrice());

            // No need to create a new item, just return the updated item
            return existingItem;
        }).orElseThrow(() -> new IllegalArgumentException("Item with id " + itemSeq + " not found"));
    }



    // 작가의 아이템 조회 메서드
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Item> findSameAuthorItems(long artistSeq) {
        return queryFactory
                .selectFrom(qItem)
                .where(qItem.artist.artistSeq.eq(artistSeq))
                .fetch();
    }

    // 아이템 비관적 잠금 조회 메서드
    @Transactional
    public Item findByIdWithPessimisticLock(int itemId) {
        return em.find(Item.class, itemId, LockModeType.PESSIMISTIC_WRITE);
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Item> getItemsBySameMember(Long authorId) {
        return queryFactory
                .selectFrom(QItem.item)
                .where(QOrderDetail.orderDetail.member.memberSeq.eq(authorId))
                .fetch();
    }
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Item> getItemsBySameArtist(Long authorId) {
        return queryFactory
                .selectFrom(QItem.item)
                .where(QOrderDetail.orderDetail.member.memberSeq.eq(authorId))
                .fetch();
    }

}
