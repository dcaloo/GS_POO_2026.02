package br.com.fiap.eduardo555933.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "GS933_BAIRRO")
@Getter
@Setter
@NoArgsConstructor
public class Bairro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nome;

    @Column(nullable = false, length = 10)
    private String zona;

    @Column(nullable = false)
    private Integer populacao;
}
