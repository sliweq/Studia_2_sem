package Lista5;

import java.io.*;
import java.util.Scanner;

public class Main {

    ListStack<Pracownik> parking = new ListStack<>();
    Baza<Pracownik> bazaDanych = new Baza<>();

    public void stworzPracownikow(){
        Pracownik[] pracownicy = new Pracownik[6];
        pracownicy[0] = new Pracownik(11111111111l,"Jan Mucha","10.05.1981","ksiegowy",6000,15);
        pracownicy[1] = new Pracownik(12345678910l,"Iwona Piec","13.04.1979","dyrektor",9200,13);
        pracownicy[2] = new Pracownik(81091234111l,"Janusz Gruszka","20.06.1967","goniec",7100,25);
        pracownicy[3] = new Pracownik(99214581811l,"Julia Cebula","19.05.1986","kierowca",5500,9);
        pracownicy[4] = new Pracownik(99918746519l,"Tomasz Polak","01.01.1971","ksiegowy",7000,19);
        pracownicy[5] = new Pracownik(68910981778l,"Anna Lis","14.08.1999","sekretarka",4000,4);

        for(int i = 0; i < 6; i++ ){
            if(bazaDanych.size() == 0){
                bazaDanych.add(pracownicy[i]);
                continue;
            }
            boolean added = false;
            int wielkoscBazy = bazaDanych.size();
            for(int x= 0; x< wielkoscBazy; x++){
                if(bazaDanych.get(x).getPesel() > pracownicy[i].getPesel()) {
                    bazaDanych.add(x, pracownicy[i]);
                    added = true;
                    break;
                }
            }
            if(!added){
                bazaDanych.add(pracownicy[i]);
            }
        }
        System.out.println("Stworzono domyslnych pracowników");
    }

    public void dodajPracownika(Pracownik prac){
        boolean added = false;
        int wielkoscBazy = bazaDanych.size();
        for(int x= 0; x< wielkoscBazy; x++){
            if(bazaDanych.get(x).getPesel() > prac.getPesel()){
                bazaDanych.add(x,prac);
                added = true;
                break;
            }
        }
        if(!added){
            bazaDanych.add(prac);
        }
        System.out.println("Dodano pomyślnie pracownika");
    }

    public Pracownik stworzPracownika(){
        try{
            Scanner scan = new Scanner(System.in);
            System.out.println("Podaj kolejno: Imie i Nazwisko, datę urodzenia, stanowisko, pesel, pensje, staz");
            String imie = scan.nextLine();
            String data = scan.nextLine();
            String stanowisko = scan.nextLine();
            long pesel = scan.nextLong();
            int pensja = scan.nextInt();
            int staz = scan.nextInt();
            System.out.println("Stworzono pomyślnie pracownika");
            return new Pracownik(pesel,imie,data,stanowisko,pensja,staz);

        }catch (Exception ex){
            System.out.println("Stworzono domyślnego pracownika");
            return new Pracownik();
        }
    }

    public void wyswietlWszystkich(){
        for(int x= 0; x< bazaDanych.size(); x++){
            System.out.println(bazaDanych.get(x).toString());
            
        }
    }

    public void wyswietlPracownika(){
        try{
            Scanner scan = new Scanner(System.in);
            System.out.println("podaj index pracownika którego chcesz zobaczyć");
            int index = scan.nextInt();

            System.out.println(bazaDanych.get(index).toString());

        }
        catch (Exception ex){
            System.out.println("Nie ma takiego pracownika");
            ex.printStackTrace();
        }
    }

    public void usunPracownika(){
        try{
            Scanner scan = new Scanner(System.in);
            System.out.println("podaj index pracownika którego chcesz usunąć");
            int index = scan.nextInt();

            bazaDanych.remove(index);

        }
        catch (Exception ex){

        }
    }
    public void obliczSredniaPensje(){
        if(bazaDanych.size() !=0){
            double srednia = 0.0;

            for(int x= 0; x< bazaDanych.size(); x++){
                srednia += bazaDanych.get(x).getPensja();
            }
            srednia = srednia /bazaDanych.size();
            System.out.println("Srednia pensja w firmie: " + srednia);
            System.out.println("Osoby zarabiające ponizej sredniej:");
            for(int x= 0; x< bazaDanych.size(); x++){
                if((double) bazaDanych.get(x).getPensja() < srednia){
                    System.out.println(bazaDanych.get(x).toString());

                }
            }
        }
    }

    public void odczytBazyZPliku(){
        try{
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Baza.ser"));
            bazaDanych = (Baza<Pracownik>) inputStream.readObject();
            System.out.println("Pomyślnie odczytano baze danych");


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void zapiszBazeDoPliku(){
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Baza.ser"));
            outputStream.writeObject(bazaDanych);
            System.out.println("Pomyślnie zapisano do bazy danych");

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void zaktualizujDanePracownika(){
        System.out.println("wybierz index pracownika którego chcesz edytować");
        Scanner scan = new Scanner(System.in);

        try{
            int index = scan.nextInt();
            System.out.println("Wybor aktualizacji: " +
                        "1- pesel" +
                        " 2- imie i nazwisko" +
                        "\n3- data urodzenia" +
                        " 4- stanowisko" +
                        "\n5- pensja" +
                        " 6- staz");
            
            int wybor = 1;
            wybor = scan.nextInt();
            while(wybor>=1 && wybor <=6){
                if(wybor > 4){
                    int dane = scan.nextInt();
                    switch (wybor){
                        case 5: bazaDanych.get(index).setPensja(dane); break;
                        case 6: bazaDanych.get(index).setStaz(dane); break;
                    }

                }else if(wybor > 1){
                    scan.nextLine();
                    String dane = scan.nextLine();
                    switch (wybor){
                        case 4: bazaDanych.get(index).setStanowisko(dane); break;
                        case 3: bazaDanych.get(index).setDataUrodzenia(dane); break;
                        case 2: bazaDanych.get(index).setImieNazwisko(dane); ;break;
                    }

                }else{
                    long pesel = scan.nextLong();
                    bazaDanych.get(index).setPesel(pesel);
                }
                System.out.println("Zaaktualizowano podne dane, które dane chcesz zaaktualizować");
                //scan.nextLine();
                wybor = scan.nextInt();
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void pokazMenu(){
        System.out.println("Menu wyboru:");
        System.out.println("1- Odczytaj bazę danych z pliku");
        System.out.println("2- Zapisz domyslnych pracownikow do bazy");
        System.out.println("3- Dodaj nowego pracownika do bazy");
        System.out.println("4- Wyswietl podanego pracownika");
        System.out.println("5- Wyswietl wszystkich pracownikow");
        System.out.println("6- Usun podanego pracownka z bazy dancyh");
        System.out.println("7- Oblicz srednia prensje w firmie oraz wyswietl kto zarabia ponizej srednia");
        System.out.println("8- Zapisz baze do pliku");
        System.out.println("9- Zmien dane wybranego pracownika");
        System.out.println("------Opcje prakingu------");
        System.out.println("10- Zaparkuj nowym samochodem");
        System.out.println("11- Pokaz parking");
        System.out.println("12- Wyjazd podanego pracownika");
    }

    public void wjazdPracownika(){
    
        try{
            Scanner scan = new Scanner(System.in);
            System.out.println("podaj index pracownika który chce zaparkować");
            int index = scan.nextInt();

            while(index < 0 || index > bazaDanych.size()){
                System.out.println("Podaj odpowiedni index");
                index = scan.nextInt();
            }

            if(parking.size()!=0){
                ListStack<Pracownik> tmpParking = new ListStack<>();
                boolean czyJest = false;
                while(!parking.isEmpty() && !czyJest){
                    Pracownik tmpPracownik = parking.pop();
                    if(tmpPracownik.getPesel() == bazaDanych.get(index).getPesel()){
                        System.out.println("Pracownik już dawno zaparkował");
                        czyJest = true;
                        tmpParking.push(tmpPracownik);
                        break;
                    }
                    tmpParking.push(tmpPracownik);
                }

                while(!tmpParking.isEmpty()){
                    parking.push(tmpParking.pop());
                }

                if(!czyJest){
                    parking.push(bazaDanych.get(index));
                    System.out.println("Pracownik zaparkował pomyślnie");

                }
            }
            else{
                parking.push(bazaDanych.get(index));
                System.out.println("Pracownik zaparkował pomyślnie");
            }   
        }
        catch (Exception ex){
            System.out.println("Blad prakowania");
            ex.printStackTrace();
        }

    } 

    public void pokazParking() throws EmptyStackException, FullStackException{
        ListStack<Pracownik> tmpParking = new ListStack<>();
        System.out.println("Najbliżej wjazdu:");

        while(!parking.isEmpty()){
            Pracownik prac = parking.pop();
            System.out.println(prac.toString());
            tmpParking.push(prac);
        }

        while(!tmpParking.isEmpty()){
            Pracownik prac = tmpParking.pop();
            parking.push(prac);
        }

    }

    public void wyjazdPracownika(){
        try{
            if(parking.isEmpty()){
                System.out.println("Parking jest pusty nie ma kto wyjechać");
            }
            else{
                Scanner scan = new Scanner(System.in);
                System.out.println("podaj nr zaparkowanego auta które chce wyjechać");
                int index = scan.nextInt();

                while(index <= 0){
                    System.out.println("Podaj liczbę dodatnią");
                    index = scan.nextInt();
                }
                while(index > parking.size()){
                    System.out.println("Podaj mniejszą liczbę");
                    index = scan.nextInt();
                }
                
                ListStack<Pracownik> tmpParking = new ListStack<>();
                int ilsocAut = parking.size();
                for(int x= 0; x < (ilsocAut-index);x++){
                    Pracownik auto = parking.pop();
                    System.out.println("Proszę o wyjazd auta Pana/i " + auto.getImieNazwisko());
                    tmpParking.push(auto);
                }
                //prosze o wyjazd pani/pana
                System.out.println("Proszę o wyjazd auta Pana/i " + parking.pop().getImieNazwisko());

                while(!tmpParking.isEmpty()){
                    Pracownik prac = tmpParking.pop();
                    System.out.println("Proszę o wjazd auta nr: " +  prac.getImieNazwisko());
                    parking.push(prac);
                }

                System.out.println("Pracownik wyjechał pomyślnie");

            }

            
        }
        catch (Exception ex){
            System.out.println("Blad parkowania");
            ex.printStackTrace();
        }


    }

    public void start(){

        Scanner scan = new Scanner(System.in);
        int wybor = 1;

        try{
            pokazMenu();
            wybor = scan.nextInt();
            while(wybor>=1 && wybor <= 12){
                switch (wybor){
                    case 1: odczytBazyZPliku(); break;
                    case 2: stworzPracownikow(); break;
                    case 3: dodajPracownika(stworzPracownika()); break;
                    case 4: wyswietlPracownika(); break;
                    case 5: wyswietlWszystkich(); break;
                    case 6: usunPracownika(); break;
                    case 7: obliczSredniaPensje(); break;
                    case 8: zapiszBazeDoPliku(); break;
                    case 9: zaktualizujDanePracownika(); break;

                    case 10: wjazdPracownika(); break;
                    case 11: pokazParking(); break;
                    case 12: wyjazdPracownika(); break;
                }

                wybor = scan.nextInt();
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        

    }

    public static void main(String[] args) {
        Main m = new Main();
        //System.out.println("Working directory: " + System.getProperty("user.dir"));

        m.start();

    }
}