package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.domain.QItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    private final EntityManager em;

    private final QItem qItem = QItem.item;

    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }

    public ItemRepository(EntityManager em) {
        this.em = em;
    }

    // 아이템 생성 메서드
    @Transactional
    public Item create(Item item) {
        em.persist(item);
        return item;
    }

    // 모든 아이템 조회 메서드
    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return queryFactory
                .selectFrom(qItem)
                .fetch();
    }

    // 아이템 일련번호로 조회 메서드
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Item findBySeq(int itemSeq) {
        return queryFactory
                .selectFrom(qItem)
                .where(qItem.itemSeq.eq(itemSeq))
                .fetchOne();
    }

    // 아이템 삭제 메서드
    @Transactional
    public void deleteById(int itemSeq) {
        queryFactory
                .delete(qItem)
                .where(qItem.itemSeq.eq(itemSeq))
                .execute();
    }

    // 인기 아이템 조회 메서드
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Item> findPopularItems() {
        return queryFactory
                .selectFrom(qItem)
                .orderBy(qItem.itemLike.desc())
                .fetch();
    }

    // 최신 아이템 조회 메서드
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Item> findLastedItems() {
        return queryFactory
                .selectFrom(qItem)
                .orderBy(qItem.itemCreatedDate.desc())
                .fetch();
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
}
