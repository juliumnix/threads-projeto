import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ComputaBusca2Thread {
    public static void main(String[] args) throws Exception {
        int numeroDeOcorrencia = 0;
        String palavra = "traditional";
        boolean verificarAListaToda = false;

        if(verificarAListaToda){
            int primeiraThread = Math.round(249/2);
            int segundaThread = primeiraThread +1;
            ComputaBuscaPalavra thread1 = new ComputaBuscaPalavra(0,primeiraThread, palavra);
            ComputaBuscaPalavra thread2 = new ComputaBuscaPalavra(segundaThread, 249, palavra);
            thread1.start();
            thread2.start();


            thread1.join();
            thread2.join();
            numeroDeOcorrencia += thread1.getValorGuardado();
            numeroDeOcorrencia += thread2.getValorGuardado();
        } else {
            List<String>lista;
            int numeroLista = 1;
            lista = convertFileToList("dataset/"+numeroLista+".txt");
            int primeiraThread = Math.round(lista.size()/2);
            int segundaThread = primeiraThread +1;
            ComputaBuscaPalavraEmLista thread1 = new ComputaBuscaPalavraEmLista(0,primeiraThread, palavra, lista);
            ComputaBuscaPalavraEmLista thread2 = new ComputaBuscaPalavraEmLista(segundaThread,lista.size()-1, palavra, lista);
            thread1.start();
            thread2.start();


            thread1.join();
            thread2.join();
            numeroDeOcorrencia += thread1.getValorGuardado();
            numeroDeOcorrencia += thread2.getValorGuardado();


        }


        System.out.println(numeroDeOcorrencia);
    }
    private static List<String> convertFileToList(String caminho) throws Exception {
        Path path = Paths.get(caminho);
        List<String> linhasArquivo = Files.readAllLines(path);
        return linhasArquivo;
    }
}
