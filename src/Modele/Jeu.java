package Modele;

import java.awt.*;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class Jeu extends Observable {

    private Chemin cheminJeu[];  //Tableau 1D contenant un chemin 
    private ModeleCase [][] grilleLVL; //Tableau 2D contenant le level que l'on va initialiser
    private CaseType[][] grilleGen; //Tableau 2D contenant les Types que l'on va instancier dans grilleLVL
    private Level level; //On prépare le contenu de la class Level 
    private int tailleGrilleLvl; //Designe la taille la grille
    private int nombreCheminLvl; //Nombre de chemin contenu dans la grille
    private int index; //Indice pour le tableau de chemin 
    private int lvl; //Correspond au numero du niveau  

    //Constructeur de Jeu 
    public Jeu( int tailleGrille, int nombreChemin,int num_lvl) {
        tailleGrilleLvl = tailleGrille;
        nombreCheminLvl = nombreChemin;
        index = 0; //Initialement à zero
        lvl=num_lvl;
        cheminJeu = new Chemin[nombreCheminLvl]; //Nombre de chemin en fonction du nombreChemin saisi lors de l'appel au constructeur
        grilleLVL = new ModeleCase[tailleGrilleLvl][tailleGrilleLvl];// /initialise grille en fonction d'une taille choisie(tailleGrilleLvL)
        level = new Level(lvl); // C'est ici que le level va être récuperer
        grilleGen = level.getGrille(lvl);///recupère la grille choisie par l'utilisateur


        //Initialise d'abord la grille en empty, puis on initialise les symboles
        for(int i =0;i<tailleGrille;i++){
            for(int j=0;j<tailleGrille;j++){
                grilleLVL[i][j]=new ModeleCase(i,j,CaseType.empty,lvl);//On initialise les case à empty
                grilleLVL[i][j].initGrilleLevelSymbole(grilleGen);// on initialise les symboles
                setChanged();//On appelle setChanged méthode de la class observable marque l'objet 
                                    //comme modifié puis méthode hasChanged retournera true.
                notifyObservers(); //notifyObservers notifie à tous ses observateurs si l'objet a changé. 
                                //Cette méthode appelle plus tard clearChanged() pour indiquer que cet objet n'a plus changé.                             
            }
        }      
        //Initialise le tableau de chemin
        for (int i = 0; i < nombreCheminLvl; i++) {
            cheminJeu[i] = new Chemin();
        }
    }

    //RecupCaseXJeu équivaut à un get de la case dans sa position X à un index préciser ultérieurement
    public int RecupCaseXJeu (int p){
        return cheminJeu[index].getCasePosX(p);
    }

    //Comme RecupCaseXJeu, mais avec la position Y
    public int RecupCaseYJeu (int p){
        return cheminJeu[index].getCasePosY(p);
    }


    //Retourne l'index de la case si ce dernier equivaut au ModeleCase demander
    // sinon renvoie l'index actuelle
    public int getCaseIndex(ModeleCase Mc) {
        for(int i = 0; i < index; i++) {
            if(cheminJeu[i].RecupTypeC(0) == Mc.getCaseType()) {
                return i;
            }
        }
        return index;
    }
    
    //int el -> position element 
    // retourne le type de la case issue de le position de l'élément demandé
    public CaseType getTypeEl(int el){
        return cheminJeu[index].RecupTypeC(el);
    }
    
    // retourne le type de la case issue de le position de l'élément demandé et de son index
    public CaseType getTypeElind(int el,int _index){
        return cheminJeu[_index].RecupTypeC(el);
    }
    

    //Debug
    //Recupere la longueur du chemin en fonction de son indice
    public int getLongueur(int ind) {
        return cheminJeu[ind].RecupTailleChemin();
    }

    //Retourne la taille du chemin du jeu
    public int getLongueurC(){
        if (index< 5){  //index Inferieur au nombre de symbole
            return cheminJeu[index].RecupTailleChemin();
        }else{
            return 0;
        }
    }

    //Recupere le chemin
    public Chemin getChemin(){
        return cheminJeu[index];
    }

    //Recupere le modele de la case en fonction d'une position X et Y.
    public ModeleCase RecupModelcase (int posX, int posY){
        return grilleLVL[posX][posY];
    }

    //Recupere le modele de la case en fonction de la position de l'element
    public ModeleCase getModelCase(int el){
        return cheminJeu[index].RecupModCase(el);
    }
    
    //Modifie l'indice 
    private void setIndex(int Id) {
        index = Id;
    }

    //Pour detruire une case du chemin 
    public void DetruireCase(int i){
        cheminJeu[index].DetruireCase(i);
    }

    // Retourne vrai si une la premiere case est identique à la dernière
    public boolean caseEstIdentique(int i){
        boolean identique = false;
        identique = getModelCase(i).equals(getModelCase(getLongueurC() - 1) );
        
        return identique;
    }

    //Permet d'increment l'index du chemin
    public void IndexIncrement() {
        if(index < nombreCheminLvl) {
            index ++;
        }else {
            index = index % 5; //Mod 5 => on a  5 chemins
            index ++;
        }
    }

    //Permet de modifier le modele de la case si le chemin est valider
    //Fait appel au fonctionnalité de l'observable 
    public void setJeuModeleCase(){
        if (cheminJeu[index].ValiderChemin()){ 
            grilleLVL[RecupCaseXJeu(getLongueurC()-2)][RecupCaseYJeu(getLongueurC()-2)].drawCase(getModelCase(getLongueurC() - 3),getModelCase(getLongueurC() - 1));
            setChanged();               // ^^ getLongueurC()-2 représente la longueur du chemin - les 2 symboles     
            notifyObservers();
        } 
        int Longueur=getLongueurC();
        if(!cheminJeu[index].ValiderChemin() && Longueur >= 3) {//Longueur >=3 correspond aux cases " précedent","actuelle","suivant"
            for(int i = 0 ; i < Longueur - 2 ; i++) {
                grilleLVL[RecupCaseXJeu(i + 1)][RecupCaseYJeu(i + 1)].drawCase(getModelCase(i), getModelCase(i + 2));
                setChanged();
                notifyObservers();
            }
        }
    }

    // Retourne vrai si le chemin est terminé //
    public boolean cheminTermine(int i) {
        return cheminJeu[i].ValiderChemin();
    }

    //Verifie que le chemin existe (utile pour le detruire)
    public boolean CheminExiste(ModeleCase Mc) {
        if(cheminJeu[0].RecupTailleChemin()>1
            && cheminJeu[0].RecupModCase(0).getCaseType() == Mc.getCaseType()
            && cheminTermine(0)){
                return true;
            }
        return false;
    }

    //Verifie si le ModeleCase demandé appartient au chemin
    public boolean appartientChemin(ModeleCase Mc) {
        if(cheminJeu[getCaseIndex(Mc)].RecupTypeC(0)== Mc.type) {
            return true;
        }
        return false;
    }

    // Construit le chemin
    public void debutChemin(ModeleCase Mc) {
        cheminJeu[index].ajoutCaseChemin(Mc);
    }

    // Détruit le chemin
    // On est notifié grace aux observers
    public void detruireChemin() {
        for (int i = 1; i < getLongueurC() - 1; i++) {
            if(grilleLVL[RecupCaseXJeu(i)][RecupCaseYJeu(i)].isCaseChemin()){
                grilleLVL[RecupCaseXJeu(i)][RecupCaseYJeu(i)].RemoveCaseToEMP();
            }
            setChanged();
            notifyObservers();
        }
        cheminJeu[index].DetruireChemin();
    }

    // Détruit le chemin si le chemin existe et qu'on clique sur le chemin
    public void detruireCheminExiste(ModeleCase MC){
        if(CheminExiste(MC)){
            setIndex(getCaseIndex(MC));
            detruireChemin();
        }
    }

    // Efface le chemin qui appartient à la case et le 
    public void effacerChemin(ModeleCase Mc) {
        if (appartientChemin(Mc)) {
            for (int i = 1; i < getLongueurC() - 1; i++) {
                getChemin().RecupModCase(i).RemoveCaseToEMP();
            }
        }
    }

    //Remet la case à vide
    public void resetCaseEmpty(ModeleCase MC){
        if(MC.isCaseChemin()){
            grilleLVL[MC.getx()][MC.gety()].RemoveCaseToEMP();
            setChanged();
            notifyObservers();
        }
    }

    
    public void cheminEstVide(){
        if (cheminTermine(index) && (getLongueurC() != 1)) {
            while(getLongueurC() != 0) {
                IndexIncrement();
            }
        }else {
            for(int i = 1; i < getLongueurC() - 1; i++) {
                effacerChemin(getModelCase(i));
                detruireChemin();
            }
        }
    }

    //Retourne vrai si la partie est termine => toute les cases ne sont plus en empty 
    // et chaque paire de symbole sont reliées dans la grille
    public boolean partieEstTerminee(){
        Boolean grilleFull = false;

        for(int i = 0; i <tailleGrilleLvl; i++) {
            for(int j = 0; j <tailleGrilleLvl; j++) {
                if(grilleLVL[i][j].getCaseType() == CaseType.empty) {
                    grilleFull = false;
                }
            }
        }
        grilleFull = true;

        return grilleFull && cheminJeu[0].ValiderChemin() &&  
               cheminJeu[1].ValiderChemin() && cheminJeu[2].ValiderChemin() &&
               cheminJeu[3].ValiderChemin() && cheminJeu[4].ValiderChemin();
    }   

}
