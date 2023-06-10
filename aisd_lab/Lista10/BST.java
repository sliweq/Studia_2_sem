package Lista10;
import java.util.ArrayList;


public class BST {
    class Node{
        String value;
        ArrayList<Integer> linijki;
        Node leftChild;
        Node rightChild;

        public Node(String value, int linijka){
            this.value = value;
            linijki = new ArrayList<>();
            linijki.add(linijka);
            leftChild = null;
            rightChild = null;
        }
        public String getValue(){
            return value;
        }

        public Node getLeftChild(){
            return leftChild;
        }

        public Node getRightChild(){
            return rightChild;
        }

        public void dodajLinijke(int linijka){
            linijki.add(linijka);
        }
        public void setLeftChild(Node node){
            leftChild = node;
        }
        public void setRightChild(Node node){
            rightChild = node;
        }
    }

    Node root;

    public void insert(String value, int linijka){
        if(root != null){
            if(containsWord(root, value)){
                wyszukaIDodajLinijke(linijka, value, root);
            }
            else{
                insert(new Node(value, linijka), root);
            }
        }
        else{
            root = new Node(value, linijka);
        }
    }

    private void insert(Node node, Node father){
        if(father.getValue().compareTo(node.getValue()) == 1){
            if(father.getLeftChild() != null){
                insert(node, father.getLeftChild());
            }
            else{
                father.setLeftChild(node);
            }
        }
        else{
            if(father.getRightChild() != null){
                insert(node, father.getRightChild());
            }
            else{
                father.setRightChild(node);
            }
        }
    }

    private boolean containsWord(Node node, String value){
        if(node.getValue().compareTo(value) == 0){
            return true;
        }else if(node.getLeftChild() != null && node.getLeftChild().getValue().compareTo(value) >= 0  ){
            return containsWord(node.getLeftChild(), value);
        }
        else if(node.getRightChild() != null && node.getRightChild().getValue().compareTo(value) < 0 ){
            return containsWord(node.getRightChild(), value);
        }
        return false;
    }
    
    private void wyszukaIDodajLinijke(int linijka, String value, Node node){
        if(node.getValue().compareTo(value) == 0){
            node.dodajLinijke(linijka);
        }
        else if(node.getLeftChild() != null && node.getLeftChild().getValue().compareTo(value) >= 0 ){
            wyszukaIDodajLinijke(linijka, value, node.getLeftChild());
        }
        else if(node.getRightChild() != null && node.getRightChild().getValue().compareTo(value) <0 ){
            wyszukaIDodajLinijke(linijka, value, node.getRightChild());
        }
    }
}
