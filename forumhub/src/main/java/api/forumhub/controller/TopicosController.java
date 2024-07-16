package api.forumhub.controller;

import api.forumhub.domain.topicos.DTOs.ListagemDeTopicos;
import api.forumhub.domain.topicos.DTOs.TopicosAtualizacao;
import api.forumhub.domain.topicos.DTOs.TopicosRequisicao;
import api.forumhub.domain.interfaces.ITopicosRepository;
import api.forumhub.domain.topicos.DTOs.DetalharTopicos;
import api.forumhub.domain.topicos.Topicos;
import api.forumhub.domain.topicos.DTOs.TopicosDadosAtualizados;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos")
public class TopicosController {

    @Autowired
    private ITopicosRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTopicos(@RequestBody @Valid TopicosRequisicao dados, UriComponentsBuilder uriBuilder) {
        if (repository.existsByTituloIgnoreCase(dados.titulo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tópico com título já existente.");
        } else if (repository.existsByMensagemIgnoreCase(dados.mensagem())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tópico com mensagem já existente.");
        }

        var topicos = new Topicos(dados);
        repository.save(topicos);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topicos.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicosDadosAtualizados(topicos));
    }


    @GetMapping
    public ResponseEntity<Page<ListagemDeTopicos>> listarTopicos(@PageableDefault(size = 10, sort = {"curso", "dataCriacao"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(ListagemDeTopicos::new);

        return ResponseEntity.ok(page);
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizarTopicos(@RequestBody @Valid TopicosAtualizacao dados) {
        var topicos = repository.getReferenceById(dados.id());
        topicos.atualizarDados(dados);

        return ResponseEntity.ok(new TopicosDadosAtualizados(topicos));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarTopicos(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity detalharTopicos(@PathVariable Long id) {
        var topicos = repository.getReferenceById(id);

        return ResponseEntity.ok(new DetalharTopicos(topicos));
    }
}
