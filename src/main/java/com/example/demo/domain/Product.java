package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name="produto")
@Entity(name="produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_id_seq")
    @SequenceGenerator(name = "produto_id_seq", sequenceName = "produto_id_seq", allocationSize = 1)
    private Long id;

    private String nome;

    private float preco;

    public Product(RequestProduct requestProduct){
        this.nome = requestProduct.nome();
        this.preco = requestProduct.preco();
    }
}
