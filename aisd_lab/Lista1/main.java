package Lista1;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class main {

    private ArrayList<Karta> karty = new ArrayList<Karta>();
    private int ilosc = 20;

    public void printmenu(){
        System.out.println("Menu:\n1 - utwórz ArrayList\n2 - wyświetl wszystkie elementy\n3 - wyświetl ilosc elementów");
        System.out.println("4 - wyświetl karty o podanej wartości\n5 - wyświetl karty o podanm kolorze\n6 - usuń duplikaty");
    }
    
    public void stworzKarty(){
        System.out.println("Stworzono karty");

        Random rand = new Random();
        if(karty.size() == 0){
            int war = rand.nextInt(0,14);
            if (war != 0){
                int kol = rand.nextInt(0,4);
                karty.add(new Karta(war, kol));
                for(int x = 1; x < ilosc;x++){
                    war = rand.nextInt(0,14);
                    if (war != 0){
                        kol = rand.nextInt(0,4);
                        Karta karta = new Karta(war, kol);
    
                        boolean dodana = false;
    
                        for(int y = 0; y < karty.size(); y++ ){
                            if (karta.getWartosc() == karty.get(y).getWartosc()){
                                if(karta.getKolor() <= karty.get(y).getKolor()){
                                    karty.add(y, karta);
                                    dodana = true;
                                    break;
                                }
                            }
                            else if (karta.getWartosc() < karty.get(y).getWartosc()){
                                karty.add(y, karta);
                                    dodana = true;
                                    break;
                            }
                        }
                        if (!dodana){
                            karty.add(karta);
                        }
                        
                    } else {
                        break;
                    }
                }
            } 
        }
        else{
            int war = rand.nextInt(0,14);

            if (war != 0){
                for(int x = 0; x < ilosc;x++){
                    if (war != 0){
                        int kol = rand.nextInt(0,4);
                        Karta karta = new Karta(war, kol);

                        boolean dodana = false;

                        for(int y = 0; y < karty.size(); y++ ){
                            if (karta.getWartosc() == karty.get(y).getWartosc()){
                                if(karta.getKolor() <= karty.get(y).getKolor()){
                                    karty.add(y, karta);
                                    dodana = true;
                                    break;
                                }
                            }
                            else if (karta.getWartosc() < karty.get(y).getWartosc()){
                                karty.add(y, karta);
                                    dodana = true;
                                    break;
                            }
                        }
                        if (!dodana){
                            karty.add(karta);
                        }
                        war = rand.nextInt(0,14);
                        
                    } else {
                        break;
                    }
                }
            } 
        }
        
    }
    
    public void pokazKarty(){
        System.out.println("Pokaz karty: ");
        if (karty.size() != 0){
            for(int x = 0; x < karty.size();x++){
                System.out.println("Wartość i kolor: " + karty.get(x).getWartosc()+ " "+ karty.get(x).getKolor() );
            }
            // for(Karta karta:karty){
            //     System.out.println("Wartość i kolor: " + karta.getWartosc()+ " "+ karta.getKolor());
            // }
            // int x = 0;
            // while(x >karty.size()){
            //     System.out.println("Wartość i kolor: " + karty.get(x).getWartosc()+ " "+ karty.get(x).getKolor() );
            //     x+=1;
            // }
            // Iterator<Karta> cardsit = karty.iterator();
            // while(cardsit.hasNext()) {
            //     System.out.println(cardsit.next());
            // }
            
        }
        else{
            System.out.println("ArrayLista jest pusta najpierw stwórz karty");
        }
        
    }
    
    public void ileKart(){
    
        System.out.println("Liczba kart: ");
        Iterator<Karta> cardsit = karty.iterator();
        int x = 0;
        while(cardsit.hasNext()) {
            x += 1;
            cardsit.next();
        }

        System.out.println(x);
        System.out.println(karty.size());
    }
    
    public void pokazWartosc(int wartosc){
        System.out.println("Podana wartość: ");
        Iterator<Karta> cardsit = karty.iterator();
        while(cardsit.hasNext()) {
            Karta tmp = cardsit.next();
            if (tmp.getWartosc() == wartosc){
                System.out.println("Wartość i kolor: "+tmp.getWartosc()+" "+tmp.getKolor());
            }
        }

        // for(Karta tmp:karty){
        //     if (tmp.getWartosc() == wartosc){
        //         System.out.println("Wartość i kolor: "+tmp.getWartosc()+" "+tmp.getKolor());
        //     }
        // }

        // for(int tmp = 0; tmp < karty.size(); tmp++){
        //     if (karty.get(tmp).getWartosc() == wartosc){
        //         System.out.println("Wartość i kolor: "+karty.get(tmp).getWartosc()+" "+karty.get(tmp).getKolor());
        //     }
        // }
        // int x = 0;
        // while(x < karty.size()){
        //     if (karty.get(x).getWartosc() == wartosc){
        //         System.out.println("Wartość i kolor: "+karty.get(x).getWartosc()+" "+karty.get(x).getKolor());
        //     }
        //     x +=1;
        // }

    }
    
    public void pokazKolor(int kolor){
        System.out.println("Podany kolor: ");
        Iterator<Karta> cardsit = karty.iterator();
        while(cardsit.hasNext()) {
            Karta tmp = cardsit.next();
            if (tmp.getKolor() == kolor){
                System.out.println("Wartość i kolor: "+tmp.getWartosc()+" "+tmp.getKolor());
            }
        }

        for(Karta tmp:karty){
            if (tmp.getKolor() == kolor){
                System.out.println("Wartość i kolor: "+tmp.getWartosc()+" "+tmp.getKolor());
            }
        }

        for(int tmp = 0; tmp < karty.size(); tmp++){
            if (karty.get(tmp).getKolor() == kolor){
                System.out.println("Wartość i kolor: "+karty.get(tmp).getWartosc()+" "+karty.get(tmp).getKolor());
            }
        }
        int x = 0;
        while(x < karty.size()){
            if (karty.get(x).getKolor() == kolor){
                System.out.println("Wartość i kolor: "+karty.get(x).getWartosc()+" "+karty.get(x).getKolor());
            }
            x +=1;
        }

    }

    public void usunDuplikaty(){
        karty.add(0,new Karta(1, 0));
        karty.add(0,new Karta(1, 0));
        karty.add(0,new Karta(1, 0));

        // Iterator<Karta> cardsit = karty.iterator();
        
        // Karta poprzednia = cardsit.next();   
        // while(cardsit.hasNext()){
        //     Karta x = cardsit.next();   
        //     if(poprzednia.getWartosc() == x.getWartosc() && x.getKolor() == x.getKolor()){
        //         cardsit.remove();
        //     }
        //     else{
        //         poprzednia = x;
        //     }
        // }
        // cardsit.forEachRemaining(karty::add);
        
        // Karta poprzednia = cardsit.next();
        // ArrayList<Karta> copykarty = new ArrayList<Karta>();   
        // copykarty.add(poprzednia);
        // while(cardsit.hasNext()){
        //     Karta x = cardsit.next();
        //     if(poprzednia.getWartosc() == x.getWartosc() && x.getKolor() == x.getKolor()){
        //         cardsit.remove();
        //     } else {
        //         copykarty.add(x);
        //     }
        // }
        // karty = copykarty;


        // for(int i = karty.size()-1; i > 0; i--){
        //     if(karty.get(i).getKolor() == karty.get(i-1)1.getKolor() && karty.get(i).getWartosc()==karty.get(i-1).getWartosc()){
        //         karty.remove(i);
        //     }
        // }
        
        int i = karty.size()-1;
        while(i > 0){
            if(karty.get(i).getKolor() == karty.get(i-1).getKolor() && karty.get(i).getWartosc()==karty.get(i-1).getWartosc()){
                karty.remove(i);
            }
            i--;
        }
        System.out.println("Usunieto duplikaty");
    }

    static public int retwartosc(){
        Scanner scan = new Scanner(System.in);
        int x = 0;
        try{
            x = scan.nextInt();
            while(x<1 || x >13){
                System.out.println("podaj poprawną wartość");
                x = scan.nextInt();
            }
        } catch(InputMismatchException ex ){
            System.out.println("Wystąpił błąd");
        }
        scan.close();
        return x; 

    }

    static public int retkolor(){
        Scanner scan = new Scanner(System.in);
        int x = 0;
        try{
            x = scan.nextInt();
            while(x<0 || x >3){
                System.out.println("podaj poprawną wartość");
                x = scan.nextInt();
            }
        } catch(InputMismatchException ex ){
            System.out.println("Wystąpił błąd");
        }
        scan.close();

        return x;    
    }

    public static void main(String[] args){
        main m = new main();
        Scanner scan = new Scanner(System.in);

        try{
            m.printmenu();
            int x = scan.nextInt();
            while(x >= 1 && x <=6){
                
                switch(x){

                    case 1: m.stworzKarty(); break;
                    case 2: m.pokazKarty();break;
                    case 3: m.ileKart();break;
                    case 4: m.pokazWartosc(retwartosc());break;
                    case 5: m.pokazKolor(retkolor()); break;
                    case 6: m.usunDuplikaty(); break;
    
                }
                x = scan.nextInt();
            }

        } catch(InputMismatchException ex ){
            System.out.println("Wystąpił błąd");
        }
        scan.close();


    }
}