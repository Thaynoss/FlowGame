package Vue;
import Modele.Jeu;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class VueControleurGrille extends JFrame implements Observer{
    private static final int PIXEL_PER_SQUARE = 60;
    
    private VueCase[][] tabCV;// tableau de cases : i, j -> case
    private Jeu game;

    private Boolean isPress=false;
    private Boolean isSymbole=false;
    private Boolean isEnter=false;
    
    private HashMap<VueCase, Point> hashmap;// hashmap : case -> i, j
    // voir (*)
    // currentComponent : par défaut, mouseReleased est exécutée pour le composant (JLabel) sur lequel elle a été enclenchée (mousePressed) même si celui-ci a changé
    // Afin d'accéder au composant sur lequel le bouton de souris est relaché, on le conserve avec la variable currentComponent à
    // chaque entrée dans un composant - voir (**)
    private JComponent currentComponent;

    public VueControleurGrille(int size,Jeu jeu) {
        setTitle("FlowGroup17");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(size * PIXEL_PER_SQUARE, size * PIXEL_PER_SQUARE);
        tabCV = new VueCase[size][size];
        game=jeu;
        hashmap = new HashMap<VueCase, Point>();

        JPanel contentPane = new JPanel(new GridLayout(size, size));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                tabCV[i][j] = new VueCase(i, j);
                contentPane.add(tabCV[i][j]);
                hashmap.put(tabCV[i][j], new Point(j, i));

                tabCV[i][j].addMouseListener(new MouseAdapter() {

                    @Override
                    public void mousePressed(MouseEvent e) {
                        isPress=true;
                        isSymbole=((VueCase)e.getSource()).verifCaseSymbole();  

                        game.resetCaseEmpty( ((VueCase)e.getSource()).getModeleCase());
                        game.detruireCheminExiste( ((VueCase)e.getSource()).getModeleCase() );

                        game.debutChemin( ((VueCase)e.getSource()).getModeleCase() );
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        currentComponent = (JComponent) e.getSource();
                        isEnter=true;
                        
                        if (isPress && isEnter){
                            game.resetCaseEmpty(((VueCase)e.getSource()).getModeleCase());
                        }
                        if (isPress && isSymbole) {
                            
                            game.resetCaseEmpty(((VueCase)e.getSource()).getModeleCase());                          

                            game.debutChemin( ((VueCase)e.getSource()).getModeleCase() );
                            game.setJeuModeleCase();

                        }

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                         // afficherChemin(); //Affiche dans la console le chemin que l'on parcourt
                         isPress=false;
                       
                        game.cheminEstVide();
                        if(game.partieEstTerminee()) {      
                            System.out.print("Partie gagnée");                
                            System.exit(0);
                        }

                        if(game.getChemin().RecupTailleChemin() == 1) {
                            game.detruireChemin();
                        }

                    }
                });

            }
        }
        setContentPane(contentPane);
    }

    public void refresh(){
        for(int i=0; i<tabCV.length;i++){
            for(int j=0; j<tabCV.length;j++){
                tabCV[i][j].setModeleCase(game.RecupModelcase(i,j));
                repaint();
            }
        }
    }

    @Override
    public void update(Observable obs,Object obj){
        refresh();
    }


    //Fonction d'affichage pour la console
    
    public void afficherChemin() {
        for(int i = 0; i < game.getLongueurC(); i++) {
            System.out.println(game.getTypeEl(i) + ", ");
        }
    }

    public void displayChemin(int index){
        for(int i=0;i<game.getLongueur(index) ; i++){
            System.out.println(game.getTypeEl(index)+" . ");
        }
    }

    public void displayAllChemin(){
        for(int i=0;i<5;i++){
            System.out.println("Chemin numero " + i + " : ");
            displayChemin(i);
        }
    }

}
