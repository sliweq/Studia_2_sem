package so_laby_part5;

import java.util.ArrayList;
import java.util.Random;

public class Algo1{
    private ArrayList<Cpu> arrayOfCpus;
    private ArrayList<Process> arrayOfProcesses;
    private int z; // pytanie sie ez razy z rzędu danego procesora
    private int p; // dane obciazenie
    private int migrationMeter;
    private int queryMeter;


    public Algo1(ArrayList<Cpu> arrayOfCpus, ArrayList<Process> arrayOfProcesses, int z, int p){
        this.arrayOfCpus = arrayOfCpus;
        this.arrayOfProcesses = arrayOfProcesses;
        this.p = p; 
        this.z = z;
        queryMeter = 0;
        migrationMeter = 0;

        startSimulation();

        System.out.println("Algorytm 1");
        System.out.println("Ilość zapytań: " + queryMeter);
        System.out.println("Ilość migracji: " + migrationMeter);


    }

    public void startSimulation(){
        while(arrayOfProcesses.size() != 0 || isCpusWorks()){
            if(arrayOfProcesses.size() == 0){
                updateCPUs();
            }
            else{
                Process process = arrayOfProcesses.remove(0);
                updateCPUs();
                allocateProcess(process);
            }
        }
        
    }

    public void allocateProcess(Process process){
        Random rand = new Random();
        int choosenCpu = process.getCpuNumber();
        ArrayList<Integer>  asked = new ArrayList<>();

        for(int x = 0; x < z; x++){
            int tmp = rand.nextInt(0,arrayOfCpus.size());
            while(tmp == choosenCpu || containsSomething(asked, tmp)){
                tmp = rand.nextInt(0,arrayOfCpus.size());
            }
            queryMeter+=1;
            if(arrayOfCpus.get(tmp).getCurrentLoad() < p ){
                choosenCpu = tmp;
                break;
            }
            asked.add(tmp);
        }   
        if(choosenCpu != process.getCpuNumber()){
            migrationMeter +=1;
        }
        arrayOfCpus.get(choosenCpu).addProcess(process);
    }

    public boolean isCpusWorks(){
        for(Cpu cpu: arrayOfCpus){
            if(cpu.getCurrentLoad() > 0){
                return true;
            }
        }
        return false;
    }

    public void updateCPUs(){
        for(Cpu cpu:arrayOfCpus){
            cpu.updateProcesses();
        }
    }   

    public boolean containsSomething(ArrayList<Integer>array, int x ){
        for(int tmp: array){
            if(x == tmp){
                return true;
            }
        }
        return false;
    }
}
