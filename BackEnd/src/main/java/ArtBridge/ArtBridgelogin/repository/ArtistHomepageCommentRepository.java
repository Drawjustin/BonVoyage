package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.ArtistHomepageComment;
import ArtBridge.ArtBridgelogin.domain.QArtistHomepageComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ArtistHomepageCommentRepository {

    private final EntityManager em;

    private QArtistHomepageComment qArtistHomepageComment  = QArtistHomepageComment.artistHomepageComment;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }
    public ArtistHomepageComment create(ArtistHomepageComment artistHomepageComment){
        em.persist(artistHomepageComment);
        return artistHomepageComment;
    }

    public ArtistHomepageComment readOne(Long seq){
        return queryFactory
                .selectFrom(qArtistHomepageComment)
                .where(qArtistHomepageComment.artistHomepageCommentSeq.eq(seq))
                .fetchOne();
    }

    public List<ArtistHomepageComment> readAll(){
        return queryFactory
                .selectFrom(qArtistHomepageComment)
                .fetch();
     }

    public void deleteBySeq(Long seq) {
        queryFactory
                .delete(qArtistHomepageComment)
                .where(qArtistHomepageComment.artistHomepageCommentSeq.eq(seq))
                .execute();
    }

    public ArtistHomepageComment updateArtistHomepageComment(Long seq, ArtistHomepageComment updatedComment) {
        queryFactory
                .update(qArtistHomepageComment)
                .where(qArtistHomepageComment.artistHomepageCommentSeq.eq(seq))
                .set(qArtistHomepageComment.artistHomepageCommentContent, updatedComment.getArtistHomepageCommentContent())
                .execute();

        return queryFactory.selectFrom(qArtistHomepageComment)
                .where(qArtistHomepageComment.artistHomepageCommentSeq.eq(seq))
                .fetchOne();
    }

    public List<ArtistHomepageComment> readAlLHomepageCommentByArtist(Long seq) {
        return queryFactory.selectFrom(qArtistHomepageComment)
                .where(qArtistHomepageComment.artist.artistSeq.eq(seq))
                .fetch();

    }
}
