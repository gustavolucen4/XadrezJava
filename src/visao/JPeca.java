package visao;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import modelos.Peca;

public class JPeca extends JLabel {
    private Peca peca;

    JPeca(Peca peca){
        this.peca = peca;
        
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new File(peca.getImagem()));
            Image image = bufferedImage.getScaledInstance(120, 100, Image.SCALE_DEFAULT);
            this.setIcon(new ImageIcon(image));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Peca getPeca(){
        return this.peca;
    }
}
