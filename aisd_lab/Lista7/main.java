package Lista7;

import java.lang.reflect.Array;
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
    private static ArrayList<Integer> mergeSort = new ArrayList<>();

    public static void createLists(){
        for(int x= 0;x < iloscLiczb; x++){
            Random rand = new Random();
            int liczba = rand.nextInt(0,20000);
            insertList.add(liczba);
            bubbleList.add(liczba);
            selectList.add(liczba);
            quickList.add(liczba);
            heapList.add(liczba);
            mergeSort.add(liczba);
        }
    }


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
    
    public static void quickSort(){}

    public static void heapSort(){}

    public static void mergeSort(){}



    public static void main(String[] args){
        System.out.println("--Pomiar  czasu odcywa się w nanosekundach 10^9 nanosekund to 1 sekunda--");
        createLists();
        bubbleSort();
        insertSort();

        selectSort();

        // for(int x: insertList){
        //     System.out.println(x);
        // }

    }
    
}
