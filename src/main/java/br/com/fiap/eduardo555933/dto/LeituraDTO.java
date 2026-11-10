package br.com.fiap.eduardo555933.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class LeituraDTO {
    private Long id;

    @NotNull(message = "A data e hora são obrigatórias")
    @PastOrPresent(message = "A data e hora não podem estar no futuro")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dataHora;

    @NotNull(message = "A lâmina de água é obrigatória")
    @Min(value = 0, message = "A lamina de agua não pode ser negativa")
    @Max(value = 300, message = "A lamina de agua nao pode ultrapassar 300 cm")
    private Integer lamina;

    private String situacao;

    @NotNull(message = "Selecione um trecho")
    private Long trechoId;
}
