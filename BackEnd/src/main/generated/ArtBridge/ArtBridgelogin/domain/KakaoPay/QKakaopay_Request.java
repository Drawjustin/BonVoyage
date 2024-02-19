package ArtBridge.ArtBridgelogin.domain.KakaoPay;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QKakaopay_Request is a Querydsl query type for Kakaopay_Request
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QKakaopay_Request extends EntityPathBase<Kakaopay_Request> {

    private static final long serialVersionUID = 624851139L;

    public static final QKakaopay_Request kakaopay_Request = new QKakaopay_Request("kakaopay_Request");

    public final StringPath approvalUrl = createString("approvalUrl");

    public final StringPath cancelUrl = createString("cancelUrl");

    public final StringPath cid = createString("cid");

    public final StringPath failUrl = createString("failUrl");

    public final StringPath itemName = createString("itemName");

    public final NumberPath<Integer> kakaopayRequestSeq = createNumber("kakaopayRequestSeq", Integer.class);

    public final StringPath partnerOrderId = createString("partnerOrderId");

    public final StringPath partnerUserId = createString("partnerUserId");

    public final StringPath quantity = createString("quantity");

    public final NumberPath<Integer> taxFreeAmount = createNumber("taxFreeAmount", Integer.class);

    public final NumberPath<Integer> totalAmount = createNumber("totalAmount", Integer.class);

    public QKakaopay_Request(String variable) {
        super(Kakaopay_Request.class, forVariable(variable));
    }

    public QKakaopay_Request(Path<? extends Kakaopay_Request> path) {
        super(path.getType(), path.getMetadata());
    }

    public QKakaopay_Request(PathMetadata metadata) {
        super(Kakaopay_Request.class, metadata);
    }

}

