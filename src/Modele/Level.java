package Modele;

import java.util.ArrayList; //arraylist class

public class Level{

    private CaseType[][] grilleLevel1; //Tableau 2D contenant des case type pour le niveau 1
    private CaseType[][] grilleLevel2; //Tableau 2D contenant des case type pour le niveau 2
    private CaseType[][] grilleLevel3; //Tableau 2D contenant des case type pour le niveau 3 ..
    public ArrayList<CaseType[][]> levels;
    private int lvlSelectionner;

    //Constructeur de Level
    public Level(int lvl_select){
        lvlSelectionner = lvl_select;
        levels = new ArrayList<CaseType[][]>();
        grilleLevel1 = level1();  //Appel des fonctions level ..
        grilleLevel2 = level2();
        grilleLevel3 = level3();
                                 //Ajoute dans la liste
        levels.add(grilleLevel1);
        levels.add(grilleLevel2);
        levels.add(grilleLevel3);
        getGrille(lvlSelectionner); //Appel au récupérateur de la grille du niveau choisie 
    }



    private CaseType[][] level1() {
        CaseType [][] grilleLevel1 = {
                {CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty,CaseType.S2},
                {CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty},
                {CaseType.empty,CaseType.empty,CaseType.S5,CaseType.S4,CaseType.empty,CaseType.empty},
                {CaseType.empty,CaseType.empty,CaseType.empty,CaseType.S3,CaseType.empty,CaseType.S5},
                {CaseType.empty,CaseType.S3,CaseType.empty,CaseType.S2,CaseType.S4,CaseType.S1},
                {CaseType.empty,CaseType.empty,CaseType.empty,CaseType.S1,CaseType.empty,CaseType.empty}
        };
        return grilleLevel1;
    }

    private CaseType[][] level2() {
        CaseType [][] grilleLevel2 = {
                {CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty,CaseType.S5},
                {CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty},
                {CaseType.empty,CaseType.S3,CaseType.S2,CaseType.empty,CaseType.empty,CaseType.empty},
                {CaseType.S5,CaseType.empty,CaseType.empty,CaseType.S4,CaseType.empty,CaseType.empty},
                {CaseType.S4,CaseType.empty,CaseType.empty,CaseType.S1,CaseType.S2,CaseType.empty},
                {CaseType.S1,CaseType.empty,CaseType.empty,CaseType.S3,CaseType.empty,CaseType.empty}

        };
        return grilleLevel2;
    }

    private CaseType[][] level3() {
        CaseType[][] grilleLevel3 = {
                {CaseType.empty, CaseType.empty, CaseType.empty, CaseType.empty, CaseType.empty, CaseType.empty},
                {CaseType.empty, CaseType.empty, CaseType.empty, CaseType.empty, CaseType.S5, CaseType.empty},
                {CaseType.empty, CaseType.empty, CaseType.S3, CaseType.S4, CaseType.S1, CaseType.S2},
                {CaseType.S2, CaseType.S5, CaseType.empty, CaseType.empty, CaseType.empty, CaseType.empty},
                {CaseType.S3, CaseType.empty, CaseType.empty, CaseType.empty, CaseType.S4, CaseType.empty},
                {CaseType.S1, CaseType.empty, CaseType.empty, CaseType.empty, CaseType.empty, CaseType.empty}
        };
        return grilleLevel3;
    }

    //Recupere la grille 
    public CaseType [][] getGrille(int levelselector) {
        return levels.get(levelselector).clone();
    }

}
