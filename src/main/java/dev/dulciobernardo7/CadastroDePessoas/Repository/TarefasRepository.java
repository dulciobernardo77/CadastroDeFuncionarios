package dev.dulciobernardo7.CadastroDePessoas.Repository;

import dev.dulciobernardo7.CadastroDePessoas.Entity.TarefasModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefasRepository  extends JpaRepository<TarefasModel,Long> {
}
