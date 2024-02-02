package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.ArtistMention;
import ArtBridge.ArtBridgelogin.domain.QArtist;
import ArtBridge.ArtBridgelogin.domain.QArtistMention;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
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

    @Transactional
    public ArtistMention create(ArtistMention artistMention) {
        em.persist(artistMention);
        return artistMention;
    }

    @Transactional(readOnly = true)
    public ArtistMention findOne(Long artistMentionSeq) {
        ArtistMention artistMention = queryFactory
                .selectFrom(qArtistMention)
                .where(qArtistMention.artistMentionSeq.eq(artistMentionSeq))
                .fetchOne();

        if (artistMention == null) {
            throw new IllegalStateException("Cannot find ArtistMention with id: " + artistMentionSeq);
        }

        return artistMention;
    }

    @Transactional(readOnly = true)
    public List<ArtistMention> findAll() {
        return queryFactory
                .selectFrom(qArtistMention)
                .stream().toList();
    }

    @Transactional
    public void deleteBySeq(Long seq) {
        queryFactory
                .delete(qArtistMention)
                .where(qArtistMention.artistMentionSeq.eq(seq))
                .execute();
    }
}
