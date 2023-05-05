package Lista3;


import java.util.*;

public class Main {
    public OneWayLinkedCardsWithHead<karta> karty;
    public int[] druga_tablica = new int[52];


    public void stworzkarty(){
        Random rng = new Random();
        int wartosc = rng.nextInt(0,15);
        while(wartosc!=0){
            ListIterator<karta> kartyiter = karty.listIterator();
            int kolor = rng.nextInt(0,4);
            if(wartosc!= 14){
                if(!kartyiter.hasNext()){
                    karty.add(new karta(wartosc,kolor,true));
                }
                else{
                    int index = 0;
                    boolean added = false;
                    karta next = kartyiter.next();
                    while((!added) && kartyiter.hasNext()){
                        if(next.getWartosc()>wartosc){
                            karty.add(index,new karta(wartosc,kolor,true));
                            added = true;
                        }else if(next.getWartosc() == wartosc && next.getKolor() > kolor ){
                            karty.add(index,new karta(wartosc,kolor,true));
                            added = true;
                        }else if(next.getWartosc() == wartosc && next.getKolor() == kolor ){
                            added = true;
                        }
                        index +=1;
                        next = kartyiter.next();

                    }
                    if(!added){
                        if(next.getWartosc()>wartosc){
                            karty.add(index,new karta(wartosc,kolor,true));
                        }else if(next.getWartosc() == wartosc && next.getKolor() > kolor ){
                            karty.add(index,new karta(wartosc,kolor,true));
                        }
                        else if(next.getWartosc() == wartosc && next.getKolor() != kolor){karty.add(new karta(wartosc,kolor,true));
                        }
                        else if(next.getWartosc() < wartosc){karty.add(new karta(wartosc,kolor,true));}
                    }

                }
            }
            else{
                karty.add(new karta(wartosc,kolor,false));
            }
            wartosc = rng.nextInt(0,15);
        }
        System.out.println("--------Stworzono pomyślnie-------");
    }



    public void pokaz_kolor(){
        int x = 0;
        try{
            Scanner scan = new Scanner(System.in);
            System.out.println("Podaj kolor do wyswietlenia");
            x = scan.nextInt();

        } catch(Exception e){
            System.err.println("Podales zla wartosc");
        }

        ListIterator<karta> kartyiter = karty.listIterator();
        while(kartyiter.hasNext()){
            karta tmp_card = kartyiter.next();
            if(tmp_card.getKolor() == x){
                System.out.println(tmp_card.toString());
            }
        }
    }
    public void pokaz_wartosc(){
        int x = 0;
        try{
            Scanner scan = new Scanner(System.in);
            System.out.println("Podaj wartosc do wyswietlenia");
            x = scan.nextInt();

        } catch(Exception e){
            System.err.println("Podales zla wartosc");
        }

        ListIterator<karta> kartyiter = karty.listIterator();
        while(kartyiter.hasNext()){
            karta tmp_card = kartyiter.next();
            if(tmp_card.getWartosc() == x){
                System.out.println(tmp_card.toString());
            }
        }
    }
    public void pokaz_liczbe_kart(){
        System.out.println("Liczba wszystkich kart: " + karty.size());
        ListIterator<karta> kartyiter = karty.listIterator();
        int ukryte = 0;
        while(kartyiter.hasNext()){
            if(!kartyiter.next().isZnacznik()){
                ukryte +=1;
            }
        }
        System.out.println("Liczba odkrytych kart: " + (karty.size()-ukryte));
        System.out.println("Liczba ukrytych kart: " + ukryte);
    }
    public void pokaz_wszystkie_karty(){
        ListIterator<karta> kartyiter = karty.listIterator();
        while(kartyiter.hasNext()){
            System.out.println(kartyiter.next().toString());
        }
    }
    public void sprawdz_talie(){
        ListIterator<karta> kartyiter = karty.listIterator();
        while(kartyiter.hasNext()){
            karta tmp_card= kartyiter.next();
            if(tmp_card.isZnacznik()){
                druga_tablica[(tmp_card.getKolor()*13)+(tmp_card.getWartosc())-1] = 1;
            }
        }
        int tmp = 0;
        for(int x = 0; x< 52;x++){
            if (tmp ==13 ){
                System.out.print(" || ");
                tmp = 0;
            }
            System.out.print(druga_tablica[x]);
            tmp +=1;
        }
        System.out.println();

        // //druga
        // for(int y = 0; y< 52; y++ ){
        //     System.out.println(tablica[y]);
        // }

    }
    public void pokaz_odkryte_karty(){
        ListIterator<karta> kartyiter = karty.listIterator();
        while(kartyiter.hasNext()){
            karta tmp_card = kartyiter.next();
            if(tmp_card.isZnacznik()){
                System.out.println(tmp_card.toString());
            }
            else{
                System.out.println("()");
            }
        }
    }
    public void usun_ukryte(){

        ListIterator<karta> kartyiter = karty.listIterator();
        int ukryte = 0;
        while(kartyiter.hasNext()){
            if(!kartyiter.next().isZnacznik()){
                ukryte +=1;
            }
        }
        for(int i = 0; i< ukryte; i++){
            karty.remove(karty.size()-1);
        }
        System.out.println("Usunięto ukryte");

    }

    public void pokaz_menu(){
        System.out.println("Menu:" +
                "\n1-Utworz liste" +
                "\n2-Wyswietl liczbe kart" +
                "\n3-Wyswietl wsyzstkie karty" +
                "\n4-Wyświetl odkryte karty" +
                "\n5-Wyświetl karty o podanym kolorze" +
                "\n6-Wyświetl karty o podanej wartosci" +
                "\n7-Usun karty zakryte" +
                "\n8-Wyswietl 'tablice' prawdy ");
    }
    public static void main(String[] args) {
        Main m = new Main();
        m.karty = new OneWayLinkedCardsWithHead<>();

        int wybor = 0;
        Scanner scan = new Scanner(System.in);
        m.pokaz_menu();
        try{
            wybor = scan.nextInt();
            while(wybor >=1 && wybor <=8){
                switch (wybor){
                    case 1: m.stworzkarty(); break;
                    case 2: m.pokaz_liczbe_kart(); break;
                    case 3: m.pokaz_wszystkie_karty(); break;
                    case 4: m.pokaz_odkryte_karty(); break;
                    case 5: m.pokaz_kolor(); break;
                    case 6: m.pokaz_wartosc(); break;
                    case 7: m.usun_ukryte(); break;
                    case 8: m.sprawdz_talie(); break;
                }
                wybor = scan.nextInt();

            }
        }
        catch (Exception e){
            System.err.println("Błąd w wejsciu danych");

        }


    }

}
