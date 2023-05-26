package Lista9;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import Lista4.ArrayStack;

public class RPN {
    String wyrazenie;
    ArrayList<String> wyrazenieArray;
    ArrayList<String> rpn;

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
                else if(((int)znak >= 40 && (int)znak <= 43) || (int)znak == 45 || (int)znak == 47 || (int)znak == 94){
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
        if(str.equals("^") ){
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
        if(str.equals("+")|| str.equals("-")|| str.equals("/") || str.equals("*") || str.equals("^")){
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
                while(!stos.isEmpty() &&!czyNawias(stos.peek()) && (wartoscOperatora(znak) < wartoscOperatora(stos.peek()) || wartoscOperatora(znak) == wartoscOperatora(stos.peek()))){
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
                //jesli jest liczba
                rpn.add(znak);
            }
        }
        while(!stos.isEmpty()){
            rpn.add(stos.pop());
        }
    }

    public void stworzDrzewo(){
        Stack<Node> stos = new Stack<>();
        for(String x: wyrazenieArray){
            //if(x == "+" ||)
        }
    }

    public void sprawdzPoprawnosc(){
        //TODO asdasdasda
    }
    public static void main(String args[]){
        RPN r = new RPN();
        r.wpiszWyrazenie();
        // for(String x: r.wyrazenieArray){
        //     System.out.println(x);
        // }
        r.stworzRPN();
        for(String x: r.rpn){
            System.out.print(x+" ");
        }
    }
    
}
