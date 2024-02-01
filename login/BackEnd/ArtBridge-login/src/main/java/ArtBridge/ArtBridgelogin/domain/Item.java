package ArtBridge.ArtBridgelogin.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "item")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_seq")
    private Long itemSeq;

    @Column(name = "artist_seq", insertable = false, updatable = false)
    private Long artistSeq;


    @Column(name = "item_name", length = 30, nullable = false)
    private String itemName;
    @Column(name = "item_width", length = 30, nullable = false)
    private String itemWidth;
    @Column(name = "item_height", length = 30, nullable = false)
    private String itemHeigth;
    @Column(name = "item_like", length = 30, nullable = false)
    private String itemLike;
    @Column(name = "item_sell_price", length = 30, nullable = false)
    private String itemSellPrice;
    @Column(name = "item_is_sold", length = 30, nullable = false)
    private String itemIsSold;
    @Column(name = "item_created_date", length = 30, nullable = false)
    private String itemCreatedDate;
}
