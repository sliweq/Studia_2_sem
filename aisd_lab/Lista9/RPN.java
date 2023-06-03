package Lista9;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class RPN {
    String wyrazenie;
    ArrayList<String> wyrazenieArray;
    ArrayList<String> rpn;
    BST bst;

    public void wpiszWyrazenie(){
        wyrazenieArray = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        wyrazenie = scan.nextLine();
        for(int i = 0; wyrazenie.length() > i; i++){
            char znak = wyrazenie.charAt(i);
            if((int)znak != 32){
                if((int)znak >= 48 && (int)znak <= 57){
                    StringBuilder tmpWyrazenie = new StringBuilder();
                    while(i < wyrazenie.length() && wyrazenie.charAt(i) >= 48 && wyrazenie.charAt(i) <= 57){
                        znak = wyrazenie.charAt(i);
                        tmpWyrazenie.append(String.valueOf(znak));
                        i+=1;
                    }
                    i-=1;
                    wyrazenieArray.add(tmpWyrazenie.toString());
                }
                else if(((int)znak >= 40 && (int)znak <= 43) || (int)znak == 45 || (int)znak == 47 || (int)znak == 37){
                    wyrazenieArray.add(String.valueOf(znak));
                }
            }
            
        }
    }

    public int wartoscOperatora(String str){
        if(str.equals("+") || str.equals("-") ){
            return 1;
        }
        if(str.equals("*")  || str.equals("/") ){
            return 2;
        }
        if(str.equals("%") ){
            return 3;
        }
        return 0;
    }

    public boolean czyNawias(String str ){
        if(str.equals("(") || str.equals(")") ){
            return true;
        }
        return false;
    }

    public boolean czyOperator(String str){
        if(str.equals("+")|| str.equals("-")|| str.equals("/") || str.equals("*") || str.equals("%")){
            return true;
        }
        return false;
    }

    public void stworzRPN(){
        rpn = new ArrayList<>();
        Stack<String> stos = new Stack<>();

        for(int i = 0; i< wyrazenieArray.size(); i++){
            String znak = wyrazenieArray.get(i);
            if(czyOperator(znak)){
                while(!stos.isEmpty() && !czyNawias(stos.peek()) && (wartoscOperatora(znak) <= wartoscOperatora(stos.peek()))){
                    rpn.add(stos.pop());
                }
                stos.push(znak);
            }
            else if(czyNawias(znak)){
                if(znak.equals("(")){
                    stos.push(znak);
                }
                else{
                    while(!stos.isEmpty() && !stos.peek().equals("(")){
                        rpn.add(stos.pop());
                    }
                    if(!stos.isEmpty()){

                        stos.pop(); 
                    }
                }
            }else{
                rpn.add(znak);
            }
        }
        while(!stos.isEmpty()){
            rpn.add(stos.pop());
        }
    }

    public void stworzDrzewo(){
        Stack<Node> stos = new Stack<>();
        for(String x: rpn){
            if(czyOperator(x)){
                Node n = new Node(x);
                n.setRightChild(stos.pop());
                n.setLeftChild(stos.pop());
                stos.push(n);
            }
            else{
                stos.push(new Node(x));
            }
        }
        bst = new BST(stos.pop());
    }

    public boolean sprawdzPoprawnosc(){
        for(int i = 0; i < wyrazenie.length(); i++){
            char tmp = wyrazenie.charAt(i);
            if(!(tmp == 32 || (tmp >= 40 && tmp <= 43) || tmp == 45 || (tmp >= 47 && tmp <=57) || tmp == 37)){
                return false;
            }            
        }
        
        Stack<String> stos = new Stack<>();
        for(int i = 0; i < wyrazenie.length(); i++){
            char tmp = wyrazenie.charAt(i);
            if(tmp == 40){
                stos.push(String.valueOf(tmp));
            }            
            else if(tmp == 41){
                if(stos.isEmpty()){
                    return false;
                }
                else{
                    stos.pop();
                }
            }
        }
        if(!stos.isEmpty()){
            return false;
        }

        stos.clear();

        for(int i = 0; i < wyrazenie.length(); i++){
            char tmp = wyrazenie.charAt(i);
            if(tmp == 32){
                continue;
            }
            if(tmp == 42 || tmp == 43 || tmp == 45 || tmp == 47 ||tmp == 37){
                if(i == 0){
                    return false;    
                }
                if(!stos.isEmpty()){
                    return false;
                }
                stos.push(String.valueOf(tmp));
            }            
            else if(tmp != 40 && tmp != 41){
                if(!stos.isEmpty()){
                    stos.pop();
                }
            }
        }
        if(!stos.isEmpty()){
            return false;
        }


        return true;
    }
    
    public static void main(String args[]){
        RPN r = new RPN();
        r.wpiszWyrazenie();

        if(r.sprawdzPoprawnosc()){
            r.stworzRPN();
            r.stworzDrzewo();
            BST bst = r.bst;
            System.out.println();

            //postfix 
            System.out.println("ONP notacja postfix:");
            r.bst.postfix();

            System.out.println("Wynik działania: " + bst.obliczWynik(bst.getRoot()));

            System.out.println("Ilość liści: " + bst.ileLisci(bst.getRoot()));

            System.out.println("Ilość węzłow: " + bst.ileWezlow(bst.getRoot()));

            System.out.println("Wysokosc drzewa: " + bst.wysokoscDrzewa(bst.getRoot()));

            System.out.print("Postać infixowa: ");
            bst.infix();

            bst.wyszukajPoPoziomach();
        }

    }
    
}
