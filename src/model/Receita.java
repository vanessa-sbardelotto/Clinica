package model;

public class Receita {
    private String medicamento;
    private String orientacao;
    private boolean controlado;

    public Receita(String medicamento, String orientacao, boolean controlado) {
        this.medicamento = medicamento;
        this.orientacao = orientacao;
        this.controlado = controlado;
    }

    @Override
    public String toString() {
        return "Medicamento: " + medicamento +
                "\nOrientações: " + orientacao +
                "\nControlado: " + (controlado ? "Sim" : "Não");
    }
}
