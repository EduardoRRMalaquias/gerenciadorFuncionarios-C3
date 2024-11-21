import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class App {
    public static void main(String[] args) {
        Cargo ObjCargo =  new Cargo(4, "limpeza", 1300.00);
        Funcionario funcionario = new Funcionario(4, "julia", "126.774.657-31", ObjCargo);
        String nomeArquivo = "src/dados/Funcionarios.csv";
        System.out.println(hoje);

        //adicionar aluno ao arquivo
        try {
            boolean arquivoExiste = new File(nomeArquivo).exists();
            FileWriter escritor = new FileWriter(nomeArquivo, arquivoExiste);
            if (!arquivoExiste) {
                escritor.write("Id;Nome;Cpf;Cargo\n");
            }

            escritor.write(
                funcionario.getId() + ";" + 
                funcionario.getNome() + ";" +
                funcionario.getCpf() + ";" +
                funcionario.getCargo().getNome() + "\n");
            escritor.flush();
            escritor.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Deu ruin");
        }


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


        // consultar filme por id
        int index = 3;
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
        
    }
}
 