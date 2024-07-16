package api.forumhub.domain.topicos.DTOs;

import jakarta.validation.constraints.NotNull;

public record TopicosAtualizacao(

        @NotNull
        Long id,
        String titulo,
        String mensagem,
        String autor,
        String curso
) {
}
