package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
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

    public Artist create(Artist artist) {
        em.persist(artist);
        return artist;
    }

    public Artist readArtistById(String artistId) {
        return queryFactory
                .selectFrom(qArtist)
                .where(qArtist.artistId.eq(artistId))
                .fetchOne();
    }

    public List<Artist> readAll() {
        return queryFactory
                .selectFrom(qArtist)
                .fetchAll()
                .stream().toList();
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

    public void deleteById(String id) {
        queryFactory
                .delete(qArtist)
                .where(qArtist.artistId.eq(id))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .execute();
    }

}
