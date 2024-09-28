package pkg;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CadastrarPesoaTest {
    @Mock
    private ApiDosCorreios apiDosCorreios = new ApiDosCorreios();

    @InjectMocks
    private CadastroPessoa cadastroPessoa;

    @Spy
    private Log log;

    @Test
    void validarDadosCadastro() {
        DadosLocalizacao dadosLocalizacao = new DadosLocalizacao("Belém", "São Paulo", "", "rua Álvaro Ramos", "SP");
        Mockito.when(apiDosCorreios.buscarDadosComBaseNoCep("32244000")).thenReturn(dadosLocalizacao);
        Pessoa pessoa = cadastroPessoa.cadastrarPessoa("André", "123", LocalDate.now(), "32244000");

        assertEquals("André", pessoa.getNome());
        assertEquals("123", pessoa.getDocumento());
        assertEquals(LocalDate.now(), pessoa.getNascimento());

        // Testing mocked things with Mockito
        assertEquals(pessoa.getEndereco().getBairro(), "Belém");
        assertEquals(pessoa.getEndereco().getCidade(), "São Paulo");
        assertEquals(pessoa.getEndereco().getUf(), "SP");
    }

    @Test
    void validarLog() {
        // See, at this point we still not used the log object in cadsatroPesssoa.cadastrarPessoa();
        Mockito.verifyNoInteractions(this.log);
        cadastroPessoa.adicionarLogger(this.log);

        String nome = "André";
        String documento = "123";
        LocalDate nascimento = LocalDate.now();
        String cep = "32244000";

        cadastroPessoa.cadastrarPessoa(nome, documento, nascimento, cep);

        Mockito.verify(this.log).log(CadastroPessoa.MENSAGEM_LOG_GENERICA);

        // "Not a mock" error to next line
        // Mockito.verify(cadastroPessoa).cadastrarPessoa(nome, documento, nascimento, cep);

        // But here we already used. So you will get an error if you uncomment this line
        // Mockito.verifyNoInteractions(this.log);

        // You also can't uncomment this next line beacuse new Log() is not a mock (do not
        // have the anotation @Mock nor @Spy as example)
        // Mockito.verifyNoInteractions(new Log());
    }

    @Test
    void validarQuantidadeChamadas() {
        this.log.count();
        this.log.count();
        this.log.count();

        Mockito.verify(
            this.log,
            Mockito.times(3)
        ).count();
    }

    @Test
    void testingForcingException() {
        Mockito.when(this.apiDosCorreios.buscarDadosComBaseNoCep(anyString())).thenThrow(IllegalArgumentException.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> cadastroPessoa.cadastrarPessoa("andré", "111", LocalDate.now(), "32244000"));
    }

    @Test
    void testingForcingSpecificReturn() {
        DadosLocalizacao dadosLocalizacao = new DadosLocalizacao("jardim sei lá", "Paraíba", "apto 25", "Rua Xexé", "PB");

        Mockito.when(this.apiDosCorreios.buscarDadosComBaseNoCep(anyString())).thenReturn(dadosLocalizacao);
        Pessoa pessoa = cadastroPessoa.cadastrarPessoa("andré", "111", LocalDate.now(), "32244000");

        Assertions.assertEquals(pessoa.getEndereco().getBairro(), dadosLocalizacao.getBairro());
        Assertions.assertEquals(pessoa.getEndereco().getCidade(), dadosLocalizacao.getCidade());
    }
}