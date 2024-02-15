package ArtBridge.ArtBridgelogin.controller.dto.webRTC;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PointDto {

    private Long pointDetailSeq;
    private Integer pointDetailId;
    private boolean pointDetailIsartist;
    private Long pointDetailTradePoint;
    private LocalDateTime pointDetailTradeDate;
    private Integer pointDetailTradeCategory;
    private Integer pointDetailTradeTableSeq;

}
