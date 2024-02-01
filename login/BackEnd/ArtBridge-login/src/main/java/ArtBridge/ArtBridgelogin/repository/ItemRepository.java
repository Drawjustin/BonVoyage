package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ItemRepository {
    private final EntityManager em;

    @Transactional
    public Item create(Item item) {
        em.persist(item);
        return item;
    }

    @Transactional(readOnly = true)
    public Item findByName(String itemName) {
        String jpql = "SELECT a FROM Item a WHERE a.itemName = :itemName";
        TypedQuery<Item> query = em.createQuery(jpql, Item.class);
        query.setParameter("itemName", itemName);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // 해당 조건에 맞는 결과가 없을 경우
        }
    }

    @Transactional(readOnly = true)
    public List<Item> findAll(){
        return em.createQuery("select m from Item m", Item.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Item findById(int itemSeq){
        return em.createQuery("select m from Item m where m.itemSeq = :itemSeq", Item.class)
                .setParameter("itemSeq", itemSeq)
                .getSingleResult();
    }

    @Transactional
    public void deleteById(int itemSeq) {
        em.createQuery("DELETE FROM Item m WHERE m.itemSeq = :itemSeq")
                .setParameter("itemSeq", itemSeq)
                .executeUpdate();
    }

    public List<Item> findPopularItems() {
        //TODO : 제한 값을 따로 인자로 둬서 나중에 편하게 바꿀 수 있도록
        return em.createQuery("select m from Item m order by item_like desc limit 100", Item.class)
                .getResultList();
    }

    public List<Item> findNewItems() {
        //TODO : 제한 값을 따로 인자로 둬서 나중에 편하게 바꿀 수 있도록
        return em.createQuery("select m from Item m order by item_created_date asc limit 100", Item.class)
                .getResultList();
    }

    //TODO: join 해결 이후 진행 필요
//    public List<Item> findSameAuthorItems(int artistSeq) {
//        TypedQuery<Item> query = em.createQuery("SELECT i FROM Item i WHERE i.artist_seq = :artistSeq", Item.class);
//        query.setParameter("artistSeq", artistSeq);
//        return query.getResultList();
//    }
}
