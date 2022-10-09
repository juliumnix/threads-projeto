import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ComputaBuscaPalavra extends Thread{
    private int inicio;
    private int fim;
    private String palavra;
    private int valorGuardado;

    public ComputaBuscaPalavra(int inicio, int fim, String palavra){
        this.inicio = inicio;
        this.fim = fim;
        this.palavra = palavra;
    }

    public int getValorGuardado(){
        return this.valorGuardado;
    }
    
    @Override
    public void run() {
       this.valorGuardado = buscaPalavra(inicio,fim);
    }



    private int buscaPalavra(int inicio, int fim)  {
        List<String> lista;
        int numeroDeOcorrencia = 0;
        for (int i = inicio; i <= fim; i++) {
            try {
                lista = convertFileToList("dataset/"+i+".txt");
                for (String palavra: lista) {
                    if(palavra.equalsIgnoreCase(this.palavra)){
                        numeroDeOcorrencia++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return numeroDeOcorrencia;
    }
    private static List<String> convertFileToList(String caminho) throws Exception {
        Path path = Paths.get(caminho);
        List<String> linhasArquivo = Files.readAllLines(path);
        return linhasArquivo;
    }
}
