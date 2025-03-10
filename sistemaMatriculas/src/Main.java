import com.opencsv.exceptions.CsvValidationException;
import model.Aluno;
import model.Disciplina;
import model.Professor;
import service.Cobranca;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CsvValidationException, IOException {
        Scanner scanner = new Scanner(System.in);

        // Load data from CSV files
        List<Aluno> alunos;
        List<Professor> professores;
        try {
            alunos = Aluno.carregarDeCSV("Springboot/sistemaMatricula/src/main/java/com/sistemaMatricula/sistemaMatricula/files/alunos.csv");
            professores = Professor.carregarDeCSV("Springboot/sistemaMatricula/src/main/java/com/sistemaMatricula/sistemaMatricula/files/professores.csv");
        } catch (IOException | com.opencsv.exceptions.CsvValidationException e) {
            e.printStackTrace();
            return;
        }

        while (true) {
            System.out.println("Fazer login como: 1. Aluno 2. Professor 3. Exit");
            int userType = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (userType == 1) {
                // Aluno login
                System.out.print("Entre com o nome: ");
                String nome = scanner.nextLine();
                System.out.print("Entre com a senha: ");
                String senha = scanner.nextLine();
                Aluno aluno = alunos.stream().filter(a -> a.getNome().equals(nome) && a.getSenha().equals(senha)).findFirst().orElse(null);

                if (aluno == null) {
                    System.out.println("Aluno não encontrado ou senha incorreta.");
                    continue;
                }

                System.out.println("Aluno fez login");

                while (true) {
                    System.out.println("1. Matricular 2. Cancelar Matrícula 3. Ver Valor de Cobrança 4. Logout");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (choice == 1) {
                        // Matricular
                        System.out.print("Entre com o nome da Disciplina: ");
                        String disciplinaNome = scanner.nextLine();
                        Disciplina disciplina = new Disciplina(disciplinaNome, true); // Assuming all disciplines are mandatory for simplicity
                        try {
                            aluno.matricular(disciplina);
                        } catch (IOException | CsvValidationException e) {
                            e.printStackTrace();
                        }
                    } else if (choice == 2) {
                        // Cancelar Matrícula
                        System.out.print("Entre com o nome da disciplina: ");
                        String disciplinaNome = scanner.nextLine();
                        Disciplina disciplina = new Disciplina(disciplinaNome, true); // Assuming all disciplines are mandatory for simplicity
                        try {
                            aluno.cancelarMatricula(disciplina);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (choice == 3) {
                        // Ver Valor de Cobrança
                        Cobranca cobranca = new Cobranca();
                        Double total = cobranca.gerarCobranca(aluno);
                        System.out.println("Cobrança total: " + total);
                    } else if (choice == 4) {
                        // Logout
                        System.out.println("Aluno fez logout");
                        break;
                    }
                }
            } else if (userType == 2) {
                // Professor login
                System.out.print("Entre com o id de professor: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Entre com a senha: ");
                String senha = scanner.nextLine();
                Professor professor = professores.stream().filter(p -> p.getId() == id && p.getSenha().equals(senha)).findFirst().orElse(null);

                if (professor == null) {
                    System.out.println("Professor não encontrado ou senha incorreta.");
                    continue;
                }

                System.out.println("Professor fez login");

                while (true) {
                    System.out.println("1. Ver Alunos em Classe 2. Logout");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (choice == 1) {
                        // Ver Alunos em Classe
                        System.out.print("Entre com o nome da Disciplina: ");
                        String disciplinaNome = scanner.nextLine();
                        Disciplina disciplina = new Disciplina(disciplinaNome, true); // Assuming all disciplines are mandatory for simplicity
                        List<String> alunosMatriculados = professor.buscarAlunosMatriculados(disciplina);
                        if (alunosMatriculados != null) {
                            System.out.println("Alunos matriculados na disciplina " + disciplinaNome + ": " + String.join(", ", alunosMatriculados));
                        } else {
                            System.out.println("Não foram encontrados estudantes para essa disciplina");
                        }
                    } else if (choice == 2) {
                        // Logout
                        System.out.println("Professor fez logout.");
                        break;
                    }
                }
            } else if (userType == 3) {
                // Exit
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("Escolha inválida.");
            }
        }

        scanner.close();
    }
}