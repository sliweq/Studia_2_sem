package asid_cwiki.Lista5;

import java.util.Random;

public class zad1 {
    private static int[] array;
    
    private static void createLists(int amount){
        Random rand = new Random();
        array = new int[amount];

        for(int x = 0; x < amount; x++ ){
            array[x] = rand.nextInt(0,amount);
        }
    }

    private static void bucketSort(int amountOfNumbers,int bucketsAmount){

        // zakładam że liczby są z przedziału od 0 do liczba elementów

        int section = (int) (amountOfNumbers/ bucketsAmount) ;

        createLists(amountOfNumbers);
        Integer[] arrayOfBucketsSize = new Integer[bucketsAmount];

        
        for(int x = 0; x < arrayOfBucketsSize.length; x++){
            arrayOfBucketsSize[x] = 0;
        }

        //ilości kubełków
        for(int x: array){
            arrayOfBucketsSize[(int)(x/section) ] += 1;
        }

        //tworzenie kubełków 
        Integer[][] buckets = new Integer[bucketsAmount][];
        for(int x = 0 ; bucketsAmount> x ; x++){
            buckets[x] = new Integer[arrayOfBucketsSize[x]];
        }

        //wkładanie do kubełków

        for(int x: array){
            int bucketNumber = (int)(x/section);
            int tmp = 0;
            while(buckets[bucketNumber][tmp]!=null){
                tmp+=1;
            }
            buckets[bucketNumber][tmp] = x;
        }

        // wszystko jest w kubełkach 

        for(int x = 0; x< buckets.length; x++){
            quickSort(buckets[x], 0, buckets[x].length-1);
        }

        int y = 0;
        while(y < array.length){
            for(int x = 0; x< buckets.length; x++){
                for(int numebr: buckets[x]){
                    array[y] = numebr;
                    y+=1;
                }
                
            }
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
        int tmp = rand.nextInt(start,end+1);
        swap(array,tmp, end);
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

    private static void prinArray(){
        for(int x: array){
            System.out.print(x + " ");
        }
    }

    // public static void main(String[] args) {
    //     bucketSort(100,10);
    //     prinArray();
    // }   

    public static void main(String[] args) {
        int x = 78;
        int y = 73;
        int z = 71;
        int p = 69;
        int w = 82;
        char a = (char)x;
        char b = (char)y;
        char c = (char)z;
        char e = (char)p;
        char f = (char)w;


        System.out.println(""+a+b+c+c+e+f);
    }      
    
}
