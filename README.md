# Message

To be clear. The content here is a content based on a free course. Noone can reproduce this material. If you wanna to know this material, my advice is to you access the **free** [course](https://web.dio.me/track/coding-the-future-claro-java-spring-boot). And if they end the course or start to ask money to study in the course? Well, this considerations are out of my control, but the DIO material is excelent and the course is amazing! If you acquire a DIO paln, you will have access to amazing courses.


# General

First of all, I created the project:

- With Maven (`~/bin/mvn-ask-parameters`);
- I already solved the problem with Java version (please see [this repository](https://github.com/andreterceiro/dio-java-mockito-initial));
- From [https://mvnrepository.com/repos/central] MVN Reposiory I installed two Mockito dependencies:
  - Mockito Core
  - Mockito JUnit Jupiter

Without annotations, the teacher showed other way to use Mockito:
![other way to use Mockito](images/other-way-to-use-mockito.png)

A test teached in the class:
![testing with Mockito](images/testing-with-mockito.png)

Teacher passed us in the class this external references:
![external references](images/external-references.png)

For now, we made the test of the `spy` this way:

```
package pkg;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
        cadastroPessoa.cadastrarPessoa("André", "123", LocalDate.now(), "32244000");

        // But here we already used. So you will get an error if you uncomment this line
        // Mockito.verifyNoInteractions(this.log);

        // You also can't uncomment this next line beacuse new Log() is not a mock (do not
        // have the anotation @Mock nor @Spy as example)
        // Mockito.verifyNoInteractions(new Log());
    }
}
```

Please pay see with attention all the things, like the `imports` , the `annotations`, the method call `Mockito.verifyNoInteractions(this.log);` and `the comments`. Please pay attention that we maybe update the source code of this class, but the source code above is the source code that I wanna you understand for a basic `spy`.

You can also inspect the order of the calls:
![image explaining in order verification](images/in-order.png)

In the previous image is cool if you verify the details, like the annotation "`@Spy`" and the call to "`ArgumentMatchers.anyInt()`".

In the next image we show how we can verify the number of times a method is called. The use can be more useful, like as example a verification of a method that is called by another method:
![counting method calls](images/count.png)

External references of the teacher when we are talking about a `spy`:
![spy external references](images/spy-external-references.png)

Related to capture arguments, I did not developed a code because this feature does not seems very interesting to me. But the code teached in the class ("main" code):
![capturing arguments - code](images/capturing-arguments-code.png)

The next part, about `forcing a return or a throw` seemed more interesting to me. The scenario is one when I wanna force a return or a throw in a object that is used by an object that I call. In other words:

- I call `cadastroPessoa.cadastrarPessoa()`;
- This method uses `apiCorreios.buscarDadosComBaseNoCep()`;
- I can force the return of `apiCorreios.buscarDadosComBaseNoCep()` or force that an exception be thrown;
- But I never call manually call `apiCorreios.buscarDadosComBaseNoCep()`. This method is called by `cadastroPessoa.cadastrarPessoa()`;
- `cadastroPessoa.cadastrarPessoa()`, that we call, return a `Pessoa`. Then we compare the returned `Pessoa..getEndereco().getBairro()` (example) with the return that we forced to `apiCorreios`;

Requisite: `apiCorreios` needs to be a mock (please see the `@Mock` annotation):
![api correios mock](images/api-correios-mock.png)

Test:
![forcing a specific return or an exception](images/forcing-return-or-exception.png)

Here I show a alternative way to test `exceptions`:
![alternative way to test exceptions](images/alternative-to-test-exceptions.png)

Here the teacher showed `doNothing` and the case when was (without `doNothing`) to be throwed and exception because the request the balance was 3000 and the operation evolves 3500:

![condition](images/condition.png)

![do nothing](images/do-nothing.png)

External references to forcing a return or throw an exception:
![external references to forcing a return or throw an exception](images/external-references-to-force-a-return-or-throw-an-exception.png)

Related to static method testing, [this video class](https://web.dio.me/course/desenvolvendo-testes-utilizando-mockito/learning/d7d7911b-d6b9-43e9-8ee1-a3643d92cfc9?back=/track/coding-the-future-claro-java-spring-boot&tab=undefined&moduleId=undefined) was incompĺete. The class ends suddenly.

I need to "manually" complete the code. I used [this reference](https://www.baeldung.com/mockito-mock-static-methods).

Please see the code:

1- Class with static method to be tested:
![class with static method to be tested](images/class-with-static-method-to-be-tested.png)

2- Test code:
![static method test code](images/static-method-test-code.png)

# References

## Github repository of the teacher

Please see [this link](https://github.com/willyancaetano/mockito-exemplos)

## Slides presented in the class

[Slides](slides-mockito.pptx)
