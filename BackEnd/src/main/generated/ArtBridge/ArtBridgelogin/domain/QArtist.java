package ArtBridge.ArtBridgelogin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArtist is a Querydsl query type for Artist
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArtist extends EntityPathBase<Artist> {

    private static final long serialVersionUID = 303183330L;

    public static final QArtist artist = new QArtist("artist");

    public final StringPath artistContact = createString("artistContact");

    public final DateTimePath<java.time.LocalDateTime> artistCreatedDate = createDateTime("artistCreatedDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> artistDeletedDate = createDateTime("artistDeletedDate", java.time.LocalDateTime.class);

    public final StringPath artistEmail = createString("artistEmail");

    public final StringPath artistHistory = createString("artistHistory");

    public final ListPath<ArtistHomepageComment, QArtistHomepageComment> artistHomepageComments = this.<ArtistHomepageComment, QArtistHomepageComment>createList("artistHomepageComments", ArtistHomepageComment.class, QArtistHomepageComment.class, PathInits.DIRECT2);

    public final StringPath artistId = createString("artistId");

    public final BooleanPath artistIsdeleted = createBoolean("artistIsdeleted");

    public final ListPath<ArtistMention, QArtistMention> artistMentions = this.<ArtistMention, QArtistMention>createList("artistMentions", ArtistMention.class, QArtistMention.class, PathInits.DIRECT2);

    public final StringPath artistName = createString("artistName");

    public final StringPath artistNickname = createString("artistNickname");

    public final NumberPath<Long> artistPoint = createNumber("artistPoint", Long.class);

    public final StringPath artistPwd = createString("artistPwd");

    public final NumberPath<Long> artistSeq = createNumber("artistSeq", Long.class);

    public final ListPath<Item, QItem> items = this.<Item, QItem>createList("items", Item.class, QItem.class, PathInits.DIRECT2);

    public final ListPath<OrderDetail, QOrderDetail> orderDetails = this.<OrderDetail, QOrderDetail>createList("orderDetails", OrderDetail.class, QOrderDetail.class, PathInits.DIRECT2);

    public final ListPath<Review, QReview> reviews = this.<Review, QReview>createList("reviews", Review.class, QReview.class, PathInits.DIRECT2);

    public QArtist(String variable) {
        super(Artist.class, forVariable(variable));
    }

    public QArtist(Path<? extends Artist> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArtist(PathMetadata metadata) {
        super(Artist.class, metadata);
    }

}

