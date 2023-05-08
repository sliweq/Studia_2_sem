package asid_cwiki.Lista5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class zad1 {
    private static int max = 10000;

    private static ArrayList<Integer> L = new ArrayList<>();

    private static int numberOfBuckets = 100;
    private static ArrayList<Integer>[] buckets = new ArrayList[numberOfBuckets];

    public static void createList(){
        Random rand = new Random();

        for(int x = 0; x<max;x++){
            L.add(Integer.valueOf(rand.nextInt(0,max)));   
        }

        for(int x: L){
            if(buckets[(int) (x/numberOfBuckets) ] == null){
                buckets[(int) (x/numberOfBuckets) ] = new ArrayList<>();
            }
            buckets[(int) (x/numberOfBuckets) ].add(Integer.valueOf(x));
        }

        for(ArrayList<Integer> x: buckets){
            heapSor(x);
        }

        L.clear();

        for(ArrayList<Integer> x: buckets){
            for(Integer i: x){
                L.add(i);
            }   
        }

    }

    public static void heapSor(ArrayList<Integer> lista){
        int iloscLiczb = lista.size();
        for (int x=(iloscLiczb-1)/2; x>=0; x--){
            fixHeap(lista, x, iloscLiczb);
        }

        for (int i = iloscLiczb-1; i>0; i--){
            if(i != 0){
                Integer tmp = lista.get(i);
                lista.set(i, lista.get(0));
                lista.set(0, tmp);
            }
            fixHeap(lista, 0, i);
        }

    }

    public static void fixHeap(ArrayList<Integer> lista, int korzen, int ostatni){
        int dziecko = 2*korzen+1;
        if(dziecko<ostatni){
            if(dziecko+1 < ostatni && lista.get(dziecko) < lista.get(dziecko+1)){
                dziecko +=1;
            }
            if(lista.get(korzen) <  lista.get(dziecko)){
                if(korzen != dziecko){
                    Integer tmp = lista.get(korzen);
                    lista.set(korzen, lista.get(dziecko));
                    lista.set(dziecko, tmp);
                }
                fixHeap(lista, dziecko, ostatni);
            }
        }
    }

    public static void main(String[] args) {
        createList();
    }

    
    
}
