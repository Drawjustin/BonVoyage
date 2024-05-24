package ArtBridge.ArtBridgelogin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuctionPointDetail is a Querydsl query type for AuctionPointDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuctionPointDetail extends EntityPathBase<AuctionPointDetail> {

    private static final long serialVersionUID = 909280729L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAuctionPointDetail auctionPointDetail = new QAuctionPointDetail("auctionPointDetail");

    public final QAuction auction;

    public final DateTimePath<java.time.LocalDateTime> auctionPointDate = createDateTime("auctionPointDate", java.time.LocalDateTime.class);

    public final BooleanPath auctionPointDetailIsWin = createBoolean("auctionPointDetailIsWin");

    public final NumberPath<Integer> auctionPointDetailPoint = createNumber("auctionPointDetailPoint", Integer.class);

    public final NumberPath<Integer> auctionPointDetailSeq = createNumber("auctionPointDetailSeq", Integer.class);

    public final QMember member;

    public QAuctionPointDetail(String variable) {
        this(AuctionPointDetail.class, forVariable(variable), INITS);
    }

    public QAuctionPointDetail(Path<? extends AuctionPointDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAuctionPointDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAuctionPointDetail(PathMetadata metadata, PathInits inits) {
        this(AuctionPointDetail.class, metadata, inits);
    }

    public QAuctionPointDetail(Class<? extends AuctionPointDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.auction = inits.isInitialized("auction") ? new QAuction(forProperty("auction"), inits.get("auction")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

