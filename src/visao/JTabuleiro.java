package visao;

import java.awt.GridLayout;
import modelos.Peca;
import modelos.Tabuleiro;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JTabuleiro extends JPanel implements MouseListener {

    private Tabuleiro tabuleiro;

    public JTabuleiro(Tabuleiro tabuleiro){
        this.tabuleiro = tabuleiro;
        desenhaTabuleiro();
    }

    public void desenhaTabuleiro(){
        this.removeAll();
        this.setLayout(new GridLayout(8, 8));

        for(int i = 0; i < 8; i++){
            for(int f = 0; f < 8; f++){
                JCelula jCelula;
                Peca peca = this.tabuleiro.getPeca(i, f);
                
                if (peca == null) {
                    jCelula = new JCelula(i, f);
                }else {
                    jCelula = new JCelula(new JPeca(peca));
                }

                if((i+f) %2 == 0){
                    jCelula.setBackground(Color.WHITE);
                }else{
                    jCelula.setBackground(Color.BLACK);
                }
                this.add(jCelula);
                jCelula.addMouseListener(this);
            }
        }

        this.revalidate();
    }

    public Tabuleiro getTabuleiro(){
        return this.tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro){
        this.tabuleiro = tabuleiro;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JCelula jCelula = (JCelula) e.getSource();
        this.tabuleiro.realizaJogada(jCelula.getLinha(), jCelula.getColuna());
        this.desenhaTabuleiro();
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}

