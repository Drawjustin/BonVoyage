package ArtBridge.ArtBridgelogin.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "kakaopay_answer")
@Data
public class kakopay_request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid", length = 20 , nullable = false)
    private String Tid;
    @Column(name = "next_redirect_app_url", length = 255, nullable = true)
    private String nextRedirectAppUrl;
    @Column(name = "next_redirect_mobile_url", length = 255, nullable = true)
    private String nextRedirectMobileUrl;
    @Column(name = "next_redirect_pc_url", length = 255, nullable = true)
    private String nextRedirectPcUrl;
    @Column(name = "android_app_scheme", length = 255, nullable = true)
    private String androidAppScheme;
    @Column(name = "ios_app_scheme", length = 255, nullable = true)
    private String iosAppScheme;
    @Column(name = "created_at", length = 30, nullable = false)
    private LocalDateTime createdAt;
}
