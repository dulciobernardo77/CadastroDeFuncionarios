package dev.dulciobernardo7.CadastroDePessoas.Repository;

import dev.dulciobernardo7.CadastroDePessoas.Entity.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoasRepository extends JpaRepository<PessoaModel, Long> {
}
