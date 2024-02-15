package ArtBridge.ArtBridgelogin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArtistMention is a Querydsl query type for ArtistMention
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArtistMention extends EntityPathBase<ArtistMention> {

    private static final long serialVersionUID = -996180568L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArtistMention artistMention = new QArtistMention("artistMention");

    public final QArtist artist;

    public final BooleanPath artistIsRemoved = createBoolean("artistIsRemoved");

    public final ListPath<ArtistMentionComment, QArtistMentionComment> artistMentionComments = this.<ArtistMentionComment, QArtistMentionComment>createList("artistMentionComments", ArtistMentionComment.class, QArtistMentionComment.class, PathInits.DIRECT2);

    public final StringPath artistMentionContent = createString("artistMentionContent");

    public final DateTimePath<java.time.LocalDateTime> artistMentionCreatedDate = createDateTime("artistMentionCreatedDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> artistMentionDeletedDate = createDateTime("artistMentionDeletedDate", java.time.LocalDateTime.class);

    public final BooleanPath artistMentionIsdeleted = createBoolean("artistMentionIsdeleted");

    public final NumberPath<Long> artistMentionSeq = createNumber("artistMentionSeq", Long.class);

    public final StringPath artistMentionSubject = createString("artistMentionSubject");

    public QArtistMention(String variable) {
        this(ArtistMention.class, forVariable(variable), INITS);
    }

    public QArtistMention(Path<? extends ArtistMention> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArtistMention(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArtistMention(PathMetadata metadata, PathInits inits) {
        this(ArtistMention.class, metadata, inits);
    }

    public QArtistMention(Class<? extends ArtistMention> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.artist = inits.isInitialized("artist") ? new QArtist(forProperty("artist")) : null;
    }

}

