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
}
