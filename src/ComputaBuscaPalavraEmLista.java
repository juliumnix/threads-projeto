import java.util.List;

public class ComputaBuscaPalavraEmLista extends Thread{
    private int inicio;
    private int fim;
    private String palavra;
    private int valorGuardado;
    private List<String> lista;

    public ComputaBuscaPalavraEmLista(int inicio, int fim, String palavra, List<String>lista){
        this.inicio = inicio;
        this.fim = fim;
        this.palavra = palavra;
        this.lista = lista;
    }

    public int getValorGuardado(){
        return this.valorGuardado;
    }
    @Override
    public void run() {
        this.valorGuardado = buscaPalavra(inicio,fim);
    }

    private int buscaPalavra(int inicio, int fim)  {
        int numeroDeOcorrencia = 0;
        for (int i = inicio; i <= fim; i++) {
                    if(this.lista.get(i).equalsIgnoreCase(this.palavra)){
                        numeroDeOcorrencia++;
                    }
        }
        return numeroDeOcorrencia;
    }
}
