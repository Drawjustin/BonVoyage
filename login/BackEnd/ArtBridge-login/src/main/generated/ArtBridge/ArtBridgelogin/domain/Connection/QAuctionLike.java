package ArtBridge.ArtBridgelogin.domain.Connection;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuctionLike is a Querydsl query type for AuctionLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuctionLike extends EntityPathBase<AuctionLike> {

    private static final long serialVersionUID = -1574213147L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAuctionLike auctionLike = new QAuctionLike("auctionLike");

    public final ArtBridge.ArtBridgelogin.domain.QAuction auction;

    public final ArtBridge.ArtBridgelogin.domain.QMember member;

    public QAuctionLike(String variable) {
        this(AuctionLike.class, forVariable(variable), INITS);
    }

    public QAuctionLike(Path<? extends AuctionLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAuctionLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAuctionLike(PathMetadata metadata, PathInits inits) {
        this(AuctionLike.class, metadata, inits);
    }

    public QAuctionLike(Class<? extends AuctionLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.auction = inits.isInitialized("auction") ? new ArtBridge.ArtBridgelogin.domain.QAuction(forProperty("auction"), inits.get("auction")) : null;
        this.member = inits.isInitialized("member") ? new ArtBridge.ArtBridgelogin.domain.QMember(forProperty("member")) : null;
    }

}

