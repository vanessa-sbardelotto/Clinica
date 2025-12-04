package principal;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.*;
import exceptions.HorarioInvalidoException;

public class Principal {

    public static void main(String[] args) {

        ArrayList<Paciente> pacientes = new ArrayList<>();
        ArrayList<Especialidade> especialidades = new ArrayList<>();
        ArrayList<Medico> medicos = new ArrayList<>();
        ArrayList<UnidadeDeSaude> unidades = new ArrayList<>();
        ArrayList<Consulta> consultas = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== MENU CLÍNICA MÉDICA =====");
            System.out.println("1 - Cadastrar paciente");
            System.out.println("2 - Cadastrar especialidade");
            System.out.println("3 - Cadastrar médico");
            System.out.println("4 - Cadastrar unidade de saúde");
            System.out.println("5 - Criar consulta");
            System.out.println("6 - Listar consultas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Erro: digite um número válido!");
                sc.nextLine();
                continue;
            }

            switch (opcao) {

                case 1:
                    System.out.print("Nome do paciente: ");
                    String nomeP = sc.nextLine();

                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();

                    pacientes.add(new Paciente(nomeP, cpf));
                    System.out.println("Paciente cadastrado!");
                    break;

                case 2:
                    System.out.print("Nome da especialidade: ");
                    especialidades.add(new Especialidade(sc.nextLine()));
                    System.out.println("Especialidade cadastrada!");
                    break;

                case 3:
                    if (especialidades.isEmpty()) {
                        System.out.println("Cadastre ao menos uma especialidade antes!");
                        break;
                    }

                    System.out.print("Nome do médico: ");
                    String nomeM = sc.nextLine();

                    System.out.println("Escolha a especialidade:");
                    for (int i = 0; i < especialidades.size(); i++) {
                        System.out.println(i + " - " + especialidades.get(i));
                    }

                    int espIndex = sc.nextInt();
                    sc.nextLine();

                    medicos.add(new Medico(nomeM, especialidades.get(espIndex)));
                    System.out.println("Médico cadastrado!");
                    break;

                case 4:
                    System.out.print("Nome da unidade de saúde: ");
                    String nomeU = sc.nextLine();

                    System.out.print("Endereço: ");
                    String endU = sc.nextLine();

                    unidades.add(new UnidadeDeSaude(nomeU, endU));
                    System.out.println("Unidade cadastrada!");
                    break;

                case 5:

                    if (pacientes.isEmpty() || medicos.isEmpty() || unidades.isEmpty()) {
                        System.out.println("Cadastre paciente, médico e unidade antes de criar uma consulta.");
                        break;
                    }

                    System.out.println("Escolha o paciente:");
                    for (int i = 0; i < pacientes.size(); i++) {
                        System.out.println(i + " - " + pacientes.get(i));
                    }
                    int pIndex = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Escolha o médico:");
                    for (int i = 0; i < medicos.size(); i++) {
                        System.out.println(i + " - " + medicos.get(i));
                    }
                    int mIndex = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Escolha a unidade:");
                    for (int i = 0; i < unidades.size(); i++) {
                        System.out.println(i + " - " + unidades.get(i));
                    }
                    int uIndex = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Horário (HH:MM): ");
                    String horario = sc.nextLine();

                    Receita receita = null;

                    System.out.print("Uso de medicamento controlado? (s/n): ");
                    String respControlado = sc.nextLine();

                    if (respControlado.equalsIgnoreCase("s")) {

                        System.out.print("Deseja cadastrar a receita agora? (s/n): ");
                        String respReceita = sc.nextLine();

                        if (respReceita.equalsIgnoreCase("s")) {
                            System.out.print("Medicamento: ");
                            String medicamento = sc.nextLine();

                            System.out.print("Orientações de uso: ");
                            String orientacao = sc.nextLine();

                            receita = new Receita(medicamento, orientacao, true);
                        }

                    } else {

                        System.out.print("Deseja cadastrar uma receita mesmo assim? (s/n): ");
                        String respOpcional = sc.nextLine();

                        if (respOpcional.equalsIgnoreCase("s")) {
                            System.out.print("Medicamento: ");
                            String medicamento = sc.nextLine();

                            System.out.print("Orientações de uso: ");
                            String orientacao = sc.nextLine();

                            receita = new Receita(medicamento, orientacao, false);
                        }
                    }
                    try {
                        Consulta consulta = new Consulta(
                                pacientes.get(pIndex),
                                medicos.get(mIndex),
                                unidades.get(uIndex),
                                horario,
                                receita
                        );

                        consultas.add(consulta);
                        System.out.println("Consulta criada com sucesso!");

                    } catch (HorarioInvalidoException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 6:
                    if (consultas.isEmpty()) {
                        System.out.println("Nenhuma consulta cadastrada.");
                    } else {
                        System.out.println("\n=== LISTA DE CONSULTAS ===");
                        for (Consulta c : consultas) {
                            System.out.println(c);
                            System.out.println("-------------------------");
                        }
                    }
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}
