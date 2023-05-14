package Lista8;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public int[] stworzListe(int limit){
        int[] tmp = new int[limit];

        Random rand = new Random();

        for(int x = 0; x < limit; x++){
            tmp[x] = rand.nextInt(0,limit+1);
        }
        return tmp;
    }

    //Tworzenie dla poprawnego shella
    public Integer[] stworzCiagA(int limit){
        ArrayList<Integer> tmpTable = new ArrayList<>();
        int tmp = 0;            tmp = (3*tmp)+1;
        while(tmp< limit){
            tmpTable.add(tmp);  
            tmp = (3*tmp)+1;
        }
        return tmpTable.toArray(new Integer[tmpTable.size()]);
     
    }

    public Integer[] stworzCiagB(int limit){
        ArrayList<Integer> tmpTable = new ArrayList<>();
        int tmp = 1;            
        int licznik = 1;
        tmp =((int)(Math.pow(2,licznik )))-1;
        while(tmp< limit){
            tmpTable.add(tmp);  
            licznik +=1;
            tmp =((int)(Math.pow(2,licznik)))-1;
        }
        return tmpTable.toArray(new Integer[tmpTable.size()]);
    }
    
    public Integer[] stworzCiagC(int limit){
        ArrayList<Integer> tmpTable = new ArrayList<>();
        int tmp = 1;            
        int licznik = 0;
        while(tmp< limit){
            tmpTable.add(tmp);  
            licznik +=1;
            tmp =((int)(Math.pow(2,licznik)))+1;
        }
        return tmpTable.toArray(new Integer[tmpTable.size()]);
    }

    public Integer[] stworzCiagD(int limit){
        ArrayList<Integer> tmpTable = new ArrayList<>();
        int tmpOne = 1;
        int tmpTwo = 1;
        int tmpAdd = 1;

        tmpTable.add(tmpAdd);
        tmpAdd = tmpOne + tmpTwo;
        tmpTable.add(tmpAdd);
        tmpOne = tmpTwo;
        tmpTwo = tmpAdd;
        tmpAdd = tmpOne + tmpTwo;

        while(tmpAdd< limit){
            tmpTable.add(tmpAdd);
            tmpOne = tmpTwo;
            tmpTwo = tmpAdd;
            tmpAdd = tmpOne + tmpTwo;
        }

        return tmpTable.toArray(new Integer[tmpTable.size()]);
    }

    public void Wersja1(){
        int limit = 5000;
        int[] oryginal = stworzListe(limit);
        int[] tablica = new int[oryginal.length];

        System.out.println("Werjsa 1");
        System.out.println("Czasy odpowiednio dla podpunktów a,b,c,d");

        System.out.println("Dla 5000");

        Integer[] odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        long timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        System.out.println("Dla 10000");
        limit = 10000;

        oryginal = stworzListe(limit);
        tablica = new int[oryginal.length];

        odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        System.out.println("Dla 50000");
        limit = 50000;

        oryginal = stworzListe(limit);
        tablica = new int[oryginal.length];

        odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        System.out.println("Dla 100000");
        limit = 100000;

        oryginal = stworzListe(limit);
        tablica = new int[oryginal.length];

        odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);


    }
    
    public void Wersja2(){
        int limit = 5000;
        int[] oryginal = stworzListe(limit);
        int[] tablica = new int[oryginal.length];

        System.out.println("Werjsa 2");
        System.out.println("Czasy odpowiednio dla podpunktów a,b,c,d");

        System.out.println("Dla 5000");

        Integer[] odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        long timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        System.out.println("Dla 10000");
        limit = 10000;

        oryginal = stworzListe(limit);
        tablica = new int[oryginal.length];

        odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        System.out.println("Dla 50000");
        limit = 50000;

        oryginal = stworzListe(limit);
        tablica = new int[oryginal.length];

        odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        System.out.println("Dla 100000");
        limit = 100000;

        oryginal = stworzListe(limit);
        tablica = new int[oryginal.length];

        odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);
    }

    public void Wersja3(){
        int limit = 5000;
        int[] oryginal = stworzListe(limit);
        int[] tablica = new int[oryginal.length];

        System.out.println("Werjsa 3");
        System.out.println("Czasy odpowiednio dla podpunktów a,b,c,d");

        System.out.println("Dla 5000");

        Integer[] odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        long timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        System.out.println("Dla 10000");
        limit = 10000;

        oryginal = stworzListe(limit);
        tablica = new int[oryginal.length];

        odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        System.out.println("Dla 50000");
        limit = 50000;

        oryginal = stworzListe(limit);
        tablica = new int[oryginal.length];

        odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        System.out.println("Dla 100000");
        limit = 100000;

        oryginal = stworzListe(limit);
        tablica = new int[oryginal.length];

        odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000000.0);
    }

    public static void main(String[] args){
        Main m = new Main();
        m.Wersja1();
        m.Wersja2();
        m.Wersja3();

    }

}
