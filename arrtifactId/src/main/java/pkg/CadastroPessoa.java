package pkg;

import java.time.LocalDate;

public class CadastroPessoa {
    private final ApiDosCorreios apiDosCorreios;

    public CadastroPessoa(final ApiDosCorreios apiDosCorreios) {
        this.apiDosCorreios = apiDosCorreios;
    }

    public Pessoa cadastrarPessoa(String nome, String documento, LocalDate nascimento, String cep) {
        Pessoa pessoa = new Pessoa(documento, nascimento, nome);
        DadosLocalizacao dadosLocalizacao = apiDosCorreios.buscarDadosComBaseNoCep(cep);
        pessoa.adicionarDadosEndereco(dadosLocalizacao);
        return pessoa;
    }
}