package ArtBridge.ArtBridgelogin.domain;

import ArtBridge.ArtBridgelogin.domain.Connection.SaleLike;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="item")
@Data
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_seq", nullable = false, insertable = false, updatable = false)
    private int itemSeq;

    @Column(name = "item_name", nullable = false, length = 100)
    private String itemName;

    @Column(name = "item_explain")
    private String explain;

    @Column(name = "item_width", nullable = false)
    private int itemWidth;

    @Column(name = "item_height", nullable = false)
    private int itemHeight;

    @Column(name = "item_like", nullable = false)
    private int itemLike;

    @Column(name = "item_sell_price")
    private Long itemSellPrice;

    @Column(name = "item_is_sold", nullable = false)
    private boolean itemIsSold;

    @Column(name = "item_created_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime itemCreatedDate;
    // 다른 필드 및 메서드들...
    @PrePersist
    protected void onCreate() {
        if (itemCreatedDate == null) {
            itemCreatedDate = LocalDateTime.now();
        }
    }

    //    ----------------------------------------------------

    // Many-to-One relationship with Artist
    @ManyToOne
    @JsonBackReference(value = "item_artist_Reference")
    @JoinColumn(name = "artist_seq", nullable = false)
    private Artist artist;

    // One-to-Many relationship with OrderDetail
    @OneToMany(mappedBy = "item")
    @JsonManagedReference
    private List<OrderDetail> orderDetails;

    // One-to-Many relationship with Wish
    @OneToMany(mappedBy = "item")
    @JsonManagedReference
    private List<Wish> wishes;

    // One-to-Many relationship with Auction
    @OneToMany(mappedBy = "item")
    @JsonManagedReference
    private List<Auction> auctions;

    // One-to-Many relationship with SaleLike
    @OneToMany(mappedBy = "item")
    @JsonManagedReference
    private List<SaleLike> saleLikes;

//    @OneToOne(mappedBy = "item")
//    @JsonManagedReference
//    private Review review;
}
