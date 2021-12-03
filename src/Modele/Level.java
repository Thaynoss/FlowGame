package Modele;

import java.util.List; //list abstract class
import java.util.ArrayList; //arraylist class

public class Level{

    private CaseType[][] grilleLevel1;
    private CaseType[][] grilleLevel2;
    private CaseType[][] grilleLevel3;
    public ArrayList<CaseType[][]> levels;
    public int lvlSelectionner;

    public Level(int lvl_select){
        lvl_select = lvlSelectionner;
        levels = new ArrayList<CaseType[][]>();
        grilleLevel1 = level1();
        grilleLevel2 = level2();
        grilleLevel3 = level3();

        levels.add(grilleLevel1);
        levels.add(grilleLevel2);
        levels.add(grilleLevel3);
        getGrille(lvl_select);
        //getGrilleTaille(lvl_select);
    }





    private CaseType[][] level1() {
        CaseType [][] grilleLevel1 = {
                {CaseType.empty,CaseType.S5,CaseType.empty,CaseType.S4,CaseType.S1,CaseType.S2},
                {CaseType.empty,CaseType.empty,CaseType.empty,CaseType.S1,CaseType.empty,CaseType.empty},
                {CaseType.S5,CaseType.empty,CaseType.S3,CaseType.empty,CaseType.empty,CaseType.empty},
                {CaseType.S4,CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty},
                {CaseType.empty,CaseType.empty,CaseType.S2,CaseType.empty,CaseType.S3,CaseType.empty},
                {CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty}
        };
        return grilleLevel1;
    }

    private CaseType[][] level2() {
        CaseType [][] grilleLevel2 = {
                {CaseType.S2,CaseType.S5,CaseType.empty,CaseType.S4,CaseType.S1,CaseType.S2},
                {CaseType.S2,CaseType.empty,CaseType.empty,CaseType.S1,CaseType.empty,CaseType.empty},
                {CaseType.S5,CaseType.empty,CaseType.S3,CaseType.empty,CaseType.empty,CaseType.empty},
                {CaseType.S4,CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty},
                {CaseType.empty,CaseType.S3,CaseType.S2,CaseType.empty,CaseType.S3,CaseType.empty},
                {CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty,CaseType.empty}

        };
        return grilleLevel2;
    }

    private CaseType[][] level3() {
        CaseType[][] grilleLevel3 = {
                {CaseType.S2, CaseType.S5, CaseType.empty, CaseType.S4, CaseType.S1, CaseType.S2},
                {CaseType.S2, CaseType.empty, CaseType.empty, CaseType.S1, CaseType.empty, CaseType.empty},
                {CaseType.S5, CaseType.empty, CaseType.S3, CaseType.empty, CaseType.empty, CaseType.empty},
                {CaseType.S4, CaseType.empty, CaseType.empty, CaseType.empty, CaseType.empty, CaseType.empty},
                {CaseType.empty, CaseType.S3, CaseType.S2, CaseType.empty, CaseType.S3, CaseType.empty},
                {CaseType.empty, CaseType.empty, CaseType.empty, CaseType.empty, CaseType.empty, CaseType.empty}
        };
        return grilleLevel3;
    }


    //temporaire à changer une fois le menu fait !se
    public CaseType [][] getGrille(int levelselector) {
        return levels.get(levelselector).clone();
    }

    public int getGrilleTaille(int levelselector) {
        CaseType[][] tableauTMP;
          tableauTMP =levels.get(levelselector);
          int tailleLVL = tableauTMP.length;
          System.out.println(tailleLVL);
          return tailleLVL;

    }

}
