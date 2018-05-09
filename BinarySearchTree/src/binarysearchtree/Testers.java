package binarysearchtree;

public class Testers {
    
    public static void checkIsEmpty(Tree t) throws Exception {
        // if the tree t is an instance of EmptyBST --> t.isEmpty() -> true
        if (t instanceof EmptyBST) {
            if (!t.isEmpty()) {
                throw new Exception("All is not good, the tree is an EmptyBST "
                        + "and it is not empty");
            }
        }
        // if the tree t is an instance of NonEmptyBST --> t.isEmpty() -> false
        else if (t instanceof NonEmptyBST) {
            if (t.isEmpty()) {
                throw new Exception("All is not good, the tree is an NonEmptyBST "
                        + "and it is not non-empty");
            }
        }
    }
    
    public static void checkAddMemberCardinality(Tree t, int x) throws Exception {
        int nT = t.add(x).cardinality();
        // Either something was added --> and the cardinality increased by 1
        if (nT == t.cardinality() + 1) {
            if (t.member(x)) {
                throw new Exception("The cardinality increased by 1 but the item"
                        + "that was added already member of the tree");
            }
        }
        // or the thing that was added was already there and therefore not 
        // really added so the cardinality stayed the same
        else if (nT == t.cardinality()) {
            if (!t.member(x)) {
                throw new Exception("The cardinality didn't increase by 1 but"
                        + "we added a new thing");
            }
        }
        else {
            throw new Exception("Something is wrong with our program");
        }
    }
}
