package ArtBridge.ArtBridgelogin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderDetail is a Querydsl query type for OrderDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderDetail extends EntityPathBase<OrderDetail> {

    private static final long serialVersionUID = 458943364L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderDetail orderDetail = new QOrderDetail("orderDetail");

    public final QArtist artist;

    public final QAuction auction;

    public final QItem item;

    public final QMember member;

    public final StringPath orderDetailAddress = createString("orderDetailAddress");

    public final DateTimePath<java.time.LocalDateTime> orderDetailCanceledDate = createDateTime("orderDetailCanceledDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> orderDetailCommission = createNumber("orderDetailCommission", Integer.class);

    public final StringPath orderDetailContract = createString("orderDetailContract");

    public final DateTimePath<java.time.LocalDateTime> orderDetailDate = createDateTime("orderDetailDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> orderDetailDeliveryPoint = createNumber("orderDetailDeliveryPoint", Integer.class);

    public final BooleanPath orderDetailIsAuction = createBoolean("orderDetailIsAuction");

    public final BooleanPath orderDetailIsCanceled = createBoolean("orderDetailIsCanceled");

    public final StringPath orderDetailPhoneNumber = createString("orderDetailPhoneNumber");

    public final NumberPath<Long> orderDetailSeq = createNumber("orderDetailSeq", Long.class);

    public final NumberPath<Long> orderDetailTotalPoint = createNumber("orderDetailTotalPoint", Long.class);

    public final NumberPath<Integer> sortingCodeDetailSeq = createNumber("sortingCodeDetailSeq", Integer.class);

    public QOrderDetail(String variable) {
        this(OrderDetail.class, forVariable(variable), INITS);
    }

    public QOrderDetail(Path<? extends OrderDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderDetail(PathMetadata metadata, PathInits inits) {
        this(OrderDetail.class, metadata, inits);
    }

    public QOrderDetail(Class<? extends OrderDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.artist = inits.isInitialized("artist") ? new QArtist(forProperty("artist")) : null;
        this.auction = inits.isInitialized("auction") ? new QAuction(forProperty("auction"), inits.get("auction")) : null;
        this.item = inits.isInitialized("item") ? new QItem(forProperty("item"), inits.get("item")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

