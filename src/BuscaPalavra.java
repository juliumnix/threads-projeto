import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BuscaPalavra {
    public static void main(String[] args) throws Exception {
        List<String> lista;
        boolean verificarAListaToda = false;

        int numeroDeOcorrencia = 0;
        if(verificarAListaToda){
            for (int i = 0; i <= 249; i++) {
                lista = convertFileToList("dataset/"+i+".txt");
                for (String palavra: lista) {
                    if(palavra.equalsIgnoreCase("traditional")){
                        numeroDeOcorrencia++;
                    }
                }
            }
        } else{
            int numeroLista = 1;
            lista = convertFileToList("dataset/"+numeroLista+".txt");
            for (String palavra: lista) {
                if(palavra.equalsIgnoreCase("traditional")){
                    numeroDeOcorrencia++;
                }
            }
        }




        System.out.println(numeroDeOcorrencia);
    }
    private static List<String> convertFileToList(String caminho) throws Exception {
        Path path = Paths.get(caminho);
        List<String> linhasArquivo = Files.readAllLines(path);
        return linhasArquivo;
    }
}
