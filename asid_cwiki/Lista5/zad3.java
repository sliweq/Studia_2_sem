package asid_cwiki.Lista5;

import java.util.Random;

public class zad3 {
    static private int quickSortPrzypisania;
    static private int quickSortPorownania;

    private static Integer[] array; 

    public static void stworzlisty(){
        quickSortPrzypisania = 0;
        quickSortPorownania = 0;
        array = new Integer[1000];
        Random rand = new Random();
        for(int x = 0; x < array.length;x++){
            array[x] = rand.nextInt(0,array.length);
        }

        //Quick sort

        System.out.println("Quicksort");
        Integer[] arrayCopy = new Integer[array.length];

        System.arraycopy(array, 0, arrayCopy, 0, array.length);
        quickSort(arrayCopy, 0, array.length-1);

        System.out.println("Kolejnośc losowa");
        System.out.println("Liczba prownan: " + quickSortPorownania);
        System.out.println("Liczba przypisan: " + quickSortPrzypisania);

        quickSortPrzypisania = 0; 
        quickSortPorownania = 0; 
        quickSort(arrayCopy, 0, array.length-1);

        System.out.println("Kolejnośc posortowana");
        System.out.println("Liczba prownan: " + quickSortPorownania);
        System.out.println("Liczba przypisan: " + quickSortPrzypisania);

        quickSortPrzypisania = 0; 
        quickSortPorownania = 0; 

        arrayCopy = reverse(arrayCopy);
        quickSort(arrayCopy, 0, array.length-1);

        System.out.println("Kolejnośc odwrotna");
        System.out.println("Liczba prownan: " + quickSortPorownania);
        System.out.println("Liczba przypisan: " + quickSortPrzypisania);

        quickSortPrzypisania = 0; 
        quickSortPorownania = 0; 

        sameElements(arrayCopy);
        quickSort(arrayCopy, 0, array.length-1);

        System.out.println("Te same liczby");
        System.out.println("Liczba prownan: " + quickSortPorownania);
        System.out.println("Liczba przypisan: " + quickSortPrzypisania);

        //Bubble sort
        System.out.println("Bubble Sort");

        System.arraycopy(array, 0, arrayCopy, 0, array.length);
        int[] tmp = bubbleSort(arrayCopy);


        System.out.println("Kolejnośc losowa");
        System.out.println("Liczba prownan: " + tmp[0]);
        System.out.println("Liczba przypisan: " + tmp[1]);

        tmp = bubbleSort(arrayCopy);


        System.out.println("Kolejnośc posortowana");
        System.out.println("Liczba prownan: " + tmp[0]);
        System.out.println("Liczba przypisan: " + tmp[1]);


        arrayCopy = reverse(arrayCopy);
        tmp = bubbleSort(arrayCopy);
        

        System.out.println("Kolejnośc odwrotna");
        System.out.println("Liczba prownan: " + tmp[0]);
        System.out.println("Liczba przypisan: " + tmp[1]);

        sameElements(arrayCopy);
        tmp = bubbleSort(arrayCopy);

        System.out.println("Te same liczby");
        System.out.println("Liczba prownan: " + tmp[0]);
        System.out.println("Liczba przypisan: " + tmp[1]);

        //Insert Sort

        System.out.println("Insert Sort");

        System.arraycopy(array, 0, arrayCopy, 0, array.length);
        tmp = insertSort(arrayCopy);


        System.out.println("Kolejnośc losowa");
        System.out.println("Liczba prownan: " + tmp[0]);
        System.out.println("Liczba przypisan: " + tmp[1]);

        tmp = insertSort(arrayCopy);


        System.out.println("Kolejnośc posortowana");
        System.out.println("Liczba prownan: " + tmp[0]);
        System.out.println("Liczba przypisan: " + tmp[1]);


        arrayCopy = reverse(arrayCopy);
        tmp = insertSort(arrayCopy);
        

        System.out.println("Kolejnośc odwrotna");
        System.out.println("Liczba prownan: " + tmp[0]);
        System.out.println("Liczba przypisan: " + tmp[1]);

        sameElements(arrayCopy);

        System.out.println("Liczba prownan: " + tmp[0]);
        tmp = insertSort(arrayCopy);


        System.out.println("Te same liczby");
        System.out.println("Liczba prownan: " + tmp[0]);
        System.out.println("Liczba przypisan: " + tmp[1]);
    

    
    }

    public static void sameElements(Integer[] array){
        for(int x = 0; x< array.length;x ++){
            array[x] = 0;
        }
    }

    public static Integer[] reverse(Integer[] array){
        Integer[] tmp = new Integer[array.length];
        for(int x = 0; x< array.length;x ++){            
            tmp[x] = array[array.length-(1+x)];
        }
        return tmp;
    }

    public static int[] bubbleSort(Integer[] bubbleList){ 
        quickSortPrzypisania = 0;
        int liczbaPorownan = 0;

        int iloscLiczb = bubbleList.length;
        for(int i = 0;i < iloscLiczb - 1; i++ ){
            for(int x = 0; x < iloscLiczb -(1+i);x++ ){
                if(bubbleList[x]> bubbleList[x+1]){
                    swap(bubbleList, x, x+1);
                }
                liczbaPorownan += 1;
            }
        }
        int[] result = {liczbaPorownan, quickSortPrzypisania};
        return result;  
    }

    public static int[] insertSort(Integer[] insertList){
        quickSortPrzypisania = 0;
        int liczbaPorownan = 0;
        int iloscLiczb = insertList.length;
        for(int x = 1; x < iloscLiczb; x++){
            for(int i = x-1; i > 0 ; i--){
                liczbaPorownan +=1;
                if(insertList[x] < insertList[i]){
                    swap(insertList, x, i);
                }
                else{
                    break;
                }
            }
        }
        int[] result = {liczbaPorownan, quickSortPrzypisania};
        return result;  
    }

    private static void quickSort(Integer[] array, int start, int end){
        if(end-start> 0){
            int partition = partition(array, start, end);
            quickSortPrzypisania +=1; 
            quickSort(array, start, partition-1);
            quickSort(array, partition + 1, end);

        }
        quickSortPorownania += 1;
    }

    private static int partition(Integer[] array, int start, int end){
        Random rand = new Random();
        int tmp = rand.nextInt(start,end+1);
        quickSortPrzypisania +=1;
        swap(array,tmp, end);
        int lower = start;
        int bigger = end;
        quickSortPrzypisania +=2;
 
        while(bigger>=lower){
            quickSortPorownania +=1;
            if(array[bigger] >= array[end]){
                bigger-=1;
            }else{
                swap(array, lower, bigger);
                lower+=1;
            }
            quickSortPorownania+=1;
        }
        quickSortPorownania +=1;
        
        swap(array, bigger+1, end);
        return bigger+1; 
    }

    private static void swap(Integer[] array, int indOne, int indTwo){
        int tmp = array[indOne];
        array[indOne] = array[indTwo];
        array[indTwo] =tmp;
        quickSortPrzypisania+=3;
    }

    public static void main(String[] args){
        stworzlisty();
    }
}
