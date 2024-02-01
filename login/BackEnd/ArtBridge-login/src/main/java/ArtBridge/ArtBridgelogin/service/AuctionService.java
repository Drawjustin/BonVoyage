package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.repository.AuctionRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;

    
}
