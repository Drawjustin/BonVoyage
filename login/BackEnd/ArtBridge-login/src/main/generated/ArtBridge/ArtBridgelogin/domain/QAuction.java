package ArtBridge.ArtBridgelogin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuction is a Querydsl query type for Auction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuction extends EntityPathBase<Auction> {

    private static final long serialVersionUID = 879254280L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAuction auction = new QAuction("auction");

    public final NumberPath<Integer> auctionAskPoint = createNumber("auctionAskPoint", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> auctionCanceledDate = createDateTime("auctionCanceledDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> auctionCreatedDate = createDateTime("auctionCreatedDate", java.time.LocalDateTime.class);

    public final BooleanPath auctionIsMiscarried = createBoolean("auctionIsMiscarried");

    public final ListPath<ArtBridge.ArtBridgelogin.domain.Connection.AuctionLike, ArtBridge.ArtBridgelogin.domain.Connection.QAuctionLike> auctionLikes = this.<ArtBridge.ArtBridgelogin.domain.Connection.AuctionLike, ArtBridge.ArtBridgelogin.domain.Connection.QAuctionLike>createList("auctionLikes", ArtBridge.ArtBridgelogin.domain.Connection.AuctionLike.class, ArtBridge.ArtBridgelogin.domain.Connection.QAuctionLike.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> auctionMiscarriedDate = createDateTime("auctionMiscarriedDate", java.time.LocalDateTime.class);

    public final ListPath<AuctionPointDetail, QAuctionPointDetail> auctionPointDetails = this.<AuctionPointDetail, QAuctionPointDetail>createList("auctionPointDetails", AuctionPointDetail.class, QAuctionPointDetail.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> auctionScheduledTime = createDateTime("auctionScheduledTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> auctionSellPoint = createNumber("auctionSellPoint", Long.class);

    public final NumberPath<Integer> auctionSeq = createNumber("auctionSeq", Integer.class);

    public final StringPath auctionSessionId = createString("auctionSessionId");

    public final NumberPath<Integer> auctionStartPoint = createNumber("auctionStartPoint", Integer.class);

    public final NumberPath<Integer> auctionStatus = createNumber("auctionStatus", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> auctionWinDate = createDateTime("auctionWinDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> auctionWinner = createNumber("auctionWinner", Integer.class);

    public final QItem item;

    public final ListPath<MemberAuctionBidding, QMemberAuctionBidding> memberAuctionBiddings = this.<MemberAuctionBidding, QMemberAuctionBidding>createList("memberAuctionBiddings", MemberAuctionBidding.class, QMemberAuctionBidding.class, PathInits.DIRECT2);

    public final ListPath<OrderDetail, QOrderDetail> orderDetails = this.<OrderDetail, QOrderDetail>createList("orderDetails", OrderDetail.class, QOrderDetail.class, PathInits.DIRECT2);

    public QAuction(String variable) {
        this(Auction.class, forVariable(variable), INITS);
    }

    public QAuction(Path<? extends Auction> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAuction(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAuction(PathMetadata metadata, PathInits inits) {
        this(Auction.class, metadata, inits);
    }

    public QAuction(Class<? extends Auction> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new QItem(forProperty("item"), inits.get("item")) : null;
    }

}

