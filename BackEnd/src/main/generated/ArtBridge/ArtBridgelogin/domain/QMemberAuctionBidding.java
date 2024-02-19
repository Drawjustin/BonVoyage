package ArtBridge.ArtBridgelogin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberAuctionBidding is a Querydsl query type for MemberAuctionBidding
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberAuctionBidding extends EntityPathBase<MemberAuctionBidding> {

    private static final long serialVersionUID = -567515059L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberAuctionBidding memberAuctionBidding = new QMemberAuctionBidding("memberAuctionBidding");

    public final QAuction auction;

    public final QMember member;

    public final DateTimePath<java.time.LocalDateTime> memberAuctionBiddingCreatedDate = createDateTime("memberAuctionBiddingCreatedDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> memberAuctionBiddingDeletedDate = createDateTime("memberAuctionBiddingDeletedDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> memberAuctionBiddingDeposit = createNumber("memberAuctionBiddingDeposit", Integer.class);

    public final BooleanPath memberAuctionBiddingIsdeleted = createBoolean("memberAuctionBiddingIsdeleted");

    public final NumberPath<Long> memberAuctionBiddingSeq = createNumber("memberAuctionBiddingSeq", Long.class);

    public QMemberAuctionBidding(String variable) {
        this(MemberAuctionBidding.class, forVariable(variable), INITS);
    }

    public QMemberAuctionBidding(Path<? extends MemberAuctionBidding> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberAuctionBidding(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberAuctionBidding(PathMetadata metadata, PathInits inits) {
        this(MemberAuctionBidding.class, metadata, inits);
    }

    public QMemberAuctionBidding(Class<? extends MemberAuctionBidding> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.auction = inits.isInitialized("auction") ? new QAuction(forProperty("auction"), inits.get("auction")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

