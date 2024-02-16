package ArtBridge.ArtBridgelogin.domain.KakaoPay;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "kakaopay_answer")
@Data
public class Kakaopay_Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    private Integer tid;

    @Column(name = "next_redirect_app_url", length = 255, nullable = true)
    private Integer nextRedirectAppUrl;
    @Column(name = "next_redirect_mobile_url", length = 255, nullable = true)
    private String nextRedirectMobileUrl;
    @Column(name = "next_redirect_pc_url", length = 255, nullable = true)
    private String nextRedirectPcUrl;
    @Column(name = "android_app_scheme", length = 255, nullable = true)
    private String androidAppScheme;
    @Column(name = "ios_app_scheme", length = 255, nullable = true)
    private String iosAppScheme;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;


}
