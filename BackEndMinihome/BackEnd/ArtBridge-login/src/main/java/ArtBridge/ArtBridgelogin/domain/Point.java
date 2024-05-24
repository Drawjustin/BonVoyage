package ArtBridge.ArtBridgelogin.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "point")
@Data
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_detail_seq")
    private Long pointDetailSeq;

    @Column(name = "point_detail_id", nullable = false)
    private Integer pointDetailId;

    @Column(name = "point_detail_isartist", length = 30, nullable = false)
    private boolean pointDetailIsartist;

    @Column(name = "point_detail_trade_point", length = 30, nullable = false)
    private Long pointDetailTradePoint;

    @Column(name = "point_detail_trade_date", length = 30, nullable = false)
    private LocalDateTime pointDetailTradeDate;

    @Column(name = "point_detail_trade_category", length = 30, nullable = false)
    private Integer pointDetailTradeCategory;

    @Column(name = "point_detail_trade_table_seq", length = 30, nullable = false)
    private Integer pointDetailTradeTableSeq;
}
