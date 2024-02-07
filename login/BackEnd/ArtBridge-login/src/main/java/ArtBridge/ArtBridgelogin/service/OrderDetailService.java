package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.OrderDetail;
import ArtBridge.ArtBridgelogin.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    //Todo: CREATE
    @Transactional
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.create(orderDetail);
    }

    //Todo: READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<OrderDetail> readAllAOrderDetail() {
        return orderDetailRepository.readAll();
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public OrderDetail readOne(Long id) {
        return orderDetailRepository.readOne(id);
    }

    //Todo: UPDATE : NONE

    //Todo: DELETE : NONE
}
