package binarysearchtree;

class NonEmptyBST<D extends Comparable> implements Tree<D> {
    
    D data;
    Tree<D> left;
    Tree<D> right;

    public NonEmptyBST(D ele) {
        data = ele;
        left = new EmptyBST<D>();
        right = new EmptyBST<D>();
    }
    
    public NonEmptyBST(D ele, Tree<D> leftTree, Tree<D> rightTree) {
        data = ele;
        left = leftTree;
        right = rightTree;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int cardinality() {
        return 1 + left.cardinality() + right.cardinality();
    }

    @Override
    public boolean member(D ele) {
        if (data == ele) {
            return true;
        } else {
            if (ele.compareTo(data) < 0) {
                return left.member(ele);
            } else {
                return right.member(ele);
            }
        }
    }

    @Override
    public NonEmptyBST<D> add(D ele) {
        if (data == ele) {
            return this;
        } else {
            if (ele.compareTo(data) < 0) {
                return new NonEmptyBST(data, left.add(ele), right);
            } else {
                return new NonEmptyBST(data, left, right.add(ele));
            }
        }
    }
}
