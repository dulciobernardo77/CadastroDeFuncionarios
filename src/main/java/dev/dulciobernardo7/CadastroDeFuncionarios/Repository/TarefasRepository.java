package dev.dulciobernardo7.CadastroDeFuncionarios.Repository;

import dev.dulciobernardo7.CadastroDeFuncionarios.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefasRepository  extends JpaRepository<TarefasModel,Long> {
}
