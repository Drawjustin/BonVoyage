package ArtBridge.ArtBridgelogin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = -473187218L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItem item = new QItem("item");

    public final QArtist artist;

    public final ListPath<Auction, QAuction> auctions = this.<Auction, QAuction>createList("auctions", Auction.class, QAuction.class, PathInits.DIRECT2);

    public final StringPath explain = createString("explain");

    public final DateTimePath<java.time.LocalDateTime> itemCreatedDate = createDateTime("itemCreatedDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> itemHeight = createNumber("itemHeight", Integer.class);

    public final BooleanPath itemIsSold = createBoolean("itemIsSold");

    public final NumberPath<Integer> itemLike = createNumber("itemLike", Integer.class);

    public final StringPath itemName = createString("itemName");

    public final NumberPath<Long> itemSellPrice = createNumber("itemSellPrice", Long.class);

    public final NumberPath<Integer> itemSeq = createNumber("itemSeq", Integer.class);

    public final NumberPath<Integer> itemWidth = createNumber("itemWidth", Integer.class);

    public final ListPath<OrderDetail, QOrderDetail> orderDetails = this.<OrderDetail, QOrderDetail>createList("orderDetails", OrderDetail.class, QOrderDetail.class, PathInits.DIRECT2);

    public final ListPath<ArtBridge.ArtBridgelogin.domain.Connection.SaleLike, ArtBridge.ArtBridgelogin.domain.Connection.QSaleLike> saleLikes = this.<ArtBridge.ArtBridgelogin.domain.Connection.SaleLike, ArtBridge.ArtBridgelogin.domain.Connection.QSaleLike>createList("saleLikes", ArtBridge.ArtBridgelogin.domain.Connection.SaleLike.class, ArtBridge.ArtBridgelogin.domain.Connection.QSaleLike.class, PathInits.DIRECT2);

    public final ListPath<Wish, QWish> wishes = this.<Wish, QWish>createList("wishes", Wish.class, QWish.class, PathInits.DIRECT2);

    public QItem(String variable) {
        this(Item.class, forVariable(variable), INITS);
    }

    public QItem(Path<? extends Item> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItem(PathMetadata metadata, PathInits inits) {
        this(Item.class, metadata, inits);
    }

    public QItem(Class<? extends Item> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.artist = inits.isInitialized("artist") ? new QArtist(forProperty("artist")) : null;
    }

}

