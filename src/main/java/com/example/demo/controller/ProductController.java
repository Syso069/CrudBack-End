package com.example.demo.controller;


import com.example.demo.domain.Product;
import com.example.demo.domain.RequestProduct;
import com.example.demo.service.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity pegaTodosProdutos(){
        return ResponseEntity.ok(productService.pegaProduto());
    }

    @PostMapping
    public ResponseEntity registraProduto(@RequestBody @Valid RequestProduct data){
        Product produto = new Product(data);
        productService.salvaNovoProduto(produto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity editaProduto(@PathVariable Long id, @RequestBody @Valid RequestProduct data){
        var atualizaProduto = productService.editaProduto(id, data);
        return ResponseEntity.ok(atualizaProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletaProduto(@PathVariable Long id){
        var deleta = productService.deletaProduto(id);
        return ResponseEntity.ok(deleta);
    }
}
