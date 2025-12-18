package controleur;

import Modele.Jeu;
import Modele.ModeleCase;
import Vue.VueCase;

/**
 * Contrôleur pour gérer les interactions entre la vue et le modèle de la grille.
 * Sépare la logique métier de la logique d'affichage.
 * 
 * @author Groupe 17
 * @version 1.0
 */
public class ControleurGrille {
    
    /** Modèle du jeu */
    private Jeu jeu;
    
    /** Indique si la souris est pressée */
    private boolean isMousePressed;
    
    /** Indique si le clic a démarré sur un symbole */
    private boolean isOnSymbol;
    
    /**
     * Constructeur du contrôleur de grille.
     * 
     * @param jeu Le modèle du jeu
     */
    public ControleurGrille(Jeu jeu) {
        this.jeu = jeu;
        this.isMousePressed = false;
        this.isOnSymbol = false;
    }
    
    /**
     * Gère l'événement de pression de la souris sur une case.
     * 
     * @param vueCase La case sur laquelle la souris a été pressée
     */
    public void handleMousePressed(VueCase vueCase) {
        isMousePressed = true;
        isOnSymbol = vueCase.verifCaseSymbole();
        
        ModeleCase modeleCase = vueCase.getModeleCase();
        
        // Réinitialise la case si nécessaire
        jeu.resetCaseEmpty(modeleCase);
        
        // Détruit le chemin existant si on clique dessus
        jeu.detruireCheminExiste(modeleCase);
        
        // Débute un nouveau chemin
        jeu.debutChemin(modeleCase);
    }
    
    /**
     * Gère l'événement d'entrée de la souris sur une case.
     * 
     * @param vueCase La case sur laquelle la souris est entrée
     */
    public void handleMouseEntered(VueCase vueCase) {
        if (!isMousePressed) {
            return;
        }
        
        ModeleCase modeleCase = vueCase.getModeleCase();
        
        if (isOnSymbol) {
            jeu.resetCaseEmpty(modeleCase);
            jeu.debutChemin(modeleCase);
            jeu.setJeuModeleCase();
        }
    }
    
    /**
     * Gère l'événement de relâchement de la souris.
     * 
     * @return true si la partie est terminée, false sinon
     */
    public boolean handleMouseReleased() {
        isMousePressed = false;
        
        jeu.cheminEstVide();
        
        if (jeu.partieEstTerminee()) {
            return true;
        }
        
        if (jeu.getChemin().RecupTailleChemin() == 1) {
            jeu.detruireChemin();
        }
        
        return false;
    }
    
    /**
     * Vérifie si la partie est terminée.
     * 
     * @return true si la partie est terminée, false sinon
     */
    public boolean isPartieTerminee() {
        return jeu.partieEstTerminee();
    }
    
    /**
     * Réinitialise l'état du contrôleur.
     */
    public void reset() {
        isMousePressed = false;
        isOnSymbol = false;
    }
}
