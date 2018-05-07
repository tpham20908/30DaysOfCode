package binarysearchtree;

public interface Tree<D extends Comparable> {
    public boolean isEmpty();
    public int cardinality();
    public boolean member(D ele);
    public NonEmptyBST<D> add(D ele);
}
