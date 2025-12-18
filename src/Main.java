import Vue.VueMenu;

import javax.swing.*;

/**
 * Point d'entrée principal de l'application Flow Game.
 * Lance le menu principal du jeu.
 * 
 * @author Groupe 17
 * @version 1.0
 */
public class Main {
    
    /**
     * Méthode principale qui démarre l'application.
     * Configure le Look and Feel système et affiche le menu principal.
     * 
     * @param args Arguments de ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        // Utiliser le Look and Feel du système pour une meilleure intégration
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Impossible de charger le Look and Feel système: " + e.getMessage());
        }
        
        // Lancer le menu dans le thread de l'interface graphique
        SwingUtilities.invokeLater(() -> {
            VueMenu menu = new VueMenu();
            menu.setVisible(true);
        });
    }
}
