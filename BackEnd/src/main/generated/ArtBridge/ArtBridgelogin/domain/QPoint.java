package ArtBridge.ArtBridgelogin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPoint is a Querydsl query type for Point
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPoint extends EntityPathBase<Point> {

    private static final long serialVersionUID = -1777582187L;

    public static final QPoint point = new QPoint("point");

    public final NumberPath<Integer> pointDetailId = createNumber("pointDetailId", Integer.class);

    public final BooleanPath pointDetailIsartist = createBoolean("pointDetailIsartist");

    public final NumberPath<Long> pointDetailSeq = createNumber("pointDetailSeq", Long.class);

    public final NumberPath<Integer> pointDetailTradeCategory = createNumber("pointDetailTradeCategory", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> pointDetailTradeDate = createDateTime("pointDetailTradeDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> pointDetailTradePoint = createNumber("pointDetailTradePoint", Long.class);

    public final NumberPath<Integer> pointDetailTradeTableSeq = createNumber("pointDetailTradeTableSeq", Integer.class);

    public QPoint(String variable) {
        super(Point.class, forVariable(variable));
    }

    public QPoint(Path<? extends Point> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPoint(PathMetadata metadata) {
        super(Point.class, metadata);
    }

}

