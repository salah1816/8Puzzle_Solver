package Model.Recherche.OuverDS;

import Model.Recherche.Noeud;

import java.util.LinkedList;
import java.util.Queue;

public class QueueO implements Ouvert {

    private final Queue<Noeud> queue;

    public QueueO() {
        queue = new LinkedList<>();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public void add(Noeud noeud) {
        queue.add(noeud);
    }

    @Override
    public boolean contain(Noeud noeud) {
        return queue.contains(noeud);
    }

    @Override
    public Noeud remove() {
        return queue.remove();
    }

    @Override
    public boolean remove(Noeud noeud) {
        return queue.remove(noeud);
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public int size() {
        return queue.size();
    }
}
