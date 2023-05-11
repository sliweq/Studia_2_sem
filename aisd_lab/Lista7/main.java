package Lista7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class main {
    private static int iloscLiczb = 1000  ;
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
        quicksort(quickList,0,iloscLiczb);

        System.out.println("\tRandomowa kolejność: " + (System.nanoTime()-timer));

        timer = System.nanoTime();
        quicksort(quickList,0,iloscLiczb);

        System.out.println("\tRosnąca kolejność: "+ (System.nanoTime()-timer));

        Collections.reverse(mergeList);

        timer = System.nanoTime();
        quicksort(quickList,0,iloscLiczb);

        System.out.println("\tMalejąca kolejność: "+(System.nanoTime()-timer));
    }
    
    private static int partQuick(ArrayList<Integer> array, int pierwszy, int ostatni)
    {
        Random random = new Random();
        int rand = pierwszy+random.nextInt(ostatni-pierwszy);
        
        int tmp = array.get(pierwszy);
        array.set(pierwszy, array.get(rand));
        array.set(rand, tmp);
        

        int value = array.get(pierwszy);
        int indexBigger = pierwszy+1, indexLower = ostatni-1;
        while (indexBigger < indexLower){
            while(indexBigger <= indexLower && array.get(indexBigger) <= value){
                indexBigger++;
            }
            while(array.get(indexLower) > value){
                indexLower--;
            }

            if(indexBigger < indexLower){
                tmp = array.get(indexBigger);
                array.set(indexBigger, array.get(indexLower));
                array.set(indexLower, tmp);
            }



        }
        tmp = array.get(pierwszy);
        array.set(pierwszy, array.get(indexLower));
        array.set(indexLower, tmp);        
        return indexLower;
    }

    private static void quicksort(ArrayList<Integer> array, int start, int koniec)
    {
        if (koniec-start > 1)
        {
            int split = partQuick(array, start, koniec);
            quicksort(array, start, split);
            quicksort(array, split+1, koniec);
        }
    }
    //heap sort

    public static void heapSortExecute(){
        System.out.println("heap sort:");

        long timer = System.nanoTime();
        heapSor(heapList); 

        System.out.println("\tRandomowa kolejność: " + (System.nanoTime()-timer));

        timer = System.nanoTime();
        heapSor(heapList);   

        System.out.println("\tRosnąca kolejność: "+ (System.nanoTime()-timer));

        Collections.reverse(heapList);

        timer = System.nanoTime();
        heapSor(heapList);   

        System.out.println("\tMalejąca kolejność: "+(System.nanoTime()-timer));

    }

    public static void heapSor(ArrayList<Integer> lista){
        for (int x=(iloscLiczb-1)/2; x>=0; x--){
            fixHeap(lista, x, iloscLiczb);
        }

        for(int i = iloscLiczb-1; i>0; i--){
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
        heapSortExecute();
    }
    
}
