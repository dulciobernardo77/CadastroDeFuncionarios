package dev.dulciobernardo7.CadastroDeFuncionarios.Repository;

import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoasRepository extends JpaRepository<PessoaModel, Long> {

	boolean existsByBi(String bi);

	boolean existsByBiAndIdNot(String bi, Long id);
}
