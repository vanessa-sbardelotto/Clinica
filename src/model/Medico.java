package model;

public class Medico {

    private String nome;
    private Especialidade especialidade;

    public Medico(String nome, Especialidade especialidade) {
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return nome + " - " + especialidade.getNome();
    }
}
