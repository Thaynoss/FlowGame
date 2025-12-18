package Modele;

import java.util.Observable;

/**
 * Classe principale du modèle de jeu Flow.
 * Gère la grille, les chemins et la logique du jeu.
 * 
 * @author Groupe 17
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public class Jeu extends Observable {

    /** Tableau contenant les chemins du jeu */
    private Chemin cheminJeu[];
    
    /** Grille contenant les cases du niveau */
    private ModeleCase[][] grilleLVL;
    
    /** Grille contenant les types de cases à initialiser */
    private CaseType[][] grilleGen;
    
    /** Niveau sélectionné */
    private Level level;
    
    /** Taille de la grille du niveau */
    private int tailleGrilleLvl;
    
    /** Nombre de chemins dans le niveau */
    private int nombreCheminLvl;
    
    /** Index du chemin actuellement sélectionné */
    private int index;
    
    /** Numéro du niveau */
    private int lvl;

    /**
     * Constructeur du jeu.
     * Initialise la grille et les chemins pour le niveau spécifié.
     * 
     * @param tailleGrille Taille de la grille (nombre de cases par côté)
     * @param nombreChemin Nombre de chemins dans le niveau
     * @param num_lvl Numéro du niveau à charger
     */
    public Jeu(int tailleGrille, int nombreChemin, int num_lvl) {
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

    /**
     * Récupère la position X d'une case du chemin actuel.
     * 
     * @param p Position dans le chemin
     * @return Position X de la case
     */
    public int RecupCaseXJeu(int p) {
        return cheminJeu[index].getCasePosX(p);
    }

    /**
     * Récupère la position Y d'une case du chemin actuel.
     * 
     * @param p Position dans le chemin
     * @return Position Y de la case
     */
    public int RecupCaseYJeu(int p) {
        return cheminJeu[index].getCasePosY(p);
    }

    /**
     * Retourne l'index du chemin correspondant au type de case donné.
     * 
     * @param Mc ModeleCase dont on cherche le chemin
     * @return Index du chemin ou index actuel si non trouvé
     */
    public int getCaseIndex(ModeleCase Mc) {
        for(int i = 0; i < index; i++) {
            if(cheminJeu[i].RecupTypeC(0) == Mc.getCaseType()) {
                return i;
            }
        }
        return index;
    }
    
    /**
     * Récupère le type d'une case dans le chemin actuel.
     * 
     * @param el Position de l'élément dans le chemin
     * @return Type de la case
     */
    public CaseType getTypeEl(int el) {
        return cheminJeu[index].RecupTypeC(el);
    }
    
    /**
     * Récupère le type d'une case dans un chemin spécifié.
     * 
     * @param el Position de l'élément dans le chemin
     * @param _index Index du chemin
     * @return Type de la case
     */
    public CaseType getTypeElind(int el, int _index) {
        return cheminJeu[_index].RecupTypeC(el);
    }

    /**
     * Récupère la longueur d'un chemin spécifié.
     * 
     * @param ind Index du chemin
     * @return Longueur du chemin
     */
    public int getLongueur(int ind) {
        return cheminJeu[ind].RecupTailleChemin();
    }

    /**
     * Retourne la taille du chemin actuel.
     * 
     * @return Taille du chemin ou 0 si index invalide
     */
    public int getLongueurC() {
        if (index < 5) {
            return cheminJeu[index].RecupTailleChemin();
        } else {
            return 0;
        }
    }

    /**
     * Récupère le chemin actuel.
     * 
     * @return Le chemin actuel
     */
    public Chemin getChemin() {
        return cheminJeu[index];
    }

    /**
     * Récupère le modèle d'une case à une position donnée.
     * 
     * @param posX Position X
     * @param posY Position Y
     * @return ModeleCase à cette position
     */
    public ModeleCase RecupModelcase(int posX, int posY) {
        return grilleLVL[posX][posY];
    }

    /**
     * Récupère le modèle d'une case dans le chemin actuel.
     * 
     * @param el Position dans le chemin
     * @return ModeleCase à cette position
     */
    public ModeleCase getModelCase(int el) {
        return cheminJeu[index].RecupModCase(el);
    }
    
    /**
     * Modifie l'index du chemin actuel.
     * 
     * @param Id Nouvel index
     */
    private void setIndex(int Id) {
        index = Id;
    }

    /**
     * Détruit une case du chemin actuel.
     * 
     * @param i Index de la case à détruire
     */
    public void DetruireCase(int i) {
        cheminJeu[index].DetruireCase(i);
    }

    /**
     * Vérifie si le chemin est terminé (première et dernière cases identiques).
     * 
     * @param i Index du chemin
     * @return true si le chemin est terminé, false sinon
     */
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

    /**
     * Détruit le chemin existant si la case en fait partie.
     * 
     * @param MC ModeleCase à vérifier
     */
    public void detruireCheminExiste(ModeleCase MC) {
        if(CheminExiste(MC)){
            setIndex(getCaseIndex(MC));
            detruireChemin();
        }
    }

    /**
     * Efface le chemin associé à une case (sans les symboles de début/fin).
     * 
     * @param Mc ModeleCase dont il faut effacer le chemin
     */
    public void effacerChemin(ModeleCase Mc) {
        if (appartientChemin(Mc)) {
            for (int i = 1; i < getLongueurC() - 1; i++) {
                getChemin().RecupModCase(i).RemoveCaseToEMP();
            }
        }
    }

    /**
     * Remet une case à l'état vide si elle contient un chemin.
     * 
     * @param MC ModeleCase à réinitialiser
     */
    public void resetCaseEmpty(ModeleCase MC) {
        if(MC.isCaseChemin()){
            grilleLVL[MC.getx()][MC.gety()].RemoveCaseToEMP();
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Vérifie si le chemin actuel est vide et gère les cas spéciaux.
     */
    public void cheminEstVide() {
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

    /**
     * Vérifie si la partie est terminée.
     * La partie est gagnée si toutes les cases sont remplies et tous les chemins sont valides.
     * 
     * @return true si la partie est terminée, false sinon
     */
    public boolean partieEstTerminee() {
        // Vérifier que toutes les cases sont remplies
        for(int i = 0; i < tailleGrilleLvl; i++) {
            for(int j = 0; j < tailleGrilleLvl; j++) {
                if(grilleLVL[i][j].getCaseType() == CaseType.empty) {
                    return false; // Il reste des cases vides
                }
            }
        }
        
        // Vérifier que tous les chemins sont validés
        for(int i = 0; i < nombreCheminLvl; i++) {
            if(!cheminJeu[i].ValiderChemin()) {
                return false; // Un chemin n'est pas valide
            }
        }
        
        // Tout est rempli et tous les chemins sont valides
        return true;
    }   

}
