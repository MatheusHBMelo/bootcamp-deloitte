package dev.matheushbmelo.bootcamp_deloitte.repository;

import dev.matheushbmelo.bootcamp_deloitte.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
