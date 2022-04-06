package Model.Recherche.OuverDS;

import Model.Recherche.Noeud;

public interface Ouvert {
    boolean isEmpty();

    void add(Noeud noeud);

    Noeud remove();

    boolean remove(Noeud noeud);

    boolean contain(Noeud noeud);

    void clear();

    int size();
}
