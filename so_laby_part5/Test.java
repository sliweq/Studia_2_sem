package so_laby_part5;

import java.util.ArrayList;
import java.util.Random;

public class Test{
    private ArrayList<Cpu> arrayOfCPUs;
    private ArrayList<Process> array1 ;
    private ArrayList<Process> array2;
    private ArrayList<Process> array3;

    private int processesAmount = 100_000;
    
    private int N = 75; //Liczba pracujaćy procesorów
    private int p; // dany prog do wszystkich algorytmow, powyzej czgos
    private int r; // minimalny prog do algorytmu 3
    private int z; //ilosc prob w algorytmie 1  
    
    public Test(){
        arrayOfCPUs = new ArrayList<>();
        array1 = new ArrayList<>();
        array2 = new ArrayList<>();
        array3 = new ArrayList<>();

        createProcesses();
        createCPUs();
        System.out.println();
    }

    private void createProcesses(){
        Random rand = new Random();
        for(int x = 0; x< processesAmount; x++){
            double load = Math.round(Math.random()*1000)/100.0;
            int length = rand.nextInt(5,26);
            array1.add(new Process(length,load));
            array2.add(new Process(length,load));     
            array3.add(new Process(length,load));
        }
    }

    private void createCPUs(){
        for(int x = 0; x < N; x++){
            arrayOfCPUs.add(new Cpu());
        }
    }

    
    public static void main(String[] args) {
        Test t = new Test();
    }
}