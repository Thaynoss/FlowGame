package Vue;

import controleur.ControleurMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Fenêtre du menu principal du jeu Flow.
 * Permet de sélectionner un niveau et de lancer la partie.
 * 
 * @author Groupe 17
 * @version 1.0
 */
public class VueMenu extends JFrame {
    /** Contrôleur gérant la logique du menu */
    private ControleurMenu controleur;
    
    /** Label affichant le numéro du niveau */
    private JLabel levelNumberLabel;
    
    /** Bouton pour démarrer le jeu */
    private JButton startButton;
    
    /** Bouton pour décrémenter le niveau */
    private JButton decrementButton;
    
    /** Bouton pour incrémenter le niveau */
    private JButton incrementButton;

    /**
     * Constructeur du menu principal.
     * Initialise l'interface graphique avec tous les composants.
     */
    public VueMenu() {
        super("FlowGame grp 17");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        // Initialiser le contrôleur
        this.controleur = new ControleurMenu(this);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(new Color(44, 62, 80)); // Bleu foncé élégant
        contentPane.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // === TITRE ===
        JLabel titre = new JLabel("Flow Game");
        titre.setFont(new Font("Arial", Font.BOLD, 56));
        titre.setForeground(new Color(236, 240, 241)); // Blanc cassé
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel sousTitre = new JLabel("Connect the colors!");
        sousTitre.setFont(new Font("Arial", Font.PLAIN, 20));
        sousTitre.setForeground(new Color(189, 195, 199)); // Gris clair
        sousTitre.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        contentPane.add(Box.createVerticalStrut(30));
        contentPane.add(titre);
        contentPane.add(Box.createVerticalStrut(10));
        contentPane.add(sousTitre);
        contentPane.add(Box.createVerticalStrut(80));

        // === SÉLECTION DU NIVEAU ===
        JLabel labelNiveau = new JLabel("Select Level");
        labelNiveau.setFont(new Font("Arial", Font.BOLD, 24));
        labelNiveau.setForeground(new Color(236, 240, 241));
        labelNiveau.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(labelNiveau);
        contentPane.add(Box.createVerticalStrut(20));

        // Panel pour les boutons de niveau
        JPanel niveauPanel = new JPanel();
        niveauPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        niveauPanel.setBackground(new Color(44, 62, 80));
        niveauPanel.setMaximumSize(new Dimension(400, 100));

        // Bouton décrément
        decrementButton = new JButton("◄");
        styliserBoutonPetit(decrementButton);
        decrementButton.addActionListener(e -> controleur.decrementerNiveau());
        
        // Affichage du numéro de niveau
        levelNumberLabel = new JLabel(String.valueOf(controleur.getNiveauSelectionne()));
        levelNumberLabel.setFont(new Font("Arial", Font.BOLD, 48));
        levelNumberLabel.setForeground(new Color(52, 152, 219)); // Bleu vif
        levelNumberLabel.setPreferredSize(new Dimension(80, 60));
        levelNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Bouton incrément
        incrementButton = new JButton("►");
        styliserBoutonPetit(incrementButton);
        incrementButton.addActionListener(e -> controleur.incrementerNiveau());

        niveauPanel.add(decrementButton);
        niveauPanel.add(levelNumberLabel);
        niveauPanel.add(incrementButton);
        
        contentPane.add(niveauPanel);
        contentPane.add(Box.createVerticalStrut(60));

        // === BOUTON START ===
        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(46, 204, 113)); // Vert
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setMaximumSize(new Dimension(250, 60));
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Effet hover pour le bouton Start
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                startButton.setBackground(new Color(39, 174, 96)); // Vert plus foncé
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                startButton.setBackground(new Color(46, 204, 113)); // Vert original
            }
        });
        
        startButton.addActionListener(e -> controleur.demarrerJeu());
        contentPane.add(startButton);
        
        contentPane.add(Box.createVerticalStrut(40));
        
        // === FOOTER ===
        JLabel footer = new JLabel("LIFAP7 - Groupe 17");
        footer.setFont(new Font("Arial", Font.ITALIC, 14));
        footer.setForeground(new Color(127, 140, 141));
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(Box.createVerticalGlue());
        contentPane.add(footer);

        this.setContentPane(contentPane);
    }

    /**
     * Met à jour l'affichage du numéro de niveau.
     * 
     * @param niveau Niveau à afficher
     */
    public void afficherNiveau(int niveau) {
        levelNumberLabel.setText(String.valueOf(niveau));
    }

    /**
     * Applique le style visuel aux petits boutons de navigation.
     * 
     * @param bouton Le bouton à styliser
     */
    private void styliserBoutonPetit(JButton bouton) {
        bouton.setFont(new Font("Arial", Font.BOLD, 24));
        bouton.setForeground(Color.WHITE);
        bouton.setBackground(new Color(52, 73, 94)); // Gris-bleu
        bouton.setFocusPainted(false);
        bouton.setBorderPainted(false);
        bouton.setPreferredSize(new Dimension(60, 60));
        bouton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Effet hover
        bouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bouton.setBackground(new Color(41, 128, 185)); // Bleu au survol
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bouton.setBackground(new Color(52, 73, 94)); // Couleur originale
            }
        });
    }

    /**
     * Point d'entrée principal de l'application.
     * 
     * @param args Arguments de ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            VueMenu menu = new VueMenu();
            menu.setVisible(true);
        });
    }
}