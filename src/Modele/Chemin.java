package Modele;

import java.util.ArrayList;

public class Chemin  {

   //on stocke les chemin dans un array list (on connait pas la taille du chemin)
   // donc il vaut mieux utiliser un tableau dynamique
    private ArrayList<ModeleCase> chemin;

    //Constructeur de Chemin
    public Chemin (){
        chemin = new ArrayList<ModeleCase>();
    }

    //Recupère la donné de la case du chemin en fonction de la positionX en paramétre
    public int getCasePosX(int posx) {
        return chemin.get(posx).getx();
    }

    //Recupère la donné de la case du chemin en fonction de la positionY en paramétre
    public int getCasePosY(int posy) {
        return chemin.get(posy).gety();
    }
    
    //Recupére la taille du chemin (debug)
    public int RecupTailleChemin(){
        return chemin.size();
    }

    //On ajoute une case à un chemin
    public void ajoutCaseChemin(ModeleCase CaseAjout){
        chemin.add(CaseAjout);
    }

    //Retourne vrai si le chemin ajouté est sur une case vide
    public boolean appartientChemin(int c){
        if(chemin.get(c).type == CaseType.empty){
            return true;
        }
        return false;
    }

    //Récupère le Modele de la case dans notre chemin (indice du chemin)
    // -> indice du modèle :renvoie la valeur représenter par ce champs sur l'objet modifier
    public ModeleCase RecupModCase (int el){
        return chemin.get(el);
    }

    //Récupère le Type de la case dans notre chemin 
    // -> indice du type la case :renvoie la valeur représenter par ce champs sur l'objet modifier
    public CaseType RecupTypeC (int el){
        return chemin.get(el).type;
    }


    //Compare le premier element à l'indice 0 de la liste chemin avec l'avant dernier élément
    public boolean ValiderChemin(){
        if(chemin.size() == 0 || chemin.size() == 1) {
            return false;
        }
        else {
            return chemin.get(0).type.equals(chemin.get(chemin.size() - 1).type);
        } 
    }

    //Pour supprimer une case 
    public void DetruireCase(int index){
        chemin.remove(index);
    }

    //On supprime tout les éléments d'une liste
    public void DetruireChemin(){
        chemin.clear();
    }

}

