package Lista8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {

    public int[] k5;
    public int[] k10;
    public int[] k50;
    public int[] k100;

    public void stworzListy(){
        Random rand = new Random();
        k5 = new int[5000];
        k10 = new int[10000];
        k50 = new int[50000];
        k100 = new int[100000];

        for(int x = 0; x < 5000; x++){
            k5[x] = rand.nextInt(0,5000);
        }
        for(int x = 0; x < 10000; x++){
            k10[x] = rand.nextInt(0,10000);
        }
        for(int x = 0; x < 50000; x++){
            k50[x] = rand.nextInt(0,50000);
        }
        for(int x = 0; x < 100000; x++){
            k100[x] = rand.nextInt(0,100000);
        }
    }


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
        int tmp = 0;            
        tmp = (3*tmp)+1;
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

    public Integer[] stworzCiagE(int limit){
        ArrayList<Integer> tmpTable = new ArrayList<>();
        tmpTable.add(limit/2);
        int tmp = (int)(0.75*limit/2);
        while(tmp > 1){
            tmpTable.add(tmp);
            tmp = (int)(0.75*tmp);
        }
        tmpTable.add(1);
        Collections.reverse(tmpTable);

        return tmpTable.toArray(new Integer[tmpTable.size()]);
    }

    public void Wersja1(){
        int limit = 5000;
        int[] oryginal = k5;
        int[] tablica = new int[oryginal.length];

        System.out.println("Werjsa 1");
        System.out.println("Czasy odpowiednio dla podpunktów a,b,c,d,e");

        System.out.println("Dla 5000");

        Integer[] odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        long timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagE(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        System.out.println("Dla 10000");
        limit = 10000;

        oryginal = k10;
        tablica = new int[oryginal.length];

        odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagE(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);


        System.out.println("Dla 50000");
        limit = 50000;

        oryginal = k50;
        tablica = new int[oryginal.length];

        odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagE(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        System.out.println("Dla 100000");
        limit = 100000;

        oryginal = k100;
        tablica = new int[oryginal.length];

        odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagE(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja1.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

    }
    
    public void Wersja2(){
        int limit = 5000;
        int[] oryginal = k5;
        int[] tablica = new int[oryginal.length];

        System.out.println("Werjsa 2");
        System.out.println("Czasy odpowiednio dla podpunktów a,b,c,d");

        System.out.println("Dla 5000");

        Integer[] odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        long timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagE(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        System.out.println("Dla 10000");
        limit = 10000;

        oryginal = k10;
        tablica = new int[oryginal.length];

        odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        System.out.println("Dla 50000");
        limit = 50000;

        oryginal = k50;
        tablica = new int[oryginal.length];

        odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagE(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);


        System.out.println("Dla 100000");
        limit = 100000;

        oryginal = k100;
        tablica = new int[oryginal.length];

        odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagE(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja2.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

    }

    public void Wersja3(){
        int limit = 5000;
        int[] oryginal = k5;
        int[] tablica = new int[oryginal.length];

        System.out.println("Werjsa 3");
        System.out.println("Czasy odpowiednio dla podpunktów a,b,c,d");

        System.out.println("Dla 5000");

        Integer[] odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        long timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagE(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);


        System.out.println("Dla 10000");
        limit = 10000;

        oryginal = k10;
        tablica = new int[oryginal.length];

        odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagE(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);


        System.out.println("Dla 50000");
        limit = 50000;

        oryginal = k50;
        tablica = new int[oryginal.length];

        odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagE(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);


        System.out.println("Dla 100000");
        limit = 100000;

        oryginal = k100;
        tablica = new int[oryginal.length];

        odstep = stworzCiagA(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        
        timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagB(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagC(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();

        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagD(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

        odstep = stworzCiagE(limit);
        System.arraycopy(oryginal, 0, tablica, 0, limit);
        timer = System.nanoTime();
        Wersja3.execute(odstep, tablica);
        System.out.println((System.nanoTime()-timer)/1000000.0);

    }

    public static void main(String[] args){
        Main m = new Main();
        m.stworzListy();
        m.Wersja1();
        m.Wersja2();
        m.Wersja3();
    }
}
