package asid_cwiki.Lista5;

import java.util.ArrayList;
import java.util.Collections;

public class zad3 {

    public static int[] bubbleSort(ArrayList<Integer> bubbleList){ 
        int liczbaPorownan = 0;
        int liczbaPrzypisan = 0;

        int iloscLiczb = bubbleList.size();
        for(int i = 0;i < iloscLiczb - 1; i++ ){
            for(int x = 0; x < iloscLiczb -(1+i);x++ ){
                if(bubbleList.get(x)> bubbleList.get(x+1)){
                    int tmp = bubbleList.get(x);
                    bubbleList.set(x, bubbleList.get(x+1));
                    bubbleList.set(x+1, tmp);
                    liczbaPrzypisan +=3;
                }
                liczbaPorownan += 1;
            }
        }
        int[] result = {liczbaPorownan, liczbaPrzypisan};
        return result;  
    }

    public static int[] insertSort(ArrayList<Integer> insertList){
        int liczbaPorownan = 0;
        int liczbaPrzypisan = 0;
        int iloscLiczb = insertList.size();
        for(int x = 1; x < iloscLiczb; x++){
            for(int i = 0; i < x; i++){
                if(insertList.get(x) < insertList.get(i)){
                    insertList.add(i,insertList.remove(x));
                    liczbaPrzypisan +=2;
                    break;
                }
                liczbaPorownan +=1;
            }
        }

        int[] result = {liczbaPorownan, liczbaPrzypisan};
        return result;  
    }
}
