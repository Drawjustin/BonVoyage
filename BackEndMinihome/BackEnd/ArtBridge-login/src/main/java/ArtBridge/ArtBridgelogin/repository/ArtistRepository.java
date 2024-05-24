package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.QArtist;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

    public Artist create(Artist artist) {
        em.persist(artist);
        return artist;
    }

    public List<Artist> readAllArtistsSorted() {
        return queryFactory
                .selectFrom(qArtist)
                .orderBy(qArtist.artistName.asc())
                .fetch();
    }

    public Artist readArtistById(String artistId) {
        return queryFactory
                .selectFrom(qArtist)
                .where(qArtist.artistId.eq(artistId))
                .fetchOne();
    }
    public Artist readArtistBySeq(Long artistSeq) {
        return queryFactory
                .selectFrom(qArtist)
                .where(qArtist.artistSeq.eq(artistSeq))
                .fetchOne();
    }
//    @Transactional(readOnly = true)
//    public Optional<Artist> findById(String artistId) {
//        Artist artist = queryFactory
//                .selectFrom(qArtist)
//                .where(qArtist.artistId.eq(artistId))
//                .fetchOne();
//
//        return Optional.ofNullable(artist);
//    }

    public List<Artist> readAll() {
        return queryFactory
                .selectFrom(qArtist)
                .fetch();
    }
    public Artist updateArtist(String artistId, Artist updatedArtist) {
        long updatedCount = queryFactory
                .update(QArtist.artist)
                .set(QArtist.artist.artistName, updatedArtist.getArtistName())
                .set(QArtist.artist.artistPwd, updatedArtist.getArtistPwd())
                .set(QArtist.artist.artistNickname, updatedArtist.getArtistNickname())
                .set(QArtist.artist.artistEmail, updatedArtist.getArtistEmail())
                .set(QArtist.artist.artistContact, updatedArtist.getArtistContact())
                .set(QArtist.artist.artistPoint, updatedArtist.getArtistPoint())
                .set(QArtist.artist.artistIsdeleted, updatedArtist.isArtistIsdeleted())
                .set(QArtist.artist.artistDeletedDate, updatedArtist.getArtistDeletedDate())
                .set(QArtist.artist.artistCreatedDate, updatedArtist.getArtistCreatedDate())
                .where(QArtist.artist.artistId.eq(artistId))
                .execute();

        if (updatedCount > 0) {
            return queryFactory
                    .selectFrom(QArtist.artist)
                    .where(QArtist.artist.artistId.eq(artistId))
                    .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                    .fetchOne();
        } else {
            return null;
        }
    }


    public Artist readByName(String name) {
        return queryFactory
                .selectFrom(qArtist)
                .where(qArtist.artistName.eq(name))
                .fetchOne();
    }


    public void deleteById(long artistSeq) {
        Artist artist = em.find(Artist.class, artistSeq);
        em.remove(artist);
    }

}
