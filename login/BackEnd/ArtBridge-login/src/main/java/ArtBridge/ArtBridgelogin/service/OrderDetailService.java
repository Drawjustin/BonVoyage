package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Transactional(readOnly = true)
    public List<OrderDetail> getAllAOrderDetail() {
        return orderDetailRepository.findAll();
    }

    @Transactional(readOnly = true)
    public OrderDetail findOne(Long id) {
        return orderDetailRepository.findOne(id);
    }

    @Transactional
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.create(orderDetail);
    }
}
