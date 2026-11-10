package br.com.fiap.eduardo555933.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrechoDTO {

    private Long id;

    @NotBlank(message = "O logradouro é obrigatorio")
    @Size(max = 120, message = "O logradouro deve ter no maximo 120 caracteres")
    private String logradouro;

    @NotBlank(message = "O tipo de calçada é obrigatorio")
    @Pattern(
            regexp = "Regular|Irregular|Escadaria|Sem iluminação",
            message = "Selecione um tipo de calçada valida"
    )
    private String tipoCalcada;

    @NotNull(message = "A Altitude é obrigatória")
    @Min(value = 0, message = "A altitude não pode ser negativa")
    @Max(value = 2000, message = "A altitude não pode ultrapassar 2000 metros")
    private Integer altitude;

    @NotNull(message = "Selecione um Bairro")
    private Long bairroId;
}
