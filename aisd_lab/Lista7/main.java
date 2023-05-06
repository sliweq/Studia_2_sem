package Lista7;

import java.lang.reflect.Array;
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import so_laby_part3.Rand;

public class main {
    private static int iloscLiczb = 10_000;
    private static ArrayList<Integer> insertList = new ArrayList<>();
    private static ArrayList<Integer> bubbleList = new ArrayList<>();
    private static ArrayList<Integer> selectList = new ArrayList<>();
    private static ArrayList<Integer> quickList = new ArrayList<>();
    private static ArrayList<Integer> heapList = new ArrayList<>();
    private static ArrayList<Integer> mergeList = new ArrayList<>();

    public static void createLists(){
        for(int x= 0;x < iloscLiczb; x++){
            Random rand = new Random();
            int liczba = rand.nextInt(0,20000);
            insertList.add(liczba);
            bubbleList.add(liczba);
            selectList.add(liczba);
            quickList.add(liczba);
            heapList.add(liczba);
            mergeList.add(liczba);
        }
    }

    //insert sort

    public static void insertSort(){

        System.out.println("Insert sort:");
        long timer = System.nanoTime();

        for(int x = 1; x < iloscLiczb; x++){
            for(int i = 0; i < x; i++){
                if(insertList.get(x) < insertList.get(i)){
                    insertList.add(i,insertList.remove(x));
                    break;
                }
            }
        }

        System.out.println("\tRandomowa kolejność: " + (System.nanoTime()-timer));
        timer = System.nanoTime();

        for(int x = 1; x < iloscLiczb; x++){
            for(int i = 0; i < x; i++){
                if(insertList.get(x) < insertList.get(i)){
                    insertList.add(i,insertList.remove(x));
                    break;
                }
            }
        }

        System.out.println("\tRosnąca kolejność: "+ (System.nanoTime()-timer));
        Collections.reverse(bubbleList);
        timer = System.nanoTime();

        for(int x = 1; x < iloscLiczb; x++){
            for(int i = 0; i < x; i++){
                if(insertList.get(x) < insertList.get(i)){
                    insertList.add(i,insertList.remove(x));
                    break;
                }
            }
        }
        System.out.println("\tMalejąca kolejność: "+(System.nanoTime()-timer));

    }

    //bubble sort 

    public static void bubbleSort(){ 
        System.out.println("Bubble sort:");
        long timer = System.nanoTime();

        for(int i = 0;i < iloscLiczb - 1; i++ ){
            for(int x = 0; x < iloscLiczb -(1+i);x++ ){
                if(bubbleList.get(x)> bubbleList.get(x+1)){
                    int tmp = bubbleList.get(x);
                    bubbleList.set(x, bubbleList.get(x+1));
                    bubbleList.set(x+1, tmp);
                }
            }
        }
        System.out.println("\tRandomowa kolejność: " + (System.nanoTime()-timer));
        timer = System.nanoTime();
        for(int i = 0;i < iloscLiczb - 1; i++ ){
            for(int x = 0; x < iloscLiczb -(1+i);x++ ){
                if(bubbleList.get(x)> bubbleList.get(x+1)){
                    int tmp = bubbleList.get(x);
                    bubbleList.set(x, bubbleList.get(x+1));
                    bubbleList.set(x+1, tmp);
                }
            }
        }
        System.out.println("\tRosnąca kolejność: "+ (System.nanoTime()-timer));
        Collections.reverse(bubbleList);
        timer = System.nanoTime();
        for(int i = 0;i < iloscLiczb - 1; i++ ){
            for(int x = 0; x < iloscLiczb -(1+i);x++ ){
                if(bubbleList.get(x)> bubbleList.get(x+1)){
                    int tmp = bubbleList.get(x);
                    bubbleList.set(x, bubbleList.get(x+1));
                    bubbleList.set(x+1, tmp);
                }
            }
        }

        System.out.println("\tMalejąca kolejność: "+(System.nanoTime()-timer));
    }

    //select sort 

    public static void selectSort(){

        System.out.println("Select sort:");
        long timer = System.nanoTime();

        for(int i = 0; i < iloscLiczb; i++){
            int najmniejsza = i;
            for(int x = i; x < iloscLiczb; x++){
                if(selectList.get(najmniejsza) > selectList.get(x)){
                    najmniejsza = x;
                }
            }
            int tmp = selectList.get(i);
            selectList.set(i,selectList.get(najmniejsza));
            selectList.set(najmniejsza, tmp);
        }

        System.out.println("\tRandomowa kolejność: " + (System.nanoTime()-timer));
        timer = System.nanoTime();

        for(int i = 0; i < iloscLiczb; i++){
            int najmniejsza = i;
            for(int x = i; x < iloscLiczb; x++){
                if(selectList.get(najmniejsza) > selectList.get(x)){
                    najmniejsza = x;
                }
            }
            int tmp = selectList.get(i);
            selectList.set(i,selectList.get(najmniejsza));
            selectList.set(najmniejsza, tmp);
        }

        System.out.println("\tRosnąca kolejność: "+ (System.nanoTime()-timer));
        Collections.reverse(bubbleList);
        timer = System.nanoTime();

        for(int i = 0; i < iloscLiczb; i++){
            int najmniejsza = i;
            for(int x = i; x < iloscLiczb; x++){
                if(selectList.get(najmniejsza) > selectList.get(x)){
                    najmniejsza = x;
                }
            }
            int tmp = selectList.get(i);
            selectList.set(i,selectList.get(najmniejsza));
            selectList.set(najmniejsza, tmp);
        }
        System.out.println("\tMalejąca kolejność: "+(System.nanoTime()-timer));

    }

    //quick sort

    public static void quickSortExecute(){
        System.out.println("quick sort:");

        long timer = System.nanoTime();
        quickList = quickSort(quickList);

        System.out.println("\tRandomowa kolejność: " + (System.nanoTime()-timer));

        timer = System.nanoTime();
        quickList = quickSort(quickList);

        System.out.println("\tRosnąca kolejność: "+ (System.nanoTime()-timer));

        Collections.reverse(mergeList);

        timer = System.nanoTime();
        quickList = quickSort(quickList);

        System.out.println("\tMalejąca kolejność: "+(System.nanoTime()-timer));
    }
    
    public static ArrayList<Integer> quickSort(ArrayList<Integer> lista){
        Random rand = new Random();
        ArrayList<Integer> mniejsze = new ArrayList<>();
        if(lista.size() == 0){
            return lista;
        }
        int pivotIndex = rand.nextInt(0,lista.size());
        Integer pivot;
        ArrayList<Integer> wieksze = new ArrayList<>();

        if(lista.size() == 1){
            pivot = lista.get(0);
            return mergeQuick(mniejsze, pivot, wieksze);
        }
        
        pivot = lista.remove(pivotIndex);
        for(Integer x : lista){
            if(x > pivot){
                wieksze.add(x);
            }
            else if(x <= pivot){
                mniejsze.add(x);
            }
        }
        return mergeQuick(quickSort(mniejsze), pivot, quickSort(wieksze));
        
    }

    public static ArrayList<Integer> mergeQuick(ArrayList<Integer> mniejsze, Integer pivot, ArrayList<Integer> wieksze){
        ArrayList<Integer> tmpList = new ArrayList<>();

        for(Integer i: mniejsze){
            tmpList.add(i);
        }
        tmpList.add(pivot);

        for(Integer i: wieksze){
            tmpList.add(i);
        }

        return tmpList;
    }

    //heap sort

    public static void heapSort(){   

    }
    //fix heap od naprawiania kopca po zmianie
    //swap zmienia korzen na ostatni
    //build heap po prostu budowanie kopca 
    public static void fixHeap(ArrayList<Integer> lista, int ostatni){

        int korzen = 0;
        while((korzen*2)+1<lista.size()){
            if((korzen*2)+2< lista.size()){
                if(lista.get(korzen*2)+2 > lista.get(korzen)){
                    //zmiana
                }
            }
            else(lista.get(korzen*2)+1 < lista.get(korzen)){

            }
        } 


        // int korzen = pierwszy;
        // int dziecko1 = 2*pierwszy +1;
        // int dziecko2 = 2*pierwszy +2;

        // if(dziecko2<ostatni){
        //     if(lista.get(dziecko2)> lista.get(dziecko1)){
        //         if(lista.get(korzen) < lista.get(dziecko2)){
        //             korzen = dziecko2;
        //         }
        //     }
        //     else if(lista.get(korzen)< lista.get(dziecko1)){
        //         korzen = dziecko1;
        //     }
        // }
        // else if(dziecko1<ostatni && lista.get(korzen)< lista.get(dziecko1)){
        //     korzen = dziecko1;
        // }

        // if(korzen != pierwszy){
        //     Integer tmp = lista.get(pierwszy);
        //     lista.set(pierwszy, lista.get(korzen));
        //     lista.set(korzen, tmp);

        //     fixHeap(lista, korzen, ostatni);
        // }
    }


    public static int swap(ArrayList<Integer> lista, int koniec){
        Integer nowyKorzen = lista.get(koniec);
        lista.set(koniec, lista.get(0));
        lista.set(0, nowyKorzen);
        //fix after that
        return (koniec-1);
    }
    
    public static void buildHeap(){}
    //merge sort 

    public static void mergeSortExecute(){
        System.out.println("Merge sort:");

        long timer = System.nanoTime();
        mergeList = mergeSort(mergeList, 0, iloscLiczb-1);
        System.out.println("\tRandomowa kolejność: " + (System.nanoTime()-timer));

        timer = System.nanoTime();
        mergeList = mergeSort(mergeList, 0, iloscLiczb-1);
        System.out.println("\tRosnąca kolejność: "+ (System.nanoTime()-timer));

        Collections.reverse(mergeList);

        timer = System.nanoTime();
        mergeList = mergeSort(mergeList, 0, iloscLiczb-1);
        System.out.println("\tMalejąca kolejność: "+(System.nanoTime()-timer));

        
    }

    public static ArrayList<Integer> mergeSort(ArrayList<Integer> lista , int start, int end){
        if(start == end){
            ArrayList<Integer> l = new ArrayList<>();
            l.add(lista.get(start));
            return l;
        }

        int split = start + (end - start) / 2;

        return mergeMerge(mergeSort(lista,start, split), mergeSort(lista,split+1,end));

    }

    public static ArrayList<Integer> mergeMerge(ArrayList<Integer> l1, ArrayList<Integer> l2){
        ArrayList<Integer> tmpList = new ArrayList<>();
        while(l1.size() != 0 && l2.size()!=0){
            if(l1.get(0)>= l2.get(0)){
                tmpList.add(l2.remove(0));
                
            }
            else{
                tmpList.add(l1.remove(0));
            }
        }
        if(l1.size() == 0){
            while(l2.size() != 0){
                tmpList.add(l2.remove(0));
            }
        }
        else if(l2.size() == 0){
            while(l1.size() != 0){
                tmpList.add(l1.remove(0));
            }
        }
        return tmpList;
    }


    public static void main(String[] args){
        System.out.println("--Pomiar  czasu odcywa się w nanosekundach 10^9 nanosekund to 1 sekunda--");
        createLists();
        bubbleSort();
        insertSort();
        selectSort();
        mergeSortExecute();
        quickSortExecute();

        // System.out.println("Xdddd");
        // for(int x: quickList){
        //     System.out.println(x);
        // }

    }
    
}
