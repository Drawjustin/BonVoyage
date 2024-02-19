package ArtBridge.ArtBridgelogin.domain.Connection;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSaleLike is a Querydsl query type for SaleLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSaleLike extends EntityPathBase<SaleLike> {

    private static final long serialVersionUID = 1895274227L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSaleLike saleLike = new QSaleLike("saleLike");

    public final ArtBridge.ArtBridgelogin.domain.QItem item;

    public final ArtBridge.ArtBridgelogin.domain.QMember member;

    public QSaleLike(String variable) {
        this(SaleLike.class, forVariable(variable), INITS);
    }

    public QSaleLike(Path<? extends SaleLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSaleLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSaleLike(PathMetadata metadata, PathInits inits) {
        this(SaleLike.class, metadata, inits);
    }

    public QSaleLike(Class<? extends SaleLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new ArtBridge.ArtBridgelogin.domain.QItem(forProperty("item"), inits.get("item")) : null;
        this.member = inits.isInitialized("member") ? new ArtBridge.ArtBridgelogin.domain.QMember(forProperty("member")) : null;
    }

}

