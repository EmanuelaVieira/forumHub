package api.forumhub.domain.topicos.DTOs;

import api.forumhub.domain.topicos.Topicos;
import api.forumhub.domain.topicos.TopicosStatus;

import java.time.LocalDateTime;

public record DetalharTopicos(

        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        TopicosStatus status,
        String autor,
        String curso) {

    public DetalharTopicos (Topicos topicos){
        this(topicos.getTitulo(), topicos.getMensagem(), topicos.getDataCriacao(), topicos.getStatus(),
                topicos.getAutor(), topicos.getCurso());
    }
}

