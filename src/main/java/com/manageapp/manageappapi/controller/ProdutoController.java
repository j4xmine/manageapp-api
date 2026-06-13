package com.manageapp.manageappapi.controller;

import com.manageapp.manageappapi.dto.ProdutoRequestDTO;
import com.manageapp.manageappapi.dto.ProdutoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.manageapp.manageappapi.service.ProdutoService;
import com.manageapp.manageappapi.model.Produto;

import java.util.List;

// CONTROLLER -> recebe a requisição

@RestController // para o springboot reconhecer que aqui é uma requisição
// aqui ele converte a classe para JSON
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/produto")
    // O @Valid diz ao Spring: antes de executar o método, VALIDE os dados do objeto.
    public ProdutoResponseDTO salvar(@Valid @RequestBody ProdutoRequestDTO request) {
        return produtoService.salvar(request);
    }

    @GetMapping("/produto") // diz ao SPRING: quando alguém fizer um GET (me retorne a lista de produtos)
    // assim que alguém clicar, ESTE MÉTODO SERÁ EXECUTADO
    public List<ProdutoResponseDTO> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @GetMapping("/produto/{id}")
    public ProdutoResponseDTO buscarPorId(@PathVariable String id) {
        // PathVariable é pra informar ao SPRING que esse ID virá da URL -> ({id})
        return produtoService.buscarPorId(id);
    }

    @DeleteMapping("/produto/{id}")
    public void deletarProdutoPorId(@PathVariable String id) {
        produtoService.deletarProdutoPorId(id);
    }

    @PutMapping("/produto/{id}")
    public ProdutoResponseDTO atualizarProduto(@PathVariable String id, @Valid @RequestBody ProdutoRequestDTO request) {
        return produtoService.atualizarProduto(id, request);
    }

    @GetMapping("/produto/buscar/nome")
    public List<ProdutoResponseDTO> buscarPorNome(@RequestParam String nome) {
        return produtoService.buscarPorNome(nome);
    }


    @GetMapping("/produto/buscar/preco")
    public List<ProdutoResponseDTO> buscarPorPrecoMaiorQue(@RequestParam Double preco) {
        return produtoService.buscarPorPrecoMaiorQue(preco);
    }

    @GetMapping("/produto/buscar/precofiltro")
    public List<ProdutoResponseDTO> buscarPrecoEntre(@RequestParam Double min, @RequestParam Double max) {
        return produtoService.buscarPrecoEntre(min, max);
    }

    @GetMapping("/produto/buscar/descricao")
    public List<ProdutoResponseDTO> buscarPorDescricao(@RequestParam String descricao) {
        return produtoService.buscarPorDescricao(descricao);
    }

    @GetMapping("/produto/buscar/em-estoque")
    public List<ProdutoResponseDTO> buscarPorEstoque() {
        return produtoService.buscarPorEstoque();
    }

    @GetMapping("/produto/buscar/sem-estoque")
    public List<ProdutoResponseDTO> produtosSemEstoque() {
        return produtoService.produtosSemEstoque();
    }
}
