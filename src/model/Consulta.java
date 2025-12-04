package model;

import exceptions.HorarioInvalidoException;

public class Consulta {

    private Paciente paciente;
    private Medico medico;
    private UnidadeDeSaude unidade;
    private String horario;
    private Receita receita;


    public Consulta(Paciente paciente, Medico medico, UnidadeDeSaude unidade, String horario, Receita receita)
            throws HorarioInvalidoException {

        if (!horario.matches("\\d{2}:\\d{2}")) {
            throw new HorarioInvalidoException("Horário inválido! Use o formato HH:MM.");
        }

        this.paciente = paciente;
        this.medico = medico;
        this.unidade = unidade;
        this.horario = horario;
        this.receita = receita;
    }

    @Override
    public String toString() {
        return "Consulta: " + paciente.getNome() +
                " | Médico: " + medico.getNome() +
                " | Unidade: " + unidade.getNome() +
                " | Horário: " + horario +
                (receita != null ? "\n  Receita: " + receita : "\n  Sem receita");
    }
}
