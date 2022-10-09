import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComputaBusca250Arquivos{
    public static void main(String[] args) throws Exception {
        String palavra = "traditional";
        int numeroDeOcorrencias = 0;
        boolean verificarAListaToda = false;

        if(verificarAListaToda){
        List<ComputaBusca250ArquivosThread> listaThreads = new ArrayList<>();
        for (int i = 0; i <=249; i++) {
            ComputaBusca250ArquivosThread thread1 = new ComputaBusca250ArquivosThread(i,i,palavra);
            listaThreads.add(thread1);
            thread1.start();
        }

        for (ComputaBusca250ArquivosThread item: listaThreads) {
            item.join();
        }

        for (ComputaBusca250ArquivosThread item: listaThreads) {
            numeroDeOcorrencias += item.getValorGuardado();
        }
        System.out.println(numeroDeOcorrencias);

        } else{
            List<String> lista;
            int numeroLista = 1;
            List<ComputaBuscaPalavraEmLista> listaDeArquivo = new ArrayList<>();
            lista = convertFileToList("dataset/"+numeroLista+".txt");
            System.out.println(lista.size());
            for (int i = 0; i <= lista.size()-1 ; i++) {
                ComputaBuscaPalavraEmLista thread = new ComputaBuscaPalavraEmLista(i,i,palavra,lista);
                listaDeArquivo.add(thread);
                thread.start();
            }

            for (ComputaBuscaPalavraEmLista thread: listaDeArquivo) {
                thread.join();
            }

            for (ComputaBuscaPalavraEmLista thread: listaDeArquivo) {
                numeroDeOcorrencias += thread.getValorGuardado();
            }
            System.out.println(numeroDeOcorrencias);

        }
    }
    private static List<String> convertFileToList(String caminho) throws Exception {
        Path path = Paths.get(caminho);
        List<String> linhasArquivo = Files.readAllLines(path);
        return linhasArquivo;
    }
}
