package com.example.concorrente_lab10.models.Dto;

import com.example.concorrente_lab10.models.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoCadastroDto {

    @Pattern(regexp = "^[1-9]\\d*$", message = "Deve ser um número positivo")
    private String id;

    @NotBlank(message = "O nome não pode Ser nulo")
    private String name;

    public ProdutoCadastroDto(Produto produto) {
        this.id = produto.getId();
        this.name = produto.getName();
    }
}
