package com.fgsilva.solarhub.installation;

import com.fgsilva.solarhub.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.management.GarbageCollectorMXBean;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "installations")
@Getter
@Setter
@NoArgsConstructor
public class Installation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String address;

    @Column(name = "installed_capacity_kwp", nullable = false, precision = 10, scale = 2)
    private BigDecimal installedCapacityKwp;

    // Average daily peak sun hours for the region; used to compute expected generation.
    @Column(name = "peak_sun_hours", nullable = false, precision = 4, scale = 2)
    private BigDecimal peakSunHours = new BigDecimal("5.00");

    @Column(name = "installed_at", nullable = false)
    private LocalDate installedAt;
}
