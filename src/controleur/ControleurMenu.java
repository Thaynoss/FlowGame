package controleur;

import Modele.ModeleMenu;
import Modele.Jeu;
import Vue.VueMenu;
import Vue.VueGrille;

/**
 * Contrôleur pour gérer les interactions du menu principal.
 * Sépare la logique métier de la vue.
 * 
 * @author Groupe 17
 * @version 1.0
 */
public class ControleurMenu {
    
    /** Modèle du menu */
    private ModeleMenu modeleMenu;
    
    /** Vue du menu */
    private VueMenu vueMenu;
    
    /** Niveau actuellement sélectionné */
    private int niveauSelectionne;
    
    /** Nombre total de niveaux disponibles */
    private int nombreNiveaux;
    
    /**
     * Constructeur du contrôleur de menu.
     * 
     * @param vueMenu La vue du menu
     */
    public ControleurMenu(VueMenu vueMenu) {
        this.vueMenu = vueMenu;
        this.modeleMenu = new ModeleMenu();
        this.niveauSelectionne = 0;
        this.nombreNiveaux = modeleMenu.nbLevel();
    }
    
    /**
     * Décrémente le niveau sélectionné.
     */
    public void decrementerNiveau() {
        niveauSelectionne = modeleMenu.decrementer(niveauSelectionne);
        vueMenu.afficherNiveau(niveauSelectionne);
    }
    
    /**
     * Incrémente le niveau sélectionné.
     */
    public void incrementerNiveau() {
        niveauSelectionne = modeleMenu.incremente(niveauSelectionne);
        vueMenu.afficherNiveau(niveauSelectionne);
    }
    
    /**
     * Démarre le jeu avec le niveau sélectionné.
     */
    public void demarrerJeu() {
        Jeu game = new Jeu(6, 5, niveauSelectionne);
        ControleurGrille controleurGrille = new ControleurGrille(game);
        
        VueGrille vueGrille = new VueGrille(6, game, niveauSelectionne, nombreNiveaux, controleurGrille);
        
        vueMenu.setVisible(false);
        vueGrille.setVisible(true);
        vueGrille.setSize(600, 600);
        vueGrille.setResizable(false);
        vueGrille.setLocationRelativeTo(null);
        vueGrille.refresh();
        game.addObserver(vueGrille);
    }
    
    /**
     * Récupère le niveau actuellement sélectionné.
     * 
     * @return Le niveau sélectionné
     */
    public int getNiveauSelectionne() {
        return niveauSelectionne;
    }
}
