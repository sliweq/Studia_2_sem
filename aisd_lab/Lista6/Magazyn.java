package Lista6;

import java.util.Scanner;

public class Magazyn {
    private ListQueue<Klient> klienci= new ListQueue<>();

    public Magazyn(){

    }

    public void dodajKlienta() throws FullQueueException{
        klienci.enqueue(dodajZamowieniaDoKlienta());
        System.out.println("-Dodano klienta-");

    }

    public Klient stworzKlienta(){
        
        try{
            Scanner scan = new Scanner(System.in);
            System.out.println("Podaj imię klienta:");

            String name = "";
            name = scan.nextLine(); 
            return new Klient(name);
        }
        catch(Exception ex){
            System.out.println("Błąd wprowadzania danych stworzono domyślnego Klienta");
        }
        return new Klient("Jan");
    }

    public int zrealizujZamowienie() throws EmptyQueueException{
        if(klienci.size() != 0){
            int kwota = 0;
            Klient klient = klienci.dequeue();
            while(klient.getZamowieniaSize() >0 ){
                kwota +=  klient.getZamowienie();
            }
            if(klient.getImie().endsWith("a")){
                System.out.println("Zrealizowano zlecenie Pani: "+klient.getImie() + " | Kwota do zapłaty: "+ kwota );
                return kwota;
            }
            else{
                System.out.println("Zrealizowano zlecenie Pana: " + klient.getImie() + " | Kwota do zapłaty: "+ kwota );
                return kwota;

            }
            
        }else{
            System.out.println("---Wszystkie zlecenia zostały zrealizowane. Kolejka jest pusta---");
            return 0;
        }
    }

    public boolean czyMaKlientow(){
        return !klienci.isEmpty();
    }

    public Klient dodajZamowieniaDoKlienta(){
        Klient klient = stworzKlienta();
        try{
            int cena = 1;
            Scanner scan = new Scanner(System.in);
            while(cena >0){
                System.out.print("Dodaj zamowienie na kwotę:");
                cena = scan.nextInt();
                klient.dodajZamowienie(Integer.valueOf(cena));
            }
            return klient;
        }   
        catch(Exception ex){

        }
        return klient;
    }

    public void pokazMenu(){

        System.out.println("-----Menu wyboru:-----");
        System.out.println("-1- dodaj Klienta oraz jego zamowenia -1-");
        System.out.println("-2- zrealizuj zamowienia pierwszgo Klienta -2-");
        System.out.println("-3- stworz domysnych klientow -3-");

    }

    public void stworzDomyslnychKlientow() throws FullQueueException{
        Klient k = new Klient("Julia");
        k.dodajZamowienie(5);
        k.dodajZamowienie(69);
        k.dodajZamowienie(13);
        klienci.enqueue(k);
        Klient a = new Klient("Andrzej");
        a.dodajZamowienie(4);
        a.dodajZamowienie(122);
        a.dodajZamowienie(105);
        klienci.enqueue(a);
        Klient b = new Klient("Bartek");
        klienci.enqueue(b);
    }

    public void stworzDomyslnychKlientowv2() throws FullQueueException{
        Klient k = new Klient("Andrzej");
        k.dodajZamowienie(1000);
        k.dodajZamowienie(1);
        k.dodajZamowienie(2136);
        klienci.enqueue(k);
        Klient a = new Klient("Michał");
        a.dodajZamowienie(3123);
        a.dodajZamowienie(1234);
        a.dodajZamowienie(9875);
        klienci.enqueue(a);
        Klient b = new Klient("Anna");
        a.dodajZamowienie(9875);
        klienci.enqueue(b);
    }

    public void menuWyboru(){
        Scanner scan = new Scanner(System.in);
        try{
            int wybor = 1;
            pokazMenu();

            wybor = scan.nextInt();

            while(wybor >=1 && wybor <= 5){
                switch(wybor){
                    case 1: dodajKlienta(); break;
                    case 2: zrealizujZamowienie(); break;
                    case 3: stworzDomyslnychKlientow(); break;
                }
                wybor = scan.nextInt();
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }


    }
 
}
