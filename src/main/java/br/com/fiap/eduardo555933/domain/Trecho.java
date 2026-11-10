package br.com.fiap.eduardo555933.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "GS933_TRECHO")
@Getter
@Setter
@NoArgsConstructor
public class Trecho {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 120)
    private String logradouro;

    @Column(nullable = false, length = 20)
    private String tipoCalcada;

    @Column(nullable = false)
    private Integer altitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BAIRRO_ID", nullable = false)
    private Bairro bairro;
}
