package visao;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class JCelula extends JPanel {
    
    private int linha, coluna;
    private JPeca jpeca;

    public JCelula(int linha, int coluna){

        this.linha = linha;
        this.coluna = coluna;
    }

    public JCelula(JPeca jpeca){
        this.jpeca = jpeca;
        this.linha = jpeca.getPeca().getLinha();
        this.coluna = jpeca.getPeca().getColuna();
        this.add(jpeca);

        if((jpeca.getPeca() != null) && jpeca.getPeca().isSelecionada()){
            this.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        }
    }

    public int getLinha() {
        return this.linha;
    }

    public int getColuna() {
        return this.coluna;
    }

}
