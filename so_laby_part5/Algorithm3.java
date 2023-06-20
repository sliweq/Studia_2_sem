package so_laby_part5;

import java.util.ArrayList;
import java.util.Random;

public class Algorithm3 {
    private ArrayList<Cpu> arrayOfCpus;
    private ArrayList<Process> arrayOfProcesses;

    private int p; // dane obciazenie
    private int r; // minimalne obiciazenie

    private int migrationMeter;
    private int queryMeter;

    private ArrayList<Double> avgCpuLoad;
    private ArrayList<Double> avgSystemLoad;

    private int tik;
    private int time;
    private int probability;


    public Algorithm3(ArrayList<Cpu> arrayOfCpus, ArrayList<Process> arrayOfProcesses, int p, int r, int tik, int probability){
        this.probability = probability;
        this.arrayOfCpus = arrayOfCpus;
        this.arrayOfProcesses = arrayOfProcesses;
        this.p = p; 
        this.r = r;
        queryMeter = 0;
        migrationMeter = 0;

        this.tik = tik;
        time = 0;
        avgCpuLoad = new ArrayList<>();
        avgSystemLoad = new ArrayList<>();

        startSimulation();

        System.out.println("Algorytm 3");
        System.out.println("Ilość zapytań: " + queryMeter);
        System.out.println("Ilość migracji: " + migrationMeter);
        standardDeviation(systemLoad());



    }

    private void startSimulation(){
         while(arrayOfProcesses.size() != 0 || isCpusWorks()){
            if(arrayOfProcesses.size() == 0){
                updateCPUs();
            }
            else{
                updateCPUs();
                allocateProcess();
                helpfulCpus();
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
    
    private void helpfulCpus(){

        
        ArrayList<Cpu> aboveP = new ArrayList<>();
        ArrayList<Cpu> belowR = new ArrayList<>();
        Random rand = new Random();

        // ponizej r ustalanie
        for(Cpu cpu: arrayOfCpus){
            if(cpu.getCurrentLoad() <= r){
                belowR.add(cpu);
            }
        }
        //te co maja wiecej
        for(Cpu cpu: arrayOfCpus){
            if(cpu.getCurrentLoad() >= p){
                //System.out.println(cpu.getCurrentLoad());
                aboveP.add(cpu);
            }
        }

        int aPSize = aboveP.size();
        int bRSzie = belowR.size();

        while(bRSzie != 0 && aPSize != 0){
            int tmpAbove = rand.nextInt(aboveP.size());
            int tmpBelow = rand.nextInt(belowR.size());
            
            if(aboveP.get(tmpAbove).getCurrentLoad() == 0){
                System.out.println("asd");
            }

            Process tmpProcess = getMostDamandingProcess(aboveP.get(tmpAbove));
                
            aboveP.get(tmpAbove).removeProcess(tmpProcess);
            
            belowR.get(tmpBelow).addProcess(tmpProcess);

            if(belowR.get(tmpBelow).getCurrentLoad() > r){
                belowR.remove(tmpBelow);
                bRSzie -=1;
            }
            if(aboveP.get(tmpAbove).getCurrentLoad() < p){
                aboveP.remove(tmpAbove);
                aPSize -=1 ;
            }  
        }
    }

    private Process getMostDamandingProcess(Cpu cpu){
        int index = 0;
        for(int x = 1; x < cpu.getProcesses().size(); x++){
            if(cpu.getProcesses().get(index).getLoad() < cpu.getProcesses().get(x).getLoad()){
                index = x;
            }
        }
        return cpu.getProcesses().get(index);
    }
}
