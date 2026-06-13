package com.manageapp.manageappapi.controller;

import com.manageapp.manageappapi.dto.ProdutoRequestDTO;
import com.manageapp.manageappapi.dto.ProdutoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

// Swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import com.manageapp.manageappapi.service.ProdutoService;

import java.util.List;

// CONTROLLER -> recebe a requisição
@Tag(
        name = "Produtos",
        description = "Operações relacionadas aos produtos"
)
@RestController // para o springboot reconhecer que aqui é uma requisição
// aqui ele converte a classe para JSON
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(summary = "Cadastrar um produto")
    @PostMapping("/produto")
    // O @Valid diz ao Spring: antes de executar o método, VALIDE os dados do objeto.
    public ProdutoResponseDTO salvar(@Parameter(description = "Dados inseridos pelo usuário")
                                         @Valid @RequestBody ProdutoRequestDTO request) {
        return produtoService.salvar(request);
    }

    @Operation(summary = "Listar todos os produtos")
    @GetMapping("/produto") // diz ao SPRING: quando alguém fizer um GET (me retorne a lista de produtos)
    // assim que alguém clicar, ESTE MÉTODO SERÁ EXECUTADO
    public List<ProdutoResponseDTO> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @Operation(summary = "Buscar um produto pelo ID")
    @GetMapping("/produto/{id}")
    public ProdutoResponseDTO buscarPorId(@Parameter(description = "ID do produto")
                                              @PathVariable String id) {
        // PathVariable é pra informar ao SPRING que esse ID virá da URL -> ({id})
        return produtoService.buscarPorId(id);
    }

    @Operation(summary = "Excluir um produto pelo ID")
    @DeleteMapping("/produto/{id}")
    public void deletarProdutoPorId(@Parameter(description = "ID do produto")
                                        @PathVariable String id) {
        produtoService.deletarProdutoPorId(id);
    }

    @Operation(summary = "Atualizar um produto pelo ID")
    @PutMapping("/produto/{id}")
    public ProdutoResponseDTO atualizarProduto(@Parameter(description = "ID do produto")@PathVariable String id,
                                               @Parameter(description = "Dados inseridos pelo usuário")@Valid @RequestBody ProdutoRequestDTO request) {
        return produtoService.atualizarProduto(id, request);
    }

    @Operation(summary = "Buscar por nome")
    @GetMapping("/produto/buscar/nome")
    public List<ProdutoResponseDTO> buscarPorNome(@Parameter(description = "Nome do produto")
                                                      @RequestParam String nome) {
        return produtoService.buscarPorNome(nome);
    }

    @Operation(summary = "Buscar por preço maior que valor x")
    @GetMapping("/produto/buscar/preco")
    public List<ProdutoResponseDTO> buscarPorPrecoMaiorQue(@Parameter(description = "Preço")@RequestParam Double preco) {
        return produtoService.buscarPorPrecoMaiorQue(preco);
    }

    @Operation(summary = "Buscar preço entre valor x e valor y")
    @GetMapping("/produto/buscar/precofiltro")
    public List<ProdutoResponseDTO> buscarPrecoEntre(@Parameter(description = "Preço mínimo")@RequestParam Double min,
                                                     @Parameter(description = "Preço máximo")@RequestParam Double max) {
        return produtoService.buscarPrecoEntre(min, max);
    }

    @Operation(summary = "Buscar por descrição")
    @GetMapping("/produto/buscar/descricao")
    public List<ProdutoResponseDTO> buscarPorDescricao(@Parameter(description = "Descrição")@RequestParam String descricao) {
        return produtoService.buscarPorDescricao(descricao);
    }

    @Operation(summary = "Listar itens com estoque")
    @GetMapping("/produto/buscar/em-estoque")
    public List<ProdutoResponseDTO> buscarPorEstoque() {
        return produtoService.buscarPorEstoque();
    }

    @Operation(summary = "Listar itens sem estoque")
    @GetMapping("/produto/buscar/sem-estoque")
    public List<ProdutoResponseDTO> produtosSemEstoque() {
        return produtoService.produtosSemEstoque();
    }
}
