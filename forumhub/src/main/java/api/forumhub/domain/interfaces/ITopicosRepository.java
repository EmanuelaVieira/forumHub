package api.forumhub.domain.interfaces;

import api.forumhub.domain.topicos.Topicos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITopicosRepository extends JpaRepository<Topicos, Long> {

    boolean existsByTituloIgnoreCase(String titulo);
    boolean existsByMensagemIgnoreCase(String mensagem);
}
