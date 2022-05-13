package visao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import controle.ControlaTempo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import modelos.*;

public class JXadres extends JFrame {

    private static JLabel lbVez;

    private Tabuleiro tabuleiro;

    private final JButton btReiniciaJogo;
    private final ControlaTempo controlaTempo;
    private final JTabuleiro jTabuleiro;
    //private final JPanel painelCemiterio;
    
    public JProgressBar pbTempo;

    public JXadres() {
        setTitle("Jogo de Xadrez");
        this.setLayout(new BorderLayout());
        pbTempo = new JProgressBar();
        pbTempo.setMinimum(0);
        pbTempo.setMaximum(10000);
        this.controlaTempo = new ControlaTempo(pbTempo);
        this.tabuleiro = new Tabuleiro(controlaTempo);
        this.jTabuleiro = new JTabuleiro(tabuleiro);
        controlaTempo.setJTabuleiro(jTabuleiro);
        this.add(jTabuleiro, BorderLayout.CENTER);
        
        JPanel pnTopo = new JPanel();
        lbVez = new JLabel("VEZ DO: BRANCO");
        pnTopo.add(lbVez);
        this.add(pnTopo, BorderLayout.NORTH);

        JPanel pnLateral = new JPanel();
        pnLateral.setLayout(new GridLayout(2, 1));
        btReiniciaJogo = new JButton("Reiniciar Jogo");

        btReiniciaJogo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                reiniciaJogo();
            }
        });
        pnLateral.add(btReiniciaJogo);

        this.add(pnLateral, BorderLayout.WEST);
        this.add(pbTempo, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Thread threadTempo = new Thread(controlaTempo);
        threadTempo.start();

        this.pack();
        this.setVisible(true);
    }

    private void reiniciaJogo(){
        controlaTempo.zeraCronometro();
        this.tabuleiro = new Tabuleiro(controlaTempo);
        this.jTabuleiro.setTabuleiro(tabuleiro);
        this.jTabuleiro.desenhaTabuleiro();
        setVez(tabuleiro.getVez());
    }

    public static void setVez(EnumCor corVez){
        lbVez.setText("VEZ DO: " + corVez);
    }

}
