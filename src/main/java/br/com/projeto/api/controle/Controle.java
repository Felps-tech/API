package br.com.projeto.api.controle;

import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.modelo.Cliente;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;
import br.com.projeto.api.servico.Servico;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class Controle {

    @Autowired
    private Repositorio acao;

    @Autowired
    private Servico servico;

    @PostMapping("/api")
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj) {
        return servico.cadastrar(obj);
    }

    @GetMapping("/api")
    public ResponseEntity<?> selecionar() {
        return servico.selecionar();
    }

    @GetMapping("/api/{codigo}")
    public ResponseEntity<?> selecionarPeloCodigo(@PathVariable int codigo) {
        return servico.selecionarPeloCodigo(codigo);
    }

    @PutMapping("/api")
    public ResponseEntity<?> editar(@RequestBody Pessoa obj) {
        return servico.editar(obj);
    }

    @DeleteMapping("/api/{codigo}")
    public ResponseEntity<?> remover(@PathVariable int codigo) {
        return servico.remover(codigo);
    }

    @GetMapping("/api/contador")
    public long contador() {
        return acao.count();
    }

    @GetMapping("/api/ordenarNome")
    public List<Pessoa> ordenarNome() {
        return acao.findByOrderByNome();

    }

    @GetMapping("api/nomeContem")
    public List<Pessoa> nomeContem() {
        return acao.findByNomeContaining("fe");
    }

    @GetMapping("api/iniciaCom")
    public List<Pessoa> iniciaCom() {
        return acao.findByNomeStartsWith("f");

    }

    @GetMapping("api/terminaCom")
    public List<Pessoa> terminaCom() {
        return acao.findByNomeEndsWith("e");

    }

    @GetMapping("api/somaIdades")
    public int somaIdades() {
        return acao.somaIdades();
    }

    @GetMapping("api/idadeMaiorIgual")
    public List<Pessoa> idadeMaiorIgual() {
        return acao.idadeMaiorIgual(18);
    }

    @GetMapping("") // URL PADRÃO localhost:8080/
    public String mensagem() {
        return "Hello World";
    }

    @GetMapping("/boasvindas") // URL DIFERENTE localhost:8080/boasvindas
    public String boasVindas() {
        return "Seja bem vindo(a)";
    }

    @GetMapping("/boasvindas/{nome}") // URL COM NOME E VARIAVEL localhost:8080/boasvindas/nome
    public String boasVindas(@PathVariable String nome) { // PathVariable serve para pegar a varivel do URL
        return "Seja bem vindo(a) " + nome; // Falha se não houver um nome escrito
    }

    @PostMapping("/pessoa") // Não usar GetMapping com RequestBody, pois o GetMapping é para buscar dados, e
                            // o PostMapping é para enviar dados
    public Pessoa pessoa(@RequestBody Pessoa p) { // Request Body
        return p;
    }

    @GetMapping("/status")

    public ResponseEntity<?> status() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/cliente")
    public void cliente(@Valid @RequestBody Cliente obj){

    }
}
