package Modele;

import java.awt.*;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class Jeu extends Observable {
    private Chemin cheminJeu[];
    private  ModeleCase [][] grilleLVL;
    private int tailleGrilleLvl;
    private int nombreCheminLvl;
    private int index;
    //private final int NbSymbolMax = 5;


    public Jeu( int tailleGrille, int nombreChemin) {
        tailleGrilleLvl = tailleGrille;
        nombreCheminLvl = nombreChemin;
        index = 0;
        cheminJeu = new Chemin[nombreCheminLvl];
        grilleLVL = new ModeleCase[tailleGrilleLvl][tailleGrilleLvl];
        
        for (int i = 0; i < nombreCheminLvl; i++) {
            cheminJeu[i] = new Chemin();
        }

        for (int i = 0; i < tailleGrilleLvl; i++) {
            for (int j = 0; j < tailleGrilleLvl; j++) {
                grilleLVL[i][j] = new ModeleCase(i, j, CaseType.empty);
                setChanged();
                notifyObservers();
            }
        }
        //GrilleInit(tailleGrilleLvl);
        //CheminInit(nombreCheminLvl);
    }


    public void CheminInit(int nbcl){
        for (int i = 0; i < nbcl ; i++) {
            cheminJeu[i] = new Chemin();
        }
    }


    public void GrilleInit(int tailleGr) {
        for (int i = 0; i < tailleGr; i++) {
            for (int j = 0; j < tailleGr; j++) {
                grilleLVL[i][j] = new ModeleCase(i, j, CaseType.empty);
            }
        }
    }

    //
    public int RecupCaseXJeu (int p){
        return cheminJeu[index].getCasePosX(p);
    }

    //
    public int RecupCaseYJeu (int p){
        return cheminJeu[index].getCasePosY(p);
    }
    //
    public int getIndex() {
        return index;
    }

    //
    public int getCaseIndex(ModeleCase Mc) {
        for(int i = 0; i < index; i++) {
            if(cheminJeu[i].RecupTypeC(0) == Mc.getCaseType()) {
                return i;
            }
        }
        return index;
    }

    //int el -> position element //
    public CaseType getTypeEl(int el){
        return cheminJeu[index].RecupTypeC(el);
    }

    public CaseType getTypeElind(int el,int _index){
        return cheminJeu[_index].RecupTypeC(el);
    }
    //
    public CaseType getCasetypeJeu(int posX,int posY){
        return grilleLVL[posX][posY].getCaseType();
    }
    //
    public int getLongueur(int ind) {
        return cheminJeu[ind].RecupTailleChemin();
    }
    //
    public int getLongueurC(){
        if (index< 5){  //index Inferieur au nombre de symbole que l'on connait a l'avance
            return cheminJeu[index].RecupTailleChemin();
        }else{
            return 0;
        }
    }

    //
    public Chemin getChemin(){
        return cheminJeu[index];
    }

    //
    public ModeleCase RecupModelcase (int posX, int posY){
        return grilleLVL[posX][posY];
    }
    //
    public ModeleCase getModelCase(int el){
        return cheminJeu[index].RecupModCase(el);
    }
    
    //
    public void setIndex(int Id) {
        index = Id;
    }

    //Pour detruire une case du chemin 
    public void DetruireCase(int i){
        cheminJeu[index].DetruireCase(i);
    }

    // Retourne vrai si une case est identique à la denrière //
    public boolean caseEstIdentique(int i){
        boolean identique = false;
        identique = getModelCase(i).equals(getModelCase(getLongueurC() - 1) );
        
        return identique;
    }

    //
    public void IndexIncrement() {
        if(index < nombreCheminLvl) {
            index ++;
        }else {
            index = index % 5;
            index ++;
        }
    }

    //verifie si le trajet est valide
/*    public boolean VerificationvaliditéT (int el){
        if (getModelCase(el).equals(getModelCase(getLongueurC() -1))){
            return true;
        }
        else{
            return false;
        }
    }
*/
/*
    public boolean cheminEstComplet(int i) {
        boolean cheminFinit = false;
        cheminFinit = cheminJeu[i].ValiderChemin();
        return cheminFinit;
    }
*/
    /*public boolean CaseDansChemin(ModeleCase caseM){
        boolean DansChemin = false;
        if(cheminEstComplet(0) && cheminJeu[0].RecupTailleChemin() > 1
                && cheminJeu[0].RecupModCase(0).getCaseType() == caseM.getCaseType() ){
            return DansChemin = true;
        }
        else{
            return DansChemin;
        }
    }
    */

    //
    public void setJeuModeleCase(){
        if (cheminJeu[index].ValiderChemin()){
            grilleLVL[RecupCaseXJeu(getLongueurC()-2)][RecupCaseYJeu(getLongueurC()-2)].drawCase(getModelCase(getLongueurC() - 3),getModelCase(getLongueurC() - 1));
            setChanged();
            notifyObservers();
        } 
        int Longueur=getLongueurC();
        if(!cheminJeu[index].ValiderChemin() && Longueur >= 3) {
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

    //
    public boolean CheminExiste(ModeleCase Mc) {
        if(cheminJeu[0].RecupTailleChemin()>1
            && cheminJeu[0].RecupModCase(0).getCaseType() == Mc.getCaseType()
            && cheminTermine(0)){
                return true;
            }
        return false;
    }

    //
    public boolean appartientChemin(ModeleCase Mc) {
        if(cheminJeu[getCaseIndex(Mc)].RecupTypeC(0)== Mc.type) {
            return true;
        }
        return false;
    }

    // Construit le chemin //
    public void debutChemin(ModeleCase Mc) {
        cheminJeu[index].ajoutCaseChemin(Mc);
    }

    // Détruit le chemin quand on "clique sur le chemin" dans la partie Vue
    // On est notifié grace aux observers   //
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

    //
    public void detruireCheminExiste(ModeleCase MC){
        if(CheminExiste(MC)){
            setIndex(getCaseIndex(MC));
            detruireChemin();
        }
    }

    // Efface du chemin qui contient cette case  //
    public void effacerChemin(ModeleCase Mc) {
        if (appartientChemin(Mc)) {
            for (int i = 1; i < getLongueurC() - 1; i++) {
                getChemin().RecupModCase(i).RemoveCaseToEMP();
            }
        }
    }

    //
    public void detruireCase(){
        for(int i = 0; i < getLongueurC(); i++){
            if(caseEstIdentique(i)) {
                for(int j = i+1; j < getLongueurC(); j++) {
                    DetruireCase(j);
                }
            }
        }
    }
    


    // Detruit le ou les chemins qui sont sur une case vide //
    public void detruireCheminVide(){
        for(int i = 0; i< 5; i++) {
            for(int j = 0; j < cheminJeu[i].RecupTailleChemin(); j++) {
                if(cheminJeu[i].appartientChemin(j)) {
                    index = i;
                    cheminJeu[index].DetruireChemin();
                }   
            }
        }
    }

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

        for(int i = 0; i < tailleGrilleLvl; i++) {
            for(int j = 0; j < tailleGrilleLvl; j++) {
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
