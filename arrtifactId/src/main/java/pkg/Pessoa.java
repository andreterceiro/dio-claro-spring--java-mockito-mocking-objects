package pkg;

import java.time.LocalDate;

public class Pessoa {
    private String nome;
    private String documento;
    private LocalDate nascimento;
    private DadosLocalizacao endereco;

    public Pessoa(String documento, LocalDate nascimento, String nome) {
        this.documento = documento;
        this.nascimento = nascimento;
        this.nome = nome;
    }

    public void adicionarDadosEndereco(DadosLocalizacao endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public DadosLocalizacao getEndereco() {
        return endereco;
    }

    public void setEndereco(DadosLocalizacao endereco) {
        this.endereco = endereco;
    }
}