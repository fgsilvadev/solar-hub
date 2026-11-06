package com.fgsilva.solarhub.reading;

import com.fgsilva.solarhub.installation.Installation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "generation_readings")
@Getter
@Setter
@NoArgsConstructor
public class GenerationReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "installation_id", nullable = false)
    private Installation installation;

    @Column(name = "reading_date", nullable = false)
    private LocalDate readingDate;

    @Column(name = "generated_kwh", nullable = false, precision = 10, scale = 3)
    private BigDecimal generatedKwh;

    @Column(name = "expected_kwh", nullable = false, precision = 10, scale = 3)
    private BigDecimal expectedKwh;
}
