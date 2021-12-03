package Modele;

import java.awt.*;
import java.util.ArrayList;

public class Chemin  {

    //on stocke les chemin dans un array list (on connait pas la taille du chemin)
    //donc vaut mmieux un
    public ArrayList<ModeleCase> chemin;

    public Chemin (){
        chemin = new ArrayList<ModeleCase>();
    }

    public int getCasePosX(int posx) {
        return chemin.get(posx).getx();
    }

    public int getCasePosY(int posy) {
        return chemin.get(posy).gety();
    }

    public int RecupTailleChemin(){
        return chemin.size();
    }

    //On vas ajouter une case au chemin
    public void ajoutCaseChemin(ModeleCase CaseAjout){
        chemin.add(CaseAjout);
    }

    //Si les chemins tracé sont sur des cases vide, retourne vrai
    public boolean appartientChemin(int c){
        if(chemin.get(c).type == CaseType.empty){
            return true;
        }
        return false;
    }

    public ModeleCase RecupModCase (int el){
        return chemin.get(el);
    }

    public CaseType RecupTypeC (int el){
        return chemin.get(el).type;
    }

    //compare le premier element a l'indice 0 de la liste chemein et sur l'élement de cette liste on prend le type
    //on recupére aussi le dernier element de ta liste donc le dernier index de ta liste et -1 car tableau
    // Retourne vrai si le premier élément et le dernier élement sont identique
    public boolean ValiderChemin(){
        if(chemin.size() == 0 || chemin.size() == 1) {
            return false;
        }
        else {
            return chemin.get(0).type.equals(chemin.get(chemin.size() - 1).type);
        } 
    }

    //Pour supprimer une case on utilise la fonction remove de java
    public void DetruireCase(int index){
        chemin.remove(index);
    }

    //Pour supprimer une liste on utilise la methode clear fournie par java
    public void DetruireChemin(){
        chemin.clear();
    }

}

