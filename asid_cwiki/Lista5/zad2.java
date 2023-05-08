package asid_cwiki.Lista5;

import java.util.ArrayList;

import java.util.Random;

public class zad2 {
    
    public static ArrayList<Integer> quickSort(ArrayList<Integer> lista){
        Random rand = new Random();

        if(lista.size() > 100 ){
            int pivotIndex;
            int pivot1Index = rand.nextInt(0,lista.size());
            int pivot2Index = rand.nextInt(0,lista.size());
            while(pivot2Index == pivot1Index){
                pivot2Index = rand.nextInt(0,lista.size());
            }
            int pivot3Index = rand.nextInt(0,lista.size());
            while(pivot3Index == pivot1Index && pivot3Index == pivot2Index ){
                pivot3Index = rand.nextInt(0,lista.size());
            }   

            // ArrayList<Integer> tmp = new ArrayList<>();
            // tmp.add(lista.get(pivot1Index));
            // tmp.add(lista.get(pivot2Index));
            // tmp.add(lista.get(pivot3Index));
            // pivotIndex = lista.indexOf(Collections.max(tmp));

            if(lista.get(pivot3Index) >= lista.get(pivot1Index) && lista.get(pivot3Index) >= lista.get(pivot2Index)){
                if(lista.get(pivot1Index) >= lista.get(pivot2Index)){
                    pivotIndex = pivot2Index;
                }
                else{
                    pivotIndex = pivot1Index;
                }
            }
            else if(lista.get(pivot2Index) >= lista.get(pivot1Index) && lista.get(pivot2Index) >= lista.get(pivot3Index)){
                if(lista.get(pivot1Index) >= lista.get(pivot3Index)){
                    pivotIndex = pivot3Index;
                }
                else{
                    pivotIndex = pivot1Index;
                }
            }
            else{
                if(lista.get(pivot2Index) >= lista.get(pivot3Index)){
                    pivotIndex = pivot3Index;
                }
                else{
                    pivotIndex = pivot2Index;
                }
            }
            ArrayList<Integer> mniejsze = new ArrayList<>();
            Integer pivot;
        ArrayList<Integer> wieksze = new ArrayList<>();

        if(lista.size() == 1){
            pivot = lista.get(0);
            return lista;
            //return mergeQuick(mniejsze, pivot, wieksze);
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

        ArrayList<Integer> mniejsze = new ArrayList<>();
        if(lista.size() == 0){
            return lista;
        }
        int pivotIndex = rand.nextInt(0,lista.size());
        Integer pivot;
        ArrayList<Integer> wieksze = new ArrayList<>();

        if(lista.size() == 1){
            pivot = lista.get(0);
            return lista;
            //return mergeQuick(mniejsze, pivot, wieksze);
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
    
}
