package ArtBridge.ArtBridgelogin.controller.dto.point;

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

    // 생성자, getter, setter 등은 롬복(@Data 어노테이션 사용)을 활용하여 자동으로 생성됩니다.
}
