package asid_cwiki.Lista5;

public class zad5 {
    public static void main(String[] args){
        BinarySeachTree bst = new BinarySeachTree();
        bst.insert(10);
        bst.insert(5);
        bst.insert(1);
        bst.insert(15);
        bst.insert(14);
        bst.insert(16);
        System.out.println(bst.getNodeAmount());
        System.out.println(bst.getTreeHeight());
        System.out.println(bst.oneChildNodes());


    }
    
}
