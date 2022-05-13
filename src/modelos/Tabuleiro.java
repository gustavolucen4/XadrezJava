package modelos;

import controle.ControlaTempo;

public class Tabuleiro {
    
    private Peca[][] pecas;
    private Peca pecaSelecionada = null;
    private EnumCor vez = EnumCor.BRANCA;
    public static final int TEMPO_JOGADA = 10000;
    private ControlaTempo controlaTempo;

    public Tabuleiro(ControlaTempo controlaTempo){
        this.controlaTempo = controlaTempo;
        this.pecas = new Peca[8][8];
        Torre torreBranca1 = new Torre(EnumCor.BRANCA, 0, 0);
        Torre torreBranca2 = new Torre(EnumCor.BRANCA, 0, 7);
        this.adicionaPeca(torreBranca1);
        this.adicionaPeca(torreBranca2);

        Torre torrePreta1 = new Torre(EnumCor.PRETA, 7, 0);
        Torre torrePreta2 = new Torre(EnumCor.PRETA, 7, 7);
        this.adicionaPeca(torrePreta1);
        this.adicionaPeca(torrePreta2);

        Cavalo cavaloBranca1 = new Cavalo(EnumCor.BRANCA, 0, 1);
        Cavalo cavaloBranca2 = new Cavalo(EnumCor.BRANCA, 0, 6);
        this.adicionaPeca(cavaloBranca1);
        this.adicionaPeca(cavaloBranca2);

        Cavalo cavaloPreta1 = new Cavalo(EnumCor.PRETA, 7, 1);
        Cavalo cavaloPreta2 = new Cavalo(EnumCor.PRETA, 7, 6);
        this.adicionaPeca(cavaloPreta1);
        this.adicionaPeca(cavaloPreta2);

        Bispo bispoBranca1 = new Bispo(EnumCor.BRANCA, 0, 2);
        Bispo bispoBranca2 = new Bispo(EnumCor.BRANCA, 0, 5);
        this.adicionaPeca(bispoBranca1);
        this.adicionaPeca(bispoBranca2);

        Bispo bispoPreta1 = new Bispo(EnumCor.PRETA, 7, 2);
        Bispo bispoPreta2 = new Bispo(EnumCor.PRETA, 7, 5);
        this.adicionaPeca(bispoPreta1);
        this.adicionaPeca(bispoPreta2);

        Rainha rainhaBranca = new Rainha(EnumCor.BRANCA, 0, 3);
        Rei reiBranca = new Rei(EnumCor.BRANCA, 0, 4);
        this.adicionaPeca(rainhaBranca);
        this.adicionaPeca(reiBranca);

        Rainha rainhaPreta = new Rainha(EnumCor.PRETA, 7, 3);
        Rei reiPreta = new Rei(EnumCor.PRETA, 7, 4);
        this.adicionaPeca(rainhaPreta);
        this.adicionaPeca(reiPreta);

        for(int i = 0; i < 8; i++){

            Peao peaoBranca = new Peao(EnumCor.BRANCA, 1 , i);
            this.adicionaPeca(peaoBranca);

            Peao peaoPreta = new Peao(EnumCor.PRETA, 6 , i);
            this.adicionaPeca(peaoPreta);
        }

    }

    public EnumCor getVez(){
        return this.vez;
    }

    public Peca getPecaSelecionada(){
        return this.pecaSelecionada;
    }

    public void setPecaSelecionada(Peca peca){
        this.pecaSelecionada = peca;
    }

    public Peca getPeca(int linha, int coluna){
        return this.pecas[linha][coluna];
    }

    public void setPeca(Peca peca){
        this.pecas[peca.getLinha()][peca.getColuna()] = peca;
        peca.setTabuleiro(this);
    }

    public void adicionaPeca(Peca peca){
        this.pecas[peca.getLinha()][peca.getColuna()] = peca;
        peca.setTabuleiro(this);
    } 

    public void selecionaPeca(Peca peca){
        if(peca.isSelecionada()){
            peca.setSelecionada(false);
            this.pecaSelecionada = null;
        }else{
            peca.setSelecionada(true);
            this.pecaSelecionada = peca;
        }
    }

    public void movePeca(Peca peca, int novaLinha, int novaColuna){
        if(peca.validaMovimento(novaLinha, novaColuna)){
            this.pecas[peca.getLinha()][peca.getColuna()] = null;
            peca.setLinha(novaLinha);
            peca.setColuna(novaColuna);
            if(peca instanceof Peao){
                Peao peao = (Peao) peca;
                peao.setPrimeiroMovimento(false);
            }
            this.setPeca(peca);
            this.selecionaPeca(peca);
            this.inverteVez();
        }
    }

    public void inverteVez(){
        if(this.vez.equals(EnumCor.BRANCA)){
            this.vez = EnumCor.PRETA;
        }else{
            this.vez = EnumCor.BRANCA;
        }
        controlaTempo.zeraCronometro();
    }

    public void realizaJogada(int linha, int coluna) {
    
        Peca peca = this.getPeca(linha, coluna);
        if(this.pecaSelecionada == null){
            if(peca != null && peca.getCor().equals(this.vez)){
                this.selecionaPeca(peca);
            }
        }else {
            if(this.pecaSelecionada == peca){
                this.selecionaPeca(peca);
            }else {
                if (peca == null || !peca.getCor().equals(this.pecaSelecionada.getCor())) {
                    this.movePeca(this.pecaSelecionada, linha, coluna);
                }
            }
        }
    }
}
