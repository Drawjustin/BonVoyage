package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.Point;
import ArtBridge.ArtBridgelogin.domain.QPoint;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PointRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;
    private final QPoint qPoint = QPoint.point;

    public PointRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Point> readAll() {
        return queryFactory
                .selectFrom(qPoint)
                .fetch();
    }

    public Point readPointByDetailSeq(Long pointDetailSeq) {
        return queryFactory
                .selectFrom(qPoint)
                .where(qPoint.pointDetailSeq.eq(pointDetailSeq))
                .fetchOne();
    }

    public Point create(Point point) {
        em.persist(point);
        return point;
    }

    // Add other methods as needed

    // You can add methods for update and delete based on your requirements

}
