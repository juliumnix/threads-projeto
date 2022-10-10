import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ComputaBuscaXThreads {
    public static void main(String[] args) throws Exception {
        int numeroDeOcorrencia = 0;
        boolean verificarAListaToda = false;
        String palavra = "traditional";
        int numeroDeCores = 6;

        List<ComputaBuscaPalavra> listaDeThreads = new ArrayList<>();
        List<ComputaBuscaPalavraEmLista> listaDeThreadsEm1Lista = new ArrayList<>();
        if(verificarAListaToda){
            int primeiraThread = Math.round(249/numeroDeCores);
            int aux = primeiraThread;
            ComputaBuscaPalavra thread1 = new ComputaBuscaPalavra(0,primeiraThread, palavra);
            listaDeThreads.add(thread1);
            for (int i = 0; i < numeroDeCores - 1; i++) {
                int valorInicio = aux +1;
                int valorFim = valorInicio + primeiraThread;

                aux = valorFim;
                if(valorFim >= 249){
                    aux = 249;
                    valorFim = 249;
                }
                ComputaBuscaPalavra thread = new ComputaBuscaPalavra(valorInicio,valorFim, palavra);
                listaDeThreads.add(thread);

            }

            for (ComputaBuscaPalavra thread: listaDeThreads) {
                thread.start();
            }

            for (ComputaBuscaPalavra thread: listaDeThreads) {
                thread.join();
            }

            for (ComputaBuscaPalavra thread: listaDeThreads) {
                numeroDeOcorrencia += thread.getValorGuardado();
            }

            System.out.println(numeroDeOcorrencia);


        }else {
            List<String> lista;
            int numeroLista = 1;
            lista = convertFileToList("src/dataset/"+numeroLista+".txt");
            int primeiraThread = Math.round(lista.size()/numeroDeCores);
            ComputaBuscaPalavraEmLista thread1 = new ComputaBuscaPalavraEmLista(0,primeiraThread, palavra,lista);
            int aux = primeiraThread;
            listaDeThreadsEm1Lista.add(thread1);

            for (int i = 0; i < numeroDeCores - 1; i++) {
                int valorInicio = aux +1;
                int valorFim = valorInicio + primeiraThread;

                aux = valorFim;
                if(valorFim >= lista.size()-1){
                    aux = lista.size()-1;
                    valorFim = lista.size()-1;
                }
                ComputaBuscaPalavraEmLista thread = new ComputaBuscaPalavraEmLista(valorInicio,valorFim, palavra, lista);
                listaDeThreadsEm1Lista.add(thread);

            }

            for (ComputaBuscaPalavraEmLista thread: listaDeThreadsEm1Lista) {
                thread.start();
            }

            for (ComputaBuscaPalavraEmLista thread: listaDeThreadsEm1Lista) {
                thread.join();
            }

            for (ComputaBuscaPalavraEmLista thread: listaDeThreadsEm1Lista) {
                numeroDeOcorrencia += thread.getValorGuardado();
            }
            System.out.println(numeroDeOcorrencia);
        }
    }
    private static List<String> convertFileToList(String caminho) throws Exception {
        Path path = Paths.get(caminho);
        List<String> linhasArquivo = Files.readAllLines(path);
        return linhasArquivo;
    }
}
