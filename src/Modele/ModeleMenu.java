package Modele;

public  class ModeleMenu{

    private Level lvl=new Level(0);
    private int nb_Level;

    public ModeleMenu(){
        nb_Level=nbLevel();
    }

    public int decrementer(int x){
        if(x>0) x--;
        return x;
    }

    public int incremente(int y){
        if(y<nb_Level-1) y++;
        return y;
    }

    public int nbLevel(){
        nb_Level=lvl.levels.size();
        return nb_Level;
    }

}
