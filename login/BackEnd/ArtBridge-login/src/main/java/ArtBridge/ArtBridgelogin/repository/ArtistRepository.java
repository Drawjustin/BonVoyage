package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.Artist;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArtistRepository {

    private final EntityManager em;

    @Transactional
    public Artist create(Artist artist) {
        em.persist(artist);
        return artist;
    }

    @Transactional(readOnly = true)
    public Artist findOne(Long id){return em.find(Artist.class, id);}

    @Transactional(readOnly = true)
    public List<Artist> findAll(){
        return em.createQuery("select m from Artist m", Artist.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<Artist> findByName(String name){
        return em.createQuery("select m from Artist m where m.artistName = :name", Artist.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Transactional
    public void deleteById(Long id) {
        em.createQuery("DELETE FROM Artist m WHERE m.artistSeq = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
