package modelos;

public class Peao extends Peca {

    private boolean primeiroMovimento = true;

    Peao(EnumCor cor, int linha, int coluna){
        super(cor, linha, coluna, "c:/Users/gusta/Documents/Programacao/Projetos-java/xadrez/src/imagens/PEAO"+cor+".png");
    }

    Peao(EnumCor cor, int linha, int coluna, String imagem) {
        super(cor, linha, coluna, imagem);
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean validaMovimento(int linhaDestino, int colunaDestino) {
        Peca  pecaDestino = getTabuleiro().getPeca(linhaDestino, colunaDestino);
        if(pecaDestino == null && colunaDestino != getColuna()){
            return false;
        }
   
        return true;
    }

    public void setPrimeiroMovimento(boolean b) {
        this.primeiroMovimento = b;
    }
    
}
