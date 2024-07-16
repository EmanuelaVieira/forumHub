package api.forumhub.domain.interfaces;

import api.forumhub.domain.usuarios.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUsuariosRepository extends JpaRepository<Usuarios, Long> {

    UserDetails findByLogin(String login);

}