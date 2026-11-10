package br.com.fiap.eduardo555933.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BairroDTO {

    private Long id;

    @NotBlank(message = "O nome do bairro é obrigatório")
    @Size(max = 80, message = "O nome deve ter no maximo 80 caracteres")
    private String nome;

    @NotBlank(message = "A zona é obrigatória")
    @Pattern(
            regexp = "Centro|Norte|Sul|Leste|Oeste",
            message = "Selecione uma zona valida"
    )
    private String zona;

    @NotNull(message = "A população é obrigatória")
    @Min(value = 0, message = "A população não pode ser negativa")
    private Integer populacao;
}
