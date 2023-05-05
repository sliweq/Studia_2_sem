package Lista2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

class PracownikIterator<Pracownik> implements Iterator<Pracownik>{
    private Pracownik[] pracownicy;

    private int index; 

    public PracownikIterator(Pracownik[] pracownicy){
        this.pracownicy = pracownicy;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < pracownicy.length; 
    }

    @Override
    public Pracownik next() {
        if(hasNext()){
            return pracownicy[index++];
        } 
        else {
            throw new NoSuchElementException();
        }
    }

}

class DaneIterator<String> implements Iterator<String>{
    private String[] dane;

    private int index; 

    public DaneIterator(String[] dane){
        this.dane = dane;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < dane.length; 
    }

    @Override
    public String next() {
        if(hasNext()){
            return dane[index++];
        } 
        else {
            throw new NoSuchElementException();
        }
    }

    
}

public class main {

    public static Pracownik[] pracownicy;
    public static String[] dane;

    public static void wystwietl_pracownikow_wyswietl(){
        if(pracownicy[0] != null){
            System.out.println("------------------------------------------------------------------------------------------------------------"); 
            System.out.printf("%-2s%-20s%-2s%-20s %-2s%-13s %-2s%-20s %-2s%2s %-1s%9s %2s %n","I","Nazwisko","I","Imie","I","Pesel","I","Stanowisko","I","Staz","I","Pensja","I");
            System.out.println("------------------------------------------------------------------------------------------------------------"); 
            PracownikIterator<Pracownik> pracownicyiterator = new PracownikIterator<Pracownik>(pracownicy);
            
            while(pracownicyiterator.hasNext()){
                pracownicyiterator.next().wyswietl();
            }
            System.out.println("------------------------------------------------------------------------------------------------------------"); 
        }
        else{
            System.out.println("----------- Brak pracownikow -----------"); 
        }
        
    }

    public static void stworz_pracownika(){
        Pracownik[] pracownicy_nowi = new Pracownik[pracownicy.length+1];
        DaneIterator<Pracownik> iterdane = new DaneIterator<Pracownik>(pracownicy);
        int x = 0;
        while(iterdane.hasNext()){
            pracownicy_nowi[x] = iterdane.next();
            x+=1;
        }
        System.out.println("1 - pracownik etatowy 2- pracownik godzinowy ");
        Scanner scan = new Scanner(System.in);
        String  tmp ;
        
        try{
            tmp = scan.nextLine();
            if(tmp == "1"){
                String nazwisko = scan.nextLine();
                String imie = scan.nextLine();
                long pesel = scan.nextLong();
                scan.nextLine();
                String stanowisko = scan.nextLine();
                int staz = scan.nextInt();
                double stawka = scan.nextDouble();
                double etat = scan.nextDouble();
                pracownicy_nowi[x] = new PracownikEtatowy(nazwisko,imie,pesel,stanowisko,staz,stawka,etat);
            }  
            else{
                String nazwisko = scan.nextLine();
                String imie = scan.nextLine();
                long pesel = scan.nextLong();
                String stanowisko = scan.nextLine();
                int staz = scan.nextInt();
                double stawka = scan.nextDouble();
                int lcizba_godzin = scan.nextInt();
                pracownicy_nowi[x] = new PracownikGodzinowy(nazwisko,imie,pesel,stanowisko,staz,lcizba_godzin,stawka);
            }
            pracownicy = pracownicy_nowi;

            
        }catch(InputMismatchException ex ){
            System.out.println("Wystąpił błąd");
        }
    }
    
    public static void wystwietl_pracownikow(){
        if(dane != null){
            DaneIterator<String> iterdane = new DaneIterator<String>(dane);
        if(iterdane.hasNext()){
            System.out.println("------------------------------------------------------------------------------------------------------------"); 
            System.out.printf("%-2s%-15s%-2s%-15s%-2s%-15s%-2s%-15s%-2s%-15s%-2s%-15s%-2s%n","I","Nazwisko","I","Imie","I","Pesel","I","Stanowisko","I","Staz","I","Pensja","I");
            System.out.println("------------------------------------------------------------------------------------------------------------"); 
            
            int x = 0;
            while(iterdane.hasNext()){
                if(x == 2 || x == 4 || x == 5){
                    System.out.printf("%2s%15s","|",iterdane.next());
                }else{
                    System.out.printf("%-2s%-15s","|",iterdane.next());

                }

                x+=1;
                if (x==6 ){
                    x = 0;
                    System.out.printf("%-2s%n","|");
                }
            }
            System.out.println("------------------------------------------------------------------------------------------------------------"); 
        }
        }
        else{System.out.println("----------- Brak danych o pracownikach -----------"); }
  
    }
   
    public static void wpisz_pracowników(){
        if(pracownicy == null){
            System.out.println("----------- Brak pracownikow na liscie -----------"); 
        }
        else{
            dane = new String[pracownicy.length*6];

            int x = 0;
            for(Pracownik prac: pracownicy){
                dane[x] = prac.getNazwisko();
                dane[x+1] = prac.getImie();
                dane[x+2] = Long.toString(prac.getPesel());
                dane[x+3] = prac.getStanowisko();
                dane[x+4] = Integer.toString(prac.getStaz());
                dane[x+5] = Double.toString(prac.pensja());
                x+=6;
            }
            System.out.println("----------- Pomyslnie wpisano dane pracownikow -----------"); 
        }
    }
    
    public static void stworz_pracownikow(){

        pracownicy = new Pracownik[6];
        pracownicy[0] = new PracownikEtatowy("Nowak","Andrzej",12345678910L,"Magazyn",10,5000,0.75);
        pracownicy[4] = new PracownikEtatowy("Kowalska","Anna",12311111910L,"Sekretariat",2,4500,0.5);
        pracownicy[3] = new PracownikEtatowy("Mak","Jacek",12222278910L,"Transport",1,4400,1);
        pracownicy[1] = new PracownikGodzinowy("Polak","Julia",33333378910L,"Kuchnia",5,175,25.2);
        pracownicy[2] = new PracownikGodzinowy("Cebula","Alicja",99999678910L,"Finanse",15,180,27);
        pracownicy[5] = new PracownikGodzinowy("Gruszka","Tomasz",88345678910L,"Menager",20,216,31.5);
        System.out.println("----------- Pomyslnie stworzono pracownikow -----------");
    }

    public static void wczytaj_z_pliku(){
        pracownicy = new Pracownik[6];
        try{
            FileInputStream fileout = new FileInputStream("pracownicy.txt");
            ObjectInputStream objout = new ObjectInputStream(fileout);

            int liczba_pracowników = objout.readInt();
            System.out.println("Liczba pracownikow "+liczba_pracowników);

            for(int i = 0; i<liczba_pracowników;i++){
                Pracownik pracownik = (Pracownik) objout.readObject();
                if (pracownik instanceof PracownikEtatowy){
                    pracownicy[5-i] = (PracownikEtatowy) pracownik;
                }
                else if (pracownik instanceof PracownikGodzinowy)
                {
                    pracownicy[5-i] = (PracownikGodzinowy) pracownik;
                }
                
            }
            System.out.println("----------- Pomyslnie wczytano z pliku -----------");
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }

    public static void pokaz_menu(){
        System.out.println("Menu:");
        System.out.println("\t1 - stworz pracownikow");
        System.out.println("\t2 - wyswietl pracowników wyswietl");
        System.out.println("\t3 - zapisz pracownikow do pliku");
        System.out.println("\t4 - wczytaj pracownikow z pliku");
        // System.out.println("\t5 - stworz pracownika");
        System.out.println("\t5 - wyswietl pracowników v2");
        System.out.println("\t6 - wpisz pracownikow do danych");

    }

    public static void zapisz_do_pliku(){
        try{
            FileOutputStream fileout = new FileOutputStream("pracownicy.txt");
            ObjectOutputStream objout = new ObjectOutputStream(fileout);

            PracownikIterator<Pracownik> pracownicyiterator = new PracownikIterator<Pracownik>(pracownicy);
            objout.writeInt(pracownicy.length);

            while(pracownicyiterator.hasNext()){
                objout.writeObject(pracownicyiterator.next());
                
            }
            System.out.println("----------- Pomyslnie zapisno do pliku -----------");

        }
        catch (Exception e){
            e.getStackTrace();
        }


    }
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        pokaz_menu();
        int wybor = 1;

        try{
            wybor = scan.nextInt();
            while(wybor>0 && wybor <7){
                switch(wybor){
                    case 1: stworz_pracownikow(); break;
                    case 2: wystwietl_pracownikow_wyswietl(); break;
                    case 3: zapisz_do_pliku(); break;
                    case 4: wczytaj_z_pliku(); break;
                    case 5: wystwietl_pracownikow(); break;
                    case 6: wpisz_pracowników(); break;
                    default: break;
                }
                wybor = scan.nextInt();
            }
        }catch(InputMismatchException ex ){
            System.out.println("Wystąpił błąd");
        }

    }
}