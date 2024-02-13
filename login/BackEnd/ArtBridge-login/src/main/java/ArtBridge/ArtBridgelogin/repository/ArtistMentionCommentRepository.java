package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.ArtistMentionComment;
import ArtBridge.ArtBridgelogin.domain.QArtistMentionComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArtistMentionCommentRepository {

    private final EntityManager em;
    private final QArtistMentionComment qArtistMentionComment = QArtistMentionComment.artistMentionComment;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }

    public List<ArtistMentionComment> findAll() {
        return queryFactory
                .selectFrom(qArtistMentionComment)
                .fetch();
    }

    public Optional<ArtistMentionComment> findById(Long artistMentionCommentSeq) {
        return Optional.ofNullable(queryFactory
                .selectFrom(qArtistMentionComment)
                .where(qArtistMentionComment.artistMentionCommentSeq.eq(artistMentionCommentSeq))
                .fetchOne());
    }

    public ArtistMentionComment save(ArtistMentionComment artistMentionComment) {
        if (artistMentionComment.getArtistMentionCommentSeq() == null) {
            em.persist(artistMentionComment);
        } else {
            em.merge(artistMentionComment);
        }
        return artistMentionComment;
    }

    public void deleteById(Long artistMentionCommentSeq) {
        queryFactory
                .delete(qArtistMentionComment)
                .where(qArtistMentionComment.artistMentionCommentSeq.eq(artistMentionCommentSeq))
                .execute();
    }
}
