package Modele;

public  class ModeleMenu{

    private Level lvl=new Level(0);
    private int nb_Level;

    public ModeleMenu(){
        nb_Level=nbLevel();
    }

    //Incremente le nombre reçu en paramètre
    public int decrementer(int x){
        if(x>0) x--;
        return x;
    }

    //Incremente le nombre reçu en paramètre
    public int incremente(int y){
        if(y<nb_Level-1) y++;
        return y;
    }

    //Retourne le nombre de niveau contenue dans la class Level 
    public int nbLevel(){
        nb_Level=lvl.levels.size();
        return nb_Level;
    }

}
