package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.domain.QArtist;
import ArtBridge.ArtBridgelogin.domain.QItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    private QItem qItem = QItem.item;

    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() { queryFactory = new JPAQueryFactory(em);}

    @Transactional
    public Item create(Item item) {
        em.persist(item);
        return item;
    }

    @Transactional(readOnly = true)
    public List<Item> findAll(){
        return queryFactory
                .selectFrom(qItem)
                .fetch();
    }

    @Transactional(readOnly = true)
    public Item findBySeq(int itemSeq){
        return queryFactory
                .selectFrom(qItem)
                .where(qItem.itemSeq.eq(itemSeq))
                .fetchOne();
    }

    @Transactional
    public void deleteById(int itemSeq) {
        queryFactory
                .delete(qItem)
                .where(qItem.itemSeq.eq(itemSeq))
                .execute();

    }

    public List<Item> findPopularItems() {
        //TODO : 제한 값을 따로 인자로 둬서 나중에 편하게 바꿀 수 있도록
        return queryFactory
                .selectFrom(qItem)
                .orderBy(qItem.itemLike.desc())
                .fetch();
    }

    public List<Item> findLastedItems() {
        //TODO : 제한 값을 따로 인자로 둬서 나중에 편하게 바꿀 수 있도록
        return queryFactory
                .selectFrom(qItem)
                .orderBy(qItem.itemCreatedDate.desc())
                .fetch();
    }

    //TODO: join 해결 이후 진행 필요
//    public List<Item> findSameAuthorItems(int artistSeq) {
//        TypedQuery<Item> query = em.createQuery("SELECT i FROM Item i WHERE i.artist_seq = :artistSeq", Item.class);
//        query.setParameter("artistSeq", artistSeq);
//        return query.getResultList();
//    }
}
