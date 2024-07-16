package api.forumhub.domain.topicos.DTOs;

import jakarta.validation.constraints.NotBlank;

public record TopicosRequisicao(

        @NotBlank
        String titulo,

        @NotBlank
        String mensagem,

        @NotBlank
        String autor,

        @NotBlank
        String curso) {
}
