package ArtBridge.ArtBridgelogin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArtistMentionComment is a Querydsl query type for ArtistMentionComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArtistMentionComment extends EntityPathBase<ArtistMentionComment> {

    private static final long serialVersionUID = 1426971543L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArtistMentionComment artistMentionComment = new QArtistMentionComment("artistMentionComment");

    public final QArtistMention artistMention;

    public final StringPath artistMentionCommentContent = createString("artistMentionCommentContent");

    public final DateTimePath<java.time.LocalDateTime> artistMentionCommentCreatedDate = createDateTime("artistMentionCommentCreatedDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> artistMentionCommentDeletedDate = createDateTime("artistMentionCommentDeletedDate", java.time.LocalDateTime.class);

    public final BooleanPath artistMentionCommentIsdeleted = createBoolean("artistMentionCommentIsdeleted");

    public final NumberPath<Long> artistMentionCommentSeq = createNumber("artistMentionCommentSeq", Long.class);

    public final QMember member;

    public QArtistMentionComment(String variable) {
        this(ArtistMentionComment.class, forVariable(variable), INITS);
    }

    public QArtistMentionComment(Path<? extends ArtistMentionComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArtistMentionComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArtistMentionComment(PathMetadata metadata, PathInits inits) {
        this(ArtistMentionComment.class, metadata, inits);
    }

    public QArtistMentionComment(Class<? extends ArtistMentionComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.artistMention = inits.isInitialized("artistMention") ? new QArtistMention(forProperty("artistMention"), inits.get("artistMention")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

