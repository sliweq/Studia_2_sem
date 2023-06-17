package so_laby_part5;

import java.util.ArrayList;
import java.util.Random;

import so_laby.proces;

public class Algo1{
    private ArrayList<Cpu> arrayOfCpus;
    private ArrayList<Process> arrayOfProcesses;
    private int z; // pytanie sie ez razy z rzędu danego procesora
    private int p; // dane obciazenie
    private int migrationMeter;
    private int queryMeter;
    private int tik;
    private int time;

    private ArrayList<Double> avgCpuLoad;
    private ArrayList<Double> avgSystemLoad;


    public Algo1(ArrayList<Cpu> arrayOfCpus, ArrayList<Process> arrayOfProcesses, int z, int p, int tik){
        this.arrayOfCpus = arrayOfCpus;
        this.arrayOfProcesses = arrayOfProcesses;
        this.p = p; 
        this.z = z;
        queryMeter = 0;
        migrationMeter = 0;
        this.tik = tik;
        time = 0;
        avgCpuLoad = new ArrayList<>();
        avgSystemLoad = new ArrayList<>();

        startSimulation();

        System.out.println("Algorytm 1");
        System.out.println("Ilość zapytań: " + queryMeter);
        System.out.println("Ilość migracji: " + migrationMeter);

        Double tmp = 0.0;
        for(Cpu cpu: arrayOfCpus){
            tmp +=cpu.getAvgLoad();
        }
        System.out.println("Średnie obciążenie procesorów: " +(tmp/arrayOfCpus.size()) );
        tmp = 0.0;
        for(Double doub: avgSystemLoad){
            tmp += doub;
        }
        System.out.println("Średnie obciążenie systemu: " +(tmp/avgSystemLoad.size()) );

        System.out.println("Odchylenie standardowe obc. systemu: " + standardDeviation((tmp/avgSystemLoad.size())));
    }

    public void startSimulation(){
        while(arrayOfProcesses.size() != 0 || isCpusWorks()){
            if(time == 1000){
                System.out.println("xddd");
            }
            if(arrayOfProcesses.size() == 0){
                updateCPUs();
            }
            else{
                Process process = arrayOfProcesses.remove(0);
                updateCPUs();
                allocateProcess(process);
            }
            time += 1;
            if(time%tik == 0){
                avgCpusLoad();
                avgSystemLoad();
            }
        }
    }

    public double standardDeviation(double svg){
        double tmp = 0.0;
        for(double x: avgSystemLoad){
            tmp+= Math.pow(x-svg,2);
        }
    
        return Math.sqrt((tmp/avgSystemLoad.size()));
    }


    private void avgCpusLoad(){
        for(Cpu cpu: arrayOfCpus){
            cpu.saveLoad();
        }
    } 

    private void avgSystemLoad(){
        Double tmp = 0.0;
        for(Cpu cpu: arrayOfCpus){
            tmp += cpu.getCurrentLoad();
        }
        avgSystemLoad.add(tmp/arrayOfCpus.size());
    }


    private void allocateProcess(Process process){
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

    private boolean isCpusWorks(){
        for(Cpu cpu: arrayOfCpus){
            if(cpu.getCurrentLoad() > 0){
                return true;
            }
        }
        return false;
    }

    private void updateCPUs(){
        for(Cpu cpu:arrayOfCpus){
            cpu.updateProcesses();
        }
    }   

    private boolean containsSomething(ArrayList<Integer> array, int x ){
        for(int tmp: array){
            if(x == tmp){
                return true;
            }
        }
        return false;
    }
}
