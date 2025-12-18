package Vue;

import Modele.Jeu;
import controleur.ControleurGrille;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Vue de la grille de jeu Flow.
 * Affiche la grille et délègue les interactions au contrôleur.
 * 
 * @author Groupe 17
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public class VueGrille extends JFrame implements Observer {
    /** Taille en pixels de chaque case de la grille */
    private static final int PIXEL_PER_SQUARE = 60;
    
    /** Grille de cases visuelles */
    private VueCase[][] gridCells;
    
    /** Modèle du jeu */
    private Jeu game;
    
    /** Contrôleur gérant la logique */
    private ControleurGrille controleur;
    
    /** Numéro du niveau actuel */
    private int currentLevel;
    
    /** Nombre total de niveaux disponibles */
    private int totalLevels;
    
    /** Map associant chaque VueCase à sa position dans la grille */
    private HashMap<VueCase, Point> cellPositions;

    /**
     * Constructeur de la vue de la grille.
     * 
     * @param size Taille de la grille (nombre de cases par côté)
     * @param jeu Modèle du jeu
     * @param niveauActuel Numéro du niveau actuel
     * @param nombreNiveaux Nombre total de niveaux
     * @param controleur Contrôleur gérant la logique
     */
    public VueGrille(int size, Jeu jeu, int niveauActuel, int nombreNiveaux, ControleurGrille controleur) {
        setTitle("FlowGroup17 - Niveau " + niveauActuel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(size * PIXEL_PER_SQUARE, size * PIXEL_PER_SQUARE);
        gridCells = new VueCase[size][size];
        game = jeu;
        this.controleur = controleur;
        this.currentLevel = niveauActuel;
        this.totalLevels = nombreNiveaux;
        cellPositions = new HashMap<>();

        JPanel contentPane = new JPanel(new GridLayout(size, size));
        contentPane.setBackground(new Color(52, 73, 94));
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gridCells[i][j] = new VueCase(i, j);
                contentPane.add(gridCells[i][j]);
                cellPositions.put(gridCells[i][j], new Point(j, i));

                final VueCase vueCase = gridCells[i][j];
                gridCells[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        controleur.handleMousePressed(vueCase);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        controleur.handleMouseEntered(vueCase);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        boolean partieTerminee = controleur.handleMouseReleased();
                        if (partieTerminee) {
                            afficherEcranVictoire();
                        }
                    }
                });
            }
        }
        setContentPane(contentPane);
    }

    /**
     * Met à jour l'affichage de toutes les cases de la grille.
     */
    public void refresh() {
        for (int i = 0; i < gridCells.length; i++) {
            for (int j = 0; j < gridCells.length; j++) {
                gridCells[i][j].setModeleCase(game.RecupModelcase(i, j));
                repaint();
            }
        }
    }

    /**
     * Méthode appelée par le modèle observable pour notifier les changements.
     * 
     * @param obs L'objet observable
     * @param obj Argument optionnel
     */
    @Override
    public void update(Observable obs, Object obj) {
        refresh();
    }
    
    /**
     * Affiche l'écran de victoire avec un compte à rebours de 3 secondes.
     * Remplace le contenu de la fenêtre par un panneau de félicitations.
     */
    private void afficherEcranVictoire() {
        JPanel victoirePanel = new JPanel();
        victoirePanel.setLayout(new BoxLayout(victoirePanel, BoxLayout.Y_AXIS));
        victoirePanel.setBackground(new Color(44, 62, 80));
        victoirePanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        JLabel iconVictoire = new JLabel("🎉");
        iconVictoire.setFont(new Font("Arial", Font.PLAIN, 80));
        iconVictoire.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel titre = new JLabel("Niveau terminé !");
        titre.setFont(new Font("Arial", Font.BOLD, 42));
        titre.setForeground(new Color(46, 204, 113));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel message = new JLabel("Bravo, bien joué !");
        message.setFont(new Font("Arial", Font.PLAIN, 24));
        message.setForeground(new Color(236, 240, 241));
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel niveauLabel = new JLabel("Niveau " + currentLevel + " complété");
        niveauLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        niveauLabel.setForeground(new Color(189, 195, 199));
        niveauLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel timerLabel = new JLabel("Niveau suivant dans 3...");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timerLabel.setForeground(new Color(52, 152, 219));
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton btnContinuer = new JButton("Continuer maintenant");
        btnContinuer.setFont(new Font("Arial", Font.BOLD, 18));
        btnContinuer.setForeground(Color.WHITE);
        btnContinuer.setBackground(new Color(52, 152, 219));
        btnContinuer.setFocusPainted(false);
        btnContinuer.setBorderPainted(false);
        btnContinuer.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnContinuer.setMaximumSize(new Dimension(250, 50));
        btnContinuer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnContinuer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                btnContinuer.setBackground(new Color(41, 128, 185));
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                btnContinuer.setBackground(new Color(52, 152, 219));
            }
        });
        
        victoirePanel.add(Box.createVerticalGlue());
        victoirePanel.add(iconVictoire);
        victoirePanel.add(Box.createVerticalStrut(20));
        victoirePanel.add(titre);
        victoirePanel.add(Box.createVerticalStrut(15));
        victoirePanel.add(message);
        victoirePanel.add(Box.createVerticalStrut(10));
        victoirePanel.add(niveauLabel);
        victoirePanel.add(Box.createVerticalStrut(40));
        victoirePanel.add(timerLabel);
        victoirePanel.add(Box.createVerticalStrut(30));
        victoirePanel.add(btnContinuer);
        victoirePanel.add(Box.createVerticalGlue());
        
        setContentPane(victoirePanel);
        revalidate();
        repaint();
        
        final int[] countdown = {3};
        Timer timer = new Timer(1000, null);
        timer.addActionListener(e -> {
            countdown[0]--;
            if (countdown[0] > 0) {
                timerLabel.setText("Niveau suivant dans " + countdown[0] + "...");
            } else {
                timer.stop();
                lancerNiveauSuivant();
            }
        });
        
        btnContinuer.addActionListener(e -> {
            timer.stop();
            lancerNiveauSuivant();
        });
        
        timer.start();
    }
    
    /**
     * Lance le niveau suivant ou affiche l'écran final si tous les niveaux sont terminés.
     */
    private void lancerNiveauSuivant() {
        if (currentLevel < totalLevels - 1) {
            Jeu nouveauGame = new Jeu(6, 5, currentLevel + 1);
            currentLevel++;
            game = nouveauGame;
            
            ControleurGrille nouveauControleur = new ControleurGrille(nouveauGame);
            this.controleur = nouveauControleur;
            
            setTitle("FlowGroup17 - Niveau " + currentLevel);
            
            gridCells = new VueCase[6][6];
            cellPositions.clear();
            
            JPanel contentPane = new JPanel(new GridLayout(6, 6));
            contentPane.setBackground(new Color(52, 73, 94));
            contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    gridCells[i][j] = new VueCase(i, j);
                    contentPane.add(gridCells[i][j]);
                    cellPositions.put(gridCells[i][j], new Point(j, i));
                    
                    final VueCase vueCase = gridCells[i][j];
                    gridCells[i][j].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            controleur.handleMousePressed(vueCase);
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            controleur.handleMouseEntered(vueCase);
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                            boolean partieTerminee = controleur.handleMouseReleased();
                            if (partieTerminee) {
                                afficherEcranVictoire();
                            }
                        }
                    });
                }
            }
            
            setContentPane(contentPane);
            game.addObserver(VueGrille.this);
            revalidate();
            repaint();
            refresh();
            
        } else {
            afficherEcranFinal();
        }
    }
    
    /**
     * Affiche l'écran de félicitations final avec un bouton de retour au menu.
     */
    private void afficherEcranFinal() {
        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.Y_AXIS));
        finalPanel.setBackground(new Color(44, 62, 80));
        
        JLabel titre = new JLabel("🏆 Félicitations ! 🏆");
        titre.setFont(new Font("Arial", Font.BOLD, 48));
        titre.setForeground(new Color(241, 196, 15));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel message = new JLabel("Vous avez terminé tous les niveaux !");
        message.setFont(new Font("Arial", Font.PLAIN, 24));
        message.setForeground(new Color(236, 240, 241));
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton btnMenu = new JButton("Retour au menu");
        btnMenu.setFont(new Font("Arial", Font.BOLD, 20));
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setBackground(new Color(52, 152, 219));
        btnMenu.setFocusPainted(false);
        btnMenu.setBorderPainted(false);
        btnMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMenu.setMaximumSize(new Dimension(250, 60));
        btnMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnMenu.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> {
                VueMenu menu = new VueMenu();
                menu.setVisible(true);
            });
        });
        
        finalPanel.add(Box.createVerticalGlue());
        finalPanel.add(titre);
        finalPanel.add(Box.createVerticalStrut(30));
        finalPanel.add(message);
        finalPanel.add(Box.createVerticalStrut(50));
        finalPanel.add(btnMenu);
        finalPanel.add(Box.createVerticalGlue());
        
        setContentPane(finalPanel);
        revalidate();
        repaint();
    }
}
