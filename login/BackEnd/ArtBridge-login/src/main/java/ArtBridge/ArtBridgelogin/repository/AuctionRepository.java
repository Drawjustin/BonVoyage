package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.Auction;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuctionRepository {


    private final EntityManager em;

    @Transactional
    public Auction create(Auction auction){
        em.persist(auction);
        return auction;
    }

    @Transactional(readOnly = true)
    public Auction findOne(Long id){return em.find(Auction.class, id);}

    @Transactional(readOnly = true)
    public List<Auction> findAll(){
        return em.createQuery("select m from Auction m", Auction.class)
                .getResultList();
    }

    @Transactional
    public void deleteById(Long id) {
        em.createQuery("DELETE FROM Auction m WHERE m.artistId = :id")
                .setParameter("artistId", id)
                .executeUpdate();
    }
}
