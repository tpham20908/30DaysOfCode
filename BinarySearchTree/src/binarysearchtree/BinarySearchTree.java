package binarysearchtree;

public class BinarySearchTree {

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        EmptyBST e = new EmptyBST();
        NonEmptyBST n = new NonEmptyBST(22);
        Testers.checkIsEmpty(e);
        Testers.checkIsEmpty(n);
        
        Testers.checkAddMemberCardinality(n, 5);
        Testers.checkAddMemberCardinality(n, 22);
        Testers.checkAddMemberCardinality(e, 5);
        
        int test = 1000;
        while (test-->0) {
            Tree t;
            if (test % 10 == 0)
                Testers.rdmTree(0);
            else 
                Testers.rdmTree(10);
            Testers.checkAddMemberCardinality(n, test);
        }
    }
}
