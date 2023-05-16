package asid_cwiki.Lista5;

public class BinarySeachTree {

    class Node{
        private int key;
        private Node leftChild, rightChild;
        public Node(int key){
            this.key = key;
            leftChild = null; 
            rightChild = null;
        }

        public int getKey(){
            return key;
        }

        public void setLeftChild(Node left){
            leftChild = left;
        }

        public void setRightChild(Node right){
            rightChild = right;
        }
    
        public Node getLeftChild(){
            return leftChild;
        }
        
        public Node getRightChild(){
            return rightChild;
        }
        
        //preorder root, lewy, prawy
        public void preOrder(){
            System.out.println(root.key);
            if(root.getLeftChild() != null){
                root.getLeftChild().preOrder();
            }
            if(root.getRightChild() != null){
                root.getRightChild().preOrder();
            }
    
            
        }
        
        //lewy, prawy, root
        public void postOrder(){
            if(root.getLeftChild() != null){
                root.getLeftChild().preOrder();
            }
            if(root.getRightChild() != null){
                root.getRightChild().preOrder();
            }
            System.out.println(root.key);
        }
    
        public void inOrder(){
            if(root.getLeftChild() != null){
                root.getLeftChild().preOrder();
            }
            System.out.println(root.key);
            if(root.getRightChild() != null){
                root.getRightChild().preOrder();
            }
        }
    }

    Node root; 
    public BinarySeachTree(){root = null;}

    public void insert(int number){
        if(root == null){
            root = new Node(number);
        }else{
            Node x = root;
            Node y = null;
            while(x != null){
                y = x;
                if(root.getKey() > number){
                    x = x.leftChild;
                }
                else{
                    x = x.rightChild;
                }
            }
            if(y.getKey() > number){
                y.setLeftChild(new Node(number));
            }
            else{
                y.setRightChild(new Node(number));
            }
        }
    }

    public void countNodes(){}

    public void treeHeight(){}

    public void evenKeysNods(){}

    public void inOrder(){root.inOrder();}
    
    public void postOrder(){root.preOrder();}

    public void preOrder(){root.preOrder();}

}
