package ArtBridge.ArtBridgelogin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArtistHomepageComment is a Querydsl query type for ArtistHomepageComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArtistHomepageComment extends EntityPathBase<ArtistHomepageComment> {

    private static final long serialVersionUID = 1547915343L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArtistHomepageComment artistHomepageComment = new QArtistHomepageComment("artistHomepageComment");

    public final QArtist artist;

    public final BooleanPath artistHomepageCommentIsdeleted = createBoolean("artistHomepageCommentIsdeleted");

    public final NumberPath<Long> artistHomepageCommentSeq = createNumber("artistHomepageCommentSeq", Long.class);

    public final StringPath artistHompageCommentContent = createString("artistHompageCommentContent");

    public final DateTimePath<java.time.LocalDateTime> artistHompageCommentContentCreatedDate = createDateTime("artistHompageCommentContentCreatedDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> artistHompageCommentContentDeletedDate = createDateTime("artistHompageCommentContentDeletedDate", java.time.LocalDateTime.class);

    public final QMember member;

    public QArtistHomepageComment(String variable) {
        this(ArtistHomepageComment.class, forVariable(variable), INITS);
    }

    public QArtistHomepageComment(Path<? extends ArtistHomepageComment> path) {
        this(path.readType(), path.readMetadata(), PathInits.readFor(path.readMetadata(), INITS));
    }

    public QArtistHomepageComment(PathMetadata metadata) {
        this(metadata, PathInits.readFor(metadata, INITS));
    }

    public QArtistHomepageComment(PathMetadata metadata, PathInits inits) {
        this(ArtistHomepageComment.class, metadata, inits);
    }

    public QArtistHomepageComment(Class<? extends ArtistHomepageComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.artist = inits.isInitialized("artist") ? new QArtist(forProperty("artist")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

