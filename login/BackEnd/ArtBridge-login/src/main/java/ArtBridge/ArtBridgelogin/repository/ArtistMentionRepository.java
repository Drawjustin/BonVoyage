package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArtistMentionRepository {

    private final EntityManager em;

    private QArtistMention qArtistMention = QArtistMention.artistMention;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }

    public ArtistMention create(ArtistMention artistMention) {
        em.persist(artistMention);
        return artistMention;
    }

    public ArtistMention readOne(Long artistMentionSeq) {
        ArtistMention artistMention = queryFactory
                .selectFrom(qArtistMention)
                .where(qArtistMention.artistMentionSeq.eq(artistMentionSeq))
                .fetchOne();

        if (artistMention == null) {
            throw new IllegalStateException("Cannot read ArtistMention with id: " + artistMentionSeq);
        }

        return artistMention;
    }

    public List<ArtistMention> readAll() {
        return queryFactory
                .selectFrom(qArtistMention)
                .stream().toList();
    }

    public void deleteBySeq(Long seq) {
        queryFactory
                .delete(qArtistMention)
                .where(qArtistMention.artistMentionSeq.eq(seq))
                .execute();
    }
    public ArtistMention artistMention(Long artistMentionSeq, ArtistMention updatedArtistMention) {

        ArtistMention managedArtistMention = queryFactory
                .selectFrom(QArtistMention.artistMention)
                .where(QArtistMention.artistMention.artistMentionSeq.eq(artistMentionSeq))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .fetchOne();


        // Update fields directly
        managedArtistMention.setArtistMentionContent(updatedArtistMention.getArtistMentionContent());
        managedArtistMention.setArtistMentionSubject(updatedArtistMention.getArtistMentionSubject());

        return managedArtistMention;
    }
}
