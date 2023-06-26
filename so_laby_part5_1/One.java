package so_laby_part5_1;

import java.util.ArrayList;
import java.util.Random;

import so_laby_part3.Rand;

public class One {
    private ArrayList<Double> historyOfSystem;
    private ArrayList<Cpu> cpus;

    private int migrations;
    private int queries;
    private int probability; 
    private int z;
    private int p;
        private int above100;

    
    public One(ArrayList<Cpu> cpus, int probability, int p, int z){
        this.cpus = cpus;
        historyOfSystem = new ArrayList<>();
        queries = 0; 
        migrations = 0; 
        this.probability = probability;
        this.p = p;
        this.z = z;
        above100 = 0;
        startSimulation();

        System.out.println("Algorytm 1");
        System.out.println("Ilość zapytań: " + queries);
        System.out.println("Ilość migracji: " + migrations);
        System.out.println("Powyzej 100: " + above100);
        
        Double tmp = 0.0;
        for(Double x: historyOfSystem){
            tmp += x;
        }
        double average = tmp/historyOfSystem.size();
        System.out.println("Srednie obciazenie systemu: " + average);

        tmp = 0.0;
        for(Double x: historyOfSystem){
            tmp += Math.pow((average - x) , 2);
        }

        double deviation = Math.sqrt(tmp/historyOfSystem.size());
        System.out.println("Odchylenie standardowe: " + deviation);

        // System.out.println("Procesory :");
        // for(Cpu cpu: cpus){
        //     cpu.printCpuStats();
        // }
    }

    private void startSimulation(){
        int x = 0;
        while(systemWorks()){
            updateCpus();
            addNewProcesses();
            saveStats();
        }
    }   

    public void addNewProcesses(){
        Random rand = new Random();
        for(Cpu cpu: cpus){
            if(cpu.getCurrentLoad() > 100){
                above100 +=1;
            }
            if(rand.nextInt(0,100) <= probability){
                if(!cpu.isWaitingQueueEmpty()){
                    allocateProcess(cpu);
                }
            }
        }
    }

    public void allocateProcess(Cpu cpu){
        Random rand = new Random();
        ArrayList<Integer> notAsked = new ArrayList<>();
        
        for(int x = 0; x < cpus.size(); x++){
            notAsked.add(x);
        }
        notAsked.remove(cpu.getCpuNumber());
        
        boolean alocated = false;

        for(int x = 0; x < z ; x++){
            if(notAsked.size() == 0){
                break;
            }

            int tmp = rand.nextInt(0,notAsked.size()); //tmp to tylko indeks
        
            queries +=1;
            if(cpus.get(notAsked.get(tmp)).getCurrentLoad() >= p){
                notAsked.remove(notAsked.get(tmp));
            }
            else{
                migrations +=1;
                cpus.get(notAsked.get(tmp)).addNewProcess(cpu.giveBackProcess(),1);
                alocated = true;
                break;
            }
        }

        if(!alocated){
            cpu.addNewProcess(new Process(-1, -1, true), 1);
        }

    }

    private void updateCpus(){
        for(Cpu cpu: cpus){
            cpu.updateCpu();            
        }
    }

    private boolean systemWorks(){
        for(Cpu cpu: cpus){
            if(cpu.isCpuWorks()){
                return true;
            }

        }
        return false;
    }

    private void saveStats(){
        int x = 0; 
        for(Cpu cpu: cpus){
            cpu.saveCpuLoad();
            x += cpu.getCurrentLoad();
        }
        Double avg = (x/(cpus.size()*1.0));
        historyOfSystem.add(avg);
    }
}
