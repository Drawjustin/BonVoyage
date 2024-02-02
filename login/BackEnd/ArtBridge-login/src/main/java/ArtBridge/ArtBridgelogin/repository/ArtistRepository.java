package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.QArtist;
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
public class ArtistRepository {

    private final EntityManager em;
    private QArtist qArtist = QArtist.artist;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Transactional
    public Artist create(Artist artist) {
        em.persist(artist);
        return artist;
    }

    @Transactional(readOnly = true)
    public Artist findArtistById(String artistId) {
        return queryFactory
                .selectFrom(qArtist)
                .where(qArtist.artistId.eq(artistId))
                .fetchOne();
    }

    @Transactional(readOnly = true)
    public List<Artist> findAll() {
        return queryFactory
                .selectFrom(qArtist)
                .fetchAll()
                .stream().toList();
    }

    @Transactional(readOnly = true)
    public Artist findByName(String name) {
        return queryFactory
                .selectFrom(qArtist)
                .where(qArtist.artistName.eq(name))
                .fetchOne();
    }

    @Transactional
    public void deleteById(String id) {
        queryFactory
                .delete(qArtist)
                .where(qArtist.artistId.eq(id))
                .execute();
    }

}
