package so_laby_part5;

import java.util.ArrayList;
import java.util.Random;

public class Algorithm2 {
    private ArrayList<Cpu> arrayOfCpus;
    private ArrayList<Process> arrayOfProcesses;

    private int p; // dane obciazenie
    private int migrationMeter;
    private int queryMeter;

    private ArrayList<Double> avgCpuLoad;
    private ArrayList<Double> avgSystemLoad;
    
    private int tik;
    private int time;
    private int probability;

    public Algorithm2(ArrayList<Cpu> arrayOfCpus, ArrayList<Process> arrayOfProcesses, int p, int tik, int probability){
        this.probability = probability;
        this.arrayOfCpus = arrayOfCpus;
        this.arrayOfProcesses = arrayOfProcesses;
        this.p = p; 
        queryMeter = 0;
        migrationMeter = 0;
        this.tik = tik;
        time = 0;
        avgCpuLoad = new ArrayList<>();
        avgSystemLoad = new ArrayList<>();
        startSimulation();

        System.out.println("Algorytm 2");
        System.out.println("Ilość zapytań: " + queryMeter);
        System.out.println("Ilość migracji: " + migrationMeter);
        standardDeviation(systemLoad());
    }

    public void startSimulation(){
        while(arrayOfProcesses.size() != 0 || isCpusWorks()){
            if(arrayOfProcesses.size() == 0){
                updateCPUs();
            }
            else{
                updateCPUs();
                allocateProcess();
            }
            saveCpuLoad();
        }
    }

    private void standardDeviation(double avg){
        double tmp = 0.0;
        for(double x: avgSystemLoad){
            tmp += Math.pow((x-avg),2);
        }
        System.out.println("Odchylenie standardowe dla systemu: " + Math.sqrt(tmp/avgSystemLoad.size()));
    }
    private void saveCpuLoad(){
        for(Cpu cpu: arrayOfCpus){
            cpu.saveLoad();
        }
    }
    private double systemLoad(){
        for(Cpu cpu: arrayOfCpus){
            avgSystemLoad.add(cpu.getAvgLoad());
        }
        double x = 0.0;
        for(double d: avgSystemLoad){
            x+=d;
        }
        System.out.println("Średnie obciążenie systemu: " + (x/avgSystemLoad.size()) );
        return (x/avgSystemLoad.size());
    }

    private boolean isCpusWorks(){
        for(Cpu cpu: arrayOfCpus){
            if(cpu.getCurrentLoad() > 0){
                return true;
            }
        }
        return false;
    }

    private void allocateProcess(){

        Random rand = new Random();
        for(Cpu cpu: arrayOfCpus){
            if(arrayOfProcesses.size() == 0){
                break;
            }
            if(cpu.getCurrentLoad() < p){
                cpu.addProcess(arrayOfProcesses.remove(0));
            }
            else{
                if(rand.nextInt(0, arrayOfCpus.size()) <= probability){
                    int[] asked = new int[arrayOfCpus.size()];
                    //zerowanie tablicy 
                    for(int x = 0; x < asked.length; x++){  
                        asked[x] = 0;
                        if(arrayOfCpus.get(x).equals(cpu)){
                            asked[x] = 1;
                        }
                    }
                    //wybieranie ofiary której damy prcess
                    int choosenCpu = arrayOfCpus.indexOf(cpu);
                    for(int x = 0; x< arrayOfCpus.size()-1; x++){
                        int tmp = rand.nextInt(0,arrayOfCpus.size());
                        while(asked[tmp] == 1){
                            tmp = rand.nextInt(0,arrayOfCpus.size());
                        }
                        queryMeter += 1;
                        if(arrayOfCpus.get(tmp).getCurrentLoad() < p){
                            choosenCpu = tmp;
                            migrationMeter +=1;
                            break;
                        }
                        else{
                            asked[tmp] = 1;
                        }
                    }

                    //wybrana ofiara
                    arrayOfCpus.get(choosenCpu).addProcess(arrayOfProcesses.remove(0));
                }
            } 
        }
    }

    private void updateCPUs(){
        for(Cpu cpu:arrayOfCpus){
            cpu.updateProcesses();
        }
    }   
    
}
