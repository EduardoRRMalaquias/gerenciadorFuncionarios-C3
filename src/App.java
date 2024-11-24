import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Cargo ObjCargo =  new Cargo(11, "programador", 2100.00);
        Funcionario funcionario = new Funcionario(11, "eduardo", "126.774.7-90", ObjCargo);
        String nomeArquivo = "src/dados/Funcionarios.csv";

        //adicionar aluno ao arquivo
        try {
            boolean arquivoExiste = new File(nomeArquivo).exists();
            FileWriter escritor = new FileWriter(nomeArquivo, arquivoExiste);
            if (!arquivoExiste) {
                escritor.write(funcionario.chaves());
            }

            escritor.write(funcionario.toString(false));
            escritor.flush();
            escritor.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Deu ruin");
        }


        //Editar arquivo
        int idFuncionario = 11;
        Funcionario novosDados = new Funcionario(9, "brunao", "126.774", ObjCargo);
        List<Funcionario> funcionarios = new ArrayList<>();
        boolean funcionarioEncontrado = false;
        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            boolean arquivoExiste = new File(nomeArquivo).exists();
            FileWriter escritor = new FileWriter(nomeArquivo, arquivoExiste);
            if (!arquivoExiste) {
                System.out.println("Arquivo não encontrado.");
            }

            

            String linha = leitor.readLine(); // Ler cabeçalho
            String cabecalho = linha;
            while ((linha = leitor.readLine()) != null) {

                String[] partes = linha.split(";");
                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];
                String cpf = partes[2];
                String cargo = partes[3];

                Funcionario f = new Funcionario(id, nome, cpf);


                if (f.getId() == idFuncionario) {
                    f = novosDados; // Substituir pelos novos dados
                    funcionarioEncontrado = true;
                }
                funcionarios.add(f);
            }

            if (!funcionarioEncontrado) {
                System.out.println("Funcionário com ID " + idFuncionario + " não encontrado.");
                return;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao ler o arquivo.");
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeArquivo))) {
            escritor.write(funcionarios.get(0).chaves()); // Escreve o cabeçalho
            for (Funcionario f : funcionarios) {
                escritor.write(f.toString(false));
            }
            System.out.println("Funcionário atualizado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar o arquivo.");
        }
 

        //consultar todos os funcionarios
        try {
            BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            boolean primeiraLinha = true;

            while ((linha = leitor.readLine()) != null ) { 
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] celula = linha.split(";");

                int id = Integer.parseInt(celula[0]) ;
                String nome = celula[1];
                String cpf = celula[2];
                String cargo = celula[3];            
            
                System.out.println
                ("Id: "+ id +
                " - Nome: "+ nome +
                " - Cpf: "+ cpf +
                " - Cargo: "+ cargo);
            }
            leitor.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Deu ruin");
        }


        // consultar funcionario por id
        int index = 9;
        try {
            BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            boolean primeiraLinha = true;

            while ((linha = leitor.readLine()) != null ) { 
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] celula = linha.split(";");

                int id = Integer.parseInt(celula[0]) ;
                

                if(index == id){
                    String nome = celula[1];
                    String cpf = celula[2];
                    String cargo = celula[3];
                
                    System.out.println
                    ("Id: "+ id +
                    " - Nome: "+ nome +
                    " - Cpf: "+ cpf +
                    " - Cargo: "+ cargo);
                }

                
            }
            leitor.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Deu ruin");
        }


        // falta aqui apagar ja esta em produção pelo chatGPT
        List<Funcionario> funcionariosA = new ArrayList<>();
        boolean funcionarioEncontradoA = false;
        int idFuncionarioA = 8;

        // Ler o arquivo CSV e carregar os dados na memória
        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            if (!new File(nomeArquivo).exists()) {
                System.out.println("Arquivo não encontrado.");
            }

            String linha = leitor.readLine(); // Ler cabeçalho
            String cabecalho = linha; // Armazenar o cabeçalho para reescrever depois
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";");
                int id = Integer.parseInt(partes[0]);

                if (id == idFuncionarioA) {
                    funcionarioEncontradoA = true;
                } else {
                    // Adiciona as linhas que não correspondem ao ID
                    String nome = partes[1];
                    String cpf = partes[2];
                    String cargo = partes[3];
                    Funcionario f = new Funcionario(id, nome, cpf);
                    funcionariosA.add(f);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao ler o arquivo.");
        }

        // Verifica se o funcionário foi encontrado
        if (!funcionarioEncontradoA) {
            System.out.println("Funcionário com ID " + idFuncionarioA + " não encontrado.");
        }

        // Reescrever o arquivo com os dados restantes
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeArquivo))) {
            if (!funcionariosA.isEmpty()) {
                escritor.write(funcionariosA.get(0).chaves()); // Escreve o cabeçalho
                for (Funcionario f : funcionariosA) {
                    escritor.write(f.toString(false));
                }
            }
            System.out.println("Funcionário com ID " + idFuncionarioA + " removido com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar o arquivo.");
        } 
    }
    
}
 