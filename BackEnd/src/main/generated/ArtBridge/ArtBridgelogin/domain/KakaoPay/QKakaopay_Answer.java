package ArtBridge.ArtBridgelogin.domain.KakaoPay;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QKakaopay_Answer is a Querydsl query type for Kakaopay_Answer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QKakaopay_Answer extends EntityPathBase<Kakaopay_Answer> {

    private static final long serialVersionUID = -42523894L;

    public static final QKakaopay_Answer kakaopay_Answer = new QKakaopay_Answer("kakaopay_Answer");

    public final StringPath androidAppScheme = createString("androidAppScheme");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath iosAppScheme = createString("iosAppScheme");

    public final NumberPath<Integer> nextRedirectAppUrl = createNumber("nextRedirectAppUrl", Integer.class);

    public final StringPath nextRedirectMobileUrl = createString("nextRedirectMobileUrl");

    public final StringPath nextRedirectPcUrl = createString("nextRedirectPcUrl");

    public final NumberPath<Integer> tid = createNumber("tid", Integer.class);

    public QKakaopay_Answer(String variable) {
        super(Kakaopay_Answer.class, forVariable(variable));
    }

    public QKakaopay_Answer(Path<? extends Kakaopay_Answer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QKakaopay_Answer(PathMetadata metadata) {
        super(Kakaopay_Answer.class, metadata);
    }

}

