package Modele;

public class ModeleCase {

    private int x, y; // pos x et y de la case
    public CaseType type; // type de la case
    private int lvl;

    private Level newLevel;
    private CaseType[][] grilleGen;

    //Constructeur de ModeleCase
    public ModeleCase(int _x, int _y,CaseType typeCh,int _lvl) {
        type = typeCh;
        x = _x;
        y = _y;
        lvl=_lvl;
        newLevel = new Level(lvl); /////// C'est ici que le level est set par défaut
        grilleGen = newLevel.getGrille(lvl);
        initGrilleLevelSymbole(grilleGen);

    }

    public boolean isCaseChemin() {

        if (type == CaseType.h0v1 || type == CaseType.h1v0 || type == CaseType.h0h1 || type == CaseType.h0v0
                || type == CaseType.h1v1 || type == CaseType.v0v1 || type == CaseType.cross) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSymbole() {

        if (type == CaseType.S1 || type == CaseType.S2 || type == CaseType.S3 ||
                type == CaseType.S4 || type == CaseType.S5) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isCaseEmpty(){
        if(type==CaseType.empty){
            return true;
        }
        return false;
    }



    public int getx() {
        return this.x;
    }

    public int gety() {
        return this.y;
    }

    public void getCaseType(CaseType t) {
        type = t;
    }

    public CaseType getCaseType() {
        return type;
    }

    // on a besoin de setcasetype pourmodifier le type des case dans la vue
    public void setCaseType(CaseType typeCase) {
        this.type = typeCase;
    }

    //permettra de supprimer tout la chemin
    public void RemoveCaseToEMP() {
        type = CaseType.empty;
    }

    //Initialise UNE CASE et non la grille
    public void CaseTypeInit(CaseType t) {
        type = t;
    }

    //InitGrilleLevelSymbol permet d'init le symbole de n'importe quelle grille
    public void initGrilleLevelSymbole(CaseType[][] caseTL) {
        if (caseTL[this.x][this.y] == CaseType.S1) {
            CaseTypeInit(CaseType.S1);
        }
        if (caseTL[this.x][this.y] == CaseType.S2) {
            CaseTypeInit(CaseType.S2);
        }
        if (caseTL[this.x][this.y] == CaseType.S3) {
            CaseTypeInit(CaseType.S3);
        }
        if (caseTL[this.x][this.y] == CaseType.S4) {
            CaseTypeInit(CaseType.S4);
        }
        if (caseTL[this.x][this.y] == CaseType.S5) {
            CaseTypeInit(CaseType.S5);
        }
    }

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

