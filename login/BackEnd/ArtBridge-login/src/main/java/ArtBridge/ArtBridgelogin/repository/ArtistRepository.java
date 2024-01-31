package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
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
    public Artist findArtistByName(String artistId) {
        String jpql = "SELECT a FROM artist a WHERE a.artist_id = :artistId";
        TypedQuery<Artist> query = em.createQuery(jpql, Artist.class);
        query.setParameter("artistId", artistId);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // 해당 조건에 맞는 결과가 없을 경우
        }
    }

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
    public void deleteById(String id) {
        em.createQuery("DELETE FROM Artist m WHERE m.artistId = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
