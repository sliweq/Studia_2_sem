package asid_cwiki.Lista5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BinarySeachTree {

    private Queue<Node> queue;

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

        public void setKey(int key ){
            this.key = key;
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
            System.out.print(key);            
            System.out.print(" ");
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
                leftChild.postOrder();
            }
            if(rightChild != null){
                rightChild.postOrder();
            }
            System.out.print(key);
            System.out.print(" ");

        }
    
        public void inOrder(){
            if(leftChild != null){
                leftChild.inOrder();
            }
            System.out.print(key);
            System.out.print(" ");
            if(rightChild != null){
                rightChild.inOrder();
            }
        }
    }

    Node root; 
    public BinarySeachTree(){root = null;}

    //wyswietl po poziomach drzewa
    public void printBSTbyLevels(){
        queue = new LinkedList<>();
        queue.add(root);
        Node tmpNode = queue.peek();
        while(!queue.isEmpty()){ 
            System.out.print(tmpNode.getKey() + " ");
   
            if(tmpNode.getLeftChild() != null){
                queue.add(tmpNode.getLeftChild());
            }
            if(tmpNode.getRightChild() != null){
                queue.add(tmpNode.getRightChild());
            }
            queue.remove();
            tmpNode = queue.peek();
        }
        System.out.println();

    }
    
    //zapis do pliku
    public void writeToFile(PrintWriter write) throws FileNotFoundException{
        zapisDoPliku(write,root);
        write.close();
    }

    private void zapisDoPliku(PrintWriter write, Node node) throws FileNotFoundException{
        write.write(String.valueOf(node.getKey())+"\n");

        if(node.getLeftChild() != null){
            zapisDoPliku(write, node.getLeftChild());
        }
        if(node.getRightChild() != null){
            zapisDoPliku(write, node.getRightChild());
        }
    }

    //wstawianie
    public void insert(int number){
        if(root == null){
            root = new Node(number);
        }else{
            Node x = root;
            Node y = null;
            while(x != null){
                y = x;
                if(x.getKey() > number){
                    x = x.getLeftChild();
                }
                else{
                    x = x.getRightChild();
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

    //wyszukiwanie elementu
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

    //usuwanie
    public void remove(int key){
        root = remove(root, key);
    }
    
    private Node remove(Node node, int key){
        if(node == null){
            return node;
        }
        else{
            if(node.getKey() >key){
                node.setLeftChild(remove(node.leftChild, key));
            }
            else if(node.getKey() < key){
                node.setRightChild(remove(node.rightChild, key));                
            }
            else{
                if(node.getLeftChild() == null){
                    return node.getRightChild();
                }else if(node.getRightChild() == null){
                    return node.getLeftChild();
                }
                // sa oba dzieci
                //ustawienie nastepnika
                //cos to nie dziala z 20 
                node.setKey(next(node));

                //usuwanie tego gościa
                node.setRightChild(remove(node.getRightChild(), node.getKey()));
                
            }

            // else if(node.getLeftChild() != null && node.getRightChild() != null){

            //     node.setKey(minimalValue(node.getRightChild()).getKey());
            //     remove(node.getRightChild(), key);

            // }
            // else if(node.getLeftChild() != null){
            //     node = node.getLeftChild();
            // }
            
            
            return node;
        }
    }

    public int next(Node node){
        Node tmpNode = node.getRightChild();
        while(tmpNode.getLeftChild() != null){
            tmpNode = tmpNode.getLeftChild();
        }

        return tmpNode.getKey(); 
    }

    public Node minimalValue(Node node){
        if(node!=null){
            while(node.getLeftChild() != null){
                node = node.getLeftChild();
            }
        }
        return node;
    }

    //ilsoc wezlow
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

    //wysokosc drzewa
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

    // wezly z jednym dzieckiem
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

    //wezly z jednym bratem
    public int oneBrother(){
        return oneBrother(root);
    }

    private int oneBrother(Node root){
        int amount = 0;
        if(root.getLeftChild() == null){
            if(root.getRightChild() == null){
                return 0;
            }
            else{
                return oneBrother(root.getRightChild());
            }
        }else if(root.getRightChild() == null){
            return oneBrother(root.getLeftChild());
        }
        else{
            amount +=2;
            amount += oneBrother(root.getLeftChild());
            amount += oneBrother(root.getRightChild());
            return amount;
        }
    }

    //przechodzenie po drzewie
    public void inOrder(){root.inOrder();}
    
    public void postOrder(){root.postOrder();}

    public void preOrder(){root.preOrder();}
}
