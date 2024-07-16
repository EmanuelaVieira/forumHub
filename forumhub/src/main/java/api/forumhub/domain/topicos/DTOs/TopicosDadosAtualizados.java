package api.forumhub.domain.topicos.DTOs;

import api.forumhub.domain.topicos.Topicos;

public record TopicosDadosAtualizados(
        Long id,
        String titulo,
        String mensagem,
        String autor,
        String curso) {

    public TopicosDadosAtualizados(Topicos topicos){
        this(topicos.getId(), topicos.getTitulo(), topicos.getMensagem(), topicos.getAutor(), topicos.getCurso());
    }
}
