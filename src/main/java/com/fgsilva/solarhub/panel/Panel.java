package com.fgsilva.solarhub.panel;

import com.fgsilva.solarhub.installation.Installation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "panels")
@Getter
@Setter
@NoArgsConstructor
public class Panel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "installation_id", nullable = false)
    private Installation installation;

    @Column(nullable = false, length = 120)
    private String model;

    @Column(name = "power_wp", nullable = false)
    private Integer powerWp;

    @Column(name = "serial_number", nullable = false, unique = true, length = 80)
    private String serialNumber;
}
