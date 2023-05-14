package asid_cwiki.Lista5;

import java.util.ArrayList;

import java.util.Random;

public class zad2 {

    static Integer[] array;

    private static void createLists(int amount){
        Random rand = new Random();
        array = new Integer[amount];

        for(int x = 0; x < amount; x++ ){
            array[x] = rand.nextInt(0,amount);
        }
    }
    
    
    private static void quickSort(Integer[] array, int start, int end){
        if(end-start> 0){
            int partition = partition(array, start, end);
            quickSort(array, start, partition-1);
            quickSort(array, partition + 1, end);

        }
    }

    private static int partition(Integer[] array, int start, int end){
        Random rand = new Random();
        int pivot = 0 ; // = rand.nextInt(start,end+1);

        if(end-start >=100){
            int tmp1 = rand.nextInt(start,end+1);
            int tmp2 = rand.nextInt(start,end+1);
            while(tmp2 == tmp1){
                tmp2= rand.nextInt(start,end+1);
            }
            int tmp3 = rand.nextInt(start,end+1);
            while(tmp3 == tmp1 || tmp3 == tmp1){
                tmp3 = rand.nextInt(start,end+1);
            }
            Integer[] tmpArray= {tmp1,tmp2,tmp3};
            quickSort(tmpArray, 0, 2);
            System.out.println(tmpArray[0]+" " + tmpArray[1]+" " + tmpArray[2]);
             
            pivot = tmpArray[1];
        }
        else{
            pivot = rand.nextInt(start,end+1);
        }

        swap(array,pivot, end);
        // high +1 to nowy pivot
        int lower = start;
        int bigger = end;
        while(bigger>=lower){
            if(array[bigger] >= array[end]){
                bigger-=1;
            }else{
                swap(array, lower, bigger);
                lower+=1;
            }
        }
        swap(array, bigger+1, end);
        return bigger+1; 
    }

    private static void swap(Integer[] array, int indOne, int indTwo){
        int tmp = array[indOne];
        array[indOne] = array[indTwo];
        array[indTwo] =tmp;
    }

    public static void main(String[] args) {
        createLists(1010);
        quickSort(array, 0, array.length-1);
    }   

    
}
