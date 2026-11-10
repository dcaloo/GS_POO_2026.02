package br.com.fiap.eduardo555933.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "GS933_LEITURA")
@Getter
@Setter
@NoArgsConstructor
public class Leitura {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private Integer lamina;

    @Column(nullable = false, length = 10)
    private String situacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TRECHO_ID", nullable = false)
    private Trecho trecho;
}
