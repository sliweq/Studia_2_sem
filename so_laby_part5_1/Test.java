package so_laby_part5_1;

import java.util.ArrayList;
import java.util.Random;

public class Test {
    private ArrayList<Cpu> arrayOfCPUs;

    private int processesAmount = 1000; // liczba zadan dla kazdego procka
    
    private int N = 50; //Liczba pracujących procesorów
    private int p = 70; // dany prog do wszystkich algorytmow, powyzej czgos
    private int r = 25; // minimalny prog do algorytmu 3    
    private int z = 25; //ilosc prob w algorytmie 1  

    private int max_load = 20;
    private int min_load = 2;

    private int min_duration = 2 ;
    private int max_duration = 28;

    private int probability = 35;

    public Test(){
        Random rand = new Random();
        arrayOfCPUs = new ArrayList<>();
        for(int x = 0; x < N; x++){
            Cpu cpu = new Cpu(x);
            for(int i = 0; i < processesAmount; i++){
                cpu.addNewProcess(new Process(rand.nextInt(min_duration,max_duration), rand.nextInt(min_load,max_load), true) , 0);
            }
            arrayOfCPUs.add(cpu);
        }
        One o = new One(arrayOfCPUs, probability, p, z);

        for(Cpu cpu: arrayOfCPUs){
            cpu.resetCpu();
            //cpu.tmpMethod();
        }
        
        Two t = new Two(arrayOfCPUs, probability, p);

        for(Cpu cpu: arrayOfCPUs){
            cpu.resetCpu();
            //cpu.tmpMethod();

        }

        Last l = new Last(arrayOfCPUs, probability, p, r);



    }

    public static void main(String[] args) {
        Test t = new Test();
    }
}
