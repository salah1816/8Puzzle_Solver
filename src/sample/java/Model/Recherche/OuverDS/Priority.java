package Model.Recherche.OuverDS;

import Model.Recherche.Noeud;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Priority implements Ouvert {

    private final Queue<Noeud> ouvert = new PriorityQueue(new CustomComparator());

    @Override
    public boolean isEmpty() {
        return ouvert.isEmpty();
    }

    @Override
    public void add(Noeud noeud) {
        ouvert.add(noeud);
    }

    @Override
    public boolean contain(Noeud noeud) {
        return ouvert.contains(noeud);
    }

    @Override
    public Noeud remove() {
        return ouvert.poll();
    }

    @Override
    public boolean remove(Noeud noeud) {
        return ouvert.remove(noeud);
    }

    @Override
    public void clear() {
        ouvert.clear();
    }


    @Override
    public int size() {
        return ouvert.size();
    }

    class CustomComparator implements Comparator<Noeud> {

        @Override
        public int compare(Noeud noeud1, Noeud noeud2) {

            if (noeud1.getScore() < noeud2.getScore()) {
                return -1;
            } else if (noeud1.getScore() > noeud2.getScore()) {
                return 1;
            }

            return 0;

        }

    }
}