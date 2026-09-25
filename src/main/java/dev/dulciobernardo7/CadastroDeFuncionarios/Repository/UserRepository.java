package dev.dulciobernardo7.CadastroDeFuncionarios.Repository;

import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
