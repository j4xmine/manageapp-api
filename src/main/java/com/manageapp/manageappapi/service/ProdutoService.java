package com.manageapp.manageappapi.service;

import com.manageapp.manageappapi.dto.ProdutoRequestDTO;
import com.manageapp.manageappapi.dto.ProdutoResponseDTO;
import org.springframework.stereotype.Service;
import com.manageapp.manageappapi.repository.ProdutoRepository;
import com.manageapp.manageappapi.model.Produto;

import java.util.List;

import com.manageapp.manageappapi.exception.ProdutoNaoEncontradoException;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoResponseDTO salvar(ProdutoRequestDTO request) {
        // cria o objeto pra converter de RequestDTO para Produto (tipo aceito no banco)
        Produto produto = new Produto();
        produto.setNome(request.getNome());
        produto.setDescricao(request.getDescricao());
        produto.setPrecoCompra(request.getPrecoCompra());
        produto.setPrecoVenda(request.getPrecoVenda());
        produto.setQuantidadeEstoque(request.getQuantidadeEstoque());

        // cria um novo objeto do tipo Produto para acessar o método "save" do Repository, e salva o objeto convertido "produto"
        Produto produtoSalvo = produtoRepository.save(produto);

        // response (resposta ao usuario)
        // aqui, convertemos Produto para ResponseDTO (que será o retorno ao front)
        ProdutoResponseDTO response = new ProdutoResponseDTO();
        response.setId(produtoSalvo.getId());
        response.setNome(produtoSalvo.getNome());
        response.setDescricao(produtoSalvo.getDescricao());
        response.setPrecoVenda(produtoSalvo.getPrecoVenda());
        response.setQuantidadeEstoque(produtoSalvo.getQuantidadeEstoque());
        response.setLucro(
                produtoSalvo.getPrecoVenda() - produtoSalvo.getPrecoCompra()
        );

        return response;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }


//    public Optional<Produto> buscarPorID(String id) {
//        return produtoRepository.findById(id);
//    }
    public Produto buscarPorId(String id) {
        return produtoRepository.findById(id).orElseThrow(() ->
                new ProdutoNaoEncontradoException("Produto com ID " + id + " não encotrado."));
    }

    public void deletarProdutoPorId(String id) {
        if(!produtoRepository.existsById(id)) {
            throw new ProdutoNaoEncontradoException("Produto com ID " + id + " não encontrado para deletar.");
        }
        produtoRepository.deleteById(id);
    }

    public ProdutoResponseDTO atualizarProduto(String id, ProdutoRequestDTO produtoAtualizado) {
        // VAMOS USAR O TIPO DE RETORNO COMO Produto CASO QUEIRA RETORNAR AO USUÁRIO os DADOS ATUALIZADOS no FLUTTER (devolver ao cliente o resultado. O "salvar" segue a mesma lógica).
        Produto produtoExistente = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado para atualizar."));
        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        produtoExistente.setPrecoCompra(produtoAtualizado.getPrecoCompra());
        produtoExistente.setPrecoVenda(produtoAtualizado.getPrecoVenda());
        produtoExistente.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());

        Produto produtoSalvo = produtoRepository.save(produtoExistente);

        ProdutoResponseDTO response = new ProdutoResponseDTO();
        response.setId(produtoSalvo.getId());
        response.setNome(produtoSalvo.getNome());
        response.setDescricao(produtoSalvo.getDescricao());
        response.setPrecoVenda(produtoSalvo.getPrecoVenda());
        response.setQuantidadeEstoque(produtoSalvo.getQuantidadeEstoque());
        response.setLucro(produtoSalvo.getPrecoVenda() - produtoSalvo.getPrecoCompra());

        return response;
    }

    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Produto> buscarPorPrecoMaiorQue(Double preco) {
        return produtoRepository.findByPrecoVendaGreaterThan(preco);
    }

    public List<Produto> buscarPrecoEntre(Double min, Double max) {
        return produtoRepository.findByPrecoVendaBetween(min, max);
    }

    public List<Produto> buscarPorDescricao(String descricao) {
        return produtoRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

    public List<Produto> buscarPorEstoque() {
        return produtoRepository.findByQuantidadeEstoqueGreaterThanEqual(1);
    }

    public List<Produto> produtosSemEstoque() {
        return produtoRepository.findByQuantidadeEstoqueEquals(0);
    }


}
