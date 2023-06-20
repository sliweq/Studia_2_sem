package so_laby_part5;

import java.util.ArrayList;
import java.util.Random;

import so_laby.proces;

public class Algorithm1{
    private ArrayList<Cpu> arrayOfCpus;
    private ArrayList<Process> arrayOfProcesses;
    private int z; // pytanie sie ez razy z rzędu danego procesora
    private int p; // dane obciazenie
    private int migrationMeter;
    private int queryMeter;
    private int tik;
    private int time;
    private int probability;

    private ArrayList<Double> avgCpuLoad;
    private ArrayList<Double> avgSystemLoad;


    public Algorithm1(ArrayList<Cpu> arrayOfCpus, ArrayList<Process> arrayOfProcesses, int z, int p, int tik, int probability){
        this.arrayOfCpus = arrayOfCpus;
        this.arrayOfProcesses = arrayOfProcesses;
        this.p = p; 
        this.z = z;
        this.probability = probability;
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

    private void allocateProcess(){
        Random rand = new Random();
        for(Cpu cpu: arrayOfCpus){
            if(arrayOfProcesses.size() == 0){
                break;
            }
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
                for(int x = 0; x< z; x++){
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
    // private ArrayList<Cpu> arrayOfCpus;
    // private ArrayList<ArrayList<Process>> allProcesess;  
    
    // private int z; // pytanie sie ez razy z rzędu danego procesora
    // private int p; // dane obciazenie
    // private int migrationMeter;
    // private int queryMeter;
    // private int processesAmount;
    // private int finishedProcesses;
    // private int probability;

    // public Algorithm1(ArrayList<Cpu> arrayOfCpus, ArrayList<ArrayList<Process>> allProcesess, int processesAmount,int z, int p, int tik, int probability){
    //     this.processesAmount = processesAmount;
    //     this.arrayOfCpus = arrayOfCpus;
    //     this.allProcesess = allProcesess; 
    
    //     this.p = p; 
    //     this.z = z;

    //     migrationMeter = 0;
    //     queryMeter = 0;
    //     finishedProcesses = 0;
    //     this.probability = probability;
    // }

    // public void startSimulation(){
    //     while(finishedProcesses != processesAmount){

    //     }
    // }

    // public void updateCPUs(){
    //     for(Cpu cpu:arrayOfCpus){
    //         finishedProcesses += cpu.updateProcesses2();
    //     }
    // }

    // public void allocateProcess(){
    //     Random rand = new Random();
    //     for(Cpu cpu: arrayOfCpus){
    //         if(cpu.getWaitingProcesses().size() != 0){
    //             int tmp = rand.nextInt(0,100);
    //             if(tmp<= probability){
    //                 //dostaje nowy proces
    //             }
    //         }
    //     }
    // }

    // public void askOthers(Cpu cpu){
    //     Random rand = new Random();

    //     int[] asked = new int[arrayOfCpus.size()];
    //     for(int x = 0; x < asked.length; x++){  
    //         asked[x] = 0;
    //         if(arrayOfCpus.get(x).equals(cpu)){
    //             asked[x] = 1;
    //         }
    //     }
    //     for(int x = 0; x< z; x++){
    //         int tmp = rand.nextInt(0,arrayOfCpus.size());
    //         while(asked[tmp] == 1){
    //             tmp = rand.nextInt(0,arrayOfCpus.size());
    //         }
    //         queryMeter += 1;
    //         if(arrayOfCpus.get(tmp).getCurrentLoad() < p){
                
    //         }
    //     }   



    // }
}

