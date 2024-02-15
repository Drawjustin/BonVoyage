package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.ArtistMention;
import ArtBridge.ArtBridgelogin.domain.QArtist;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArtistMentionRepository {

    @PersistenceContext
    private final EntityManager em;

    private final ArtBridge.ArtBridgelogin.domain.QArtistMention qArtistMention = ArtBridge.ArtBridgelogin.domain.QArtistMention.artistMention;

    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }

    public ArtistMentionRepository(EntityManager em) {
        this.em = em;
    }

    // 아티스트 멘션 생성 메서드
    public ArtistMention create(ArtistMention artistMention) {

        em.persist(artistMention);
        return artistMention;
    }

    // 모든 아티스트 멘션 조회 메서드
    public List<ArtistMention> readAll() {
        return queryFactory
                .selectFrom(qArtistMention)
                .fetch();
    }

    public List<ArtistMention> readAllbyArtist(Long Seq) {
        return queryFactory
                .selectFrom(qArtistMention)
                .where(qArtistMention.artist.artistSeq.eq(Seq))
                .fetch();
    }
    // 아티스트 멘션 일련번호로 조회 메서드
    public ArtistMention readOne(Long artistMentionId) {
        return queryFactory
                .selectFrom(qArtistMention)
                .where(qArtistMention.artist.artistSeq.eq(artistMentionId))
                .fetchOne();
    }

    public ArtistMention readById(Long artistMentionId) {
        return queryFactory
                .selectFrom(qArtistMention)
                .where(qArtistMention.artistMentionSeq.eq(artistMentionId))
                .fetchOne();
    }

    // 아티스트 멘션 업데이트 메서드
    public void updateArtistMention(Long artistMentionSeq, ArtistMention updatedArtistMention) {
        em.merge(updatedArtistMention);
    }

    // 아티스트 멘션 삭제 메서드
    public void deleteArtistMention(Long artistMentionSeq) {
        queryFactory
                .delete(qArtistMention)
                .where(qArtistMention.artistMentionSeq.eq(artistMentionSeq))
                .execute();
    }

}
