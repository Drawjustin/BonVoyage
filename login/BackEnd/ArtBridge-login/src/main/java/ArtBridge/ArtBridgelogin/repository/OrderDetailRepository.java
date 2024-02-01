package ArtBridge.ArtBridgelogin.repository;

import ArtBridge.ArtBridgelogin.domain.OrderDetail;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDetailRepository {

    private final EntityManager em;

    @Transactional
    public OrderDetail create(OrderDetail orderDetail){
        em.persist(orderDetail);
        return orderDetail;
    }

    @Transactional(readOnly = true)
    public OrderDetail findOne(Long id){return em.find(OrderDetail.class, id);}

    @Transactional(readOnly = true)
    public List<OrderDetail> findAll(){
        return em.createQuery("select m from OrderDetail m", OrderDetail.class)
                .getResultList();
    }

    // TODO: Upate

}
