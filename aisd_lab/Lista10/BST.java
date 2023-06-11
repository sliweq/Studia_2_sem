package Lista10;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

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
        public ArrayList<Integer> getLinijki(){
            return linijki;
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
        public void resetNode(String value, ArrayList<Integer> linijki){
            this.value = value;
            this.linijki = linijki;

        }
    }

    Node root;

    public void remove(String key){
        root = remove(root, key);
    }
    
    private Node remove(Node node, String key){
        if(node == null){
            return node;
        }
        else{
            if(comparator(node.getValue(), key) == 1 ){
                node.setLeftChild(remove(node.leftChild, key));
            }

            else if(comparator(node.getValue(), key) == -1){
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
                Node nastepnik = getNastepnik(node); 
                node.resetNode(nastepnik.getValue(), nastepnik.getLinijki());

                node.setRightChild(remove(node.getRightChild(), node.getValue()));
                
            }
            
            return node;
        }
    }

    private Node getNastepnik(Node node){
        if(node.getRightChild() != null){
            Node tmpNode = node.getRightChild();
            while(tmpNode.getLeftChild() != null){
                tmpNode = tmpNode.getLeftChild();
            }
            return tmpNode;
        }
        return null;
    }


    public void wszerz(){
        System.out.println("Przejscie drzewa wszerz:");
        wszerz(root);
        System.out.println();
    }

    private void wszerz(Node node){
        Queue<Node> queue = new LinkedList<>();
        if(node != null){
            queue.add(node);
            while(!queue.isEmpty()){
                if(queue.peek().getLeftChild() != null){
                    queue.add(queue.peek().getLeftChild());
                }
                if(queue.peek().getRightChild() != null){
                    queue.add(queue.peek().getRightChild());
                }
                System.out.print(queue.remove().getValue()+" ");
            }
        }

    }
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
        if(comparator(father.getValue(), node.getValue()) == 1){
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
        if(comparator(value, node.getValue()) == 0){
            return true;
        }else if(node.getLeftChild() != null && comparator(node.getValue(), value) == 1  ){
            return containsWord(node.getLeftChild(), value);
        }
        else if(node.getRightChild() != null &&  comparator(node.getValue(), value) == -1 ){
            return containsWord(node.getRightChild(), value);
        }
        return false;
    }
    
    private void wyszukaIDodajLinijke(int linijka, String value, Node node){
        if(comparator(value, node.getValue()) == 0){
            node.dodajLinijke(linijka);
        }
        else if(node.getLeftChild() != null && comparator(node.getValue(), value) == 1 ){
            wyszukaIDodajLinijke(linijka, value, node.getLeftChild());
        }
        else if(node.getRightChild() != null && comparator(node.getValue(), value) == -1 ){
            wyszukaIDodajLinijke(linijka, value, node.getRightChild());
        }
    }

    public void inorder(){
        System.out.println("Skorowidz:");
        if(root != null){
            inorder(root);
        }
    }

    private void inorder(Node node){
        if(node.getLeftChild() != null){
            inorder(node.getLeftChild());
        }
        System.out.print(node.getValue());
        for(int x: node.getLinijki()){
            System.out.print(" " + x);
        }
        System.out.println();
        if(node.getRightChild() != null){
            inorder(node.getRightChild());
        }
    }

    public static int comparator(String one, String two){
        if(one.equals(two)){
            return 0;
        }
        if(one.length() >= two.length()){
            for(int x = 0; x < two.length(); x++){
                if(one.charAt(x)>two.charAt(x)){
                    return 1;
                }
                else if(one.charAt(x)<two.charAt(x)){
                    return -1;
                }
            }
            return 1;
        }
        if(two.length() > one.length()){
            for(int x = 0; x < one.length(); x++){
                if(one.charAt(x)>two.charAt(x)){
                    return 1;
                }
                else if(one.charAt(x)<two.charAt(x)){
                    return -1;
                }
            }
            return -1;
        }
        return -1;
    }

}
