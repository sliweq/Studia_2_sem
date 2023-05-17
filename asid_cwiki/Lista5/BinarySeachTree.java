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
            System.out.println(key);
            if(leftChild != null){
                leftChild.preOrder();
            }
            if(rightChild != null){
                rightChild.preOrder();
            }
    
            
        }
        
        //lewy, prawy, root
        public void postOrder(){
            if(leftChild != null){
                leftChild.preOrder();
            }
            if(rightChild != null){
                rightChild.preOrder();
            }
            System.out.println(key);
        }
    
        public void inOrder(){
            if(leftChild != null){
                leftChild.preOrder();
            }
            System.out.println(key);
            if(rightChild != null){
                rightChild.preOrder();
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
                if(x.getKey() > number){
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

    public Node treeSearch(Node node,int x){
        if(node.key == x){
            return node;
        }
        else{
            if(root.key > x){
                return treeSearch(node.leftChild, x);
            }
            else{
                return treeSearch(node.rightChild, x);
            }
        }
    }

    public void remove(int key){
        root = remove(root, key);
    }
    
    private Node remove(Node node, int key){
        if(node == null){
            return node;
        }
        else{
            if(node.key >key){
                node.setLeftChild(remove(node.leftChild, key));
            }
            else if(node.key < key){
                node.setRightChild(remove(node.rightChild, key));
            }
            else if(node.getRightChild() == null){
                return node.getLeftChild();
            } else if(node.getLeftChild() == null){
                return node.getRightChild();
            }

            root.key = minimalValue(node.getRightChild());
            root.rightChild = remove(node.rightChild, root.key);

        }
        
        return root;
    }

    public int minimalValue(Node node){
        System.out.println(node.key);
        int tmp = node.key;
        while(node.getLeftChild()!= null){
            tmp = node.getLeftChild().key;
            node = node.getLeftChild();
            System.out.println(node.key);

        }
        return tmp;
    }

    public int getNodeAmount(){
        return getNodeAmount(root);
    }

    private int getNodeAmount(Node root){
        int amount = 1; 
        if(root.getLeftChild() != null){
            amount += getNodeAmount(root.getLeftChild());
        }
        if(root.getRightChild() != null){
            amount += getNodeAmount(root.getRightChild());
        }
        return amount;
    }

    public int getTreeHeight(){
        return getTreeHeight(root);
    }
    
    private int getTreeHeight(Node root){
        int height = 1;
        int leftH = 0;
        int rightH = 0;
        if(root.getLeftChild() != null){
            leftH = getTreeHeight(root.getLeftChild());
        }
        if(root.getRightChild() != null){
            rightH = getTreeHeight(root.getRightChild());
        }
        if(leftH > rightH){
            return height + leftH;
        }
        return height+ rightH;
    }

    public int oneChildNodes(){
        return oneChildNodes(root);
    }

    private int oneChildNodes(Node root){
        int amount = 0; 
        if(root.getLeftChild() == null){
            if(root.getRightChild() == null){
                return 0;
            }
            else{
                amount +=1;
                amount += oneChildNodes(root.getRightChild());
                return amount;
            }
        }else if(root.getRightChild() == null){
            amount +=1;
            amount += oneChildNodes(root.getLeftChild());
            return amount;
        }else{
            amount+= oneChildNodes(root.getLeftChild());
            amount+= oneChildNodes(root.getRightChild());
            return amount;
        }
    }

    public void inOrder(){root.inOrder();}
    
    public void postOrder(){root.preOrder();}

    public void preOrder(){root.preOrder();}

}
