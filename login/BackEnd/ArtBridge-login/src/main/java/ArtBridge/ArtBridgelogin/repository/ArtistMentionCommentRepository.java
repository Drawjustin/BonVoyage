package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.ArtistMentionComment;
import ArtBridge.ArtBridgelogin.domain.Auction;
import ArtBridge.ArtBridgelogin.domain.QArtistMention;
import ArtBridge.ArtBridgelogin.domain.QArtistMentionComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArtistMentionCommentRepository {

    private final EntityManager em;

    private QArtistMentionComment qArtistMentionComment = QArtistMentionComment.artistMentionComment;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Transactional
    public ArtistMentionComment create(ArtistMentionComment artistMentionComment) {
        em.persist(artistMentionComment);
        return artistMentionComment;
    }

    @Transactional(readOnly = true)
    public ArtistMentionComment findOne(Long seq) {
        return queryFactory
                .selectFrom(qArtistMentionComment)
                .where(qArtistMentionComment.artistMentionCommentSeq.eq(seq))
                .fetchOne();
    }

    @Transactional(readOnly = true)
    public List<ArtistMentionComment> findAll() {
        return queryFactory
                .selectFrom(qArtistMentionComment)
                .fetch();
    }

    @Transactional
    public void deleteById(Long seq) {
        queryFactory
                .delete(qArtistMentionComment)
                .where(qArtistMentionComment.artistMentionCommentSeq.eq(seq))
                .execute();
    }

    @Transactional
    public ArtistMentionComment updateArtistMentionComment(Long seq, ArtistMentionComment updatedComment) {
        queryFactory
                .update(qArtistMentionComment)
                .where(qArtistMentionComment.artistMentionCommentSeq.eq(seq))
                .set(qArtistMentionComment.artistMentionCommentContent, updatedComment.getArtistMentionCommentContent())
                .execute();

        return queryFactory.selectFrom(qArtistMentionComment)
                .where(qArtistMentionComment.artistMentionCommentSeq.eq(seq))
                .fetchOne();
    }
}
