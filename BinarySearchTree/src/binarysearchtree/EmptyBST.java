package binarysearchtree;

public class EmptyBST<D extends Comparable> implements Tree<D> {

    public EmptyBST() {
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public int cardinality() {
        return 0;
    }

    @Override
    public boolean member(D ele) {
        return false;
    }

    @Override
    public NonEmptyBST<D> add(D ele) {
        return new NonEmptyBST<D>(ele);
    }
    
}
