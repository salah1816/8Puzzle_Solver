package Model.Recherche.OuverDS;

import Model.Recherche.Noeud;

import java.util.Stack;

public class StackOv implements Ouvert {

    private final Stack<Noeud> stack;

    public StackOv() {
        stack = new Stack<>();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public void add(Noeud noeud) {
        stack.push(noeud);
    }

    @Override
    public boolean contain(Noeud noeud) {
        return stack.contains(noeud);
    }

    @Override
    public Noeud remove() {
        return stack.pop();
    }

    @Override
    public boolean remove(Noeud noeud) {
        return stack.remove(noeud);
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public int size() {
        return stack.size();
    }
}
