package pkg;

import java.time.LocalDate;

public class CadastroPessoa {
    private final ApiDosCorreios apiDosCorreios;
    private Log log;
    public static final String MENSAGEM_LOG_GENERICA = "log";

    public CadastroPessoa(final ApiDosCorreios apiDosCorreios) {
        this.apiDosCorreios = apiDosCorreios;
    }

    public Pessoa cadastrarPessoa(String nome, String documento, LocalDate nascimento, String cep) {
        Pessoa pessoa = new Pessoa(documento, nascimento, nome);
        DadosLocalizacao dadosLocalizacao = apiDosCorreios.buscarDadosComBaseNoCep(cep);
        pessoa.adicionarDadosEndereco(dadosLocalizacao);
        if (this.log != null) {
            this.log.log(CadastroPessoa.MENSAGEM_LOG_GENERICA);
        }
        return pessoa;
    }

    public void adicionarLogger(Log log) {
        this.log = log;
    }
}