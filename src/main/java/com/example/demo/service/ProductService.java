package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.domain.RequestProduct;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> pegaProduto(){
        List<Product> products = repository.findAll(Sort.by("preco").ascending());
        if (products.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum produto encontrado");
        }else {
            return products;
        }
    }

    public Product salvaNovoProduto(Product novoProduto){
        Product produtoSalvo = repository.save(novoProduto);
        if (produtoSalvo == null){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar produto.");
        }else {
            return produtoSalvo;
        }
    }

    public Product editaProduto(Long id, RequestProduct data){
        Optional<Product> produtos = repository.findById(String.valueOf(id));
        Product produto = produtos.get();
        produto.setNome(data.nome());
        produto.setPreco(data.preco());
        return repository.save(produto);
    }

    public Product deletaProduto(Long id){
        Optional<Product> produtos = repository.findById(String.valueOf(id));
        Product produto = produtos.get();
        repository.deleteById(String.valueOf(id));
        return produto;
    }
}
