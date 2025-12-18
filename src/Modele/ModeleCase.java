package Modele;

/**
 * Modèle d'une case de la grille.
 * Contient les informations de position et de type de la case.
 * 
 * @author Groupe 17
 * @version 1.0
 */
public class ModeleCase {

    /** Position X de la case */
    private int x;
    
    /** Position Y de la case */
    private int y;
    
    /** Type de la case */
    public CaseType type;

    /** Générateur de niveau */
    private Level newLevel;
    
    /** Grille générée du niveau */
    private CaseType[][] grilleGen;

    /**
     * Constructeur d'une case.
     * 
     * @param x Position X
     * @param y Position Y
     * @param typeCh Type initial de la case
     * @param lvl Numéro du niveau
     */
    public ModeleCase(int x, int y, CaseType typeCh, int lvl) {
        type = typeCh;
        this.x = x;
        this.y = y;
        newLevel = new Level(lvl);
        grilleGen = newLevel.getGrille(lvl);
        initGrilleLevelSymbole(grilleGen);
    }

    //Renvoie vrai si le type de la case est un chemin
    public boolean isCaseChemin() {
        return type == CaseType.h0v1 || type == CaseType.h1v0 || type == CaseType.h0h1 
            || type == CaseType.h0v0 || type == CaseType.h1v1 || type == CaseType.v0v1 
            || type == CaseType.cross;
    }

    /**
     * Vérifie si la case contient un symbole de départ/arrivée.
     * 
     * @return true si c'est un symbole, false sinon
     */
    public boolean isSymbole() {
        return type == CaseType.S1 || type == CaseType.S2 || type == CaseType.S3
            || type == CaseType.S4 || type == CaseType.S5;
    }

    /**
     * Vérifie si la case est vide.
     * 
     * @return true si la case est vide, false sinon
     */
    public Boolean isCaseEmpty() {
        return type == CaseType.empty;
    }

    /**
     * Récupère la position X.
     * 
     * @return Position X
     */
    public int getx() {
        return this.x;
    }

    /**
     * Récupère la position Y.
     * 
     * @return Position Y
     */
    public int gety() {
        return this.y;
    }

    /**
     * Définit le type de la case (méthode obsolète, utiliser setCaseType).
     * 
     * @param t Nouveau type
     * @deprecated Utiliser setCaseType à la place
     */
    @Deprecated
    public void getCaseType(CaseType t) {
        type = t;
    }

    /**
     * Récupère le type de la case.
     * 
     * @return Type de la case
     */
    public CaseType getCaseType() {
        return type;
    }

    /**
     * Définit le type de la case.
     * 
     * @param typeCase Nouveau type de case
     */
    public void setCaseType(CaseType typeCase) {
        this.type = typeCase;
    }

    /**
     * Supprime le chemin et remet la case à vide.
     */
    public void RemoveCaseToEMP() {
        type = CaseType.empty;
    }

    /**
     * Initialise le type de la case.
     * 
     * @param t Type à initialiser
     */
    public void CaseTypeInit(CaseType t) {
        type = t;
    }

    /**
     * Initialise les symboles de la grille selon le niveau sélectionné.
     * 
     * @param caseTL Grille de types de cases du niveau
     */
    public void initGrilleLevelSymbole(CaseType[][] caseTL) {
        CaseType typeAtPosition = caseTL[this.x][this.y];
        
        if (typeAtPosition == CaseType.S1 || typeAtPosition == CaseType.S2 
            || typeAtPosition == CaseType.S3 || typeAtPosition == CaseType.S4 
            || typeAtPosition == CaseType.S5) {
            CaseTypeInit(typeAtPosition);
        }
    }

    //Dessine les chemins en fonction de la position de la case suivante et de la case précédente
    //On dessine seulement si la case est vide (evite de dessiner sur d'autre chemein)
    public void drawCase(ModeleCase PreviousCase, ModeleCase NextCase) {
        if(isCaseEmpty()) {
            if(getx() == PreviousCase.getx() && getx() == NextCase.getx()) {
            setCaseType(CaseType.h0h1);
            }

            if(gety() == PreviousCase.gety() && gety() == NextCase.gety()) {
                setCaseType(CaseType.v0v1);
            }

            if((getx() == PreviousCase.getx()
                    && getx() < NextCase.getx()
                    && PreviousCase.gety() < gety()
                    && gety() == NextCase.gety())){
                setCaseType(CaseType.h0v1);
            }

            if ((getx() < PreviousCase.getx()
                    && getx() == NextCase.getx()
                    && gety() > NextCase.gety()
                    && gety() == PreviousCase.gety())) {
                setCaseType(CaseType.h0v1);
            }

            if((getx() > PreviousCase.getx()
                    && getx() == NextCase.getx()
                    && PreviousCase.gety() == gety()
                    && gety() > NextCase.gety())){
                setCaseType(CaseType.h0v0);
            }


            if ((getx() == PreviousCase.getx()
                    && getx() > NextCase.getx()
                    && gety() > PreviousCase.gety()
                    && gety() == NextCase.gety())) {
                setCaseType(CaseType.h0v0);
            }

            if((getx() > PreviousCase.getx()
                    && getx() == NextCase.getx()
                    && PreviousCase.gety() == gety()
                    && gety() < NextCase.gety())){
                setCaseType(CaseType.h1v0);
            }

            if ((getx() == PreviousCase.getx()
                    && getx() > NextCase.getx()
                    && gety() < PreviousCase.gety()
                    && gety() == NextCase.gety())) {
                setCaseType(CaseType.h1v0);
            }

            if((getx() == PreviousCase.getx()
                    && getx() < NextCase.getx()
                    && PreviousCase.gety() > gety()
                    && gety() == NextCase.gety())){
                setCaseType(CaseType.h1v1);
            }

            if((getx() < PreviousCase.getx()
                    && getx() == NextCase.getx()
                    && gety() == PreviousCase.gety()
                    && gety() < NextCase.gety())) {
                setCaseType(CaseType.h1v1);
            }


        }

    }
}

