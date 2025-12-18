package Vue;

import Modele.CaseType;
import Modele.ModeleCase;

import java.awt.*;
import javax.swing.*;

/**
 * Représentation visuelle d'une case de la grille.
 * Gère l'affichage des symboles et des chemins.
 * 
 * @author Groupe 17
 * @version 1.0
 */
public class VueCase extends JPanel {
    
    /** Modèle de la case contenant son état */
    private ModeleCase modeleCase;

    /**
     * Constructeur d'une case visuelle.
     * 
     * @param x Position X dans la grille
     * @param y Position Y dans la grille
     */
    public VueCase(int x, int y) {
        modeleCase = new ModeleCase(x, y, CaseType.empty, 0);
    }

    /**
     * Définit le modèle de la case.
     * 
     * @param modeleCase Le nouveau modèle de la case
     */
    public void setModeleCase(ModeleCase modeleCase) {
        this.modeleCase = modeleCase;
    }

    /**
     * Définit le type de la case.
     * 
     * @param type Le nouveau type de case
     */
    public void setType(CaseType type) {
        modeleCase.setCaseType(type);
    }

    /**
     * Récupère le type de la case.
     * 
     * @return Le type de la case
     */
    public CaseType getCaseType() {
        return modeleCase.getCaseType();
    }

    /**
     * Récupère le modèle de la case.
     * 
     * @return Le modèle de la case
     */
    public ModeleCase getModeleCase() {
        return modeleCase;
    }

    /**
     * Vérifie si la case contient un symbole de départ.
     * 
     * @return true si la case contient un symbole, false sinon
     */
    public boolean verifCaseSymbole() {
        return modeleCase.isSymbole();
    }

    /**
     * Vérifie si la case est vide.
     * 
     * @return true si la case est vide, false sinon
     */
    public boolean verifCaseVide() {
        return modeleCase.isCaseEmpty();
    }

    /**
     * Vérifie si la case contient un chemin.
     * 
     * @return true si la case contient un chemin, false sinon
     */
    public boolean verifCaseSymboleHorsS() {
        return modeleCase.isCaseChemin();
    }

    /**
     * Dessine une ligne vers le haut (12h).
     * 
     * @param g Contexte graphique
     */
    private void drawNoon(Graphics g) {
        g.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, 0);
    }

    /**
     * Dessine une ligne vers la gauche (9h).
     * 
     * @param g Contexte graphique
     */
    private void drawNine(Graphics g) {
        g.drawLine(0, getHeight() / 2, getWidth() / 2, getHeight() / 2);
    }

    /**
     * Dessine une ligne vers le bas (6h).
     * 
     * @param g Contexte graphique
     */
    private void drawSix(Graphics g) {
        g.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight());
    }

    /**
     * Dessine une ligne vers la droite (3h).
     * 
     * @param g Contexte graphique
     */
    private void drawThree(Graphics g) {
        g.drawLine(getWidth() / 2, getHeight() / 2, getWidth(), getHeight() / 2);
    }

    /**
     * Dessine le contenu de la case (symboles et chemins).
     * 
     * @param g Contexte graphique
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Fond blanc
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        // Bordure de la case
        g2d.setColor(new Color(200, 200, 200));
        g2d.setStroke(new BasicStroke(2f));
        g2d.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 8, 8);

        // Dessiner les chemins et symboles
        g2d.setStroke(new BasicStroke(6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(new Color(100, 100, 120));
        
        switch(modeleCase.type) {
            case S1 :
                g2d.setColor(new Color(52, 152, 219)); // Bleu
                g2d.fillOval(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
                g2d.setColor(new Color(41, 128, 185));
                g2d.setStroke(new BasicStroke(2f));
                g2d.drawOval(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
                break;
            case S2 :
                g2d.setColor(new Color(231, 76, 60)); // Rouge
                g2d.fillOval(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
                g2d.setColor(new Color(192, 57, 43));
                g2d.setStroke(new BasicStroke(2f));
                g2d.drawOval(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
                break;
            case S3 :
                g2d.setColor(new Color(230, 126, 34)); // Orange
                g2d.fillOval(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
                g2d.setColor(new Color(211, 84, 0));
                g2d.setStroke(new BasicStroke(2f));
                g2d.drawOval(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
                break;
            case S4 :
                g2d.setColor(new Color(46, 204, 113)); // Vert
                g2d.fillOval(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
                g2d.setColor(new Color(39, 174, 96));
                g2d.setStroke(new BasicStroke(2f));
                g2d.drawOval(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
                break;
            case S5 :
                g2d.setColor(new Color(155, 89, 182)); // Violet
                g2d.fillOval(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
                g2d.setColor(new Color(142, 68, 173));
                g2d.setStroke(new BasicStroke(2f));
                g2d.drawOval(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
                break;
            case h0v0 :
                drawNine(g2d);
                drawNoon(g2d);
                break;
            case h0v1 :
                drawNine(g2d);
                drawSix(g2d);
                break;
            case h1v0:
                drawThree(g2d);
                drawNoon(g2d);
                break;
            case h1v1 :
                drawThree(g2d);
                drawSix(g2d);
                break;
            case h0h1:
                drawThree(g2d);
                drawNine(g2d);
                break;
            case v0v1:
                drawNoon(g2d);
                drawSix(g2d);
                break;
            case cross:
                drawNoon(g2d);
                drawSix(g2d);
                drawThree(g2d);
                drawNine(g2d);
                break;
            default:
                // Case vide, pas de dessin nécessaire
                break;
        }
    }
    
}
