package Modele;

public class Grille {

    //private ModeleCase grilleJeu[][];
    private int tailleGrille;
    private int CompteurSymbolLVL;

    Level newLevel = new Level(2);
    CaseType[][] grilleGen = newLevel.getGrille(2);

    public Grille(int _tailleGrille){
        grilleGen = new CaseType[_tailleGrille][_tailleGrille];
        CompteurSymbolLVL = 0;
        tailleGrille = _tailleGrille;
    }

    public int getTailleGrilleLVL (ModeleCase[][] GrilleLVLCurrent){
        int tailleTmp =0;
        for (int i =0;i <GrilleLVLCurrent.length; i++)
        {
            tailleTmp += 1;

        }
        return tailleTmp*tailleTmp;
    }

    //On vas vérifier le type de la case debug
    public boolean verifiSimbol(CaseType[][] GrilleLVLCurrent, int posX, int posY){
        if(GrilleLVLCurrent[posX][posY] == CaseType.S1 || GrilleLVLCurrent[posX][posY] ==CaseType.S2 || GrilleLVLCurrent[posX][posY] == CaseType.S3
                || GrilleLVLCurrent[posX][posY] ==CaseType.S4 || GrilleLVLCurrent[posX][posY] ==CaseType.S5){

        }
        return true;
    }

    public int CompteurSymbol (CaseType[][] GrilleLVLCurrent){
        int vart = 0;
        for (int i =0;i <tailleGrille; i++)
        {
            for(int j =0 ;j<tailleGrille;j++)
            {
                if (verifiSimbol(GrilleLVLCurrent,i,j)){
                    vart += 1;
                }
            }
        }
        System.out.println(CompteurSymbolLVL);
        return CompteurSymbolLVL = vart/2;
    }

/*    //debug
    public CaseType RecupCaseTypeLVL (CaseType[][] GrilleLVLCurrrent,int x,int y){
        return GrilleLVLCurrrent[x][y];
    }

    public void initBridge(CaseType caseT,int posX, int PosY){
        //si different de case vide alors imposssible de faire un bridge evite de faire un bridge sue un symbole
        if (caseT != CaseType.empty){
            grilleJeu.setBridge()
        }
    }
*/

}
