package pkg;

public class DadosLocalizacao {
    private String uf;
    private String cidade;
    private String logradouro;
    private String complemento;
    private String bairro;

    public DadosLocalizacao(String bairro, String cidade, String complemento, String logradouro, String uf) {
        this.bairro = bairro;
        this.cidade = cidade;
        this.complemento = complemento;
        this.logradouro = logradouro;
        this.uf = uf;
    }

    public String getUf() {
        return uf;
    }

    public String getCidade() {
        return cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
