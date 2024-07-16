package api.forumhub.domain.topicos;

import api.forumhub.domain.topicos.DTOs.TopicosAtualizacao;
import api.forumhub.domain.topicos.DTOs.TopicosRequisicao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Topicos")
@Table(name = "topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private TopicosStatus status;

    private String autor;

    private String curso;

    public Topicos(TopicosRequisicao dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.autor = dados.autor();
        this.curso = dados.curso();
    }

    public void atualizarDados(TopicosAtualizacao dados) {
        if (dados.titulo() != null){
            this.titulo = dados.titulo();
        }

        if (dados.mensagem() != null){
            this.mensagem = dados.mensagem();
        }

        if (dados.autor() != null){
            this.autor = dados.autor();
        }

        if (dados.curso() != null){
            this.curso = dados.curso();
        }
    }
}
