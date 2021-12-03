package Vue;



import Modele.ModeleMenu;
import Modele.Level;
import Modele.CaseType;
import Modele.Jeu;
import Modele.ModeleCase;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VueMenu extends JFrame implements ActionListener {
    private ModeleMenu MM = new ModeleMenu();

    private static final int widthWindows = 600; // Taille de la fenetre

    // Valeur des choix de level
    private int nbLevel;
    
    public String i="0";
    private JLabel numLevel;
    private int j=Integer.parseInt(i);


    //
    private JLabel titre = new JLabel("FlowGame LIFAP7");
    private JButton b1= new JButton("-");
    private JButton b2=new JButton("+");
    private JButton Valider=new JButton("Valider");

    public VueMenu(){
        
        /* Divers parametre pour gérer la fenetre : titre fenetre, Gestion de la fermeture de fenetre, le calibrage de la taille de la fenetre
        et centrage de la fenetre*/
        super("FlowGame grp 17");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(widthWindows-150, widthWindows);
        this.setLocationRelativeTo(null);
        nbLevel=MM.nbLevel();

        
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.setBackground(Color.LIGHT_GRAY);

        Font font = new Font("Verdana",Font.PLAIN,43);
        titre.setFont(font);
        contentPane.add(titre,CENTER_ALIGNMENT);
        

        /*Creer un bouton b1 : sert pour décrementer les level et l'affiche*/
        b1.addActionListener(this);
        contentPane.add(b1);
        
        /*Label qui indique le level que l'on souhaite, a chaque Incrementation/decrementation le numero change*/
        this.numLevel= new JLabel(i);
        contentPane.add(numLevel);


        /*Creer un bouton b2 : sert pour incrementer les level et l'affiche*/
        
        b2.addActionListener(this);
        contentPane.add(b2);
    
        /*Creer un bouton Valider : sert pour valider le choix de l'utilisateur*/
        Valider.addActionListener(this);
        contentPane.add(Valider);
    }

    public void actionPerformed(ActionEvent evt) {
        
        String composant = evt.getActionCommand();
        System.out.println(composant);
        if(composant=="-"){
            System.out.println("------");
            j=MM.decrementer(j);
            i=Integer.toString(j); 
            numLevel.setText(i);
        }
        if(composant=="+"){
            System.out.println("++++++");
            j=MM.incremente(j);
            i=Integer.toString(j);
            numLevel.setText(i);            
        }
        
        else if(composant=="Valider"){
            System.out.println("Ok level");
            // j est de type int , a mettre dans getGrille
            int lvl = MM.getLevel(j);
            //System.out.println(lvl);
            MM.setLevel(lvl);
            //MM.setLevel(j);
            //ModeleCase MC=new ModeleCase(5, 5);

            //MM.setLevel(j);
            /*this.setVisible(false);
            VueControleurGrille vue = new VueControleurGrille(6,Jeu);
            vue.setVisible(true);
            vue.setSize(widthWindows-150, widthWindows);
            vue.setLocationRelativeTo(null);*/
        } 

     }

    public static void main(String[] args) {
        VueMenu VM = new VueMenu();
        VM.setVisible(true);
    }

}