package Modele;

import java.util.Random;

public class ModeleCase {

    private int x, y; // pos x et y de la case
    public CaseType type; // type de la case
    private Boolean isEmpty;
    private int L, lvl;

    Level newLevel = new Level(1);
    CaseType[][] grilleGen = newLevel.getGrille(0);

    //Constructeur de ModeleCase
    public ModeleCase(int _x, int _y, CaseType typeCh) {
        type = typeCh;
        x = _x;
        y = _y;
        //Level level = new Level(L);
        //CaseType[][] grilleGen = level.getGrille(L);
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


    public void setLevel(int j) {
        grilleGen = newLevel.getGrille(j);
    }

    public int getLevel(int i) {
        return i;
    }

// public CaseType getType (){return this.type; }

    //Previous correspond à la case précedente (la première) next
    // à la case suivante (la troisième) et dans MDC on vas modifier
    // la deuxième case (la case current)
    //Plus simple de faire cette fonction dans modelecase car on a besoin quede deux paramétre
    // et l'on travaille de base sur le modele case actuelle
    //On vas crér tout les cas posible
    /*public void drawCase (ModeleCase PreviousCase, ModeleCase NextCase ){
        if (isCaseEmpty()) {
            //d'abord les très verticaux ( == getx car pas oublier que l'on travaille
            // sur la deuxieme case (case courant)
            // version haut 1 -> 2 -> 3
            // version  bas 3 <- 2 <- 1
            if (PreviousCase.getx() == getx() && PreviousCase.gety() > gety())
            { setCaseType(CaseType.v0v1);}
            else if (PreviousCase.getx() == getx() && PreviousCase.getx() < getx())
            { setCaseType(CaseType.v0v1);}
            //idem trait horizontaux version gauche et droite
            else if (PreviousCase.getx() > getx() && PreviousCase.gety() == gety())
            { setCaseType(CaseType.h0h1);}
            else if (PreviousCase.getx() < getx() && PreviousCase.gety() == gety())
            { setCaseType(CaseType.h0h1);}
            else if (PreviousCase.gety()==gety() && gety() < NextCase.gety() && PreviousCase.getx() < getx()
                    && NextCase.getx() == getx())
            { setCaseType(CaseType.h0v1);}
            else if (gety() < PreviousCase.gety() && NextCase.gety() == gety() && getx() > NextCase.getx()
                    && getx() == PreviousCase.getx())
            { setCaseType(CaseType.h0v1);}
            else if (gety() > PreviousCase.gety() && gety() == NextCase.gety() && PreviousCase.getx() == getx()
                    && getx() < NextCase.getx())
            { setCaseType(CaseType.h1v0);}
             else if (PreviousCase.gety() == gety() && NextCase.gety() < gety() && getx() < PreviousCase.getx()
                    &&  NextCase.getx()== getx())
            { setCaseType(CaseType.h1v0);}
            else if( PreviousCase.gety() < gety() && NextCase.gety()==gety() && PreviousCase.getx() == getx()
                    &&  NextCase.getx() < getx())
            { setCaseType(CaseType.h0v0);}
            else if ( PreviousCase.gety()== gety() && NextCase.gety() < gety()  && getx() > PreviousCase.getx()
                    && getx() == NextCase.getx())
            { setCaseType(CaseType.h0v0);}
            else if(gety() == PreviousCase.gety() && gety() < NextCase.gety() && PreviousCase.getx() > getx()
                    && getx() == NextCase.getx())
            { setCaseType(CaseType.h1v1);}
            else if ( PreviousCase.gety() > gety() && NextCase.gety() == gety() && PreviousCase.getx() == getx()
                    && getx() < NextCase.getx())
            { setCaseType(CaseType.h1v1);}

        }

    }*/

    public void drawCase(ModeleCase Mc1, ModeleCase Mc2) {
        if(isCaseEmpty()) {
            if((getx() == Mc1.getx() 
            && getx() < Mc2.getx()
            && Mc1.gety() < gety()
            && gety() == Mc2.gety())
            || (getx() < Mc1.getx()
            && getx() == Mc2.getx()
            && gety() > Mc2.gety()
            && gety() == Mc1.gety())) {
                setCaseType(CaseType.h0v1);
            } 

            if((getx() > Mc1.getx() 
            && getx() == Mc2.getx()
            && Mc1.gety() == gety()
            && gety() > Mc2.gety())
            || (getx() == Mc1.getx()
            && getx() > Mc2.getx()
            && gety() > Mc1.gety()
            && gety() == Mc2.gety())) {
                setCaseType(CaseType.h0v0);
            }

            if((getx() > Mc1.getx() 
            && getx() == Mc2.getx()
            && Mc1.gety() == gety()
            && gety() < Mc2.gety())
            || (getx() == Mc1.getx()
            && getx() > Mc2.getx()
            && gety() < Mc1.gety()
            && gety() == Mc2.gety())) {
                setCaseType(CaseType.h1v0);
            }

            if((getx() == Mc1.getx() 
            && getx() < Mc2.getx()
            && Mc1.gety() > gety()
            && gety() == Mc2.gety())
            || (getx() < Mc1.getx()
            && getx() == Mc2.getx()
            && gety() == Mc1.gety()
            && gety() < Mc2.gety())) {
                setCaseType(CaseType.h1v1);
            }

            if(getx() == Mc1.getx() && getx() == Mc2.getx()) {
                setCaseType(CaseType.h0h1);
            }

            if(gety() == Mc1.gety() && gety() == Mc2.gety()) {
                setCaseType(CaseType.v0v1);
            }
        }
    }

}

