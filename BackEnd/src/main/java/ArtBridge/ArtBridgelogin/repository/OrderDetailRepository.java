package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.domain.OrderDetail;
import ArtBridge.ArtBridgelogin.domain.QMember;
import ArtBridge.ArtBridgelogin.domain.QOrderDetail;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDetailRepository {

    private final EntityManager em;

    private QOrderDetail qOderdetail = QOrderDetail.orderDetail;

    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {queryFactory = new JPAQueryFactory(em);}

    public OrderDetail create(OrderDetail orderDetail){
        em.persist(orderDetail);
        return orderDetail;
    }

    public OrderDetail readOne(Long id){return em.find(OrderDetail.class, id);}

    public List<OrderDetail> readAll(){
        List<OrderDetail> orderDetails =
                queryFactory
                .selectFrom(qOderdetail)
                .fetch();

        return orderDetails;
    }

    public void updateMember(Long id, String newUsername, String newPassword) {
        // Querydsl 사용하여 해당 ID에 해당하는 Member 조회
        QMember qMember = QMember.member;
        Member member = queryFactory
                .selectFrom(qMember)
                .where(qMember.memberSeq.eq(id))
                .fetchOne();

        // 해당 ID에 해당하는 Member가 없으면 예외 처리
        if (member == null) {
            throw new EntityNotFoundException("Member with ID " + id + " not found");
        }

        // 수정할 필드 업데이트
        member.setMemberName(newUsername);
        member.setMemberPwd(newPassword);

        // 업데이트된 Member를 저장
        em.persist(member);
    }

}
