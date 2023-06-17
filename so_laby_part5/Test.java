package so_laby_part5;

import java.util.ArrayList;
import java.util.Random;

public class Test{
    private ArrayList<Cpu> arrayOfCPUs;
    private ArrayList<Process> array1 ;
    private ArrayList<Process> array2;
    private ArrayList<Process> array3;

    private int processesAmount = 50_000; // liczba zadan dla kazdego procka
    
    private int N = 50; //Liczba pracujących procesorów
    private int p = 90; // dany prog do wszystkich algorytmow, powyzej czgos
    private int r = 50; // minimalny prog do algorytmu 3
    private int z = 25; //ilosc prob w algorytmie 1  
    private int tik = 5; //tik do sprawdzania obciazenia;
    
    public Test(){
        arrayOfCPUs = new ArrayList<>();
        array1 = new ArrayList<>();
        array2 = new ArrayList<>();
        array3 = new ArrayList<>();
        
        createCPUs();
        createProcesses(); 

        Algo1 a = new Algo1(arrayOfCPUs, array1, z, p, tik);
        for(Cpu cpu: arrayOfCPUs){
            cpu.clearHistory();
        }
        Algo2 b = new Algo2(arrayOfCPUs, array2, p, tik);
        for(Cpu cpu: arrayOfCPUs){
            cpu.clearHistory();
        }
        Algo3 c = new Algo3(arrayOfCPUs, array3, p, r,tik);
    }



    private void createProcesses(){
        Random rand = new Random();

        for(int x = 0; x < processesAmount; x++){
            int i = rand.nextInt(0,N);
            double load = Math.round(rand.nextDouble(1,10)*100)/100;
            int length = rand.nextInt(1,100);
            array1.add(new Process(length, load, i));
            array2.add(new Process(length, load, i));
            array3.add(new Process(length, load, i));    
        }
    }

    private void createCPUs(){
        for(int x = 0;x< N; x++ ){
            arrayOfCPUs.add(new Cpu(x));
        }
    }
    
    public static void main(String[] args) {
        Test t = new Test();
    }
}