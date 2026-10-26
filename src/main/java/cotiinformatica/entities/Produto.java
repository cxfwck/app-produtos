package cotiinformatica.entities;

import cotiinformatica.enums.TipoProduto;
import lombok.Data;

import java.util.UUID;

@Data
public class Produto {

    private UUID id;
    private String nome;
    private Double preco;
    private Integer quantidade;
    private TipoProduto tipo;
    private Categoria categoria;
}
