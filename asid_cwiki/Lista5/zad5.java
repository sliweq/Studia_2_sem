package asid_cwiki.Lista5;

public class zad5 {
    public static void main(String[] args){
        BinarySeachTree bst = new BinarySeachTree();
        int[] insert = {20,7,10,25,4,1,2,12,30};
        int[] delete = {12,1,20};

        for(int x: insert){
            bst.insert(x);
            bst.inOrder();
            System.out.println();
        }
        bst.remove(1);
        bst.inOrder();
        System.out.println();
        bst.insert(0);
        bst.inOrder();
        System.out.println();
        // for(int x: delete){
        //     bst.remove(x);
        //     bst.inOrder();
        //     System.out.println();
        // }
        System.out.println("Print tree by levels");

        bst.printBSTbyLevels();
        


    }
    
}
