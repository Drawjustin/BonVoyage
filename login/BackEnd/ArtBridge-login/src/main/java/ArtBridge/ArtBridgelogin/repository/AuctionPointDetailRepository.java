package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.AuctionPointDetail;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuctionPointDetailRepository {

    private final EntityManager em;

    @Transactional
    public AuctionPointDetail create(AuctionPointDetail auctionPointDetail){
        em.persist(auctionPointDetail);
        return auctionPointDetail;
    }

    @Transactional(readOnly = true)
    public AuctionPointDetail findOne(Long id){return em.find(AuctionPointDetail.class, id);}

    @Transactional(readOnly = true)
    public List<AuctionPointDetail> findAll(){
        return em.createQuery("select m from AuctionPointDetail m", AuctionPointDetail.class)
                .getResultList();
    }

    @Transactional
    public void deleteById(Long id) {
        em.createQuery("DELETE FROM AuctionPointDetail m WHERE m.auctionPointDetail = :id")
                .setParameter("AuctionPointDetail", id)
                .executeUpdate();
    }
}
