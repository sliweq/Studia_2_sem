package Lista9;

import java.util.LinkedList;
import java.util.Queue;

public class BST {
    Node root;

    public BST(){
        root = null;
    }   

    public BST(Node node){
        root = node;
    }
    
    public Node getRoot(){
        return root;
    }

    public int ileLisci(Node node){
        if(node.getLeftChild() == null){
            if(node.getRightChild() == null){
                return 1;
            }
            return ileLisci(node.getRightChild());
        }
        else if(node.getRightChild() == null){{
                return ileLisci(node.getLeftChild());
            }
        }
        return ileLisci(node.getLeftChild()) + ileLisci(node.getRightChild());
    }

    public int ileWezlow(Node node){
        int licznik = 1;
        if(node.getLeftChild() != null){
            licznik += ileWezlow(node.getLeftChild());
        }
        if(node.getRightChild() != null){
            licznik += ileWezlow(node.getRightChild());
        }
        return licznik;   
    }

    public void postfix(){
        System.out.println( " = " + postOrder(root));
    }

    private double postOrder(Node node){

        double lewe = 0;
        double prawe = 0;

        if(node.getLeftChild() != null){
            lewe += postOrder(node.getLeftChild());
        }
        if(node.getRightChild() != null ){
            prawe += postOrder(node.getRightChild());
        }
        System.out.print(node.getValue() + " ");
        if(czyOperator(node.getValue())){
            String op = node.getValue();
                if(op.equals("+")){
                    return lewe + prawe;
                }
                else if(op.equals("-")){
                    return lewe - prawe;
                }
                else if(op.equals("*")){
                    return lewe*prawe;
                }
                else if(op.equals("/")){
                    return (lewe/(prawe*1.0));
                }
                else{
                    return lewe%prawe;
                }

        }else{
            return Double.valueOf(node.getValue());
        }  
    }

    public double obliczWynik(Node node){ 
        double lewe = 0;
        double prawe = 0;
        if(node.getLeftChild() != null){
            lewe = obliczWynik(node.getLeftChild());
        }
        if(node.getRightChild() != null){
            prawe = obliczWynik(node.getRightChild());
        }
        if(czyOperator(node.getValue())){
            String op = node.getValue();
            if(op.equals("+")){
                return lewe + prawe;
            }
            else if(op.equals("-")){
                return lewe - prawe;
            }
            else if(op.equals("*")){
                return lewe*prawe;
            }
            else if(op.equals("/")){
                return (lewe/(prawe*1.0));
            }
            else{
                return lewe%prawe;
            }
        }
        return Double.valueOf(node.getValue());
    }

    private boolean czyOperator(String str){
        if(str.equals("+")|| str.equals("-")|| str.equals("/") || str.equals("*") || str.equals("%")){
            return true;
        }
        return false;
    }

    public int wysokoscDrzewa(Node node){
        if(node.getLeftChild() == null){
            if(node.getRightChild() == null){
                return 1;
            }
            return wysokoscDrzewa(node.getRightChild())+1;
        }
        else if(node.getRightChild() == null){{
                return wysokoscDrzewa(node.getLeftChild())+1;
            }
        }
        return Math.max(ileLisci(node.getLeftChild()),ileLisci(node.getRightChild()))+1;
    }

    public void infix(){
        System.out.println(" = "+infix(root));
    }
    
    private double infix(Node node)
    {
        double lewe = 0;
        double prawe = 0;
        
        if (node.getLeftChild() != null)
        {
            System.out.print("(");
            lewe += infix(node.getLeftChild());
        }

        if (!czyOperator(node.getValue()))
            System.out.print("(" + node.getValue() + ")");
        else
            System.out.print(node.getValue());

        if (node.getRightChild() != null)
        {
            prawe += infix(node.getRightChild());
            System.out.print(")");
        }

        if(czyOperator(node.getValue())){
                String op = node.getValue();
                if(op.equals("+")){
                    return lewe + prawe;
                }
                else if(op.equals("-")){
                    return lewe - prawe;
                }
                else if(op.equals("*")){
                    return lewe*prawe;
                }
                else if(op.equals("/")){
                    return (lewe/(prawe*1.0));
                }
                else{
                    return lewe%prawe;
                }
            
        }
        else{
            return Double.valueOf(node.getValue());
        }
    }

    public void wyszukajPoPoziomach(){
        System.out.println("Printowanie po poziomach");
        wyszukajPoPoziomach(root);
        System.out.println();
    }
    
    private void wyszukajPoPoziomach(Node node){
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

}
