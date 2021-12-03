package Modele;
import Vue.VueMenu;
import Modele.*;

public  class ModeleMenu{

    public Level newLevel = new Level(1);
    CaseType[][] grilleGen=newLevel.getGrille(1) ;
    
    public VueMenu VM;
    public Level lvl=new Level(0);
    public int nbLevel,getLVL,setLVL;

    public ModeleMenu(){
        nbLevel=nbLevel();
        //setLVL=getLevel(getLVL);
        //setLevel(setLVL);
    }

    public int decrementer(int x){

        System.out.println("decremente");
        System.out.println(x);
        if(x>0) x--;
        return x;
    }

    public int incremente(int y){
        System.out.println("incremente");
        System.out.println(y);
        if(y<nbLevel-1) y++;
        return y;
    }

    public int nbLevel(){
        nbLevel=lvl.levels.size();
        return nbLevel;
    }

    public void setLevel(int lvl1){
            System.out.print(lvl1+"i du set");
        //grilleGen = newLevel.getGrille(lvl);
        //System.out.print(grilleGen);
    }
    public int getLevel(int i){
        //System.out.print(i);
        return i;
    }

}
