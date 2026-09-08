package dev.dulciobernardo7.CadastroDeFuncionarios;

import dev.dulciobernardo7.CadastroDeFuncionarios.Controller.DTO.PessoaDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class CadastroDeFuncionariosApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void pessoaDtoShouldAllowEmptySexoValueForFormBinding() {
		PessoaDTO pessoa = new PessoaDTO();
		assertNull(pessoa.getSexo());
	}

}
