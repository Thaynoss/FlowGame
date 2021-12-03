package Vue;
import javax.swing.*;

import Modele.CaseType;
import Modele.ModeleCase;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class VueCase extends JPanel {
    private int x, y;
    private ModeleCase ModelC;


    //Constructeur de VueCase
    public VueCase(int _x, int _y) {
        this.x = _x;
        this.y = _y;

        ModelC=new ModeleCase(x,y,CaseType.empty);
    }

    public void setModeleCase(ModeleCase ModelC) {
        this.ModelC = ModelC;
    }

    public void setType(CaseType type) {
        ModelC.setCaseType(type);
    }

    public CaseType getCaseType(){
        return ModelC.getCaseType();
    }

    public ModeleCase getModeleCase(){
        return ModelC;
    }

    
    // Verifie si la case contient un symbole de depart
    public boolean verifCaseSymbole() {
        return ModelC.isSymbole();
    } // coter modele

    // Vérifie si la case est vide
    public boolean verifCaseVide() {
        return ModelC.isCaseEmpty();
    } // coter modele

    // Verifie si la case contient un symbole de chemin
    public boolean verifCaseSymboleHorsS() {
        return ModelC.isCaseChemin();
    } // coter modele



    private void drawNoon(Graphics g) {
        g.drawLine(getWidth()/2, getHeight()/2, getWidth()/2, 0);
    }

    private void drawNine(Graphics g) {
        g.drawLine(0, getHeight()/2, getWidth()/2, getHeight()/2);
    }

    private void drawSix(Graphics g) {
        g.drawLine(getWidth()/2, getHeight()/2, getWidth()/2, getHeight());
    }

    private void drawThree(Graphics g) {
        g.drawLine(getWidth()/2, getHeight()/2, getWidth(), getHeight()/2);
    }



    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());

        g.drawRoundRect(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2, 5, 5);

        Rectangle2D deltaText =  g.getFont().getStringBounds("0", g.getFontMetrics().getFontRenderContext()); // "0" utilisé pour gabarit

        switch(ModelC.type) {
            case S1 :
                //System.out.println("S1");
                g.setColor(Color.BLUE);
                g.fillRect(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
                break;
            case S2 :
                g.setColor(Color.RED);
                g.fillRect(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
                break;
            case S3 :
                g.setColor(Color.ORANGE);
                g.fillRect(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
                break;
            case S4 :
                g.setColor(Color.GREEN);
                g.fillRect(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
                break;
            case S5 :
                g.setColor(Color.YELLOW);
                g.fillRect(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
                break;
            case h0v0 :
                drawNine(g);
                drawNoon(g);
                break;
            case h0v1 :
                drawNine(g);
                drawSix(g);
                break;
            case h1v0:
                drawThree(g);
                drawNoon(g);
                break;
            case h1v1 :
                drawThree(g);
                drawSix(g);
                break;
            case h0h1:
                drawThree(g);
                drawNine(g);
                break;
            case v0v1:
                drawNoon(g);
                drawSix(g);
                break;
            case cross:
                drawNoon(g);
                drawSix(g);
                drawThree(g);
                drawNine(g);
                break;

        }
    }

    public String toString() {
        return x + ", " + y;

    }

}
